package ChessGame.ChessPieces;

import ChessGame.ConsoleOutput;
import ChessGame.PlayerNumber;

import java.util.HashMap;

public class ChessPieces {
    public static final int CHESS_BOARD_SIZE = 8;
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
        for (int i = CHESS_BOARD_SIZE; i >= 1; i--) {
            for (int j = 1; j <= CHESS_BOARD_SIZE; j++) {
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

    public void move(PlayerNumber playerNumber, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) {    //TODO 검증들이 지저분하니 함수로 빼버리기
        if (chessPieces.get(fromPosition).getPlayerNumber() != playerNumber.getPlayerNumber()) {
            ConsoleOutput.printNotYourTurnError();
            return;
        }
        if (fromPosition.equals(toPosition)) {
            ConsoleOutput.printSamePositionErrorMessage();
            return;
        }
        if (chessPieces.get(toPosition) != null && chessPieces.get(toPosition).getPlayerNumber() == playerNumber.getPlayerNumber()) {
            return;    //TODO Playernumber를 없애고, 아예 Player로 가야할듯..
        }
        if (chessPieces.get(fromPosition).isMovable(fromPosition, toPosition)) {
            if (chessPieces.get(toPosition) != null) {
                chessPieces.remove(toPosition);
            }
            chessPieces.put(toPosition, chessPieces.get(fromPosition));
            chessPieces.remove(fromPosition);
        }
    }
}
