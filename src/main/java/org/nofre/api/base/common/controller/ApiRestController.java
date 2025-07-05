package org.nofre.api.base.common.controller;


import io.micrometer.observation.annotation.Observed;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;


@Observed
@Documented
@RestController
@RequestMapping
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiRestController {

    @AliasFor(annotation = RequestMapping.class, attribute = "value") String[] value() default {};

}