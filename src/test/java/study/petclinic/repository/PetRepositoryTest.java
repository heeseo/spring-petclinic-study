package study.petclinic.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.petclinic.domain.Address;
import study.petclinic.domain.Owner;
import study.petclinic.domain.Pet;
import study.petclinic.domain.PetType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PetRepositoryTest {

    @Autowired PetRepository petRepository;
    @Autowired OwnerRepository ownerRepository;

    @Test
    public void saveAndFindPet() {
        Address address = new Address("city", "street", "myzip");
        Owner owner = new Owner("Fu", "Bao", address, "1112111");
        ownerRepository.save(owner);

        Pet pet = Pet.registerPet("loopy", owner, LocalDateTime.now(), PetType.DOG);
        petRepository.save(pet);

        Optional<Pet> petOptional = petRepository.findById(pet.getId());

        assertThat(petOptional).isPresent();
        Pet retrievedPet = petOptional.get();

        assertThat(retrievedPet.getName()).isEqualTo("loopy");
        assertThat(retrievedPet.getBirthDate()).isEqualTo(pet.getBirthDate());
        assertThat(retrievedPet.getOwner()).isEqualTo(owner);
        assertThat(retrievedPet.getType()).isEqualTo(pet.getType());
        assertThat(retrievedPet).isEqualTo(pet);
    }

    @Test
    public void findByTypeTest() {
        Address address = new Address("city", "street", "myzip");
        Owner owner = new Owner("Fu", "Bao", address, "1112111");
        ownerRepository.save(owner);

        Pet pet = Pet.registerPet("loopy", owner, LocalDateTime.now(), PetType.DOG);
        petRepository.save(pet);

        //when
        List<Pet> pets = petRepository.findByType(PetType.DOG);

        //then
        assertThat(pets.size()).isEqualTo(1);
        assertThat(pets.get(0).getName()).isEqualTo("loopy");
        assertThat(pets.get(0).getBirthDate()).isEqualTo(pet.getBirthDate());
        assertThat(pets.get(0).getOwner()).isEqualTo(owner);
        assertThat(pets.get(0).getType()).isEqualTo(pet.getType());
        assertThat(pets.get(0)).isEqualTo(pet);

        List<Pet> birds = petRepository.findByType(PetType.BIRD);
        assertThat(birds).isEmpty();
    }

}