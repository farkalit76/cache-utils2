package ae.gov.sdg.paperless.platform.common.service.mpay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;

import ae.gov.sdg.paperless.platform.common.model.UserInfo;
import ae.gov.sdg.paperless.platform.common.model.mpay.GetSubscribedEntityServiceAccountsReqBean;
import ae.gov.sdg.paperless.platform.common.model.mpay.MPayAllSubscribedEntityAccountsResponse;
import ae.gov.sdg.paperless.platform.common.model.mpay.PayService;
import ae.gov.sdg.paperless.platform.common.model.mpay.SubscriptionBean;
import ae.gov.sdg.paperless.platform.common.model.mpay.TxnHistoryRequest;
import ae.gov.sdg.paperless.platform.common.model.mpay.TxnHistoryResponse;
import ae.gov.sdg.paperless.platform.common.service.generic.JourneyExecutionContext;

/**
 * @author swetabh raj
 *
 */
@Profile("stub")
@Service("mPayStubService")
public class MPayStubServiceImpl implements MPayService {

    @Override
    public List<PayService> getSubscribedEntityServiceAccounts(GetSubscribedEntityServiceAccountsReqBean reqData,
            String accessToken, String dubaiNowJWTToken, String dubaiNowAccessToken) {
        return new ArrayList<>();
    }

    @Override
    public List<SubscriptionBean> getAccounts(UserInfo userInfo, String serviceCode, String accessToken,
            String dubaiNowJWTToken, String dubaiNowAccessToken) {
    	return new ArrayList<>();
    }

    @Override
    public SubscriptionBean getDubaiNowSubscriptions(UserInfo userInfo, String serviceCode, String accountNickName,
            String accessToken, String dubaiNowJWTToken, String dubaiNowAccessToken) {
        return null;
    }

    @Override
    public List<MPayAllSubscribedEntityAccountsResponse> getAllSubscribedEntityAccounts(String dubaiNowJWTToken,
                                                                                        String dubaiNowAccessToken) {
    	return new ArrayList<>();
    }

    @Override
    public List<TxnHistoryResponse> fetchTransactionHistory(@NonNull String jwt, @NonNull String accessToken, @NonNull TxnHistoryRequest request) {
        return new ArrayList<>();
    }

    @Override
    public <T> T userDataByObjectTypeId(@NonNull String dubaiNowJWTToken, @NonNull String dubaiNowAccessToken, @NonNull ObjectID objectId, MultiValueMap<String, String> requestParameters, Class<T> responseType) throws IOException {
        return null;
    }

    @Override
    public Boolean deleteUserDataById(@NonNull JourneyExecutionContext context, @NonNull Long id) throws IOException {
        return false;
    }

    @Override
    public Boolean deleteUserDataById(@NonNull String jwt, @NonNull String accessToken, @NonNull Long id) throws IOException {
        return false;
    }

    @Override
    public <T> T userDataByObjectTypeId(@NonNull String dubaiNowJWTToken, @NonNull String dubaiNowAccessToken, @NonNull ObjectID objectId, MultiValueMap<String, String> requestParameters, @NonNull TypeReference<T> typeReference) throws IOException {
        return null;
    }

	@Override
	public <T> void updateUserData(String dubaiNowJWTToken, String dubaiNowAccessToken, ObjectID objectId,
			Map<String, Object> request, TypeReference<T> typeReference) {
		// Implement stub as required
	}

}
