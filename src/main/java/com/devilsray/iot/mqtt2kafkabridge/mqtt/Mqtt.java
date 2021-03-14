package com.devilsray.iot.mqtt2kafkabridge.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Mqtt {

    private IMqttClient instance;

    @Autowired
    public Mqtt(@Value(value = "${mqtt.address}") String address, @Value(value = "${mqtt.id}") String publisherId) throws MqttException {
        instance = new MqttClient(address, publisherId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        instance.connect(options);
    }

    public IMqttClient getInstance() {
        return instance;
    }
}