#!/bin/bash

curl -X POST localhost:2426/api/comic/c49d3c47-d478-482c-a3ea-ea396e4494e0/chapters \
-H "Content-Type: application/json" \
-d '{"title": "Spider-Girl", "number": 105}'