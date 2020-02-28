import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    void setUp(){
        chessGame = new ChessGame();
    }
    @Test
    void 체스게임생성_NotNullTest(){
        assertNotNull(chessGame);
    }

    @Test
    void 체스게임판에게_초기세팅시키기_Test(){
        chessGame.boardInitialize();
    }

    @Test
    void 현재게임판을_출력하라_Test(){
        chessGame.boardInitialize();
        chessGame.boardPrint();
    }
}
