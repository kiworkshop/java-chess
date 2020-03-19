package ChessGame.ChessPieces;

import ChessGame.ConsoleOutput;
import ChessGame.Exception.NotYourTurnException;
import ChessGame.Exception.SamePositionException;
import ChessGame.Exception.TakenPositionException;
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

    public static ChessPieces makeTestSetting() {
        return new ChessPieces(TestSetting.makeInitialSettings());
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

    public void move(PlayerNumber playerNumber, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws Exception {    //TODO 검증들이 지저분하니 함수로 빼버리기
        validateMovePosition(playerNumber, fromPosition, toPosition);
        chessPieces.get(fromPosition).validateEachPieceMove(chessPieces, fromPosition, toPosition);

        chessPieces.put(toPosition, chessPieces.get(fromPosition));
        chessPieces.remove(fromPosition);
    }

    private void validateMovePosition(PlayerNumber playerNumber, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws Exception {
        validateTurn(playerNumber, fromPosition);
        validateSamePosition(fromPosition, toPosition);
        validateTakenPosition(playerNumber, toPosition);
    }

    private void validateTakenPosition(PlayerNumber playerNumber, ChessPiecePosition toPosition) throws TakenPositionException {
        if (chessPieces.get(toPosition) != null && chessPieces.get(toPosition).getPlayerNumber() == playerNumber.getPlayerNumber()) {
            throw new TakenPositionException();
        }
    }

    private void validateSamePosition(ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws SamePositionException {
        if (fromPosition.equals(toPosition)) {
            ConsoleOutput.printSamePositionExceptionMessage();
            throw new SamePositionException();
        }
    }

    private void validateTurn(PlayerNumber playerNumber, ChessPiecePosition fromPosition) throws Exception {
        if (chessPieces.get(fromPosition).getPlayerNumber() != playerNumber.getPlayerNumber()) {
            ConsoleOutput.printNotYourTurnExceptionMessage();
            throw new NotYourTurnException();
        }
    }
}
