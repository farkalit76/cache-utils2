package ae.gov.sdg.paperless.platform.common.service.mpay;

import static ae.gov.sdg.paperless.platform.common.PlatformConstants.DATE_FORMAT1;
import static ae.gov.sdg.paperless.platform.util.JsonUtil.fromJson;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.emptyList;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.io.IOUtils.toInputStream;
import static org.apache.commons.lang3.time.DateFormatUtils.format;
import static org.apache.commons.lang3.time.DateUtils.addDays;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.core.type.TypeReference;

import ae.gov.sdg.paperless.platform.common.model.UserInfo;
import ae.gov.sdg.paperless.platform.common.model.mpay.GetSubscribedEntityServiceAccountsReqBean;
import ae.gov.sdg.paperless.platform.common.model.mpay.MPayAllSubscribedEntityAccountsResponse;
import ae.gov.sdg.paperless.platform.common.model.mpay.PayService;
import ae.gov.sdg.paperless.platform.common.model.mpay.SubscriptionBean;
import ae.gov.sdg.paperless.platform.common.model.mpay.TxnHistoryRequest;
import ae.gov.sdg.paperless.platform.common.model.mpay.TxnHistoryResponse;
import ae.gov.sdg.paperless.platform.common.model.mpay.UserDataResponse;
import ae.gov.sdg.paperless.platform.common.service.generic.JourneyExecutionContext;
import ae.gov.sdg.paperless.platform.exceptions.ExternalApiException;

/**
 * @author swetabh raj
 */
public interface MPayService {

    /**
     * fetch transaction history
     *
     * @param context   jwtToken and access_token to be retrieved
     * @param fromDate  from date in string yyyy-MM-dd format
     * @param toDate    to date in string yyyy-MM-dd format
     * @param maxResult max nunmer of records to be retrieved
     * @return list of transactions -> list will be empty if there is no transaction found
     * @throws ExternalApiException while invoking external api
     */
    default List<TxnHistoryResponse> fetchTransactionHistory(@NonNull JourneyExecutionContext context, @NonNull String fromDate, @NonNull String toDate, int maxResult) {
        return fetchTransactionHistory(context.getJwtToken(), context.getAccessToken(), new TxnHistoryRequest(fromDate, toDate, maxResult));
    }

    /**
     * Get subscribed entity service accounts for the provided details
     *
     * @param reqData             request object
     * @param accessToken         - na
     * @param dubaiNowJWTToken    DubaiNow Jwt Bearer token
     * @param dubaiNowAccessToken DubaiNow Access accessToken
     * @return non nullable list of {@link PayService}
     */
    List<PayService> getSubscribedEntityServiceAccounts(GetSubscribedEntityServiceAccountsReqBean reqData, String accessToken, String dubaiNowJWTToken, String dubaiNowAccessToken) throws IOException;

    /**
     * Get subscribed entity service accounts for the provided details asynchronously
     *
     * @param reqData             request object
     * @param accessToken         - na
     * @param dubaiNowJWTToken    DubaiNow Jwt Bearer token
     * @param dubaiNowAccessToken DubaiNow Access accessToken
     * @param executor            executor for async call
     * @return non nullable list of {@link PayService}
     */
    default Future<List<PayService>> getSubscribedEntityServiceAccountsAsync(GetSubscribedEntityServiceAccountsReqBean reqData, String accessToken, String dubaiNowJWTToken, String dubaiNowAccessToken, Executor executor) {
        return supplyAsync(() -> {
            try {
                return getSubscribedEntityServiceAccounts(reqData, accessToken, dubaiNowJWTToken, dubaiNowAccessToken);
            } catch (IOException e) {
                throw new ExternalApiException("invalid json response");
            }
        }, executor);
    }

    List<SubscriptionBean> getAccounts(UserInfo userInfo, String serviceCode, String accessToken, String dubaiNowJWTToken, String dubaiNowAccessToken);

