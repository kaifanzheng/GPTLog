package Service;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AudioServiceTest {
    @Test
    void convertAudioToStringWorkSuccessfully(){
        // /Users/kaifan/Documents/GitHub/GPTLog/src/test/java/FilesForTest/python_example_test.wav
        String path = "../GPTLog/src/test/java/FilesForTest/python_example_test.wav";
        String result = AudioService.convertAudioToString(path);
        assertNotNull(result);
    }

    @Test
    void convertAudioToStringWorkConvertDifferentFormatFileSuccessfully(){
        String path = "../GPTLog/src/test/java/FilesForTest/life.wav";
        String result = AudioService.convertAudioToString(path);
        assertNotNull(result);
    }

    @Test
    void separateVideoWithAudioWorkSuccessfully(){
        String path = "/Users/kaifan/Documents/GitHub/GPTLog/src/test/java/FilesForTest/TestVideo.mp4";
        AudioService.separateVideoWithAudio(path);
    }

}