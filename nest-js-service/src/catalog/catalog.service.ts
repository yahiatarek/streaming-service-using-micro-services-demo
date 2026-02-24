import { Injectable, NotFoundException } from '@nestjs/common';

import { VIDEO_CATALOG, VideoRecord } from './video-catalog';

@Injectable()
export class CatalogService {
  findAll(): VideoRecord[] {
    return VIDEO_CATALOG;
  }

  findById(videoId: string): VideoRecord {
    const video = VIDEO_CATALOG.find((item) => item.id === videoId);

    if (!video) {
      throw new NotFoundException(`Video with id '${videoId}' was not found`);
    }

    return video;
  }
}