    SubscriptionBean getDubaiNowSubscriptions(UserInfo userInfo, String serviceCode, String accountNickName, String accessToken, String dubaiNowJWTToken, String dubaiNowAccessToken);

    List<MPayAllSubscribedEntityAccountsResponse> getAllSubscribedEntityAccounts(String dubaiNowJWTToken, String dubaiNowAccessToken) throws IOException;

    default Future<List<MPayAllSubscribedEntityAccountsResponse>> getAllSubscribedEntityAccountsAsync(String dubaiNowJWTToken, String dubaiNowAccessToken, Executor executor) {
        return supplyAsync(() -> {
            try {
                return getAllSubscribedEntityAccounts(dubaiNowJWTToken, dubaiNowAccessToken);
            } catch (IOException e) {
                throw new ExternalApiException(e.getMessage());
            }
        }, executor);
    }

    /**
     * Fetches list of transactions done by user
     *
     * @param jwt         dubaiNow jwt
     * @param accessToken dubaiNow access_token
     * @param request     request object
     * @return list of txn history -> list will be empty if there is no transaction found
     * @throws ExternalApiException while invoking external API and also throws IOException while parsing JSON
     */
    List<TxnHistoryResponse> fetchTransactionHistory(@NonNull final String jwt, @NonNull final String accessToken, @NonNull TxnHistoryRequest request);

    /**
     * fetch transaction history with default max 1000 records
     *
     * @param context  jwtToken and access_token to be retrieved
     * @param fromDate from date in string yyyy-MM-dd format
     * @param toDate   to date in string yyyy-MM-dd format
     * @return list of transactions -> list will be empty if there is no transaction found
     * @throws ExternalApiException while invoking external api
     */
    default List<TxnHistoryResponse> fetchTransactionHistory(@NonNull JourneyExecutionContext context, @NonNull String fromDate, @NonNull String toDate) {
        return fetchTransactionHistory(context, fromDate, toDate, 1000);
    }

    /**
     * fetch transaction history with latest 30 days with max 1000 records
     *
     * @param context jwtToken and access_token to be retrieved
     * @return list of transaction history -> list will be empty if there is no transaction found
     * @throws ExternalApiException while invoking external api
     */
    default List<TxnHistoryResponse> fetchTransactionHistory(@NonNull JourneyExecutionContext context) {
        return fetchTransactionHistory(context, format(addDays(new Date(), -30), DATE_FORMAT1), format(new Date(), DATE_FORMAT1), 20);
    }

    default List<UserDataResponse> userData(@NonNull JourneyExecutionContext context, @NonNull final ObjectID objectID) throws IOException {
        return userData(context, objectID, null);
    }

    default List<UserDataResponse> userData(@NonNull JourneyExecutionContext context, @NonNull final ObjectID objectID, MultiValueMap<String, String> requestParameters) throws IOException {
        return userDataByObjectTypeId(context.getJwtToken(), context.getAccessToken(), objectID, requestParameters, new TypeReference<List<UserDataResponse>>() {
        });
    }

    /**
     * @param dubaiNowJWTToken    dubai now jwt bearer token
     * @param dubaiNowAccessToken dubai now access_token
     * @param objectId            {@link ObjectID} enum
     * @return json string value based on objectId
     * @throws IOException          while parsing json
     * @throws ExternalApiException while invoking external service
     */
    default String userDataByObjectTypeId(@NonNull final String dubaiNowJWTToken, @NonNull final String dubaiNowAccessToken, @NonNull final ObjectID objectId, MultiValueMap<String, String> requestParameters) throws IOException {
        return userDataByObjectTypeId(dubaiNowJWTToken, dubaiNowAccessToken, objectId, requestParameters, String.class);
    }

    /**
     * @param dubaiNowJWTToken    dubai now jwt bearer token
     * @param dubaiNowAccessToken dubai now access_token
     * @param objectId            {@link ObjectID} enum
     * @return json string value based on objectId
     * @throws IOException          while parsing json
     * @throws ExternalApiException while invoking external service
     */
    default String userDataByObjectTypeId(@NonNull final String dubaiNowJWTToken, @NonNull final String dubaiNowAccessToken, @NonNull final ObjectID objectId) throws IOException {
        return userDataByObjectTypeId(dubaiNowJWTToken, dubaiNowAccessToken, objectId, null);
    }

