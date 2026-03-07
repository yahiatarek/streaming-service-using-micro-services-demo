import { IsNotEmpty, IsString } from "class-validator";

export class StartPlaybackDto {
  @IsString()
  @IsNotEmpty()
  videoId!: string;

  @IsString()
  @IsNotEmpty()
  userId!: string;
}
