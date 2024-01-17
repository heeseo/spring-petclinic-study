package study.petclinic.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {


    @Test
    public void testEntityCreation() {
        // Arrange
        Address address = new Address("City", "Street", "12345");

        // Act
        Owner owner = new Owner("John", "Doe", address, "123-456-7890");

        // Assert
        assertThat(owner).isNotNull();
        assertThat(owner.getId()).isNull(); // Assuming id is initialized as null by default
        assertThat(owner.getFirstName()).isEqualTo("John");
        assertThat(owner.getLastName()).isEqualTo("Doe");
        assertThat(owner.getAddress()).isEqualTo(address);
        assertThat(owner.getPhoneNumber()).isEqualTo("123-456-7890");
        assertThat(owner.getPets()).isEmpty(); // Assuming pets are initialized as an empty list
    }

    @Test
    public void testEntityWithPets() {
        // Arrange
        Address address = new Address("City", "Street", "12345");
        Owner owner = new Owner("Bob", "Johnson", address, "123-456-7890");
        Pet pet = new Pet("Fluffy", owner, LocalDateTime.now(), PetType.BIRD);

        // Act
        owner.getPets().add(pet);

        // Assert
        assertThat(owner).isNotNull();
        assertThat(owner.getPets()).hasSize(1);
        assertThat(owner.getPets().get(0)).isEqualTo(pet);
    }
}