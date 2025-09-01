package com.atguigu.RS.RountineLoad;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class TestKafkaProducer {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers","hadoop102:9092,hadoop103:9092,hadoop104:9092");
        prop.put("acks","-1");
        prop.put("batch.size", "1048576");
        prop.put("linger.ms", "5");
        prop.put("compression.type", "snappy");
        prop.put("buffer.memory", "33554432");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //Kafka生产者
        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);
        for (int i = 10000; i < 20000; i++) {
            ProducerRecord<String, String> record =
                    new ProducerRecord<>("test", i + ",1,2000-01-01,222@qq.com,test2,2021-11-20,A");
            producer.send(record);
        }
        producer.flush();
        producer.close();
    }
}
