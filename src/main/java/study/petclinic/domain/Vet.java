package study.petclinic.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vet {

    @Id @GeneratedValue
    @Column(name = "vet_id")
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vet_id")
    private List<Speciality> specialities = new ArrayList<>();


    public Vet(String firstName, String lastName, Speciality... specialities) {
        this.firstName = firstName;
        this.lastName = lastName;

        for (Speciality speciality : specialities) {
            this.specialities.add(speciality);
        }
    }
}
