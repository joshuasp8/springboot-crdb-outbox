#!/bin/bash

# Start docker containers (Kafka, Zookeeper, Postgres)
docker-compose up -d

wait_for_postgres() {
  echo "Waiting for Postgres to be ready..."
  until docker exec -it $(docker ps -qf "ancestor=postgres:17.4") pg_isready -U postgres > /dev/null 2>&1; do
    sleep 1
  done
  echo "Postgres is ready."
}

wait_for_kafka() {
  echo "Waiting for Kafka to be ready..."
  until echo > /dev/tcp/localhost/9092 2>/dev/null; do
    sleep 1
  done
  echo "Kafka is ready."
}

# Wait for Postgres and Kafka to be ready
wait_for_postgres
wait_for_kafka

# Create demo database if it doesn't exist
# this may fail if the database already exists
echo "Creating 'demo' database..."
docker exec -it $(docker ps -qf "ancestor=postgres:17.4") psql -U postgres -c 'CREATE DATABASE "demo"'

echo "Database 'demo' ready."

# Create Kafka topic
docker exec -it $(docker ps -qf "ancestor=confluentinc/cp-kafka:7.6.5") \
  kafka-topics --create \
  --topic demo-topic \
  --bootstrap-server localhost:9092 \
  --partitions 3 \
  --replication-factor 1

echo "Kafka topic 'demo-topic' created."
