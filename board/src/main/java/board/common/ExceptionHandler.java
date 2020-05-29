package board.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice   //해당 클래스가 예외처리 클래스임을 알려줌
public class ExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //해당 메서드에서 처리할 예외 지정
    //NullPointerException, NumberFormatException 등 각각의 예외에 맞는 적절한 예외처리
    //Exception.class를 처리하는 메서드는 가장 마지막에 위치시킴
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception exception){
        //예외 발생 시 보여줄 화면 지정
        ModelAndView mv = new ModelAndView("/error/error_default");
        mv.addObject("exception", exception);
        //에러 로그 출력
        log.error("exception", exception);
        return mv;
    }

}

