package study.petclinic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.petclinic.service.ClinicService;

@Controller
@RequiredArgsConstructor
public class VisitController {

    private final ClinicService clinicService;

    @GetMapping("/owners/{ownerId}/pet/{petId}/visit/add")
    public String addVisitForm(Model model) {
        model.addAttribute("visitForm", new VisitForm());
        return "visits/addVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pet/{petId}/visit/add")
    public String addVisit(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId, @ModelAttribute("visitForm") VisitForm visitForm) {
        clinicService.addVisit(petId, visitForm.getDate(), visitForm.getDescription());
        return "redirect:/owners/" + ownerId;
    }
}
