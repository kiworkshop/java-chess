package chess.util;

import chess.domain.ChessPiece;
import chess.domain.ChessTeam;
import chess.domain.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessPieceFactory {

    public static List<ChessPiece> makePieces() {
        List<ChessPiece> chessPieces = new ArrayList<>();
        for (ChessTeam chessTeam : ChessTeam.values()) {
            makePawns(chessTeam, chessPieces);
            makeBishops(chessTeam, chessPieces);
            makeKnights(chessTeam, chessPieces);
            makeRooks(chessTeam, chessPieces);
            makeKingAndQueen(chessTeam, chessPieces);
        }
        return chessPieces;
    }

    private static void makePawns(ChessTeam chessTeam, List<ChessPiece> chessPieces) {
        for (int nthRow = 1; nthRow < 9; nthRow++) {
            chessPieces.add(Pawn.from(chessTeam, nthRow));
        }
    }

    private static void makeBishops(ChessTeam chessTeam, List<ChessPiece> chessPieces) {
        chessPieces.addAll(Arrays.asList(Bishop.from(chessTeam, 3), Bishop.from(chessTeam, 6)));
    }

    private static void makeKnights(ChessTeam chessTeam, List<ChessPiece> chessPieces) {
        chessPieces.addAll(Arrays.asList(Knight.from(chessTeam, 2), Knight.from(chessTeam, 7)));
    }

    private static void makeRooks(ChessTeam chessTeam, List<ChessPiece> chessPieces) {
        chessPieces.addAll(Arrays.asList(Rook.from(chessTeam, 1), Rook.from(chessTeam, 8)));
    }

    private static void makeKingAndQueen(ChessTeam chessTeam, List<ChessPiece> chessPieces) {
        chessPieces.addAll(Arrays.asList(King.from(chessTeam, 5), Queen.from(chessTeam, 4)));
    }
}

