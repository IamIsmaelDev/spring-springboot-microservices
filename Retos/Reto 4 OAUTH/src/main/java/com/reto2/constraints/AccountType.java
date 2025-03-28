package com.reto2.constraints;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { AccountType.Validator.class })
public @interface AccountType {

    String message() default "Tipo de cuenta inválido. Debe ser 'Personal' o 'Company'.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Validator implements ConstraintValidator<AccountType, String> {
        @Override
        public void initialize(final AccountType accountType) {
        }

        @Override
        public boolean isValid(final String accountType, final ConstraintValidatorContext context) {
            if (accountType == null) {
                return false; // Null no es válido
            }
            return accountType.equals("Personal") || accountType.equals("Company");
        }
    }
}
