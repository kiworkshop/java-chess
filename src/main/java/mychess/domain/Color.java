package mychess.domain;

public enum Color {

    WHITE("White"),
    BLACK("Black");

    private final String colorName;

    Color(String colorName) {
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }
}
