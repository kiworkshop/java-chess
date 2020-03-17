import chess.Command;
import chess.controller.ChessRequest;
import chess.view.console.ConsoleQueryProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleQueryProcessorTest {

    @Test
    @DisplayName("커맨드만 들어오는 경우")
    void queryProcess1() {
        //given
        Command given = Command.START;
        //when
        ChessRequest chessRequest = ConsoleQueryProcessor.parse(given.name());
        //then
        assertThat(chessRequest.getCommand()).isEqualTo(given);
        assertThat(chessRequest.getParameters()).hasSize(0);
    }

    @Test
    @DisplayName("커맨드와 N개의 파라미터가 같이 들어오는 경우")
    void queryProcess2() {
        //given
        Command given = Command.START;
        //when
        ChessRequest chessRequest = ConsoleQueryProcessor.parse(given.name() + " hello world kiworkshop");
        //then
        assertThat(chessRequest.getCommand()).isEqualTo(Command.START);
        assertThat(chessRequest.getParameters()).hasSize(3);
        assertThat(chessRequest.getParameters().get(0)).isEqualTo("hello");
        assertThat(chessRequest.getParameters().get(1)).isEqualTo("world");
        assertThat(chessRequest.getParameters().get(2)).isEqualTo("kiworkshop");
    }

    @Test
    @DisplayName("커맨드가 안들어온느 경우")
    void queryProcess3() {
        //given
        //when
        ChessRequest chessRequest = ConsoleQueryProcessor.parse("");
        //then
        assertThat(chessRequest.getCommand()).isEqualTo(Command.UNKNOWN);
        assertThat(chessRequest.getParameters()).hasSize(0);
    }

    @Test
    @DisplayName("모르는 커맨드가 들어오는 경우")
    void queryProcess4() {
        //given
        //when
        ChessRequest chessRequest = ConsoleQueryProcessor.parse("kiworkshop");
        //then
        assertThat(chessRequest.getCommand()).isEqualTo(Command.UNKNOWN);
        assertThat(chessRequest.getParameters()).hasSize(0);
    }
}