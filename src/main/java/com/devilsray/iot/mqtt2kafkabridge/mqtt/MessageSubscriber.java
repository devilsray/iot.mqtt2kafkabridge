package com.devilsray.iot.mqtt2kafkabridge.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MessageSubscriber implements IMqttMessageListener, IMqttActionListener {


    Logger logger = LoggerFactory.getLogger(MessageSubscriber.class);
    
	@Override
	public void onSuccess(IMqttToken asyncActionToken) {
		// TODO Auto-generated method stub
		System.out.println(asyncActionToken.getMessageId());
	}

	@Override
	public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		logger.error(topic + " -> " + message.toString());

	}

}
