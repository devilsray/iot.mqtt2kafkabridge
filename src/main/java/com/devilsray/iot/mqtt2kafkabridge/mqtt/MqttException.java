package com.devilsray.iot.mqtt2kafkabridge.mqtt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MqttException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4368735652352199270L;

	public MqttException(String message) {
        super(message);
    }
}