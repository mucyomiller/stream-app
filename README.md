# Kafka Stream Application Experiments


Install deps

```shell
$ mvn clean install
```

run development kafka & zookeeper

```shell
$ docker-compose up
```

## Observing data
with kafkacat

```shell
$ kafkacat -b localhost:9092 -f topic_name
```
producer

```shell
$ kafkacat -b localhost:9092 -t topic_name -P
```

## Tools

```shell
$ sudo apt install kafkacat
```