package study.petclinic.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Owner {

    @Id @GeneratedValue
    @Column(name = "owner_id")
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    @Embedded
    private Address address;

    private String phoneNumber;
}
