package org.kdubij.orderservice.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveNonZeroAmountValidator implements ConstraintValidator<PositiveNonZeroAmountConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) || value.matches("^[1-9]\\d*$");
    }
}
