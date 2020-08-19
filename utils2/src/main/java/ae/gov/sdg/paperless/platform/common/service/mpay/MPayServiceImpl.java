package ae.gov.sdg.paperless.platform.common.service.mpay;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.ACCESS_TOKEN;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.AUTHORIZATION;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.BEARER;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.CONTENT_TYPE;
import static ae.gov.sdg.paperless.platform.common.PlatformConstants.PARAMETERS;
import static ae.gov.sdg.paperless.platform.util.CommonUtil.is404Status;
import static ae.gov.sdg.paperless.platform.util.CommonUtil.isStatusEquals;
import static ae.gov.sdg.paperless.platform.util.JsonUtil.fromJson;
import static java.lang.Boolean.FALSE;
import static java.net.URI.create;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.apache.commons.collections4.CollectionUtils.size;
import static org.apache.commons.io.IOUtils.toInputStream;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.ALL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;

import ae.gov.sdg.paperless.platform.common.PlatformConfig;
import ae.gov.sdg.paperless.platform.common.PlatformConstants;
import ae.gov.sdg.paperless.platform.common.model.BearerAuth;
import ae.gov.sdg.paperless.platform.common.model.UserInfo;
import ae.gov.sdg.paperless.platform.common.model.mpay.DubaiNowSubscriptionRequest;
import ae.gov.sdg.paperless.platform.common.model.mpay.GetSubscribedEntityServiceAccountsReqBean;
import ae.gov.sdg.paperless.platform.common.model.mpay.MPayAllSubscribedEntityAccountsResponse;
import ae.gov.sdg.paperless.platform.common.model.mpay.PayService;
import ae.gov.sdg.paperless.platform.common.model.mpay.SubscriptionBean;
import ae.gov.sdg.paperless.platform.common.model.mpay.TxnHistoryRequest;
import ae.gov.sdg.paperless.platform.common.model.mpay.TxnHistoryResponse;
import ae.gov.sdg.paperless.platform.common.service.generic.IRestService;
import ae.gov.sdg.paperless.platform.exceptions.ExternalApiException;
import ae.gov.sdg.paperless.platform.util.CommonUtil;
import ae.gov.sdg.paperless.platform.util.JsonUtil;

/**
 * @author swetabh raj
 *
 */
@Profile("!stub")
@Service
public class MPayServiceImpl implements MPayService {

    private static final Logger log = LoggerFactory.getLogger(MPayServiceImpl.class);

    private final PlatformConfig config;

    private final IRestService<BearerAuth> restService;
    
	public MPayServiceImpl(final PlatformConfig config,
			@Qualifier("restBearerAuthService") final IRestService<BearerAuth> restService) {
		this.config = config;
    	this.restService = restService;
    }

    /**
     * Get subscribed entity service accounts for the provided details
     * @param reqData
     * @param accessToken
     * @param dubaiNowJWTToken
     * @param dubaiNowAccessToken
     * @return
     */
    @Override
	public List<PayService> getSubscribedEntityServiceAccounts(final GetSubscribedEntityServiceAccountsReqBean reqData,
			final String accessToken, final String dubaiNowJWTToken, final String dubaiNowAccessToken)
			throws IOException {

    	final RequestEntity<GetSubscribedEntityServiceAccountsReqBean> requestEntity = RequestEntity
				.post(create(config.getDubaiNowMpaySubscriptionContextUrl().concat("entityserviceaccounts/v2")))
				.accept(ALL).header(AUTHORIZATION, BEARER + dubaiNowJWTToken).header(ACCESS_TOKEN, dubaiNowAccessToken)
				.header(CONTENT_TYPE, PlatformConstants.APPLICATION_JSON).body(reqData);

		log.info("invoking /entityserviceaccounts : {}", requestEntity.getBody());

		final ResponseEntity<String> response = restService.invoke(requestEntity, String.class);
		if (response != null && StringUtils.isNotEmpty(response.getBody())) {
			final PayService[] subscriptionArray = JsonUtil.fromJson(response.getBody(), PayService[].class);
			final List<PayService> payServices = new ArrayList<>();
			if (null != subscriptionArray && subscriptionArray.length > 0)
				for (final PayService payService : subscriptionArray)
					if (payService != null)
						payServices.add(payService.filterNonNullAccounts(payService));

			log.info("Success Response from MPayServiceImpl getSubscribedEntityServiceAccounts method: payServices {}",
					payServices.size());
			return payServices;
		}
		return emptyList();
	}

