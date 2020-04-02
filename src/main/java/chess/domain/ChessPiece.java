package chess.domain;

public abstract class ChessPiece {

    private ChessTeam chessTeam;
    private ChessBoardPosition chessBoardPosition;

    public ChessPiece(ChessTeam chessTeam, ChessBoardPosition chessBoardPosition) {
        this.chessTeam = chessTeam;
        this.chessBoardPosition = chessBoardPosition;
    }

    public abstract boolean canMove(ChessBoardPosition targetPosition);

    public boolean isSameTeam(ChessPiece chessPiece) {
        return this.chessTeam.equals(chessPiece.chessTeam);
    }

    public void move(ChessBoardPosition targetPosition) {
        if (ChessTeam.isTurn(this.chessTeam)) {
            throw new IllegalArgumentException("현재 차례의 기물이 아닙니다.");
        }

        if (canMove(targetPosition)) {
            this.chessBoardPosition = targetPosition;
        }
    }

    public ChessBoardPosition getChessBoardPosition() {
        return chessBoardPosition;
    }

    // todo abstract method
    public String getUnicode() {
        return chessTeam.getPawnUnicode();
    }
}
