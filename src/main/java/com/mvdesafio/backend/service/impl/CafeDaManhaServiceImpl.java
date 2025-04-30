package com.mvdesafio.backend.service.impl;

import com.mvdesafio.backend.dto.CafeDaManhaDTO;
import com.mvdesafio.backend.exception.BusinessException;
import com.mvdesafio.backend.model.CafeDaManha;
import com.mvdesafio.backend.repository.CafeDaManhaRepository;
import com.mvdesafio.backend.service.CafeDaManhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CafeDaManhaServiceImpl implements CafeDaManhaService {

    @Autowired
    private CafeDaManhaRepository cafeDaManhaRepository;

    @Override
    public CafeDaManhaDTO cadastrar(CafeDaManhaDTO dto) {
        // Verifica se data é maior que hoje
        if (dto.getData() == null || !dto.getData().isAfter(LocalDate.now())) {
            throw new BusinessException("A data do café deve ser maior que a data atual.");
        }

        // Verifica se já existe café da manhã nesta data
        if (cafeDaManhaRepository.findByData(dto.getData()).isPresent()) {
            throw new BusinessException("Já existe um café da manhã nesta data.");
        }

        CafeDaManha cafe = new CafeDaManha();
        cafe.setNome(dto.getNome());
        cafe.setData(dto.getData());
        cafe = cafeDaManhaRepository.save(cafe);

        dto.setId(cafe.getId());
        return dto;
    }

    @Override
    public List<CafeDaManhaDTO> listarTodos() {
        return cafeDaManhaRepository.findAll().stream().map(cafe -> {
            CafeDaManhaDTO dto = new CafeDaManhaDTO();
            dto.setId(cafe.getId());
            dto.setNome(cafe.getNome());
            dto.setData(cafe.getData());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CafeDaManhaDTO editar(Long id, CafeDaManhaDTO dto) {
        CafeDaManha cafe = cafeDaManhaRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Café da manhã não encontrado."));

        cafe.setNome(dto.getNome());
        cafe.setData(dto.getData());
        cafe = cafeDaManhaRepository.save(cafe);

        dto.setId(cafe.getId());
        return dto;
    }
}
