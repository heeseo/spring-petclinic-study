package study.petclinic.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet {

    @Id @GeneratedValue
    @Column(name = "pet_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @OneToMany(mappedBy = "pet")
    private List<Visit> visits = new ArrayList<>();

    public Pet(String name, Owner owner, LocalDateTime birthDate, PetType type) {
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.type = type;
    }
}
