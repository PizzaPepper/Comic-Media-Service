#!/bin/bash

# 1. Create a comic 

ID_COMIC=$(curl -X POST localhost:8080/api/comic \
-s \
-H "Content-Type: application/json" \
-d '{"title": "What if...?", "author": "Marvel"}' | jq -r '.data.id' )

echo "Comic [$ID_COMIC]"

# 2. Create a chapter

ID_CHAPTER=$(curl -X POST "localhost:8080/api/comic/$ID_COMIC/chapters" \
-s \
-H "Content-Type: application/json" \
-d '{"title": "Spider-Girl", "number": 105}' | jq -r '.data.id')

echo "Chapter [$ID_CHAPTER]"

# 3. Upload pages

pages=()

for file in ~/Desktop/"What If...? (1989) - Issue #105"/*.jpg; do
  pages+=(-F "pages=@${file}")
done

curl -X POST "localhost:8080/api/comic/$ID_COMIC/chapters/$ID_CHAPTER" \
-H "X-Scan-Group-Id: c1afee26-910b-4bf1-a77b-cbcbc21706d3" \
"${pages[@]}"