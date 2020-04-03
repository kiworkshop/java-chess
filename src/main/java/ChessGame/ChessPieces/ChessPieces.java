package ChessGame.ChessPieces;

import ChessGame.ConsoleOutput;
import ChessGame.Exception.*;
import ChessGame.PlayerNumber;

import java.util.HashMap;

public class ChessPieces {
    public static final int CHESS_BOARD_SIZE = 8;
    public HashMap<ChessPiecePosition, ChessPiece> chessPieces;


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

    public void move(PlayerNumber playerNumber, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws Exception {
        validateMovePosition(playerNumber, fromPosition, toPosition);
        chessPieces.get(fromPosition).validateEachPieceMove(chessPieces, fromPosition, toPosition);    // TODO chessPieces를 DTO로 만들기
        checkKingCaptured(toPosition);

        chessPieces.put(toPosition, chessPieces.get(fromPosition));
        chessPieces.remove(fromPosition);
    }

    private void checkKingCaptured(ChessPiecePosition toPosition) throws GameOverException {
        if (chessPieces.get(toPosition) != null && chessPieces.get(toPosition).getClass() == King.class) {
            throw new GameOverException();
        }
    }

    private void validateMovePosition(PlayerNumber playerNumber, ChessPiecePosition fromPosition, ChessPiecePosition toPosition) throws Exception {
        validateNoPieceToMove(fromPosition);
        validateTurn(playerNumber, fromPosition);
        validateSamePosition(fromPosition, toPosition);
        validateTakenPosition(playerNumber, toPosition);
    }

    private void validateNoPieceToMove(ChessPiecePosition fromPosition) throws NoPieceToMoveException {
        if (chessPieces.get(fromPosition) == null) {
            throw new NoPieceToMoveException();
        }
    }

    private void validateTakenPosition(PlayerNumber playerNumber, ChessPiecePosition toPosition) throws TakenPositionException {
        if (chessPieces.get(toPosition) != null && chessPieces.get(toPosition).getPlayerNumber().equals(playerNumber.getPlayerNumber())) {
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
        if (!chessPieces.get(fromPosition).getPlayerNumber().equals(playerNumber)) {
            ConsoleOutput.printNotYourTurnExceptionMessage();
            throw new NotYourTurnException();
        }
    }
}
