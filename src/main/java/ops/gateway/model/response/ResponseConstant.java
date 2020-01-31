package ops.gateway.model.response;

import ops.gateway.util.Constant;

import java.util.List;

public class ResponseConstant {
    public static ResponseDTO ERROR_USER_NOT_ROLE = new ResponseDTO(Constant.ErrorCodeApi.USER_NOT_ROLE,Constant.MessageApi.USER_NOT_ROLE);

    public static ResponseDTO ERROR_INVALID_INPUT(String err){return  new ResponseDTO(Constant.ErrorCodeApi.INVALID_INPUT,err);}

    public static ResponseDTO ERROR_INVALID_INPUT_EXCEPTION(String err,List<String> list){return  new ResponseDTO(Constant.ErrorCodeApi.INVALID_INPUT,err, list);}

    public static ResponseDTO responseOK(String user_name){ return new ResponseDTO(Constant.ErrorCodeApi.SUCCESS,Constant.MessageApi.SUCCESS,user_name);}

    public static ResponseDTO responseOK(Object object){ return new ResponseDTO(Constant.ErrorCodeApi.SUCCESS,Constant.MessageApi.SUCCESS,object);}

    public static ResponseDTO responseOK(String user_name, Object object){ return new ResponseDTO(Constant.ErrorCodeApi.SUCCESS,Constant.MessageApi.SUCCESS,user_name,object);}

    public static ResponseDTO responseError401(String user_name){ return new ResponseDTO(Constant.ErrorCodeApi.USER_NOT_ROLE,Constant.MessageApi.PAY_MAKER_CHECKER_FALSE);}
}
