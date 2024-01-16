package study.petclinic.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Pet {

    @Id @GeneratedValue
    @Column(name = "pet_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @OneToMany(mappedBy = "pet")
    private List<Visit> visits = new ArrayList<>();
}
