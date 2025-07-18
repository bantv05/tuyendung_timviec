package org.example.bejobs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApplicationDTO {
    private Integer applicationId;
    private LocalDate applicationDate;
    private JobOfferDTO jobOfferDTO;
    private InfoUserDTO infoUserDTO;
    private StatusDTO statusDTO;
}
