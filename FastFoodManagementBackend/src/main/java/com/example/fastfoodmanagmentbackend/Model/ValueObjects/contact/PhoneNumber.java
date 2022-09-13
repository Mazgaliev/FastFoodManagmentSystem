package com.example.fastfoodmanagmentbackend.Model.ValueObjects.contact;

import com.example.fastfoodmanagmentbackend.Model.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class PhoneNumber implements ValueObject {

    private final Operator operator;
    private final String number;

    public PhoneNumber(Operator operator, String number) {
        this.operator = operator;
        this.number = number;
    }

    protected PhoneNumber() {
        this.operator = null;
        this.number = "000";
    }

    public static PhoneNumber valueOf(Operator operator, String number) {
        return new PhoneNumber(operator, number);
    }
}
