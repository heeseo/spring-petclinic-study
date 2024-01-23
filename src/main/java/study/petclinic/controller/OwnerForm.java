package study.petclinic.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OwnerForm {

    private String firstName;
    private String lastName;

    private String city;
    private String street;
    private String zipcode;

    private String phoneNumber;
}
