package org.example.bejobs.service.impl;

import org.example.bejobs.model.company.Level;
import org.example.bejobs.repository.ILevelRepository;
import org.example.bejobs.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService implements ILevelService {
    @Autowired
    private ILevelRepository iLevelRepository;
    @Override
    public List<Level> findAll() {
        return iLevelRepository.findAll();
    }
}
