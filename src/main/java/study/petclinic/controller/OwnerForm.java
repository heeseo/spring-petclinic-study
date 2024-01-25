package study.petclinic.controller;

import lombok.Getter;
import lombok.Setter;
import study.petclinic.domain.Owner;

@Getter @Setter
public class OwnerForm {

    private Long id;

    private String firstName;
    private String lastName;

    private String city;
    private String street;
    private String zipcode;

    private String phoneNumber;
}
