package study.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.petclinic.domain.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
