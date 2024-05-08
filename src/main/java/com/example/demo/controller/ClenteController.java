package com.example.demo.controller;

import com.example.demo.api.ClienteApi;
import com.example.demo.dtos.ClienteRecordDto;
import com.example.demo.models.ClienteModel;
import com.example.demo.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClenteController implements ClienteApi {

    @Autowired
    ClienteRepository clienteRepository;

    public ResponseEntity<ClienteModel> insertCliente(ClienteRecordDto clienteRecordDto) {
        System.out.println("INTO insertCliente");
        var clienteModel = new ClienteModel();
        clienteModel.setData(LocalDate.now());
        clienteModel.setHora(LocalTime.now());
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteModel));
    }

    public ResponseEntity<List<ClienteModel>> findCliente() {
        System.out.println("INTO findCliente");
        List<ClienteModel> listaClienteModel = clienteRepository.findAll();
        if (!listaClienteModel.isEmpty()) {
            for (ClienteModel cliente : listaClienteModel) {
                UUID id = cliente.getIdCliente();
                cliente.add(linkTo(methodOn(ClenteController.class).getCliente(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    public Object getCliente(UUID id) {
        System.out.println("INTO getCliente");
        Optional<ClienteModel> optional = clienteRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    }

    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        System.out.println("INTO updateCliente");
        Optional<ClienteModel> optional = clienteRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }
        var clienteModel = optional.get();
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteModel));
    }

    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") UUID id) {
        System.out.println("INTO deleteCliente");
        Optional<ClienteModel> optional = clienteRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }
        clienteRepository.delete(optional.get());
        return ResponseEntity.status(HttpStatus.OK).body("CLIENTE DELETADO");
    }
}
