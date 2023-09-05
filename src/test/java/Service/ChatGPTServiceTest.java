package Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatGPTServiceTest {
    @Test
    void ChatGETServiceRequestWorkSuccessfully(){
        try {
            String input = "While the City of Montreal has signed a declaration to revitalize Montreal-Est (Government of Québec, 2018), a key challenge has been urban mobility in this low-income area (Guilbault, 2017).  As a result of the lack of mobility options, Montreal-Est residents must be more dependent on cars which account for around 25% of their income (Lavin, 2023; Statistics Canada, 2016). Mobility growth will allow Montreal-Est residents to be better connected to employers, education and greenspaces. This will reduce transportation costs and lessen the inequalities faced by this community. ";
            String output = ChatGPTService.EditInputText(input);
            System.out.println("ChatGETServiceRequestWorkSuccessfully log:");
            System.out.println(output);
            assertNotNull(output);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    void ChatInputTextWorkSuccessfully(){
        try{
            String input = "While the City of Montreal has signed a declaration to revitalize Montreal-Est (Government of Québec, 2018), a key challenge has been urban mobility in this low-income area (Guilbault, 2017).  As a result of the lack of mobility options, Montreal-Est residents must be more dependent on cars which account for around 25% of their income (Lavin, 2023; Statistics Canada, 2016). Mobility growth will allow Montreal-Est residents to be better connected to employers, education and greenspaces. This will reduce transportation costs and lessen the inequalities faced by this community. ";
            String instruction = "list some label for this sentence";
            String output = ChatGPTService.ChatInputText(input,instruction);
            System.out.println("ChatInputTextWorkSuccessfully slog:");
            System.out.println(output);
            assertNotNull(output);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}