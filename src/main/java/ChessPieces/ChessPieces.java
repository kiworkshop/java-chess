package ChessPieces;

import java.util.ArrayList;
import java.util.HashMap;

public class ChessPieces {
    private ArrayList<ChessPiece> chessPieces;

    public ChessPieces(ArrayList<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public static ChessPieces makeInitialSetting() {
        return new ChessPieces(InitialSetting.makeInitialSettings());
    }

    @Override
    public String toString() {
        HashMap<ChessPiecePosition, ChessPiece> positionToPiece = new HashMap<ChessPiecePosition, ChessPiece>();
        for (ChessPiece chessPiece : chessPieces) {
            positionToPiece.put(chessPiece.getPosition(), chessPiece);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 8; i > 0; i--) {
            for (int j = 1; j < 9; j++) {
                ChessPiece chessPiece = positionToPiece.get(ChessPiecePosition.getPositionByArray(j, i));
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

}