    /**
     * Get all subscription accounts for the specified service code
     * @param userInfo
     * @param serviceCode
     * @param accessToken
     * @param dubaiNowJWTToken
     * @param dubaiNowAccessToken
     * @return
     */
    @Override
	public List<SubscriptionBean> getAccounts(final UserInfo userInfo, final String serviceCode,
			final String accessToken, final String dubaiNowJWTToken, final String dubaiNowAccessToken) {

    	log.debug("Request Params for MPayServiceImpl getAccounts method : {}", serviceCode);
        try {
        	final RequestEntity<DubaiNowSubscriptionRequest> requestEntity = RequestEntity
                .post(create(config.getDubaiNowMpaySubscriptionContextUrl().concat("accounts")))
                .accept(ALL).accept(ALL)
                .header(AUTHORIZATION, BEARER + dubaiNowJWTToken)
                .header(ACCESS_TOKEN, dubaiNowAccessToken)
                .header(CONTENT_TYPE, PlatformConstants.APPLICATION_JSON)
                .body(new DubaiNowSubscriptionRequest(serviceCode));
            final ResponseEntity<String> response = restService.invoke(requestEntity, String.class);
            if (response != null && StringUtils.isNotEmpty(response.getBody())) {
	            final SubscriptionBean[] subscriptionArray = JsonUtil.fromJson(response.getBody(), SubscriptionBean[].class);
	            log.info("Success Response from MPayServiceImpl getAccounts method, subscriptionArray size : {}", size(subscriptionArray));
	            return Arrays.asList(subscriptionArray);
            }
        } catch (final Exception ex) {
            log.error("Error in MPayServiceImpl getAccounts method for Service Code {}", serviceCode, ex);
        }
        return emptyList();
    }

    /**
     * Get all subscription details for the specified service code and accountNickName
     * @param userInfo
     * @param serviceCode
     * @param accountNickName
     * @param accessToken
     * @param dubaiNowJWTToken
     * @param dubaiNowAccessToken
     * @return data map
     */
    @Override
	public SubscriptionBean getDubaiNowSubscriptions(final UserInfo userInfo, final String serviceCode,
			final String accountNickName, final String accessToken, final String dubaiNowJWTToken,
			final String dubaiNowAccessToken) {

    	log.debug("Request Params for MPayServiceImpl getDubaiNowSubscriptions method ServiceCode : {}, NickName : {}", serviceCode, accountNickName);
        try {
        	final RequestEntity<SubscriptionBean> requestEntity = RequestEntity
                .post(create(config.getDubaiNowMpaySubscriptionContextUrl().concat("subscriptionaccount")))
                .accept(ALL)
                .header(AUTHORIZATION, BEARER + dubaiNowJWTToken)
                .header(ACCESS_TOKEN, dubaiNowAccessToken)
                .header(CONTENT_TYPE, PlatformConstants.APPLICATION_JSON)
                .body(new SubscriptionBean(serviceCode, accountNickName));

            final ResponseEntity<SubscriptionBean> response = restService.invoke(requestEntity, SubscriptionBean.class);
            if (response != null && response.getBody() != null) {
	            log.info("Success Response from MPayServiceImpl getDubaiNowSubscriptions method");
	            return response.getBody();
            }
            return null;
        } catch (final ExternalApiException ex) {
            if (isStatusEquals(ex, BAD_REQUEST)) {
            	log.info("no subscription account found for nickname : {}, serviceCode={}, uid: {}, idn:{}", accountNickName, serviceCode, userInfo.getUuid(), userInfo.getIdn());
            } else {
                log.error("Error in MPayServiceImpl getDubaiNowSubscriptions method", ex);
            }
            throw ex;
        }
    }

    /**
     * Get all subscribed entity service accounts for the user
     * @param dubaiNowJWTToken jwt token of dubai-now
     * @param dubaiNowAccessToken access-token of dubai-now
     * @return list of entity accounts
     */
    @Override
	public List<MPayAllSubscribedEntityAccountsResponse> getAllSubscribedEntityAccounts(final String dubaiNowJWTToken,
			final String dubaiNowAccessToken) throws IOException {

    	log.info("getAllSubscribedEntityAccounts method called");
        try {
        	final RequestEntity<String> requestEntity = RequestEntity
                .post(create(config.getDubaiNowMpaySubscriptionContextUrl().concat("allsubscribedentities")))
                .accept(ALL)
                .header(AUTHORIZATION, BEARER + dubaiNowJWTToken)
                .header(ACCESS_TOKEN, dubaiNowAccessToken)
                .header(CONTENT_TYPE, PlatformConstants.APPLICATION_JSON).body("{}");

            final ResponseEntity<String> response = restService.invoke(requestEntity, String.class);
            if (response != null && StringUtils.isNotEmpty(response.getBody())) {
            	final List<MPayAllSubscribedEntityAccountsResponse> subscriptionArray = fromJson(
						toInputStream(response.getBody(), UTF_8),
						new TypeReference<List<MPayAllSubscribedEntityAccountsResponse>>() {
						});
                log.info("Success Response from MPayServiceImpl getSubscribedEntityServiceAccounts method");
                return subscriptionArray;
            }
            return emptyList();
        } catch (final ExternalApiException ex) {
        	if (is404Status(ex)) {
                log.info("no subscribed entity account found");
                return emptyList();
            }
        	log.error(ex.getMessage(), ex);
            throw ex;
        } 
    }

