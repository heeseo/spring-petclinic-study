package study.petclinic.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.transaction.annotation.Transactional;
import study.petclinic.domain.*;
import study.petclinic.repository.OwnerRepository;
import study.petclinic.repository.PetRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClinicServiceTest {

    @Autowired PetRepository petRepository;
    @Autowired OwnerRepository ownerRepository;
    @Autowired ClinicService clinicService;

    @Test
    public void addVisitTest() {
        //arrange
        Owner owner = getOwner();
        ownerRepository.save(owner);

        Pet pet1 = Pet.registerPet("fubao", owner, LocalDateTime.now(), PetType.DOG);
        petRepository.save(pet1);

        //act
        clinicService.addVisit(pet1.getId(), LocalDateTime.now(), "vaccine");

        //assert
        Pet retrievedPet = petRepository.findById(pet1.getId()).orElseThrow(null);
        assertThat(retrievedPet).isNotNull();

        List<Visit> visits = retrievedPet.getVisits();
        assertThat(visits.size()).isEqualTo(1);
        assertThat(visits.get(0).getDescription()).isEqualTo("vaccine");
    }

    @Test
    public void updateVisit() {
        //arrange
        Owner owner = getOwner();
        ownerRepository.save(owner);

        Pet pet1 = Pet.registerPet("fubao", owner, LocalDateTime.now(), PetType.DOG);
        petRepository.save(pet1);

        Long savedVisitId = clinicService.addVisit(pet1.getId(), LocalDateTime.now(), "vaccine");

        //act
        clinicService.editVisit(savedVisitId, LocalDateTime.of(2024, 10, 1, 10,10), "virus");

        //assert
        Pet retrievedPet = petRepository.findById(pet1.getId()).orElseThrow(null);
        assertThat(retrievedPet).isNotNull();

        List<Visit> visits = retrievedPet.getVisits();
        assertThat(visits.size()).isEqualTo(1);
        assertThat(visits.get(0).getDate()).hasMonthValue(10).hasDayOfMonth(1);
        assertThat(visits.get(0).getDescription()).isEqualTo("virus");

    }

    private Owner getOwner() {
        Address address = new Address("city", "street", "myzip");
        return new Owner("Fu", "Bao", address, "1112111");
    }

}