package com.example.demo.api;

import com.example.demo.dtos.RecordOneDto;
import com.example.demo.dtos.TesteRecordDto;
import com.example.demo.models.TesteModel;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Teste"})
@RequestMapping("/v1/teste")
public interface TesteApi {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    List<TesteModel> find();

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    TesteModel insert(@RequestBody @Valid TesteRecordDto testeRecordDto);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{id}")
    Object findOne(@PathVariable(value = "id") Long id);

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/{id}")
    ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                  @RequestBody @Valid TesteRecordDto testeRecordDto);

//    @ResponseStatus(HttpStatus.CREATED)
//    @PatchMapping(value = "/{id}")
//    ResponseEntity<Object> updateOne(@PathVariable(value = "id") Long id,
//                                  @RequestBody @Valid RecordOneDto testeRecordDto);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "{id}")
    ResponseEntity<Object> delete(@PathVariable(value = "id") Long id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/count")
    long count();
}
