package org.nofre.api.base.observability;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class ObservedRepositoryAspect {

    private final ObservationRegistry registry;

    @Pointcut("within(@org.nofre.api.base.observability.annotation.ObservedRepository *)")
    public void withinPointcut() {
    }

    @Pointcut("execution(* (@org.nofre.api.base.observability.annotation.ObservedRepository *).*(..))")
    public void executionPointcut() {
    }


    @Around("withinPointcut() || executionPointcut()")
    public Object wrapWithObservation(ProceedingJoinPoint pjp) throws Throwable {

        String className = "ObservabilityRepository";
        String kebabClassName = className.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();

        String methodName = pjp.getSignature().getName();
        String kebabMethodName = methodName.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();


        String observationName = className + "." + methodName;
        String contextualName = kebabClassName + "#" + kebabMethodName;

        return Observation
                .createNotStarted(observationName, registry)
                .contextualName(contextualName)
                .observe(() -> {
                    try {
                        return pjp.proceed();
                    } catch (Throwable t) {
                        if (t instanceof RuntimeException) {
                            throw (RuntimeException) t;
                        }
                        throw new RuntimeException(t);
                    }
                });
    }
}