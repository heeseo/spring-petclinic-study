package study.petclinic.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.petclinic.domain.Address;
import study.petclinic.domain.Owner;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OwnerRepositoryTest {

    @Autowired OwnerRepository ownerRepository;

    @Test
    public void saveAndFindTest() {
        Address address = new Address("city", "street", "myzip");
        Owner owner = new Owner("Fu", "Bao", address, "1112111");

        ownerRepository.save(owner);

        Owner retrievedOwner = ownerRepository.findById(owner.getId()).orElse(null);

        assertThat(retrievedOwner).isNotNull();
        assertThat(retrievedOwner.getFirstName()).isEqualTo(owner.getFirstName());
        assertThat(retrievedOwner.getLastName()).isEqualTo(owner.getLastName());
        assertThat(retrievedOwner).isEqualTo(owner);

        ownerRepository.delete(owner);

        Owner deletedOwner = ownerRepository.findById(owner.getId()).orElse(null);

        assertThat(deletedOwner).isNull();
    }

    @Test
    public void findByFirstNameTest() {
        Address address = new Address("city", "street", "myzip");
        Owner owner = new Owner("Fu", "Bao", address, "1112111");

        ownerRepository.save(owner);

        List<Owner> owners = ownerRepository.findByFirstName(owner.getFirstName());

        assertThat(owners).isNotEmpty();
        assertThat(owners.get(0).getFirstName()).isEqualTo(owner.getFirstName());
        assertThat(owners.get(0).getLastName()).isEqualTo(owner.getLastName());
        assertThat(owners.get(0)).isEqualTo(owner);

        List<Owner> ownersEmpty = ownerRepository.findByFirstName("NONAME");

        assertThat(ownersEmpty).isEmpty();
    }

    @Test
    public void findByNameTest() {
        Address address = new Address("city", "street", "myzip");
        Owner owner = new Owner("Fu", "Bao", address, "1112111");
        ownerRepository.save(owner);

        List<Owner> ownerByFirstName = ownerRepository.findByName(owner.getFirstName());
        List<Owner> ownerByLastName = ownerRepository.findByName(owner.getLastName());
        List<Owner> ownersEmpty = ownerRepository.findByName("NONAME");

        assertThat(ownerByFirstName).isNotEmpty();
        assertThat(ownerByFirstName.get(0)).isEqualTo(owner);

        assertThat(ownerByLastName).isNotEmpty();
        assertThat(ownerByLastName.get(0)).isEqualTo(owner);

        assertThat(ownersEmpty).isEmpty();
    }

}