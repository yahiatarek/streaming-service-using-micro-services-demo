import { Controller, Get, Param } from '@nestjs/common';

import { CatalogService } from './catalog.service';

@Controller('catalog')
export class CatalogController {
  constructor(private readonly catalogService: CatalogService) {}

  @Get()
  listCatalog() {
    return this.catalogService.findAll();
  }

  @Get(':videoId')
  getVideo(@Param('videoId') videoId: string) {
    return this.catalogService.findById(videoId);
  }
}
