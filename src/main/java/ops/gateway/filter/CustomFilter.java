package ops.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ops.gateway.model.response.ResponseConstant;
import ops.gateway.model.response.ResponseDTO;
import ops.gateway.util.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Configuration
public class CustomFilter implements Filter {
    @Value("${app.public-key}")
    private String public_key ;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse response  = (HttpServletResponse) servletResponse;
        String token = httpRequest.getHeader(Constant.FilterConstant.HEADER_AUTHENTICATION);
        ApiVerifyToken apiVerifyToken = new ApiVerifyToken(token,public_key);
        Map<String, Object> result = apiVerifyToken.verifyToken();

        if (result != null){
            httpRequest.setAttribute("user_name",result.get("user_name"));
            filterChain.doFilter(httpRequest,servletResponse);
        }else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getOutputStream().write(restResponseBytes(ResponseConstant.ERROR_USER_NOT_ROLE));
            return;
        }
    }

    @Override
    public void destroy() {

    }

    private byte[] restResponseBytes(ResponseDTO responseDto) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(responseDto);
        return serialized.getBytes();
    }

}
