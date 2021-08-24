package chess.controller;

import chess.domain.position.Position;
import chess.domain.view.InputView;
import chess.domain.view.OutputView;
import chess.dto.ChessGameDto;
import chess.dto.ScoreDto;
import chess.service.ChessGameService;

public class ChessGameController {
    private final ChessGameService chessGameService;

    public ChessGameController() {
        this.chessGameService = new ChessGameService();
    }

    public void run() {
        OutputView.printStartMessage();

        while (isReady()) {
            try {
                String start = InputView.getUserInput();
                if (start.equals("start")) {
                    gameStart();
                }
                throw new IllegalArgumentException("체스 게임을 시작하려면 start를 입력해 주세요.");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

        while (isPlaying()) {
            try {
                String input = InputView.getUserInput();
                if (input.contains("move")) {
                    String[] moveInput = InputView.parseMoveInput(input);
                    movePiece(moveInput[0], moveInput[1]);
                    continue;
                }
                if (input.equals("status")) {
                    status();
                    continue;
                }
                if (input.equals("end")) {
                    gameEnd();
                    break;
                }
                throw new IllegalArgumentException("입력이 올바르지 않습니다.");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean isReady() {
        return chessGameService.isReady();
    }

    private void gameStart() {
        ChessGameDto chessGameDto = chessGameService.startGame();
        OutputView.printChessBoard(chessGameDto);
    }

    private boolean isPlaying() {
        return chessGameService.isPlaying();
    }

    private void movePiece(String source, String target) {
        ChessGameDto chessGameDto = chessGameService.movePiece(Position.from(source), Position.from(target));
        OutputView.printChessBoard(chessGameDto);
    }

    private void status() {
        ScoreDto scoreDto = chessGameService.status();
        OutputView.printScore(scoreDto);
    }

    private void gameEnd() {
        chessGameService.endGame();
    }
}
