package chess.controller;

import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebChessController {

    private final ChessService chessService;

    public WebChessController() {
        this.chessService = new ChessService();
    }

    public void run() {
        get("/", this::landingPage);

        post("/start", this::startGame);
    }

    private String landingPage(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    private Object startGame(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessService.getBoardView());
        model.put("currentTurn", chessService.getCurrentTurn());
        return render(model, "game.html");
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
