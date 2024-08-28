package com.jpabooks.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class IpAddressIpml implements ConstraintValidator<IpAddress, String> {

    private static final Pattern IP_PATTERN = Pattern.compile(
            "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;  }

        var matcher = IP_PATTERN.matcher(value);
        if (!matcher.matches()) {
            return false;
        }

        for (int i = 1; i <= 4; i++) {
            int octet = Integer.parseInt(matcher.group(i));
            if (octet > 255) {
                return false;
            }
        }

        return true;
    }
}
