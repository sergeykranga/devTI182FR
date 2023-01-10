package main

import (
	"fmt"
	"github.com/Shopify/sarama"
)

func main() {
	config := sarama.NewConfig()
	config.Producer.Return.Successes = true
	client, err := sarama.NewClient([]string{"kafka:9092"}, config)
	if err != nil {
		panic(err)
	}
	defer client.Close()

	producer, err := sarama.NewSyncProducerFromClient(client)
	if err != nil {
		panic(err)
	}
	defer producer.Close()

	message := &sarama.ProducerMessage{
		Topic: "test",
		Value: sarama.StringEncoder("hello world"),
	}
	partition, offset, err := producer.SendMessage(message)
	if err != nil {
		panic(err)
	}
	fmt.Printf("Message sent to partition %d at offset %d\n", partition, offset)
}
