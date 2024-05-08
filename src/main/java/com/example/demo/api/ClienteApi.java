package com.example.demo.api;

import com.example.demo.dtos.ClienteRecordDto;
import com.example.demo.models.ClienteModel;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Api(tags = {"Cliente"})
@RequestMapping("/v1/cliente")
public interface ClienteApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/insert")
    ResponseEntity<ClienteModel> insertCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/find")
    ResponseEntity<List<ClienteModel>> findCliente();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/findOne/{id}")
    Object getCliente(@PathVariable(value = "id") UUID id);

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/update/{id}")
    ResponseEntity<Object> updateCliente(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ClienteRecordDto clienteRecordDto);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") UUID id);
}
