package com.wyz;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
public class CatAopService {
    @Around("@annotation(enableCat)")
    public Object aroundMethod(ProceedingJoinPoint pjp, EnableCat enableCat) {
        MethodSignature joinPointObject = (MethodSignature) pjp.getSignature();
        Method method = joinPointObject.getMethod();
        log.info("埋点: " + method.getName());
        Transaction t = Cat.newTransaction(enableCat.transactionName(), method.getName());
        Cat.logMetricForCount(enableCat.countMetricName());
        try {
            // 參數
            Object[] args = pjp.getArgs();
            Object result = pjp.proceed(args);
            t.setStatus(Message.SUCCESS);
            return result;
        } catch (Throwable e) {
            t.setStatus(e);
            Cat.logError(e);
        } finally {
            t.complete();
        }
        return null;
    }
}
