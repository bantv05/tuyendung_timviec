package org.example.bejobs.service;

import org.example.bejobs.dto.InfoUserDTO;
import org.example.bejobs.dto.responses.LoginResponse;
import org.example.bejobs.exception.DataNotFoundException;
import org.example.bejobs.exception.PermissionDenyException;
import org.example.bejobs.model.user.InfoUser;

public interface IInfoUserService {
    InfoUser createInfoUser (InfoUserDTO infoUserDTO)throws PermissionDenyException;
    InfoUser createInfoCompany (InfoUserDTO infoUserDTO)throws PermissionDenyException;
    InfoUser updateInfoUser (Integer id, InfoUserDTO infoUserDTO) throws DataNotFoundException;
    InfoUser updateInfoCompany (Integer id, InfoUserDTO infoUserDTO) throws DataNotFoundException;
    LoginResponse loginUser(String email, String password/*, Long roleId*/) throws Exception;
    LoginResponse loginCompany(String email, String password/*, Long roleId*/) throws Exception;
    void deleteUser(Integer id) throws DataNotFoundException;
    InfoUserDTO findByIdUser (Integer id);
    Boolean existsById(Integer id);
}
