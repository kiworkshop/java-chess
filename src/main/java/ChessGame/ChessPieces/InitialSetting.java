package ChessGame.ChessPieces;

import java.util.HashMap;

public enum InitialSetting {
    WHITE_KING(King.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(4, 1)),
    BLACK_KING(King.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(4, 8)),
    WHITE_QUEEN(Queen.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(5, 1)),
    BLACK_QUEEN(Queen.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(5, 8)),
    WHITE_ROOK_1(Rook.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(1, 1)),
    WHITE_ROOK_2(Rook.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(8, 1)),
    BLACK_ROOK_1(Rook.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(1, 8)),
    BLACK_ROOK_2(Rook.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(8, 8)),
    WHITE_BISHOP_1(Bishop.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(3, 1)),
    WHITE_BISHOP_2(Bishop.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(6, 1)),
    BLACK_BISHOP_1(Bishop.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(3, 8)),
    BLACK_BISHOP_2(Bishop.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(6, 8)),
    WHITE_KNIGHT_1(Knight.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(2, 1)),
    WHITE_KNIGHT_2(Knight.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(7, 1)),
    BLACK_KNIGHT_1(Knight.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(2, 8)),
    BLACK_KNIGHT_2(Knight.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(7, 8)),
    WHITE_PAWN_1(Pawn.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(1, 2)),
    WHITE_PAWN_2(Pawn.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(2, 2)),
    WHITE_PAWN_3(Pawn.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(3, 2)),
    WHITE_PAWN_4(Pawn.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(4, 2)),
    WHITE_PAWN_5(Pawn.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(5, 2)),
    WHITE_PAWN_6(Pawn.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(6, 2)),
    WHITE_PAWN_7(Pawn.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(7, 2)),
    WHITE_PAWN_8(Pawn.setPiece(Constants.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(8, 2)),
    BLACK_PAWN_1(Pawn.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(1, 7)),
    BLACK_PAWN_2(Pawn.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(2, 7)),
    BLACK_PAWN_3(Pawn.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(3, 7)),
    BLACK_PAWN_4(Pawn.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(4, 7)),
    BLACK_PAWN_5(Pawn.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(5, 7)),
    BLACK_PAWN_6(Pawn.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(6, 7)),
    BLACK_PAWN_7(Pawn.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(7, 7)),
    BLACK_PAWN_8(Pawn.setPiece(Constants.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(8, 7));

    private ChessPiece chessPiece;
    private ChessPiecePosition initialPosition;

    InitialSetting(ChessPiece chessPiece, ChessPiecePosition initialPosition) {
        this.chessPiece = chessPiece;
        this.initialPosition = initialPosition;
    }

    public static HashMap<ChessPiecePosition, ChessPiece> makeInitialSettings() {
        HashMap<ChessPiecePosition, ChessPiece> chessPieces = new HashMap<ChessPiecePosition, ChessPiece>();
        for (InitialSetting initialSetting : InitialSetting.values()) {
            chessPieces.put(initialSetting.initialPosition, initialSetting.chessPiece);
        }
        return chessPieces;
    }

    private static class Constants {
        public static final int PLAYER_NUMBER_ONE = 1;
        public static final int PLAYER_NUMBER_TWO = 2;
    }
}
