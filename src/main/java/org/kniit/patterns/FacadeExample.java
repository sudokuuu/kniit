package org.kniit.patterns;

public class FacadeExample {

    public static void main(String[] args) {
        VideoConverterFacade converter = new VideoConverterFacade();

        converter.convert("movie.avi", "mp4");
    }
}

class VideoFile {
    private final String name;
    public VideoFile(String name) { this.name = name; }
    public String getName() { return name; }
}

class Codec {}
class MPEG4Codec extends Codec {}
class H264Codec extends Codec {}

class CodecFactory {
    public static Codec extract(VideoFile file) {
        System.out.println("Определяем кодек для " + file.getName());
        return new MPEG4Codec();
    }
}

class AudioMixer {
    public void fix(VideoFile file) {
        System.out.println("Обработка аудио для " + file.getName());
    }
}

class SubtitleService {
    public void load(String fileName) {
        System.out.println("Загрузка субтитров из " + fileName);
    }
}

class VideoConverterFacade {

    public void convert(String sourceFileName, String targetFormat) {
        System.out.println("=== Конвертация видео через фасад ===");

        VideoFile file = new VideoFile(sourceFileName);

        Codec sourceCodec = CodecFactory.extract(file);
        Codec destCodec = switch (targetFormat.toLowerCase()) {
            case "mp4" -> new MPEG4Codec();
            case "h264" -> new H264Codec();
            default -> throw new IllegalArgumentException("Неизвестный формат: " + targetFormat);
        };

        System.out.println("Преобразуем " + sourceFileName +
            " из " + sourceCodec.getClass().getSimpleName() +
            " в " + destCodec.getClass().getSimpleName());

        AudioMixer mixer = new AudioMixer();
        mixer.fix(file);

        SubtitleService subtitles = new SubtitleService();
        subtitles.load(sourceFileName.replaceFirst("\\.[^.]+$", ".srt"));

        System.out.println("Сохраняем итоговый файл в формате " + targetFormat);
    }
}


