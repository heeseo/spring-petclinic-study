package study.petclinic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import study.petclinic.domain.Vet;
import study.petclinic.service.VetService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping("/vets")
    public String vets(Model model) {
        List<Vet> vets = vetService.findAllVets();
        model.addAttribute("vets", vets);
        return "vets/vetList";
    }
}
