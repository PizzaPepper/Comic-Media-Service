#!/bin/bash

curl -X POST localhost:8080/api/comic/aaa16568-2100-4d43-b232-75f9f7efb06c/chapters \
-H "Content-Type: application/json" \
-d '{"title": "Spider-Girl", "number": 105}'