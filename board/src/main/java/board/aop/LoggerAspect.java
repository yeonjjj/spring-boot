package board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect     //AOP 설정
public class LoggerAspect {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //해당 기능이 실행될 시점(어드바이스) 정의
    //Around 어드바이스: 대상 메서드의 실행 전후 또는 예외 발생 시점에 사용
    //execution: 포인트 컷 표현식, 적용할 메서드 명시
    @Around("execution(* board..controller.*Controller.*(..)) || " +
            "execution(* board..service.*Impl.*(..)) || " +
            "execution(* board..mapper.*Mapper.*(..))")
    public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable{
        String type="";
        String name=joinPoint.getSignature().getDeclaringTypeName();
        //실행되는 메서드의 이름을 이용해서 컨트롤러, 서비스, 매퍼를 구분
        if(name.indexOf("Controller")>-1){
            type="Controller \t: ";     //실행되는 메서드의 이름 출력
        }
        else if(name.indexOf("Service")>-1){
            type="ServiceImpl \t: ";
        }
        else if(name.indexOf("Mapper")>-1){
            type="Mapper \t: ";
        }
        log.debug(type+name+"."+joinPoint.getSignature().getName()+"()");
        return joinPoint.proceed();
    }

}

