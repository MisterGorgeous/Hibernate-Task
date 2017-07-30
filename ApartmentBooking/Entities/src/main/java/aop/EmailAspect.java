package aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utills.SendEmail;

@Component
@Aspect
public class EmailAspect {
    private SendEmail sendEmail;

    @Autowired
    public void setSendEmail(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Pointcut("execution(* *.changeUserPassword(..))")
    public void changeUserPassword() {}

    @AfterReturning(pointcut = "changeUserPassword()", returning = "result")
    public void logAfter(Object result) {
        Boolean isChanged = (Boolean) result;
        if(isChanged){
            sendEmail.sendEmail();
        }
    }
}
