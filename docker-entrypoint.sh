#!/bin/bash
set -e

KEY_DIR=/keys
mkdir -p "$KEY_DIR"

if [ ! -f "$KEY_DIR/privateKey.pem" ]; then
    echo "Generating RSA key pair..."
    openssl genrsa -out "$KEY_DIR/privateKey.pem" 2048
    openssl rsa -in "$KEY_DIR/privateKey.pem" -pubout -out "$KEY_DIR/publicKey.pem"
    echo "Keys generated."
fi

exec java -jar /deployments/quarkus-run.jar
