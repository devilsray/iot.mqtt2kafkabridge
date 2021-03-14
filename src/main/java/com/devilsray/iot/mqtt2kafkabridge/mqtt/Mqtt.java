package com.devilsray.iot.mqtt2kafkabridge.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Mqtt {

    private static final String MQTT_PUBLISHER_ID = "test"; //FIXME
    private static final String MQTT_SERVER_ADDRES= "tcp://bella.fritz.box:1883"; // FIXME
    private static IMqttClient instance;
    private static MessageSubscriber subscriber = new MessageSubscriber();
    
    public static IMqttClient getInstance() {
        try {
            if (instance == null) {
                instance = new MqttClient(MQTT_SERVER_ADDRES, MQTT_PUBLISHER_ID);
            }

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            if (!instance.isConnected()) {
                instance.connect(options);
                instance.subscribe("#",  subscriber);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private Mqtt() {

    }
}