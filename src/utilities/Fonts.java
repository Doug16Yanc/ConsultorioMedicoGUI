package utilities;

public enum Fonts {
    JET_BRAINS_MONO("JetBrains Mono");

    private final String fontName;

    Fonts(String fontName) {
        this.fontName = fontName;
    }

    public String getFontName() {
        return fontName;
    }
}
