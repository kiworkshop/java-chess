package ChessPieces;

import java.util.HashMap;

public class ChessPieces {
    public HashMap<ChessPiecePosition, ChessPiece> chessPieces ;

    public ChessPieces(HashMap<ChessPiecePosition, ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public static ChessPieces makeInitialSetting() {
        return new ChessPieces(InitialSetting.makeInitialSettings());
    }

    public ChessPiece getPieceByPosition(ChessPiecePosition position) {
        return chessPieces.get(position);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 8; i > 0; i--) {
            for (int j = 1; j < 9; j++) {
                ChessPiece chessPiece = chessPieces.get(ChessPiecePosition.getPositionByArray(j, i));
                if (chessPiece != null) {
                    sb.append(chessPiece.getPrintCode());
                }
                if (chessPiece == null) {
                    if ((i + j) % 2 == 0) {
                        sb.append("□ ");
                    }
                    if ((i + j) % 2 == 1) {
                        sb.append("■ ");
                    }
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void move(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {
        if (chessPieces.get(toPosition) != null) {
            chessPieces.remove(toPosition);
        }
        if (chessPieces.get(fromPosition).isMovable(fromPosition, toPosition)) {
            chessPieces.put(toPosition, chessPieces.get(fromPosition));
            chessPieces.remove(fromPosition);
        }
    }
}
