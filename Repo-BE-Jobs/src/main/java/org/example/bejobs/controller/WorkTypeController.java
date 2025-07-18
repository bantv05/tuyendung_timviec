package org.example.bejobs.controller;

import jakarta.transaction.Transactional;
import org.example.bejobs.model.company.Status;
import org.example.bejobs.model.company.WorkType;
import org.example.bejobs.service.IWorkTypeService;
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
public class WorkTypeController {
    @Autowired
    private IWorkTypeService iWorkTypeService;
    @GetMapping("public/work-type")
    public ResponseEntity<List<WorkType>> findAll(){
        List<WorkType> workTypeList = iWorkTypeService.findAll();
        return ResponseEntity.ok(workTypeList);
    }
}
