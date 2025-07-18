package org.example.bejobs.service;

import org.example.bejobs.model.company.WorkType;

import java.util.List;

public interface IWorkTypeService {
    List<WorkType> findAll();
}
