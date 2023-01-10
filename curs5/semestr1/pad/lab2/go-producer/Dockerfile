FROM golang:latest

ENV GO111MODULE=off

RUN go get github.com/Shopify/sarama

COPY go-producer/producer.go .

CMD ["go", "run", "producer.go"]
