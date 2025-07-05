package org.nofre.api.base.observability.annotation;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Observed
@Documented
@RestController
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ObservedRestController {
}
