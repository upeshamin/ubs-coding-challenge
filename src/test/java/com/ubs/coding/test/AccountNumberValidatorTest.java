package com.ubs.coding.test;

import com.ubs.coding.test.AccountNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountNumberValidatorTest {

    private AccountNumberValidator accountNumberValidator;

    @BeforeEach
    void init() {
        accountNumberValidator = new AccountNumberValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"49927398716", " 49927398716", " 4992 739   8716 ", "79927398713"})
    void shouldReturnTrueWhenNumberIsValid (String accountNumber) {
        boolean isValid = accountNumberValidator.validate(accountNumber);
        assertThat(isValid).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "abcd", "12345678", "123"})
    void shouldReturnTrueWhenNumberIsInvalid (String invalidAccountNumber) {
        boolean isValid = accountNumberValidator.validate(invalidAccountNumber);
        assertThat(isValid).isFalse();
    }

    @Test
    void shouldReturnTrueWhenNumberIsNull () {
        boolean isValid = accountNumberValidator.validate(null);
        assertThat(isValid).isFalse();
    }

}
