package study.petclinic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Speciality {

    @Id @GeneratedValue
    @Column(name = "speciality_id")
    private Long id;

    private String name;
}
