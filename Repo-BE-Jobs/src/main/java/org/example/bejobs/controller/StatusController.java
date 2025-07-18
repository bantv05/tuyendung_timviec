package org.example.bejobs.controller;

import jakarta.transaction.Transactional;
import org.example.bejobs.model.company.Level;
import org.example.bejobs.model.company.Status;
import org.example.bejobs.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@Transactional
@RestController
@RequestMapping("${api.prefix}")
public class StatusController {
    @Autowired
    private IStatusService iStatusService;
    @GetMapping("public/status")
    public ResponseEntity<List<Status>> findAll(){
        List<Status> statusList = iStatusService.findAll();
        return ResponseEntity.ok(statusList);
    }
}
