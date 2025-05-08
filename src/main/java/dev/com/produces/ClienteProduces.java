package dev.com.produces;

import dev.com.dtos.EmailDto;
import dev.com.models.ClienteModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClienteProduces {

    final RabbitTemplate rabbitTemplate;
    @Value("${broker.queue.email.name}")
    private String routtingKey;

    public ClienteProduces(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMenssageEmail (ClienteModel clienteModel) {
        var emailDto = new EmailDto();
        emailDto.setIdCliente(clienteModel.getIdCliente());
        emailDto.setEmailTo(clienteModel.getEmail());
        emailDto.setSubject("CADASTRO");
        emailDto.setText("TESTE ENVIO");

        rabbitTemplate.convertAndSend("", routtingKey, emailDto);
    }
}
