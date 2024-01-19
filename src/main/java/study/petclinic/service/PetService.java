package study.petclinic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.petclinic.domain.Owner;
import study.petclinic.domain.Pet;
import study.petclinic.domain.PetType;
import study.petclinic.exception.OwnerNotFoundException;
import study.petclinic.exception.PetNotFoundException;
import study.petclinic.repository.OwnerRepository;
import study.petclinic.repository.PetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;


    @Transactional
    public Long register(Long ownerId, String name, LocalDateTime birthDate, PetType type) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException("Owner not found with id: " + ownerId));
        Pet pet = Pet.registerPet(name, owner, birthDate, type);
        petRepository.save(pet);
        return pet.getId();
    }

    @Transactional
    public void updatePet(Long id, String name, LocalDateTime birthDate, PetType type) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));
        pet.updatePet(name, birthDate, type);
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public Pet findPet(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));
    }
}
