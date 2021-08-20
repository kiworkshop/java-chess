package chess.view;

import chess.domain.RankComparator;
import chess.domain.plate.File;
import chess.domain.piece.PiecePosition;
import chess.domain.plate.Rank;
import chess.domain.plate.ChessPlate;

import java.util.Arrays;

public class OutputView {

    public void printChessPlate(ChessPlate chessPlate) {
        RankComparator rankComparator = new RankComparator();
        Arrays.stream(Rank.values())
                .sorted(rankComparator::compare)
                .forEach(rank -> {
                    for (File file : File.values()) {
                        if(chessPlate.getPlate().get(new PiecePosition(file, rank)) == null) {
                            System.out.print(".");
                        }else{
                            System.out.print(chessPlate.getPlate().get(new PiecePosition(file, rank)).getDisplayName());
                        }
                    }
                    System.out.println();
                });
    }

    public void initPrint() {
        System.out.println(">체스 게임을 시작합니다.");
        System.out.println(">게임 시작 : start");
        System.out.println(">게임 종료 : end");
        System.out.println(">게임 이동 : move source 위치 target 위치 - 예. move b2 b3");
    }

    public void printInCorrectCommandMessage(){
        System.out.println("start/end/move 중 하나를 입력해주세요");
    }

    public void printCannotMoveMessage() {
        System.out.println("기물을 움직일 수 없습니다.");
    }


}
