package org.example.bejobs.service;

import org.example.bejobs.dto.ApplicationDTO;
import org.example.bejobs.dto.ApplicationRequestDTO;
import org.example.bejobs.dto.StatusDTO;
import org.example.bejobs.exception.DataNotFoundException;
import org.example.bejobs.model.company.Application;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IApplicationService {
    Page<ApplicationDTO> findAll(Integer pageNumber, Integer pageSize);
    // Phương thức để nộp đơn
    public void applyForJob(ApplicationRequestDTO applicationRequestDTO, Integer jobOfferId, Integer userId, MultipartFile cvFile) throws IOException;
    // Phương thức để cập nhật trạng thái đơn ứng tuyển
    Application updateApplication(Integer id, StatusDTO statusDTO) throws DataNotFoundException;
}
