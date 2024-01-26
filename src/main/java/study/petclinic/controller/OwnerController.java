package study.petclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.petclinic.domain.Address;
import study.petclinic.domain.Owner;
import study.petclinic.service.OwnerService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/owners/add")
    public String addOwnerForm(Model model) {
        model.addAttribute("ownerForm", new OwnerForm());
        return "owners/createOwnerForm";
    }


    @PostMapping("/owners/add")
    public String addOwner(@Validated @ModelAttribute("ownerForm") OwnerForm ownerForm, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOwnerForm";
        }

        Address address = new Address(ownerForm.getCity(), ownerForm.getStreet(), ownerForm.getZipcode());
        Owner owner = new Owner(ownerForm.getFirstName(), ownerForm.getLastName(), address, ownerForm.getPhoneNumber());
        ownerService.register(owner);

        return "redirect:/owners";
    }

    @GetMapping("/owners")
    public String findOwners(@RequestParam(required = false) String ownerName, Model model) {
        log.info("name = {}", ownerName);

        List<Owner> owners = ownerService.searchOwners(ownerName);

        model.addAttribute("ownerName", ownerName);
        model.addAttribute("owners", owners);
        return "owners/ownerList";
    }

    @GetMapping("/owners/{id}")
    public String ownerDetail(@PathVariable("id") Long id, Model model) {
        Owner owner = ownerService.findOne(id);
        model.addAttribute("owner", owner);
        return "owners/ownerDetail";
    }

    @GetMapping("/owners/{id}/edit")
    public String editOwnerForm(@PathVariable("id") Long id, Model model) {
        Owner owner = ownerService.findOne(id);
        OwnerEditForm ownerEditForm = OwnerEditForm.convertToForm(owner);
        model.addAttribute("ownerEditForm", ownerEditForm);
        return "owners/editOwnerForm";
    }

    @PostMapping("/owners/{id}/edit")
    public String updateOwner(@ModelAttribute("owner") OwnerEditForm ownerEditForm, BindingResult result){
        if (result.hasErrors()) {
            return "owners/editOwnerForm";
        }
        log.info("ownerId = {}", ownerEditForm.getId());
        ownerService.update(ownerEditForm.getId(), new Address(ownerEditForm.getCity(), ownerEditForm.getStreet(), ownerEditForm.getZipcode()));
        return "redirect:/owners/" + ownerEditForm.getId();
    }
}
