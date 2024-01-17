package study.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.petclinic.domain.Owner;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findByFirstName(String firstName);
}
