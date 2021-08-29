package chess;

import chess.controller.WebChessController;

import static spark.Spark.staticFiles;

public class WebChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/public");

        WebChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
