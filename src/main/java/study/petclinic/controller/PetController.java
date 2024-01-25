package study.petclinic.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.petclinic.domain.Pet;
import study.petclinic.domain.PetType;
import study.petclinic.service.PetService;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PetController {

    private final PetService petService;

    @GetMapping("/owners/{ownerId}/pet/add")
    public String createPetForm(Model model) {
        model.addAttribute("types", PetType.values());
        model.addAttribute("petForm", new PetForm());
        return "pets/createPetForm";
    }

    @PostMapping("/owners/{ownerId}/pet/add")
    public String createPet(@PathVariable("ownerId") Long ownerId, @ModelAttribute("petForm") PetForm petForm) {
        log.info("pet type = {}", petForm.getType());
        log.info("birth date = {}", petForm.getBirthDate());
        log.info("pet name = {}", petForm.getName());
        petService.register(ownerId, petForm.getName(), petForm.getBirthDate().atStartOfDay(), petForm.getType());
        return "redirect:/owners/" + ownerId;
    }

    @GetMapping("/owners/{ownerId}/pet/{petId}/edit")
    public String editPetForm(@PathVariable("petId") Long petId, Model model) {
        Pet pet = petService.findPet(petId);
        PetForm petForm = new PetForm();
        petForm.setName(pet.getName());
        petForm.setBirthDate(pet.getBirthDate().toLocalDate());
        petForm.setType(pet.getType());

        model.addAttribute("petForm", petForm);
        model.addAttribute("types", PetType.values());
        return "pets/createPetForm";
    }

    @PostMapping("/owners/{ownerId}/pet/{petId}/edit")
    public String updatePet(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId, @ModelAttribute("petForm") PetForm petForm, BindingResult bindingResult) {
        petService.updatePet(petId, petForm.getName(), petForm.getBirthDate().atStartOfDay(), petForm.getType());
        return "redirect:/owners/" + ownerId;
    }

}
