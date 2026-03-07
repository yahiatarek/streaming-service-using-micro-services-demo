import { Body, Controller, Post } from "@nestjs/common";

import { StartPlaybackDto } from "./dto/start-playback.dto";
import { PlaybackService } from "./playback.service";

@Controller("playback")
export class PlaybackController {
  constructor(private readonly playbackService: PlaybackService) {}

  @Post("start")
  start(@Body() dto: StartPlaybackDto) {
    return this.playbackService.start(dto);
  }
}
