export type VideoSource = {
  key: string;
  type: 'hls' | 'mp4';
};

export type VideoRecord = {
  id: string;
  title: string;
  description: string;
  durationSeconds: number;
  genre: string;
  releaseYear: number;
  source: VideoSource;
};

export const VIDEO_CATALOG: VideoRecord[] = [
  {
    id: 'sintel-trailer',
    title: 'Sintel Trailer',
    description: 'Open movie trailer often used for streaming demos',
    durationSeconds: 312,
    genre: 'Animation',
    releaseYear: 2010,
    source: {
      type: 'hls',
      key: 'sintel/hls/playlist.m3u8',
    },
  },
  {
    id: 'big-buck-bunny',
    title: 'Big Buck Bunny',
    description: 'Open movie sample clip for testing playback flows',
    durationSeconds: 596,
    genre: 'Animation',
    releaseYear: 2008,
    source: {
      type: 'mp4',
      key: 'big-buck-bunny/video.mp4',
    },
  },
];
