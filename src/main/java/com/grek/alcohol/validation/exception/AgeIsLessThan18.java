package com.grek.alcohol.validation.exception;

public class AgeIsLessThan18 extends RuntimeException {
    public AgeIsLessThan18() {
        super("Age is less than 18");
    }
}
