package study.petclinic.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import study.petclinic.domain.PetType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class PetForm {

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private PetType type;
}
