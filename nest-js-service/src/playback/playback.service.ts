import { Injectable } from "@nestjs/common";
import { ConfigService } from "@nestjs/config";
import { createHmac, randomUUID } from "node:crypto";

import { CatalogService } from "../catalog/catalog.service";
import { StartPlaybackDto } from "./dto/start-playback.dto";

@Injectable()
export class PlaybackService {
  constructor(
    private readonly catalogService: CatalogService,
    private readonly configService: ConfigService
  ) {}

  start(input: StartPlaybackDto) {
    const video = this.catalogService.findById(input.videoId);
    const sessionId = randomUUID();
    const expiresAt = new Date(Date.now() + 15 * 60 * 1000).toISOString();

    const baseUrl =
      this.configService.get<string>("LOCAL_MEDIA_BASE_URL") ??
      "http://localhost:3001/media";
    const playbackUrl = `${baseUrl}/${video.source.key}`;

    const payload = Buffer.from(
      JSON.stringify({
        sessionId,
        userId: input.userId,
        videoId: input.videoId,
        expiresAt,
      })
    ).toString("base64url");
    const secret =
      this.configService.get<string>("PLAYBACK_TOKEN_SECRET") ?? "dev-secret";
    const signature = createHmac("sha256", secret)
      .update(payload)
      .digest("base64url");

    return {
      sessionId,
      token: `${payload}.${signature}`,
      stream: { type: video.source.type, playbackUrl },
      expiresAt,
    };
  }
}
