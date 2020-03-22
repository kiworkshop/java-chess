package view.output;

import controller.ChessMoveResponse;
import model.chessDomain.ChessBoard;
import model.chessDomain.ChessGame;
import model.chessDomain.Position;
import model.chessDomain.pieces.ChessPiece;
import model.chessDomain.pieces.PieceColor;
import model.chessDomain.pieces.PieceType;

public class ChessOutputConsole implements ChessOutput {
    @Override
    public void outputStartChessGameNotice() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    @Override
    public void outputChessBoard(ChessGame chessGame, ChessMoveResponse errorMessage) {
        System.out.println();
        for (int y = 0; y < ChessBoard.GRID_SIZE; y++) {
            System.out.println("--------------------------------------");
            for (int x = 0; x < ChessBoard.GRID_SIZE; x++) {
                System.out.print("| ");
                ChessPiece piece = chessGame.getChessBoard().get(Position.of(x, y));
                if (piece == null) {
                    System.out.print("　 "); // the first space is a special space
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.PAWN) {
                    System.out.print("♙ ");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.ROOK) {
                    System.out.print("♖ ");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.KNIGHT) {
                    System.out.print("♘ ");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.BISHOP) {
                    System.out.print("♗ ");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.QUEEN) {
                    System.out.print("♕ ");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.KING) {
                    System.out.print("♔ ");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.PAWN) {
                    System.out.print("♟ ");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.ROOK) {
                    System.out.print("♜ ");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.KNIGHT) {
                    System.out.print("♞ ");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.BISHOP) {
                    System.out.print("♝ ");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.QUEEN) {
                    System.out.print("♛ ");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.KING) {
                    System.out.print("♚ ");
                }
            }
            System.out.println("| (" + (ChessBoard.GRID_SIZE - y) + ")");

        }
        System.out.println("--------------------------------------");
        System.out.println(" (a)  (b)  (c) (d)  (e)  (f) (g)  (h)\n");

        // print error Message
        if (errorMessage == ChessMoveResponse.BLACK_TURN) {
            System.out.println("It's black turn.");
        } else if (errorMessage == ChessMoveResponse.WHITE_TURN) {
            System.out.println("It's white turn.");
        } else if (errorMessage == ChessMoveResponse.INPUT_OUT_OF_BOUND) {
            System.out.println("Wrong input (position out of board).");
        } else if (errorMessage == ChessMoveResponse.SOURCE_NOT_VALID) {
            System.out.println("Wrong input (no piece at the source position).");
        } else if (errorMessage == ChessMoveResponse.WRONG_MOVE) {
            System.out.println("Piece can not move to the destination.");
        }
        System.out.println();

        if (chessGame.getTurn() == PieceColor.WHITE) {
            System.out.println("-> White turn");
        } else if (chessGame.getTurn() == PieceColor.BLACK) {
            System.out.println("-> Black turn");
        }
    }
}
