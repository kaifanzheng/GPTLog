package Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.vosk.LogLevel;
import org.vosk.Recognizer;
import org.vosk.LibVosk;
import org.vosk.Model;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.javacv.*;


public class AudioService {
    private static String LanguageCode= "English";

    //input file has to be 16000 sample rate
    public static String convertAudioToString(String inputAudioFile ){
        LibVosk.setLogLevel(LogLevel.WARNINGS);
        String result = null;
        try {
            Model model = new Model("/Users/kaifan/Downloads/vosk-model-en-us-0.22-lgraph");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(inputAudioFile));
            AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,16000.0f,16,1,2,16000.0f,false);
            AudioInputStream ais = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
            Recognizer recognizer = new Recognizer(model, 16000);

            int nbytes;
            byte[] b = new byte[4096];
            while ((nbytes = ais.read(b)) >= 0) {
                if (recognizer.acceptWaveForm(b, nbytes)) {
                    //System.out.println(recognizer.getResult());
                } else {
                    //System.out.println(recognizer.getPartialResult());
                }
            }
            result = recognizer.getFinalResult();
            System.out.println(result);
        }catch (Exception e){
            System.out.println("audio converter issue:");
            System.out.println(e.getMessage());
        }
        return result;
    }



    public static String separateVideoWithAudio(String videoPath){
        File file = new File(videoPath);
        String rootPath = file.getParent();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String id = now.format(formatter).toString();
        String audioPath = rootPath+"/"+id+".wav";
        try {
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,16000.0f,16,1,2,16000.0f,false);
            AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(new byte[0]), format, 0);
            AudioSystem.write(stream, AudioFileFormat.Type.WAVE, new File(audioPath));

            

        } catch (Exception e) {
            System.out.println("separateVideoWithAudio problem: "+e.getMessage());
        }
        return id;
    }



}
