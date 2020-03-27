package ChessGame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMessageTest {


    @Test
    void testMakerUserMessage() {
        UserMessage.processInput("start");
    }
}