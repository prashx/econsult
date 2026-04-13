#!/bin/bash

set -e

TIMESTAMP=$(date)

echo "Fetching external README..."

# Fetch raw content
CONTENT=$(curl -s https://raw.githubusercontent.com/prashx/comments_microservices/main/README.md)

# Extract words (remove markdown/special chars)
WORDS=$(echo "$CONTENT" | tr -cs '[:alnum:]' '\n')

# Pick a random word
RANDOM_WORD=$(echo "$WORDS" | shuf -n 1)

echo "Random word picked: $RANDOM_WORD"

# Update README
if grep -q "Last updated:" README.md; then
  sed -i "s/Last updated:.*/Last updated: $TIMESTAMP | Word: $RANDOM_WORD/" README.md
else
  echo -e "\nLast updated: $TIMESTAMP | Word: $RANDOM_WORD" >> README.md
fi
