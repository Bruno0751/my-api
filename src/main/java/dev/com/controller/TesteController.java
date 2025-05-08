package dev.com.controller;

import java.util.List;
import java.util.Optional;
import dev.com.api.TesteApi;
import dev.com.dtos.TesteRecordDto;
import dev.com.models.TesteModel;
import dev.com.repositories.TesteRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class TesteController implements TesteApi  {

    @Autowired
    TesteRepository testeRepository;

    @Override
    public List<TesteModel> find() {
        return testeRepository.findAll();
    }

    @Override
    public TesteModel insert(TesteRecordDto testeRecordDto) {
        var testeModel = new TesteModel();
        BeanUtils.copyProperties(testeRecordDto, testeModel);
        try {
            return testeRepository.save(testeModel);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(violation -> {
                System.out.println(violation.getMessage());
            });
        }
        return null;
    }

    @Override
    public Object findOne(Long id) {
        Optional<TesteModel> optional = testeRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    }

    @Override
    public ResponseEntity<Object> update(Long id, TesteRecordDto testeRecordDto) {
        Optional<TesteModel> optional = testeRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }
        var testeModel = optional.get();
        BeanUtils.copyProperties(testeRecordDto, testeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(testeRepository.save(testeModel));
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        Optional<TesteModel> optional = testeRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }
        testeRepository.delete(optional.get());
        return ResponseEntity.status(HttpStatus.OK).body("CLIENTE DELETADO");
    }

    @Override
    public long count() {
        return testeRepository.count();
    }
}
