package ubs.coding.test;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class AccountNumberValidator {

    public boolean validate(String accountNumber) {
        if (StringUtils.isNotBlank(accountNumber)) {
            String trimmedAccountNumber = accountNumber.replaceAll("\\s","");
            return StringUtils.isNumeric(trimmedAccountNumber) && validateWithLuhns(trimmedAccountNumber);
        }
        return false;
    }

    private boolean validateWithLuhns(String validatedAccountNumber) {
        List<Integer> numbers =
                Arrays.stream(validatedAccountNumber.split("\\B")).map(Integer::parseInt).toList();
        List<Integer> copy = new ArrayList<>(numbers);
        Collections.reverse(copy);
        List<Integer> doubleAlternate = new ArrayList<>();
        IntStream.range(0, copy.size()).forEach(idx -> {
            if (idx % 2 == 1) {
                doubleAlternate.add(copy.get(idx) * 2);
            } else {
                doubleAlternate.add(copy.get(idx));
            }
        });
        int sum = doubleAlternate.stream().mapToInt(value -> {
            if (value >= 10) {
                return String.valueOf(value)
                        .chars()
                        .map(Character::getNumericValue)
                        .sum();
            }
            return value;
        }).sum();
        return sum % 10 == 0;
    }
}
