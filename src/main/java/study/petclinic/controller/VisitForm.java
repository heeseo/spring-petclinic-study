package study.petclinic.controller;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitForm {

    private LocalDateTime date;
    private String description;
}
