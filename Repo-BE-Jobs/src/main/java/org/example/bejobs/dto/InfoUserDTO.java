package org.example.bejobs.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.bejobs.model.company.Application;
import org.example.bejobs.model.user.RoleEntity;

import java.util.List;

@Setter
@Getter
public class InfoUserDTO {
    private Integer idInfoUser;
    private String userName;
    private String CV;
    private String email;
    private String phone;
    private String password;
    private List<Application> applications;
}
