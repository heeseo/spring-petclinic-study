package study.petclinic.controller;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitForm {
    @NotNull
    @FutureOrPresent
    private LocalDateTime date;
    private String description;
}
