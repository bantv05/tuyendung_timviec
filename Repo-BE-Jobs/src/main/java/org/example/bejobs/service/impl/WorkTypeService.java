package org.example.bejobs.service.impl;

import org.example.bejobs.model.company.WorkType;
import org.example.bejobs.repository.IWorkTypeRepository;
import org.example.bejobs.service.IWorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkTypeService implements IWorkTypeService {
    @Autowired
    private IWorkTypeRepository iWorkTypeRepository;
    @Override
    public List<WorkType> findAll() {
        return iWorkTypeRepository.findAll();
    }
}
