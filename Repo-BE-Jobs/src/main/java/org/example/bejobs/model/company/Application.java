package org.example.bejobs.model.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.bejobs.model.user.InfoUser;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;
    private LocalDate applicationDate;
    private String fileCV;
    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    @JsonIgnore
    private JobOffer jobOffer;
    @ManyToOne
    @JoinColumn(name = "info_user_id")
    private InfoUser infoUser;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
