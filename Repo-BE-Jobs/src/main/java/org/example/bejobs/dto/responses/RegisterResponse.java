package org.example.bejobs.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.example.bejobs.model.user.InfoUser;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("user")
    private InfoUser infoUser;
}
