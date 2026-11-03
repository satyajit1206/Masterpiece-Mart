#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
API="${API_BASE_URL:-http://localhost:8080}"
curl -fsS "$API/actuator/health" >/dev/null
seed() {
  curl -fsS "$API/api/allproducts" \
    -F "productName=$1" -F "artistName=Demo Studio" -F "artistId=1" \
    -F "price=$2" -F "productDescription=$3" \
    -F "file0=@$ROOT/masterpiecemart-master/public/$4;type=image/jpeg" \
    -F "file1=@$ROOT/masterpiecemart-master/public/$4;type=image/jpeg" \
    -F "file2=@$ROOT/masterpiecemart-master/public/$4;type=image/jpeg"
  echo
}
# Run once against a freshly started demo database; names must be unique.
seed "Quiet Horizons" 2400 "A warm study of colour and atmosphere. Sample catalogue entry for the portfolio demonstration." homeimg1.jpeg
seed "Colour Study" 1800 "A playful composition from the sample collection. Image supplied in the original project." prod4.jpg
seed "Still Moments" 3200 "A piece from the original project assets, presented as sample data for the demo." prodimg2.jpg
curl -fsS "$API/api/posts" -H 'Content-Type: application/json' -d '{"name":"Finding your first artwork","postedBy":"Demo Studio","content":"Start with the colours, subjects and textures you enjoy. Save a few pieces to your wishlist, compare them, and make space for a favourite. This is sample journal content for the portfolio demo.","img":"/homeimg1.jpeg","tags":["art","collecting"]}' >/dev/null
echo 'Demo collection and journal are ready.'
