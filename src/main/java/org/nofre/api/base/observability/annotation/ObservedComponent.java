package org.nofre.api.base.observability.annotation;

import io.micrometer.observation.annotation.Observed;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

@Observed
@Component
@Documented
@RequestMapping
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObservedComponent {
    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value() default {};
}
