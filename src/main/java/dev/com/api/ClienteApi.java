package dev.com.api;

import dev.com.dtos.ClienteRecordDto;
import dev.com.models.ClienteModel;
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
    @PostMapping(value = "/")
    ClienteModel insert(@RequestBody @Valid ClienteRecordDto clienteRecordDto);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    List<ClienteModel> find();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{id}")
    Object findOne(@PathVariable(value = "id") UUID id);

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "{id}")
    ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ClienteRecordDto clienteRecordDto);

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "{id}")
    ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/count")
    long count();

}
