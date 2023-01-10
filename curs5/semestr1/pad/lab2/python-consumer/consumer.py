import logging
import os

from dotenv import load_dotenv
from kafka.consumer import KafkaConsumer

load_dotenv(verbose=True)
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

def main():
  print("Starting consumer", os.environ["BOOTSTRAP_SERVERS"])
  consumer = KafkaConsumer( 
    bootstrap_servers=[os.environ["BOOTSTRAP_SERVERS"]],
    auto_offset_reset="earliest",
    enable_auto_commit=True
  )

  consumer.subscribe([os.environ["TOPIC"]])

  for message in consumer:
    try:
      kafka_message = f"""
      Message received: {message.value}
      Message key: {message.key}
      Message partition: {message.partition}
      Message offset: {message.offset}
      """
      logger.info(kafka_message)
    except Exception as e:
      logger.error(e)
  

if __name__ == "__main__":
  main()