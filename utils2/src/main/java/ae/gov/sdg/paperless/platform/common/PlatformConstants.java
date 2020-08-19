package ae.gov.sdg.paperless.platform.common;

import java.util.Locale;

public class PlatformConstants {
	
	private PlatformConstants() {}
	
	public static final String DATE_FORMAT1 = "yyyy-MM-dd";
	public static final String PARAMETERS = "parameters";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";
	public static final String X_TRACE_ID = "x-DN-Traceid";
	public static final String X_SESSION_ID = "x-DN-Sid";
	public static final String X_DN_APP_VERSION = "x-DN-AppVersion";
	public static final String X_DN_PLATFORM = "x-DN-Platform";
	public static final String LOG_SPAN_ID = "spanId";
	public static final String LANG = "lang";
	public static final String JOURNEY_INTRO_TEMPLATE_JSON_SUFFIX = "-intro-template-screen";
	public static final String SCREEN_TEMPLATE = "template-screen";
	public static final String PROCESS_VAR_JSON = "json_";
	public static final String PARAM_PROCESS_ID = "processId";
	public static final String JOURNEY = "journey";

	public static final String GENERIC_ERROR_SCREEN = "generic-error-screen";
	public static final String GENERIC_ERROR_TEMPLATE_SCREEN = "generic-error-template-screen";
	public static final String GENERIC_ERROR_EXIT_TEMPLATE_SCREEN = "generic-error-exit-template-screen";
	public static final String BUSINESS_VLD_ERROR_DISMISS_TEMPLATE_SCREEN = "business-validation-error-dismiss-template-screen";
	public static final String PREVIOUS_SCREEN = "previousScreen";
	public static final String GENERIC_ERROR_DISMISS_TEMPLATE_SCREEN = "generic-error-dismiss-template-screen";
	public static final String COMMON = "common";

	public static final String EXCEPTION_ERR_CODE = "E1000"; // Any other exceptions - Null pointer exception, user info not valid - 500
	public static final String CUSTOM_EXCEPTION_ERR_CODE = "E1001"; // HttpStatusCode, Transformation error - 500
	public static final String INVALID_REQUEST_ERR_CODE = "E1002"; // Invalid Request - 400
	public static final String UNAUTHORIZED_ERR_CODE = "E1003";  // Token eror - 401
	public static final String NOT_FOUND_ERR_CODE = "E1004"; // URL Not Found - 404
	public static final String SCREEN_EXCEPTION_ERR_CODE = "E1005"; // Process not found, Screen not found - 500
	public static final String GENERIC_SCREEN_ERR_CODE = "E1006"; // Generic error template screen - 200
	public static final String EXTERNAL_API_ERR_CODE = "E1007";
	public static final String SOCKET_TIMEOUT_ERR_CODE = "E1009";
	public static final String ROUTE_INSTANTIATION_ERR_CODE = "E1010"; // Invalid Request - 400
	public static final String RESOURCE_LOADER_ERR_CODE = "E1011"; 
	public static final String EXTERNAL_API_RESOURCE_ERR_CODE = "E1012"; 
	public static final String NO_DATA_FOUND_ERR_CODE = "E1101"; // No data returned from entity - 500
	public static final String BUSINESS_VALIDATION_ERR_CODE = "B1000"; // Any business validation like user doesn't have valid emirates id trying to get ejari contract. - 200
	public static final String TRACE_LOGGING_ERR_CODE = "T0000";
	public static final String LOG_ERROR_CODE = "errorCode";
	public static final String ERROR_SEVERITY = "errorSeverity";
	public static final String REFERENCE_CODE = "referenceCode";

	public static final String DOUBLE_SLASH = "\\";
	public static final String COLON = ":";
	public static final String LOCAL_PROFILE = "local";
	public static final String DEV_PROFILE = "dev";
	
	public static final String DUBAI_NOW_ACCESS_TOKEN = "DubaiNowToken";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String BEARER = "Bearer ";

	public static final String MPAY = "mPay";
	public static final String PROFILE_TYPE_HEADER = "profileType";
	public static final String PROFILE_TYPE_FULL = "full";
	public static final String DUBAI_NOW_MPAY_AUTHORIZATION = "mPayAuthorization";
	public static final String AUTHORIZATION = "Authorization";
	public static final String CERT_DEV_PATH = "cert/dev/ids-jwt-issuer.crt";
	public static final String CERT_QA_PATH = "cert/qa/ids-jwt-issuer.crt";
	public static final String CERT_PROD_PATH = "cert/prod/ids-jwt-issuer-prod.crt";
	public static final String QA_PROFILE = "qa";
	public static final String PROD_PROFILE = "prod";
	public static final String JOURNEYTYPE_LISTING = "Listing";
	public static final String DICTONARYKEY_PARTIAL_ERROR = "partialError";
	
	public static final String ERROR = "ERROR";
	public static final String METHOD_NOT_IN_ERR_CODE = "E1099";
	public static final String COMMA = ",";
	public static final String DEFAULT = "default";
	public static final String JSON_PREFIX = ".json";
	public static final String TEMPLATE_PREFIX = ".ftl";
	public static final String LABELS_KEY = "labels";
	public static final String JOURNEY_INTRO_JSON_SUFFIX = "-intro-screen";
	public static final Locale UAE_LOCALE = new Locale("ar", "SA");
	public static final String REQUIRED_PAGNIATION_INFO_NOT_PRESENT = "Required pagniation info not present";
}