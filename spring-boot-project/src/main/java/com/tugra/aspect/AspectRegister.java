package com.tugra.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AspectRegister {

    private Logger logger = Logger.getLogger(AspectRegister.class.getName());

    @Before("execution(* com.tugra.service.impl.KullaniciServiceImpl.kayit(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("Metod çalışacak : " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.tugra.service.impl.KullaniciServiceImpl.kayit(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("Kayıt olan kişi : " + joinPoint.getArgs().toString());
    }

}
