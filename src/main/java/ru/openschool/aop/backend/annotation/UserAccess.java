package ru.openschool.aop.backend.annotation;

import ru.openschool.aop.backend.access.ActionId;
import ru.openschool.aop.backend.access.ResourceId;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserAccess {
        ActionId action();
        ResourceId resource();
}
