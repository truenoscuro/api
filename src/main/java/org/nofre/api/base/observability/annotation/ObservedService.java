package org.nofre.api.base.observability.annotation;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Service
@Observed
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObservedService {
}
