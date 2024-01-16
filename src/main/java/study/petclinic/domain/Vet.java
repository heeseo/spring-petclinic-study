package study.petclinic.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Vet {

    @Id @GeneratedValue
    @Column(name = "vet_id")
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vet_id")
    private List<Speciality> specialities = new ArrayList<>();
}
