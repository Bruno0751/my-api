package dev.com.controller;

import dev.com.api.ClienteApi;
import dev.com.controller.client.EmailClient;
import dev.com.dtos.ClienteRecordDto;
import dev.com.models.ClienteModel;
//import dev.com.produces.ClienteProduces;
import dev.com.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RestController
public class ClinteController implements ClienteApi {


    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EmailClient emailClient;

    @Transactional
    public ClienteModel insert(ClienteRecordDto clienteRecordDto) {
        var clienteModel = new ClienteModel();
        clienteModel.setDataTime(new Timestamp(System.currentTimeMillis()));
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        try {
            clienteModel = clienteRepository.save(clienteModel);
            emailClient.publishMenssageEmail(clienteModel);
            return clienteModel;
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().forEach(violation -> {
                System.out.println(violation.getMessage());
            });
        }
        return null;
    }

    public List<ClienteModel> find() {
        List<ClienteModel> listaClienteModel = clienteRepository.findAll();
        if (!listaClienteModel.isEmpty()) {
            for (ClienteModel cliente : listaClienteModel) {
                UUID id = cliente.getIdCliente();
                cliente.add(linkTo(methodOn(ClinteController.class).findOne(id)).withSelfRel());
            }
        }
        return clienteRepository.findAll();
    }

    public Object findOne(UUID id) {
        Optional<ClienteModel> optional = clienteRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optional.get());
    }

    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ClienteRecordDto clienteRecordDto) {
        Optional<ClienteModel> optional = clienteRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }
        var clienteModel = optional.get();
        BeanUtils.copyProperties(clienteRecordDto, clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteModel));
    }

    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<ClienteModel> optional = clienteRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NAO ENCONTRADO");
        }
        clienteRepository.delete(optional.get());
        return ResponseEntity.status(HttpStatus.OK).body("CLIENTE DELETADO");
    }

    @Override
    public long count() {
        return clienteRepository.count();
    }

}
