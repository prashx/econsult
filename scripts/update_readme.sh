#!/bin/bash

TIMESTAMP=$(date)

echo "Updating README with timestamp: $TIMESTAMP"

# Replace or append timestamp
echo -e "\nLast updated: $TIMESTAMP" >> README.md
