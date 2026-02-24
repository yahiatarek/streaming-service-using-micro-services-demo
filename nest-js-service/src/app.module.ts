import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config';

import { CatalogModule } from './catalog/catalog.module';
import { HealthModule } from './health/health.module';
import { PlaybackModule } from './playback/playback.module';

@Module({
  imports: [ConfigModule.forRoot({ isGlobal: true }), HealthModule, CatalogModule, PlaybackModule],
})
export class AppModule {}
