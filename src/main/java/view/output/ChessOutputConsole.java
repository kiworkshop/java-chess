package view.output;

import model.chessDomain.ChessBoard;
import model.chessDomain.ChessPiece;
import model.chessDomain.PieceColor;
import model.chessDomain.PieceType;

public class ChessOutputConsole implements ChessOutput {
    @Override
    public void printStartChessGame() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    @Override
    public void printChessBoard(ChessBoard chessBoard) {
        for (int y = 0; y < ChessBoard.GRID_SIZE; y++) {
            for (int x = 0; x < ChessBoard.GRID_SIZE; x++) {
                ChessPiece piece = chessBoard.get(y, x);
                if (piece == null) {
                    System.out.print(".");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.PAWN) {
                    System.out.print("P");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.ROOK) {
                    System.out.print("R");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.KNIGHT) {
                    System.out.print("N");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.BISHOP) {
                    System.out.print("B");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.QUEEN) {
                    System.out.print("Q");
                } else if (piece.getColor() == PieceColor.WHITE && piece.getType() == PieceType.KING) {
                    System.out.print("K");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.PAWN) {
                    System.out.print("p");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.ROOK) {
                    System.out.print("r");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.KNIGHT) {
                    System.out.print("n");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.BISHOP) {
                    System.out.print("b");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.QUEEN) {
                    System.out.print("q");
                } else if (piece.getColor() == PieceColor.BLACK && piece.getType() == PieceType.KING) {
                    System.out.print("k");
                }
            }
            System.out.println();
        }
    }
}
