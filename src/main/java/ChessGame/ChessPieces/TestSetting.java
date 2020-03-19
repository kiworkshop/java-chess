package ChessGame.ChessPieces;

import ChessGame.PlayerNumber;

import java.util.HashMap;

public enum TestSetting {    //대충 백퀸 주위로 흑폰이 팔방 둘러싸고 있고 백킹 앞에 흑킹이 있는 그런 모양
    WHITE_KING(King.setPiece(PlayerNumber.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(6, 1)),
    BLACK_KING(King.setPiece(PlayerNumber.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(6, 2)),
    WHITE_QUEEN(Queen.setPiece(PlayerNumber.PLAYER_NUMBER_ONE), ChessPiecePosition.getPositionByArray(4, 5)),
    BLACK_PAWN_1(Pawn.setPiece(PlayerNumber.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(3, 4)),
    BLACK_PAWN_2(Pawn.setPiece(PlayerNumber.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(3, 5)),
    BLACK_PAWN_3(Pawn.setPiece(PlayerNumber.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(3, 6)),
    BLACK_PAWN_4(Pawn.setPiece(PlayerNumber.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(4, 4)),
    BLACK_PAWN_5(Pawn.setPiece(PlayerNumber.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(4, 6)),
    BLACK_PAWN_6(Pawn.setPiece(PlayerNumber.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(5, 4)),
    BLACK_PAWN_7(Pawn.setPiece(PlayerNumber.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(5, 5)),
    BLACK_PAWN_8(Pawn.setPiece(PlayerNumber.PLAYER_NUMBER_TWO), ChessPiecePosition.getPositionByArray(5, 6));

    private ChessPiece chessPiece;
    private ChessPiecePosition initialPosition;

    TestSetting(ChessPiece chessPiece, ChessPiecePosition initialPosition) {
        this.chessPiece = chessPiece;
        this.initialPosition = initialPosition;
    }

    public static HashMap<ChessPiecePosition, ChessPiece> makeInitialSettings() {
        HashMap<ChessPiecePosition, ChessPiece> chessPieces = new HashMap<ChessPiecePosition, ChessPiece>();
        for (TestSetting initialSetting : TestSetting.values()) {
            chessPieces.put(initialSetting.initialPosition, initialSetting.chessPiece);
        }
        return chessPieces;
    }
}
