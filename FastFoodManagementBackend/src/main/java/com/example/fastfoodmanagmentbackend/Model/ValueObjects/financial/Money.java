package com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial;

import com.example.fastfoodmanagmentbackend.Model.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Money implements ValueObject {
    private final Currency currency;

    private final double amount;

    public Money(Currency currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    protected Money() {
        this.currency = null;
        this.amount = 0.0;
    }

    public static Money valueOf(Currency currency, double amount) {
        return new Money(currency, amount);
    }

    public static Money convert(Currency from, Currency to, double amount) {
        //TODO
        return null;
    }

    public Money add(Money money) {
        if (money.currency != this.currency)
            throw new IllegalArgumentException("Cannot add different currencies");

        return valueOf(this.currency, money.amount + this.amount);
    }

    public Money subtract(Money money) {
        if (money.currency != this.currency)
            throw new IllegalArgumentException("Cannot subtract different currencies");

        return valueOf(this.currency, money.amount - this.amount);
    }

    public Money multiply(int number) {

        return valueOf(this.currency, number * this.amount);
    }
}
