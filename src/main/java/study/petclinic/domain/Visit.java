package study.petclinic.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visit {

    @Id @GeneratedValue
    @Column(name = "visit_id")
    private Long id;

    private String description;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Visit(Pet pet, LocalDateTime date, String description) {
        this.pet = pet;
        this.date = date;
        this.description = description;
        pet.addVisit(this);
    }
}
