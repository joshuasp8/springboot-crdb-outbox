#!/bin/bash

CRDB_URL="postgresql://root@localhost:26257/defaultdb?sslmode=disable"

# Start docker containers (Kafka, Zookeeper, CockroachDB)
docker-compose up -d

wait_for_cockroachdb() {
  echo "Waiting for CockroachDB to be ready..."
  until docker exec -it $(docker ps -qf "ancestor=cockroachdb/cockroach:latest-v24.3") \
    cockroach node status --all --url=$CRDB_URL > /dev/null 2>&1; do
    sleep 1
  done
  echo "CockroachDB is ready."
}

wait_for_kafka() {
  echo "Waiting for Kafka to be ready..."
  until echo > /dev/tcp/localhost/9092 2>/dev/null; do
    sleep 1
  done
  echo "Kafka is ready."
}

# Wait for CRDB and Kafka to be ready
wait_for_cockroachdb
wait_for_kafka

# Create demo database if it doesn't exist
# this may fail if the database already exists
echo "Creating 'demo' database..."
docker exec -it $(docker ps -qf "ancestor=cockroachdb/cockroach:latest-v24.3") cockroach sql --url=$CRDB_URL --execute "CREATE DATABASE IF NOT EXISTS demo;"

echo "Database 'demo' ready."

# Create Kafka topics
docker exec -it $(docker ps -qf "ancestor=confluentinc/cp-kafka:7.6.5") \
  kafka-topics --create \
  --topic demo-order-topic \
  --bootstrap-server kafka:9092 \
  --partitions 3 \
  --replication-factor 1

docker exec -it $(docker ps -qf "ancestor=confluentinc/cp-kafka:7.6.5") \
  kafka-topics --create \
  --topic order-outbox-events \
  --bootstrap-server kafka:9092 \
  --partitions 3 \
  --replication-factor 1

echo "Bootstrap complete."
