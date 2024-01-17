package study.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.petclinic.domain.Pet;
import study.petclinic.domain.PetType;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByType(PetType petType);
}
