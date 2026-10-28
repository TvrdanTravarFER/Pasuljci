#!/bin/bash

# usage: ./scripts/diagram-generator.sh

for file in docs/puml/*.puml; do
    if grep -q 'KrvnaVeza' "$file"; then
        plantuml -tsvg -o ../assets/diagrams "$file"
    fi
done