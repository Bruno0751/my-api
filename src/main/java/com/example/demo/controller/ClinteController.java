package com.example.demo.controller;

import com.example.demo.api.ClienteApi;
import com.example.demo.dtos.ClienteRecordDto;
import com.example.demo.models.Cliente;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClinteController implements ClienteApi {

    @Autowired
    ClienteRepository clienteRepository;

    public ClienteModel insertCliente(ClienteRecordDto clienteRecordDto) {
        System.out.println("INTO insertCliente");
        System.out.println(clienteRecordDto.toString());
        var clienteModel = new ClienteModel();
        clienteModel.setData(LocalDate.now());
        clienteModel.setHora(LocalTime.now());
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        return clienteRepository.save(clienteModel);
    }

    public List<ClienteModel> findCliente() {
        System.out.println("INTO findCliente");
        List<ClienteModel> listaClienteModel = clienteRepository.findAll();
        if (!listaClienteModel.isEmpty()) {
            for (ClienteModel cliente : listaClienteModel) {
                UUID id = cliente.getIdCliente();
                cliente.add(linkTo(methodOn(ClinteController.class).getCliente(id)).withSelfRel());
            }
        }
        return clienteRepository.findAll();
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

    @Override
    public long count() {
        System.out.println("INTO count");
        return clienteRepository.count();
    }

    @Override
    public Cliente buscarId() {
        System.out.println("INTO buscarId");
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteRepository.findID(), cliente);
        return cliente;
    }

}
