package com.shopkart.emailservice.clients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopkart.emailservice.clients.dtos.EmailServicePayLoadDto;
import com.shopkart.emailservice.exceptions.ObjectToJSONException;
import com.shopkart.emailservice.utilities.EmailUtility.EmailUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerClient {

    private ObjectMapper objectMapper;
    private EmailUtil emailUtil;
    public KafkaConsumerClient(ObjectMapper objectMapper,EmailUtil emailUtil){
        this.objectMapper=objectMapper;
        this.emailUtil=emailUtil;
    }

    @KafkaListener(topics = "sendEmail" ,groupId = "emailService")
    public void handleSendEmail(String message){
        try {
            EmailServicePayLoadDto emailServicePayLoadDto =objectMapper.readValue(message, EmailServicePayLoadDto.class);
            emailUtil.sendMail(emailServicePayLoadDto.getTo(),emailServicePayLoadDto.getSubject(),emailServicePayLoadDto.getBody());
        } catch (JsonProcessingException e) {
            throw new ObjectToJSONException(e.getMessage());
        }
    }
}
