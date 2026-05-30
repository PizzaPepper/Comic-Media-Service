#!/bin/bash

pages=()

for file in ~/Desktop/"What If...? (1989) - Issue #105"/*.jpg; do
  pages+=(-F "pages=@${file}")
done

# 2. Run your curl command using the array
curl -X POST "localhost:8080/api/comic/aaa16568-2100-4d43-b232-75f9f7efb06c/chapters/960d5379-f134-4767-b7b7-0b48bd0acaaa" \
-H "X-Scan-Group-Id: c1afee26-910b-4bf1-a77b-cbcbc21706d3" \
"${pages[@]}"