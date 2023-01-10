FROM python:latest

COPY python-consumer/consumer.py .
COPY python-consumer/requirements.txt .
COPY python-consumer/.env .

RUN pip install -r requirements.txt

CMD ["python", "consumer.py"]
