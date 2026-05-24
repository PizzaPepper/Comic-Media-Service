#!/bin/bash

curl -X POST localhost:2426/api/comic \
-H "Content-Type: application/json" \
-d '{"title": "What if...?", "author": "Marvel"}'
