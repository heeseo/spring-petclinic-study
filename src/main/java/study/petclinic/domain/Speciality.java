package study.petclinic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Speciality {

    @Id @GeneratedValue
    @Column(name = "speciality_id")
    private Long id;

    private String name;

    public Speciality(String name) {
        this.name = name;
    }
}
