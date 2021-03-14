# Mqtt to Kafka bridge

## Motivation

Motivation for building this service is my home automation combined with a learning effect of current technologies. 
This service will be only a simple services which received messages from a configures mqtt broker and sends them to a configured kafka broker.

The values and device information will be handled this way:

- An additional server will receive the kafka messages and maps them depending on a given configuration. Than it sends them on a new topic. 
- Another service is responsible for the latest value and will store them in a redis
- Another service will be responsible for the historical data and will store them 


__Currently there are no payload and health checks, the receiving and sending is currently absolutely basic status__