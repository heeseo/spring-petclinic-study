package study.petclinic.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import study.petclinic.domain.PetType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class PetForm {

    @NotBlank
    private String name;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull
    private PetType type;
}
