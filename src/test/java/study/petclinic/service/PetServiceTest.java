package study.petclinic.service;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.petclinic.domain.Address;
import study.petclinic.domain.Owner;
import study.petclinic.domain.Pet;
import study.petclinic.domain.PetType;
import study.petclinic.repository.PetRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PetServiceTest {

    @Autowired PetService petService;
    @Autowired PetRepository petRepository;
    @Autowired OwnerService ownerService;
    @Autowired EntityManager em;

    @Test
    void register() {
        Owner owner = getOwner();
        ownerService.register(owner);

        Long petId = petService.register(owner.getId(), "pikachu", LocalDateTime.now(), PetType.DOG);

        Pet retrievedPet = petRepository.findById(petId).orElse(null);
        assertThat(retrievedPet).isNotNull();
        assertThat(retrievedPet.getName()).isEqualTo("pikachu");
        assertThat(owner.getPets()).containsExactly(retrievedPet);
    }

    @Test
    void updatePet() {
        Owner owner = getOwner();
        ownerService.register(owner);
        Long petId = petService.register(owner.getId(), "pikachu", LocalDateTime.now(), PetType.DOG);

        petService.updatePet(petId, "raichu", LocalDateTime.now(), PetType.CAT);

        Pet retrievedPet = petRepository.findById(petId).orElse(null);
        assertThat(retrievedPet).isNotNull();
        assertThat(retrievedPet.getName()).isEqualTo("raichu");
        assertThat(retrievedPet.getType()).isEqualTo(PetType.CAT);
    }

    @Test
    void findAllPets() {
        Owner owner = getOwner();
        ownerService.register(owner);

        Pet pet1 = Pet.registerPet("fubao", owner, LocalDateTime.now(), PetType.DOG);
        Pet pet2 = Pet.registerPet("lebao", owner, LocalDateTime.now(), PetType.DOG);
        petRepository.save(pet1);
        petRepository.save(pet2);

        em.flush();
        em.clear();

        List<Pet> allPets = petService.findAllPets();

        assertThat(allPets.size()).isEqualTo(2);
        assertThat(allPets).extracting("name").containsExactly("fubao", "lebao");
    }

    @Test
    void findPet() {
        Owner owner = getOwner();
        ownerService.register(owner);

        Pet pet1 = Pet.registerPet("fubao", owner, LocalDateTime.now(), PetType.DOG);
        Pet pet2 = Pet.registerPet("lebao", owner, LocalDateTime.now(), PetType.DOG);
        petRepository.save(pet1);
        petRepository.save(pet2);

        Pet retrievedPet = petService.findPet(pet1.getId());

        assertThat(retrievedPet).isEqualTo(pet1);
        assertThat(retrievedPet.getName()).isEqualTo("fubao");
    }

    private Owner getOwner() {
        Address address = new Address("city", "street", "myzip");
        return new Owner("Fu", "Bao", address, "1112111");
    }
}