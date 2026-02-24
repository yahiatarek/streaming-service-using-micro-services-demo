# Netflix Demo - NestJS Service (Step 1)

This service implements the first backend piece of a Netflix-like system:

- `Catalog API`: list videos and metadata
- `Playback API`: issue short-lived playback URLs for S3 objects

## Why this is the first step

A streaming platform usually separates concerns:

- `Catalog service`: what titles exist, metadata, availability
- `Playback/entitlement service`: who can watch what, return signed URL/token
- `Streaming CDN/object storage`: actual HLS/MP4 bytes

This project starts with `catalog + playback gateway`.

## Endpoints

- `GET /api/health`
- `GET /api/catalog`
- `GET /api/catalog/:videoId`
- `POST /api/playback/token`
- `GET /api/playback/:videoId?userId=...`

### Example request

```bash
curl -X POST http://localhost:3001/api/playback/token \
  -H 'Content-Type: application/json' \
  -d '{"videoId":"sintel-trailer","userId":"u-123"}'
```

## Environment

Copy `.env.example` to `.env` and adjust values.

Required for signed URLs:

- `AWS_REGION`
- `S3_BUCKET_NAME`
- valid AWS credentials in environment/profile

Optional:

- `S3_KEY_PREFIX`
- `S3_PUBLIC_BASE_URL` (fallback when objects are public and you do not want signing)

## Run

```bash
npm install
npm run start:dev
```

## Current catalog data

The current data is static in `src/catalog/video-catalog.ts` for learning speed.
Later we can move it to Postgres + Spring Boot microservice integration.
