package org.kdubij.orderservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PositiveNonZeroAmountValidator.class)
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PositiveNonZeroAmountConstraint {

    String message() default "Parameter or field not in ^[1-9]\\d*$ format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
