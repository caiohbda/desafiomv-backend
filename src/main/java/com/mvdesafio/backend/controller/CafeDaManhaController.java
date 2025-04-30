package com.mvdesafio.backend.controller;

import com.mvdesafio.backend.dto.CafeDaManhaDTO;
import com.mvdesafio.backend.service.CafeDaManhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cafes")
public class CafeDaManhaController {

    @Autowired
    private CafeDaManhaService cafeDaManhaService;

    @PostMapping
    public ResponseEntity<CafeDaManhaDTO> cadastrar(@RequestBody @Valid CafeDaManhaDTO dto) {
        return ResponseEntity.ok(cafeDaManhaService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CafeDaManhaDTO>> listarTodos() {
        return ResponseEntity.ok(cafeDaManhaService.listarTodos());
    }
}
