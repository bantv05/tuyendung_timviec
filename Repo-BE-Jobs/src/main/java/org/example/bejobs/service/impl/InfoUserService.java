package org.example.bejobs.service.impl;

import org.example.bejobs.components.JwtTokenUtils;
import org.example.bejobs.dto.InfoUserDTO;
import org.example.bejobs.dto.responses.LoginResponse;
import org.example.bejobs.exception.DataNotFoundException;
import org.example.bejobs.exception.PermissionDenyException;
import org.example.bejobs.model.user.InfoUser;
import org.example.bejobs.model.user.RoleEntity;
import org.example.bejobs.repository.IInfoUserRepository;
import org.example.bejobs.repository.user.IRoleEntityRepository;
import org.example.bejobs.service.IInfoUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfoUserService implements IInfoUserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IInfoUserRepository iInfoUserRepository;
    @Autowired
    private IRoleEntityRepository iRoleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    public Boolean exist(String email){
        return iInfoUserRepository.existsByEmail(email);
    }
    @Override
    public InfoUser createInfoUser(InfoUserDTO infoUserDTO) {
        Boolean checkEmail = exist(infoUserDTO.getEmail());
        if(checkEmail){
            throw new DataIntegrityViolationException("Email already exists");
        }
        RoleEntity role = iRoleEntityRepository.findByCode(RoleEntity.USER);
        InfoUser infoUser = modelMapper.map(infoUserDTO, InfoUser.class);
        infoUser.setRole(role);
        encodePassword(infoUserDTO, infoUser);
        return iInfoUserRepository.save(infoUser);
    }

    @Override
    public InfoUser createInfoCompany(InfoUserDTO infoUserDTO) throws PermissionDenyException {
        Boolean checkEmail = iInfoUserRepository.existsByEmail(infoUserDTO.getEmail());
        if(checkEmail){
            throw new DataIntegrityViolationException("Email already exists");
        }
        RoleEntity companyRole = iRoleEntityRepository.findByCode(RoleEntity.COMPANY);
        InfoUser infoUser = modelMapper.map(infoUserDTO, InfoUser.class);
        infoUser.setCV(null);
        infoUser.setRole(companyRole);
        encodePassword(infoUserDTO, infoUser);
        return iInfoUserRepository.save(infoUser);
    }
    private void encodePassword(InfoUserDTO dto, InfoUser entity) {
        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    }
    @Override
    public Boolean existsById(Integer id) {
        return iInfoUserRepository.existsById(id);
    }
    @Override
    public void deleteUser(Integer id) throws DataNotFoundException{
        if (!existsById(id)) {
            throw new DataNotFoundException("This id does not exist");
        }
        iInfoUserRepository.deleteById(id);
    }

    @Override
    public InfoUserDTO findByIdUser(Integer id) {
        InfoUser infoUser = iInfoUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot user with id" + id));
        return modelMapper.map(infoUser, InfoUserDTO.class);
    }

    @Override
    public InfoUser updateInfoUser(Integer id, InfoUserDTO infoUserDTO) throws DataNotFoundException {
        InfoUser exitingIinfoUser = iInfoUserRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot infoUser with id"+ id));
        InfoUser infoUser = exitingIinfoUser.toBuilder()
                .idInfoUser(exitingIinfoUser.getIdInfoUser())
                .userName(infoUserDTO.getUserName())
                .CV(infoUserDTO.getCV())
                .email(infoUserDTO.getEmail())
                .password(infoUserDTO.getPassword() != null ? passwordEncoder.encode(infoUserDTO.getPassword()) : exitingIinfoUser.getPassword())
                .phone(infoUserDTO.getPhone())
                .role(exitingIinfoUser.getRole())
                .applications(exitingIinfoUser.getApplications())
                .build();
        return iInfoUserRepository.save(infoUser);
    }

    @Override
    public InfoUser updateInfoCompany(Integer id, InfoUserDTO infoUserDTO) throws DataNotFoundException {
        InfoUser exitingInfoCompany = iInfoUserRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot infoUser with id"+ id));
        InfoUser infoUser = exitingInfoCompany.toBuilder()
                .idInfoUser(exitingInfoCompany.getIdInfoUser())
                .userName(infoUserDTO.getUserName())
                .email(infoUserDTO.getEmail())
                .password(infoUserDTO.getPassword() != null ? passwordEncoder.encode(infoUserDTO.getPassword()) : exitingInfoCompany.getPassword())
                .phone(infoUserDTO.getPhone())
                .role(exitingInfoCompany.getRole())
                .applications(exitingInfoCompany.getApplications())
                .build();
        return iInfoUserRepository.save(infoUser);
    }

    @Override
    public LoginResponse loginUser(String email, String password) throws Exception {
        Optional<InfoUser> existingInfoUser =iInfoUserRepository.findByEmail(email);
        if(existingInfoUser.isEmpty() || !existingInfoUser.get().getRole().getCode().equals(RoleEntity.USER)){
            throw new DataNotFoundException("Wrong email number or password");
        }
        return getLoginResponse(email, password, existingInfoUser);
    }

    @Override
    public LoginResponse loginCompany(String email, String password) throws Exception {
        Optional<InfoUser> existingInfoUser =iInfoUserRepository.findByEmail(email);
        if(existingInfoUser.isEmpty() || !existingInfoUser.get().getRole().getCode().equals(RoleEntity.COMPANY)){
            throw new DataNotFoundException("Wrong email number or password");
        }
        return getLoginResponse(email, password, existingInfoUser);
    }

    private LoginResponse getLoginResponse(String email, String password, Optional<InfoUser> existingInfoUser) throws Exception {
        InfoUser user = existingInfoUser.get();
        System.out.println(new BCryptPasswordEncoder().encode("admin123"));
        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Wrong email number or password");
        }
        RoleEntity role = iRoleEntityRepository.findByCode(user.getRole().getCode());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email,password, user.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        String token = jwtTokenUtils.generateToken(user);
        return new LoginResponse(token, role, user);
    }
}
