package org.example.bejobs.controller;

import jakarta.transaction.Transactional;
import org.example.bejobs.dto.LevelDTO;
import org.example.bejobs.model.company.Level;
import org.example.bejobs.service.ILevelService;
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
public class LevelController {
    @Autowired
    private ILevelService iLevelService;
    @GetMapping("public/level")
    public ResponseEntity<List<Level>> findAll(){
        List<Level> levels = iLevelService.findAll();
        return ResponseEntity.ok(levels);
    }
}
