#!/bin/bash

set -e

echo "Sending message..."
curl -X POST http://localhost:8080/api/v1/messages/send \
  -H 'Content-Type: application/json' \
  -d '{"message": "Hello World!"}'
