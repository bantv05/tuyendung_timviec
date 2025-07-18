package org.example.bejobs.controller;

import jakarta.transaction.Transactional;
import org.example.bejobs.dto.InfoUserDTO;
import org.example.bejobs.dto.responses.LoginResponse;
import org.example.bejobs.dto.UserLoginDTO;
import org.example.bejobs.exception.DataNotFoundException;
import org.example.bejobs.exception.PermissionDenyException;
import org.example.bejobs.service.IInfoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Transactional
@RestController
@RequestMapping("${api.prefix}")
public class InfoUserController {
    @Autowired
    private IInfoUserService iInfoUserService;
    @GetMapping("info-user/{id}")
    public ResponseEntity<?> detailInfoUser(@PathVariable Integer id){
        InfoUserDTO infoUserDTO = iInfoUserService.findByIdUser(id);
        return ResponseEntity.ok(infoUserDTO);
    }
    @PostMapping("/company/register")
    public ResponseEntity<?> registerCompany(@RequestBody InfoUserDTO dto) throws PermissionDenyException {
        return ResponseEntity.ok(iInfoUserService.createInfoCompany(dto));
    }
    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody InfoUserDTO dto) throws PermissionDenyException {
        return ResponseEntity.ok(iInfoUserService.createInfoUser(dto));
    }
    @PostMapping("/login/company")
    public ResponseEntity<?> loginCompanySubmit(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            LoginResponse loginResponse = iInfoUserService.loginCompany(userLoginDTO.getEmail(), userLoginDTO.getPassword());
            return ResponseEntity.ok(LoginResponse.builder()
                    .message("Login success")
                    .token(loginResponse.getToken())
                    .roles(loginResponse.getRoles())
                    .infoUser(loginResponse.getInfoUser())
                    .build()
            );
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(
                    LoginResponse.builder()
                            .message("Login failed")
                            .build()
            );
        }
    }
    @PostMapping("/login/user")
    public ResponseEntity<?> loginUserSubmit(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            LoginResponse loginResponse = iInfoUserService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
            return ResponseEntity.ok(LoginResponse.builder()
                    .message("Login success")
                    .token(loginResponse.getToken())
                    .roles(loginResponse.getRoles())
                    .infoUser(loginResponse.getInfoUser())
                    .build()
            );
        }catch (Exception ex){
            return ResponseEntity.badRequest().body(
                    LoginResponse.builder()
                            .message("Login failed")
                            .build()
            );
        }
    }

    @DeleteMapping("/user/{id}")
    public void deleteByIdInfoUser(@PathVariable Integer id) throws DataNotFoundException {
        iInfoUserService.deleteUser(id);
    }
}
