package net.snackbag.neilon;

public record Color(int r, int g, int b) {
    public int toInt() {
        return 255 << 24 | r << 16 | g << 8 | b;
    }

    public static final Color BLACK = of(0);
    public static final Color DARK_BLUE = of(0, 0, 170);
    public static final Color DARK_GREEN = of(0, 170, 0);
    public static final Color DARK_AQUA = of(0, 170, 170);
    public static final Color DARK_RED = of(170, 0, 0);
    public static final Color DARK_PURPLE = of(170, 0, 170);
    public static final Color GOLD = of(255, 170, 0);
    public static final Color GRAY = of(170, 170, 170);
    public static final Color DARK_GRAY = of(85, 85, 85);
    public static final Color BLUE = of(85, 85, 255);
    public static final Color GREEN = of(85, 255, 85);
    public static final Color AQUA = of(85, 255, 255);
    public static final Color RED = of(255, 85, 85);
    public static final Color LIGHT_PURPLE = of(255, 85, 255);
    public static final Color YELLOW = of(255, 255, 85);
    public static final Color WHITE = of(255);
    
    public static Color of(int r, int g, int b) {
        return new Color(r, g, b);
    }
    
    public static Color of(int all) {
        return new Color(all, all, all);
    }
}
