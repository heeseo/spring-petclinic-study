package study.petclinic.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.petclinic.domain.Address;
import study.petclinic.domain.Owner;
import study.petclinic.repository.OwnerRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OwnerServiceTest {

    @Autowired OwnerService ownerService;
    @Autowired OwnerRepository ownerRepository;
    @Autowired EntityManager em;

    @Test
    void register() {
        Owner owner = new Owner("Fu", "Bao", new Address("c", "s", "z"), "111-222-333");

        Long savedOwnerId = ownerService.register(owner);

        assertThat(savedOwnerId).isNotNull();

        // Check that the saved owner can be retrieved from the repository using the returned id
        Owner retrievedOwner = ownerRepository.findById(savedOwnerId).orElse(null);

        assertThat(retrievedOwner).isNotNull();
        assertThat(retrievedOwner).isEqualTo(owner);
        assertThat(savedOwnerId).isEqualTo(owner.getId());
    }

    @Test
    void findOwners() {
        Owner owner1 = new Owner("owner1", "Doe", new Address("Street1", "City1", "Zip1"), "111-222-333");
        Owner owner2 = new Owner("owner2", "Doe", new Address("Street2", "City2", "Zip2"), "444-555-666");

        ownerRepository.save(owner1);
        ownerRepository.save(owner2);

        List<Owner> foundOwners = ownerService.findOwners();

        assertThat(foundOwners).isNotEmpty();
        assertThat(foundOwners.size()).isEqualTo(2);
        assertThat(foundOwners).containsExactly(owner1, owner2);
        assertThat(foundOwners).extracting("firstName").containsExactly("owner1", "owner2");
    }

    @Test
    void findOne() {
        Owner owner = new Owner("owner1", "Doe", new Address("Street1", "City1", "Zip1"), "111-222-333");
        ownerRepository.save(owner);

        Owner savedOwner = ownerService.findOne(owner.getId());

        assertThat(savedOwner.getFirstName()).isEqualTo("owner1");
        assertThat(savedOwner.getLastName()).isEqualTo("Doe");
        assertThat(savedOwner.getId()).isEqualTo(owner.getId());
        assertThat(savedOwner).isEqualTo(owner);
    }

    @Test
    void searchOwners() {
        Owner owner1 = new Owner("owner1", "Doe", new Address("Street1", "City1", "Zip1"), "111-222-333");
        Owner owner2 = new Owner("owner2", "Doe", new Address("Street2", "City2", "Zip2"), "444-555-666");
        ownerRepository.save(owner1);
        ownerRepository.save(owner2);

        List<Owner> ownerList1 = ownerService.searchOwners("owner1");
        List<Owner> ownerList2 = ownerService.searchOwners("Doe");

        assertThat(ownerList1).isNotEmpty();
        assertThat(ownerList1.size()).isEqualTo(1);
        assertThat(ownerList1).containsExactly(owner1);
        assertThat(ownerList1).extracting("firstName").containsExactly("owner1");

        assertThat(ownerList2).isNotEmpty();
        assertThat(ownerList2.size()).isEqualTo(2);
        assertThat(ownerList2).containsExactly(owner1, owner2);
        assertThat(ownerList2).extracting("firstName").containsExactly("owner1", "owner2");
    }

    @Test
    void update() {
        Owner owner = new Owner("owner1", "Doe", new Address("Street1", "City1", "Zip1"), "111-222-333");
        ownerRepository.save(owner);

        ownerService.update(owner.getId(), new Address("new", "address","zip"));

        em.flush();
        em.clear();

        Owner retrievedOwner = ownerRepository.findById(owner.getId()).orElse(null);

        assertThat(retrievedOwner).isNotNull();
        assertThat(retrievedOwner.getFirstName()).isEqualTo("owner1");
        assertThat(retrievedOwner.getLastName()).isEqualTo("Doe");
        assertThat(retrievedOwner.getAddress().getCity()).isEqualTo("new");
        assertThat(retrievedOwner.getAddress().getStreet()).isEqualTo("address");
        assertThat(retrievedOwner.getAddress().getZipcode()).isEqualTo("zip");
        assertThat(retrievedOwner.getPhoneNumber()).isEqualTo("111-222-333");
    }
}