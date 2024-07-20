package com.example.demo.api;

import com.example.demo.dtos.ClienteRecordDto;
import com.example.demo.models.Cliente;
import com.example.demo.models.ClienteModel;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.UUID;

@Api(tags = {"Cliente"})
@RequestMapping("/v1/cliente")
public interface ClienteApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/insert")
    ClienteModel insertCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/find")
    List<ClienteModel> findCliente();

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/count")
    long count();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/findId")
    Cliente buscarId();
}
