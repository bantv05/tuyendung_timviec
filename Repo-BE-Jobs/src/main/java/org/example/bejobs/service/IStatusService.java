package org.example.bejobs.service;

import org.example.bejobs.model.company.Status;

import java.util.List;

public interface IStatusService {
    List<Status> findAll();
}
