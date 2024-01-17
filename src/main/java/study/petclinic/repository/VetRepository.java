package study.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.petclinic.domain.Vet;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
