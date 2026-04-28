#!/bin/bash
KEY_DIR="src/main/resources"  # или просто "." если хранить в корне проекта
PUB_KEY="$KEY_DIR/publicKey.pem"
PRIV_KEY="$KEY_DIR/privateKey.pem"

mkdir -p "$KEY_DIR"

if [ ! -f "$PRIV_KEY" ] || [ ! -f "$PUB_KEY" ]; then
    echo "🔐 Generating RSA key pair..."
    openssl genrsa -out "$PRIV_KEY" 2048
    openssl rsa -in "$PRIV_KEY" -pubout -out "$PUB_KEY"
    echo "✅ Keys saved to $KEY_DIR/"
else
    echo "✓ Keys already exist."
fi

source .env
docker-compose up -d
./gradlew quarkusDev