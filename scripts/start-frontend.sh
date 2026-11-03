#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"
cd "$ROOT/masterpiecemart-master"
if [[ ! -d node_modules ]]; then npm ci; fi
exec npm run dev -- --host 127.0.0.1 --port 5173 --strictPort
