package Service;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.edit.EditRequest;

import java.util.ArrayList;

public class ChatGPTService {
    private static final String instructionForEdit = "summarize into one sentence";
    private static final String apiKey = ".";
    public static String EditInputText(String inputText){
        try{
            OpenAiService service = new OpenAiService(apiKey);
            EditRequest request = EditRequest.builder()
                    .model("text-davinci-edit-001")
                    .input(inputText)
                    .instruction(instructionForEdit)
                    .build();
            String msg = service.createEdit(request).getChoices().get(0).getText();
            return msg;

        }catch (Exception e){
            throw new IllegalArgumentException("request edit timeout");
        }
    }

    public static String ChatInputText(String inputText,String instruction){
        try{
            OpenAiService service = new OpenAiService(apiKey);
            ChatMessage message = new ChatMessage("user",instruction+":"+inputText);
            ArrayList<ChatMessage> input= new ArrayList<>();
            input.add(message);
            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .messages(input)
                    .n(1)
                    .build();
            String msg = service.createChatCompletion(request).getChoices().get(0).getMessage().getContent();
            return msg;
        }catch (Exception e){
            throw new IllegalArgumentException("request chat timeout");
        }

    }
}