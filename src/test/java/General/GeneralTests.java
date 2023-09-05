package General;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneralTests {
    @Test
    void TestCurrentTimeAsId(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String id = now.format(formatter).toString();
        assertEquals(id,id);
        System.out.println("ID: " + id);
    }

    @Test
    void TestFileRoot(){
        String filePath = "../GPTLog/src/test/java/FilesForTest/python_example_test.wav";

        File file = new File(filePath);
        String rootPath = file.getParent();
        System.out.println("rootPath: "+rootPath);
    }

    @Test
    void TestAudioFormat(){
        try{
            String path = "/Users/kaifan/Documents/GitHub/GPTLog/src/test/java/FilesForTest/python_example_test.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            AudioFormat sourceFormat = audioInputStream.getFormat();
            System.out.println(sourceFormat.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void CreateNewWavFile() throws IOException {
        int sampleRate = 44100;  // 44.1 kHz
        int bitDepth = 16;       // 16-bit signed PCM
        int numChannels = 2;     // stereo

        AudioFormat format = new AudioFormat(sampleRate, bitDepth, numChannels, true, false);
        AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(new byte[0]), format, 0);

        AudioSystem.write(stream, AudioFileFormat.Type.WAVE, new File("../GPTLog/src/test/java/FilesForTest/new_file.wav"));
    }
}
