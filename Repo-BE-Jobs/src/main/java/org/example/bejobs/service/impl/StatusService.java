package org.example.bejobs.service.impl;

import org.example.bejobs.model.company.Status;
import org.example.bejobs.repository.IStatusRepository;
import org.example.bejobs.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements IStatusService {
    @Autowired
    private IStatusRepository iStatusRepository;
    @Override
    public List<Status> findAll() {
        return iStatusRepository.findAll();
    }
}
