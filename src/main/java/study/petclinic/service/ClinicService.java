package study.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.petclinic.domain.Pet;
import study.petclinic.domain.Visit;
import study.petclinic.exception.VisitNotFoundException;
import study.petclinic.repository.VisitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClinicService {

    private final OwnerService ownerService;
    private final PetService petService;
    private final VisitRepository visitRepository;

    @Transactional
    public Long addVisit(Long petId, LocalDateTime date, String description) {
        Pet pet = petService.findPet(petId);
        Visit visit = new Visit(pet, date, description);
        visitRepository.save(visit);
        return visit.getId();
    }

    @Transactional
    public void editVisit(Long id, LocalDateTime date, String description) {
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new VisitNotFoundException("Visit not found with id: " + id));
        visit.updateVisit(date, description);
    }

    public List<Visit> findAllVisits() {
        return visitRepository.findAll();
    }

}


