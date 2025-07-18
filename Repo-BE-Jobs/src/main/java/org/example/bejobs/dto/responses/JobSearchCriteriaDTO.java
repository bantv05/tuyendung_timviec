package org.example.bejobs.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobSearchCriteriaDTO {
    private String jobTitle;
    private List<String> cities;
}
