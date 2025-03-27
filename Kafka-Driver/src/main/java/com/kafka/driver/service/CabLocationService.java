package com.kafka.driver.service;

import com.kafka.driver.constants.AppConstants;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CabLocationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CabLocationService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean updateLocation(String location){
        kafkaTemplate.send(AppConstants.CAB_LOCATION, location);
        return true;
    }
}
