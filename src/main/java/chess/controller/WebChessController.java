package chess.controller;

import chess.domain.command.MoveParameters;
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

    private static final String LANDING_PAGE = "index.html";
    private static final String GAME_PAGE = "game.html";
    private static final String RESULT_PAGE = "result.html";

    private final ChessService chessService;

    public WebChessController() {
        this.chessService = new ChessService();
    }

    public void run() {
        get("/", this::landingPage);

        post("/start", this::startGame);

        post("/status", this::calculateStatus);

        post("/move", this::movePiece);

        post("/result", this::finishGame);
    }

    private String landingPage(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return render(model, LANDING_PAGE);
    }

    private String startGame(Request request, Response response) {
        return render(getGameInfo(), GAME_PAGE);
    }

    private String calculateStatus(Request request, Response response) {
        return render(getGameInfoWithStatus(), GAME_PAGE);
    }

    private String movePiece(Request request, Response response) {
        try {
            MoveParameters moveParameters = new MoveParameters(request.queryParams("source"), request.queryParams("target"));
            chessService.movePiece(moveParameters);
        } catch (IllegalArgumentException e) {
            return render(getGameInfoWithMessage(e.getMessage()), GAME_PAGE);
        }

        return nextPage();
    }

    private String finishGame(Request request, Response response) {
        return render(getGameResult(), RESULT_PAGE);
    }

    private String nextPage() {
        if (chessService.isGameFinished()) {
            return render(getGameResult(), RESULT_PAGE);
        }
        return render(getGameInfo(), GAME_PAGE);
    }

    private Map<String, Object> getGameInfo() {
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessService.getBoardView());
        model.put("currentTurn", chessService.getCurrentTurn());
        return model;
    }

    private Map<String, Object> getGameInfoWithStatus() {
        Map<String, Object> model = getGameInfo();
        model.put("status", chessService.getStatus());
        return model;
    }

    private Map<String, Object> getGameInfoWithMessage(String message) {
        Map<String, Object> model = getGameInfo();
        model.put("message", message);
        return model;
    }

    private Map<String, Object> getGameResult() {
        Map<String, Object> model = new HashMap<>();
        return model;
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
