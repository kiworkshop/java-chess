package mychess.controller.dto;

import java.util.List;

public class MoveParams {
    private String source;
    private String destination;

    private MoveParams(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public static MoveParams of(List<String> parameters) {
        if (parameters.size() != 2) {
            throw new IndexOutOfBoundsException();
        }
        return new MoveParams(parameters.get(0), parameters.get(1));
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