    /**
     * @inheritDoc
     */
    @Override
	public <T> T userDataByObjectTypeId(@NonNull final String dubaiNowJWTToken,
			@NonNull final String dubaiNowAccessToken, @NonNull final ObjectID objectId,
			MultiValueMap<String, String> requestParameters, final Class<T> responseType) {
    	log.info("going to fetch user details for object id {}, request parameters : {}", objectId.value, requestParameters);
    	final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(AUTHORIZATION, BEARER + dubaiNowJWTToken);
        headers.add(ACCESS_TOKEN, dubaiNowAccessToken);
        final HttpEntity<String> entity = new HttpEntity<>(PARAMETERS, headers);
        try {
            String url = config.getDubaiNowMpayUserDataUrl();
            if (requestParameters == null) requestParameters = new LinkedMultiValueMap<>();
            requestParameters.put("objTypeId", singletonList(objectId.value));
            url = CommonUtil.appendRequestParameters(url, requestParameters);
            final T body = restService.invoke(url, entity, responseType, GET);
            if (body == null) return null;
            log.info("user-data response body is not null: {}", body != null);

            return body;
        } catch (final ExternalApiException ex) {
            if (is404Status(ex)) {
                log.info("no user-data found, objTypeId:{}", objectId.value);
                return null;
            }
            log.error("unable to fetch user-data for object type id : {} and request params : {} -> {}", objectId, requestParameters, ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
	public Boolean deleteUserDataById(@NonNull final String jwt, @NonNull final String accessToken,
			@NonNull final Long id) throws IOException {
        log.info("going to delete user details for id {}", id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(AUTHORIZATION, BEARER + jwt);
        headers.add(ACCESS_TOKEN, accessToken);
        final HttpEntity<String> entity = new HttpEntity<>(PARAMETERS, headers);
        try {
        	final String url = substringBeforeLast(config.getDubaiNowUserDataUrl(), "?").concat("/").concat(id.toString());
        	final String body = restService.invoke(url, entity, String.class, DELETE);
            if (body == null) return FALSE;
            final boolean isDeleted = "User data deleted successfully".equals(fromJson(defaultIfBlank(body, "{}")).get("text"));
            log.info("user-data deleted : {}", isDeleted);
            return isDeleted;
        } catch (final ExternalApiException ex) {
            if (is404Status(ex)) {
                log.info("no user-data found, id:{}", id);
                return FALSE;
            }
            log.error("unable to delete user-data with id {} -> {}", id, ex.getMessage(), ex);
            return FALSE;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
	public <T> T userDataByObjectTypeId(@NonNull final String dubaiNowJWTToken,
			@NonNull final String dubaiNowAccessToken, @NonNull final ObjectID objectId,
			@Nullable final MultiValueMap<String, String> requestParameters,
			@NonNull final TypeReference<T> typeReference)
			throws IOException {
    	final String response = userDataByObjectTypeId(dubaiNowJWTToken, dubaiNowAccessToken, objectId, requestParameters);
        if (isNotBlank(response)) return fromJson(toInputStream(response, UTF_8), typeReference);
        return null;
    }
    
    @Override
	public <T> void updateUserData(@NonNull final String dubaiNowJWTToken, @NonNull final String dubaiNowAccessToken,
			@NonNull final ObjectID objectId, @Nullable final Map<String, Object> request,
			@NonNull final TypeReference<T> typeReference) {
        log.info("updating user-data for objectID {}", objectId.value);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(AUTHORIZATION, BEARER + dubaiNowJWTToken);
        headers.add(ACCESS_TOKEN, dubaiNowAccessToken);
        final HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        try {
        	final String url = config.getDubaiNowUserDataUrl().concat(objectId.value);
            restService.invoke(url, entity, Void.class, POST);
        } catch (final ExternalApiException ex) {
            if (is404Status(ex)) {
                log.info("no user-data found, objTypeId:{}", objectId.value);
                return;
            } else
                log.error("unable to fetch user-data for object type id : {} and request params : {} -> {}", objectId, request, ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<TxnHistoryResponse> fetchTransactionHistory(@NonNull final String jwt, @NonNull final String accessToken, @NonNull final TxnHistoryRequest request) {
        log.info("fetching transaction History {}", request);
        final RequestEntity<TxnHistoryRequest> requestEntity = RequestEntity
            .post(create(config.getDubaiNowMpaySubscriptionContextUrl().concat("gettransaction")))
            .accept(ALL)
            .header(ACCESS_TOKEN, accessToken)
            .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .header(AUTHORIZATION, BEARER + jwt)
            .body(request);

        final ResponseEntity<List<TxnHistoryResponse>> response = restService.invoke(requestEntity, new ParameterizedTypeReference<List<TxnHistoryResponse>>() {
        });
        if (response != null) {
            log.info("{} transactions found", size(response.getBody()));
            return response.getBody();
        }
        return emptyList();
    }

}
