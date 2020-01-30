package ops.gateway.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ops.gateway.util.Constant;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogInterceptor {

    private static Logger logger = null;

    @Before(value = "ops.gateway.annotation.LogManager.auditLog() && target(bean) && @annotation(ops.gateway.annotation.LoggingActivityGateway)", argNames = "bean")
    public void logRequest(JoinPoint p,Object bean) {
        if (logger == null){
            logger = LoggerFactory.getLogger(bean.getClass().getName());
        }
        if (logger != null){
            Object[] signatureArgs = p.getArgs();
            ObjectMapper mapper = new ObjectMapper();

            try {
                if (signatureArgs[0] != null) {
                    logger.info(String.format(Constant.Logging.LOG_REQUEST_BY_CLASS_AND_METHOD, p.getSignature().getName(),mapper.writeValueAsString(signatureArgs[0])));
                }
            } catch (JsonProcessingException e) {
                logger.info( String.format(Constant.Logging.LOG_REQUEST_BY_CLASS_AND_METHOD, p.getSignature().getName(),e.getMessage()));
            }
        }
    }

    @AfterReturning(value = "ops.gateway.annotation.LogManager.auditLog() && target(bean) && @annotation(ops.gateway.annotation.LoggingActivityGateway)",
             returning = "returnValue")
    public void logResponse(JoinPoint p,Object bean, Object returnValue) {
        if (logger == null){
            logger =  LoggerFactory.getLogger(bean.getClass().getName());
        }
        if (logger != null){
            try {
                ObjectMapper mapper = new ObjectMapper();
                logger.info(String.format(Constant.Logging.LOG_RESPONSE_BY_CLASS_AND_METHOD, p.getSignature().getName(),mapper.writeValueAsString(returnValue)));

            } catch (JsonProcessingException e) {
                logger.info(String.format(Constant.Logging.LOG_RESPONSE_BY_CLASS_AND_METHOD, p.getSignature().getName(),e.getMessage()));

            }
        }
    }

}