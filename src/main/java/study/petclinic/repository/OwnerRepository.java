package study.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.petclinic.domain.Owner;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findByFirstName(String firstName);

    @Query("SELECT o FROM Owner o WHERE " +
            "COALESCE(:name, '') = '' OR o.firstName = :name OR o.lastName = :name")
    List<Owner> findByName(@Param("name") String name);

    @Query("SELECT o FROM Owner o WHERE " +
            "COALESCE(:name, '') = '' OR LOWER(o.firstName) = LOWER(:name) OR LOWER(o.lastName) = LOWER(:name)")
    List<Owner> findByNameIgnoreCase(@Param("name") String name);
}
