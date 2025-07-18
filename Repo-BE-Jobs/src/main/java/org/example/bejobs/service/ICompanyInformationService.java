package org.example.bejobs.service;

import org.example.bejobs.dto.CompanyInformationDTO;
import org.example.bejobs.exception.DataNotFoundException;
import org.example.bejobs.model.company.CompanyInformation;
import org.springframework.data.domain.Page;

public interface ICompanyInformationService {
    Page<CompanyInformationDTO> findAll(Integer pageNumber, Integer pageSize);
    CompanyInformation createCompanyInformation (CompanyInformationDTO companyInformationDTO);
    CompanyInformation updateCompanyInformation (Integer id, CompanyInformationDTO companyInformationDTO) throws DataNotFoundException;
}
