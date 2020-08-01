package schoolsys.base.exceptionhandler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import schoolsys.base.bean.RespBean;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
    @ExceptionHandler(value = AccessDeniedException.class)
    public RespBean errorHandler(AccessDeniedException ex) {
        return RespBean.fail("当前帐号无权限访问");
    }
}
