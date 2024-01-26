package study.petclinic.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import study.petclinic.domain.Owner;

@Getter @Setter
public class OwnerEditForm {

    @NotNull
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

    public static OwnerEditForm convertToForm(Owner owner) {
        OwnerEditForm ownerEditForm = new OwnerEditForm();
        ownerEditForm.setId(owner.getId());
        ownerEditForm.setFirstName(owner.getFirstName());
        ownerEditForm.setLastName(owner.getLastName());
        ownerEditForm.setCity(owner.getAddress().getCity());
        ownerEditForm.setStreet(owner.getAddress().getStreet());
        ownerEditForm.setZipcode(owner.getAddress().getZipcode());
        ownerEditForm.setPhoneNumber(owner.getPhoneNumber());
        return ownerEditForm;
    }

}
