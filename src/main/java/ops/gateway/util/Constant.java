package ops.gateway.util;

public class Constant {
    public static class Logging {
        public static final String LOG_REQUEST_BY_CLASS_AND_METHOD = "Call method: %s | Request object: %s";
        public static final String LOG_RESPONSE_BY_CLASS_AND_METHOD = "Call method: %s | Response object: %s";
    }

    public static class ErrorCodeApi {
        public static final String SUCCESS = "200000";
        public static final String UNAUTHORIZED = "401000";
        public static final String USER_NOT_ROLE = "401001";
        public static final String INVALID_INPUT = "400001";
    }

    public static  class ErrorValidation{
        public static final String MESSAGE_NOT_NULL_INPUT_FILE_NAME ="[input_file_name] truyền vào không được null";
        public static final String MESSAGE_NOT_NULL_SESSION ="[session] truyền vào không được null";
        public static final String MESSAGE_ERROR_OTHER ="[input_file_name]Lỗi khác";
    }

    public static class MessageApi {
        public static final String SUCCESS = "Thành công";
        public static final String USER_NOT_ROLE = "User Không được phép truy cập chức năng";
        public static final String PAY_MAKER_CHECKER_FALSE = "Sai key connect tại pay-maker-checker";
        public static final String VALIDATION_FALSE = "Validation Failed";
    }

    public class FilterConstant {
        public static final String HEADER_AUTHENTICATION = "Authorization";
        public static final String PREFIX = "Bearer ";
        public static final String X_API_KEY = "api_key";
        public static final String SECRET = "api_secret";
        public static final String ROLE = "permissions";
        public static final String KEY_FACTORY = "RSA";
        public static final String VIN_PAY_CASH_BACK_MANAGEMENT = "VinPay_CashbackManagement";

    }
}
