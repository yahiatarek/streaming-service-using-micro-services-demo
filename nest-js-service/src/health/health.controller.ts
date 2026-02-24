import { Controller, Get } from '@nestjs/common';

@Controller('health')
export class HealthController {
  @Get()
  health() {
    return {
      status: 'ok',
      service: 'netflix-demo-nest-service',
      time: new Date().toISOString(),
    };
  }
}
