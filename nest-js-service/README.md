# Netflix Demo - NestJS Service (Step 1)

This service implements the first backend piece of a Netflix-like system:

- `Catalog API`: list videos and metadata
- `Playback API`: create short-lived playback sessions for CDN-hosted videos

## Why this is the first step

A streaming platform usually separates concerns:

- `Catalog service`: what titles exist, metadata, availability
- `Playback/entitlement service`: who can watch what, create session/token
- `Streaming CDN`: actual HLS/MP4 bytes

This project starts with `catalog + playback gateway`.

## Endpoints

- `GET /api/health`
- `GET /api/catalog`
- `GET /api/catalog/:videoId`
- `POST /api/playback/start`
- `POST /api/playback/token` (compat alias)
- `GET /api/playback/:videoId?userId=...`
- `GET /api/playback/sessions/:sessionId`

### Example request

```bash
curl -X POST http://localhost:3001/api/playback/start \
  -H 'Content-Type: application/json' \
  -d '{"videoId":"sintel-trailer","userId":"u-123"}'
```

## Environment

Copy `.env.example` to `.env` and adjust values.

Required:

- `PLAYBACK_SESSION_TTL_SECONDS`
- `PLAYBACK_TOKEN_SECRET`

## Step-by-step learning flow

1. Call `GET /api/catalog` and inspect each video's `source.type`.
2. Call `POST /api/playback/start` with `videoId` and `userId`.
3. Use `stream.playbackUrl` in a player (`hls.js` for HLS).
4. Track `sessionId` and check it with `GET /api/playback/sessions/:sessionId`.
5. Later, replace in-memory session storage with Redis/Postgres.

## Run

```bash
npm install
npm run start:dev
```

## Current catalog data

The current data is static in `src/catalog/video-catalog.ts` for learning speed.
Later we can move it to Postgres + Spring Boot microservice integration.
