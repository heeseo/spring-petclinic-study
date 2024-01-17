package study.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.petclinic.domain.Owner;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findByFirstName(String firstName);

    @Query("select o from Owner o where o.firstName = :name or o.lastName = :name")
    List<Owner> findByName(@Param("name") String name);
}
