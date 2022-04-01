package com.grek.alcohol.validation;

import com.grek.alcohol.user.controller.UserDto;
import com.grek.alcohol.validation.exception.AgeIsLessThan18;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ValidationService {
    //metoda walidacyjna może być o wiele bardziej rozbudowana, np. zbierać listę pól które się nie zgadzają
    public void validate(UserDto userDto) {
        if(ChronoUnit.YEARS.between(userDto.getBirthDate(), LocalDate.now()) < 18) {
            throw new AgeIsLessThan18();
        }
    }
}
