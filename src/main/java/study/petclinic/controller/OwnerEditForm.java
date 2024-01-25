package study.petclinic.controller;

import lombok.Getter;
import lombok.Setter;
import study.petclinic.domain.Owner;

@Getter @Setter
public class OwnerEditForm {

    private Long id;

    private String firstName;
    private String lastName;

    private String city;
    private String street;
    private String zipcode;

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
