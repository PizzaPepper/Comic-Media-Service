#!/bin/bash

curl -X POST localhost:8080/api/comic \
-H "Content-Type: application/json" \
-d '{"title": "What if...?", "author": "Marvel"}'
