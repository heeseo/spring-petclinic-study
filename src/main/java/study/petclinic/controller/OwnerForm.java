package study.petclinic.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import study.petclinic.domain.Owner;

@Getter @Setter
public class OwnerForm {

    private Long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @NotBlank
    private String city;
    private String street;
    private String zipcode;

    @NotBlank
    private String phoneNumber;
}
