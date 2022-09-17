package com.example.fastfoodmanagmentbackend.Service.converter;

import com.example.fastfoodmanagmentbackend.Model.Person;
import com.example.fastfoodmanagmentbackend.Service.forms.WorkerForm;
import org.springframework.stereotype.Component;

@Component
public class FormConverter {


    public Person convert(WorkerForm form) {

        Person p= new Person(form.getUsername(), form.getPassword(), form.getRole());

        return p;
    }
}
