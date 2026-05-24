#!/bin/bash

pages=()

for file in ~/Desktop/"What If...? (1989) - Issue #105"/*.jpg; do
  pages+=(-F "pages=@${file}")
done

# 2. Run your curl command using the array
curl -X POST "localhost:2426/api/comic/c49d3c47-d478-482c-a3ea-ea396e4494e0/chapters/45658333-5329-487b-81c0-1bfd64bc2f31" \
-H "X-Scan-Group-Id: c1afee26-910b-4bf1-a77b-cbcbc21706d3" \
"${pages[@]}"