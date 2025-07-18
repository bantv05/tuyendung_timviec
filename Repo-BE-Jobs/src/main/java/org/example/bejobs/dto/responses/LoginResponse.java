package org.example.bejobs.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.bejobs.model.user.InfoUser;
import org.example.bejobs.model.user.RoleEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("token")
    private String token;

    @JsonProperty("infoUser")
    private InfoUser infoUser;

    @JsonProperty("role")
    private RoleEntity roles;
    public LoginResponse(String token, RoleEntity roles, InfoUser infoUser) {
        this.token = token;
        this.roles = roles;
        this.infoUser = infoUser;
    }
}
