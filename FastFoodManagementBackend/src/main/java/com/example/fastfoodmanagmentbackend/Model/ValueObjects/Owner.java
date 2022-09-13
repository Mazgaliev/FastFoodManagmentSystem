package com.example.fastfoodmanagmentbackend.Model.ValueObjects;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.contact.PhoneNumber;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
public class Owner {
    private final String ownerName;
    private final String ownerSurname;
    private final String e_mail;
    @Embedded
    private final PhoneNumber ownerPhoneNumber;

    public Owner(String ownerName, String ownerSurname, String e_mail, PhoneNumber ownerPhoneNumber) {
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.e_mail = e_mail;
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    protected Owner() {
        this.ownerName = "";
        this.ownerSurname = "";
        this.e_mail = "";
        this.ownerPhoneNumber = null;
    }
    public static Owner valueOf(String name, String surname, String e_mail, PhoneNumber phoneNumber){
        return new Owner(name,surname,e_mail,phoneNumber);
    }
}
