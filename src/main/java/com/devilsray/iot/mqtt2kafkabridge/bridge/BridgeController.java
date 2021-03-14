package com.devilsray.iot.mqtt2kafkabridge.bridge;

import com.devilsray.iot.mqtt2kafkabridge.mqtt.Mqtt;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BridgeController {

    private final IMqttClient mqtt;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String topic;

    @Autowired
    public BridgeController(KafkaTemplate<String, String> kafkaTemplate, Mqtt mqtt, @Value(value = "${bridge.kafka.topic}") String topic, @Value(value = "${bridge.mqtt.topic}") String[] mqttTopics) {
        this.mqtt = mqtt.getInstance();
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        // FIXME better initialization and error handling
        var receiver = new IMqttMessageListener(){
            @Override
            public void messageArrived(String mqttTopic, MqttMessage mqttMessage) {
                kafkaTemplate.send(topic, mqttTopic, mqttMessage.toString());
            }
        };

        try {
            for(String mqttTopic : mqttTopics) {
                this.mqtt.subscribe(mqttTopic,  receiver);
            }
        } catch (MqttException e) {
            // FIXME handle the exception!
            e.printStackTrace();
        }
    }

}