    /**
     * @param dubaiNowJWTToken    dubai now jwt bearer token
     * @param dubaiNowAccessToken dubai now access_token
     * @param objectId            {@link ObjectID} enum
     * @return response of type T based on objectId
     * @throws IOException          while parsing json
     * @throws ExternalApiException while invoking external service
     */
    <T> T userDataByObjectTypeId(@NonNull final String dubaiNowJWTToken, @NonNull final String dubaiNowAccessToken, @NonNull final ObjectID objectId, MultiValueMap<String, String> requestParameters, Class<T> responseType) throws IOException;

    /**
     * @param context JourneyExecutionContext
     * @param id      from user-data response
     * @return if deleted or not
     * @throws IOException             while parsing json
     * @throws HttpStatusCodeException any http error
     * @throws ExternalApiException    while invoking external api
     */
    default Boolean deleteUserDataById(@NonNull final JourneyExecutionContext context, @NonNull final Long id) throws IOException {
        return deleteUserDataById(context.getJwtToken(), context.getAccessToken(), id);
    }

    /**
     * @param jwt         dubaiNow JWT
     * @param accessToken dubaiNow access_token
     * @param id          from user-data response
     * @return if deleted or not
     * @throws IOException             while parsing json
     * @throws HttpStatusCodeException any http error
     * @throws ExternalApiException    while invoking external api
     */
    Boolean deleteUserDataById(@NonNull final String jwt, @NonNull final String accessToken, @NonNull final Long id) throws IOException;

    /**
     * @param dubaiNowJWTToken    dubai now jwt bearer token
     * @param dubaiNowAccessToken dubai now access_token
     * @param objectId            {@link ObjectID} enum
     * @return type reference of response type
     * @throws IOException          while parsing json
     * @throws ExternalApiException while invoking external service
     */
    <T> T userDataByObjectTypeId(@NonNull final String dubaiNowJWTToken, @NonNull final String dubaiNowAccessToken, @NonNull final ObjectID objectId, @Nullable MultiValueMap<String, String> requestParameters, @NonNull TypeReference<T> typeReference) throws IOException;

    default List<String> dataFromUserData(@NonNull JourneyExecutionContext context, @NonNull final ObjectID objectID) throws IOException {
        List<UserDataResponse> userDataResponses = userData(context, objectID);
        if (isNotEmpty(userDataResponses)) {
            return userDataResponses.stream().map(UserDataResponse::getData).filter(Objects::nonNull).collect(toList());
        }
        return emptyList();
    }

    default <T> List<? extends T> dataFromUserData(@NonNull JourneyExecutionContext context, @NonNull final ObjectID objectID, @NonNull TypeReference<T> typeReference) throws IOException {
        List<String> data = dataFromUserData(context, objectID);
        if (isNotEmpty(data)) {
            return data
                .stream()
                .map(json -> {
                    try {
                        return fromJson(toInputStream(json, UTF_8), typeReference);
                    } catch (Exception e) {
                        return null;
                    }
                }).filter(Objects::nonNull).collect(toList());
        }
        return emptyList();
    }

    <T> void updateUserData(@NonNull final String dubaiNowJWTToken, @NonNull final String dubaiNowAccessToken, @NonNull final ObjectID objectId, @NonNull Map<String, Object> request, @NonNull TypeReference<T> typeReference);

    public enum ObjectID {
        OBJECT_TYPE_DRIVER("6"),
        OBJECT_TYPE_MYCAR("3"),
        OBJECT_TYPE_FLIGHT("7"),
        OBJECT_TYPE_CHILD_DETAIL("2"),
        OBJECT_TYPE_VACCINATION_DETAIL("5");

        public final String value;

        ObjectID(String value) {
            this.value = value;
        }
    }
}