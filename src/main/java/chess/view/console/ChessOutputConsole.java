package chess.view.console;

import chess.controller.dto.ChessResponse;
import chess.view.ChessOutput;

public class ChessOutputConsole implements ChessOutput {
    public static final int GRID_SIZE = 8;

    @Override
    public void notifyChessStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    @Override
    public void notifyChessEnd() {
        System.out.println("체스 게임을 끝냅니다.");
    }

    @Override
    public void showChessBoard(ChessResponse response) {
        switch (response.getStatus()) {
            case SUCCESS:
                printChessBoard(response.getPieces());
                printTurn(response.getTurn());
                break;
            case WHITE_WIN:
            case BLACK_WIN:
                printChessBoard(response.getPieces());
                printWinner(response.getStatus());
                break;
            case ERROR_GAME_NOT_STARTED:
            case ERROR_BLACK_TURN:
            case ERROR_WHITE_TURN:
            case ERROR_WRONG_MOVE:
            case ERROR_INPUT_OUT_OF_BOUND:
            case ERROR_SOURCE_NOT_VALID:
                printErrorMessage(response.getStatus());
                break;
        }
    }

    private void printChessBoard(ChessResponse.Piece[][] pieces) {
        System.out.println();
        for (int y = 0; y < GRID_SIZE; y++) {
            System.out.println("--------------------------------------");
            for (int x = 0; x < GRID_SIZE; x++) {
                System.out.print("| ");
                ChessResponse.Piece piece = pieces[y][x];
                switch (piece) {
                    case EMPTY:
                        System.out.print("　 "); // the first space is a special space
                        break;

                    case WHITE_PAWN:
                        System.out.print("♙ ");
                        break;
                    case WHITE_ROOK:
                        System.out.print("♖ ");
                        break;
                    case WHITE_KNIGHT:
                        System.out.print("♘ ");
                        break;
                    case WHITE_BISHOP:
                        System.out.print("♗ ");
                        break;
                    case WHITE_KING:
                        System.out.print("♔ ");
                        break;
                    case WHITE_QUEEN:
                        System.out.print("♕ ");
                        break;

                    case BLACK_PAWN:
                        System.out.print("♟ ");
                        break;
                    case BLACK_ROOK:
                        System.out.print("♜ ");
                        break;
                    case BLACK_KNIGHT:
                        System.out.print("♞ ");
                        break;
                    case BLACK_BISHOP:
                        System.out.print("♝ ");
                        break;
                    case BLACK_KING:
                        System.out.print("♚ ");
                        break;
                    case BLACK_QUEEN:
                        System.out.print("♛ ");
                        break;
                }
            }
            System.out.println("| (" + (GRID_SIZE - y) + ")");
        }
        System.out.println("--------------------------------------");
        System.out.println(" (a)  (b)  (c) (d)  (e)  (f) (g)  (h)\n");
    }

    private void printTurn(ChessResponse.Turn turn) {
        switch (turn) {
            case WHITE:
                System.out.println("-> White turn");
                break;
            case BLACK:
                System.out.println("-> Black turn");
                break;
        }
    }

    private void printWinner(ChessResponse.Status status) {
        switch (status) {
            case WHITE_WIN:
                System.out.println("-> White win!!!");
                break;
            case BLACK_WIN:
                System.out.println("-> Black win!!!");
                break;
        }
    }

    private void printErrorMessage(ChessResponse.Status status) {
        switch (status) {
            case ERROR_GAME_NOT_STARTED:
                System.out.println("Please start the game first.");
                break;
            case ERROR_BLACK_TURN:
                System.out.println("It's black turn.");
                break;
            case ERROR_WHITE_TURN:
                System.out.println("It's white turn.");
                break;
            case ERROR_WRONG_MOVE:
                System.out.println("Piece cannot move to the destination.");
                break;
            case ERROR_INPUT_OUT_OF_BOUND:
                System.out.println("Wrong input (position out of board).");
                break;
            case ERROR_SOURCE_NOT_VALID:
                System.out.println("Wrong input (no piece at the source position).");
                break;
        }
        System.out.println();
    }
}
