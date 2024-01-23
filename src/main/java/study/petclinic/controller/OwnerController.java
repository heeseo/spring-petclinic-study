package study.petclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String addOwner(@ModelAttribute("ownerForm") OwnerForm ownerForm, BindingResult result) {
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
}
