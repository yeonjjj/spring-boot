package board.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Collections;

@Configuration
public class TransactionAspect {

    // 트랜잭션을 설정할 때 사용되는 설정값을 상수로 선언
    private static final String AOP_TRANSACTION_METHOD_NAME="*";
//  private static final String AOP_TRANSACTION_EXPRESSION="execution(* board..service.*Impl.*(..))";
    private static final String AOP_TRANSACTION_EXPRESSION="execution(* board..*..*..*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor transactionAdvice(){
        MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        //트랜잭션의 이름 설정
        transactionAttribute.setName(AOP_TRANSACTION_METHOD_NAME);
        //트랜잭션에서 롤백하는 룰 설정, 예외가 발생하면 롤백 수행
        transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        source.setTransactionAttribute(transactionAttribute);

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor transactionAdviceAdvisor(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_TRANSACTION_EXPRESSION);     //AOP의 포인트컷 설정
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
    }

}

