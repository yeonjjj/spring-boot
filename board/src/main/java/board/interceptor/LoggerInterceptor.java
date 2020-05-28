package board.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {  //상속

    //컨트롤러가 실행되기 전 수행
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception{
        log.debug("============================== START ==============================");
        log.debug("Request URI \t: "+request.getRequestURI());  //호출된 URI 출력
        return super.preHandle(request, response, handler);
    }

    //컨트롤러가 정상적으로 실행된 후 수행
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception{
        log.debug("=============================== END ===============================\n");
    }

}

