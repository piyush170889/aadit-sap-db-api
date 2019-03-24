package com.loungeapp.constants;

public class Constants {
	public static final String APPLICATION_JSON = "application/json";

	public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

	public static final String BASE_MAPPING = "/";

	public static final String BASE_POINTCUT = "execution(* com.loungeapp.*.*.*(..))";

	public static final String EXCEPTION_POINTCUT = "execution(* com.loungeapp.restcontrollers.*.*(..))";

	public static final String ERROR_OCCURED = "Error Occured";

	public static final String ERROR_PAGE_URL = "error/error";

	public static final String SUCCESS_OK = "OK";

	public static final String ERROR_MSSG_LABEL = "errorMssg";

	public static final String ERROR = "ERROR";

	public static final String SUCCESS_MSSG_LABEL = "successMssg";

	public static final String SMS_GATEWAY_TWILIO = "Twilio";

	public static final String SMS_GATEWAY_MSSG_91 = "Mssg91";

	public static final byte IS_ACTIVE = 1;

	public static final String CREATED_BY_CRONS = "CRON";

	public static final int ITEM_GRP_CD = 105;

}
