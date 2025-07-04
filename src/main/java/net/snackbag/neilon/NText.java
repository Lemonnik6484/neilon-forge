package net.snackbag.neilon;

import net.minecraft.text.Text;
import net.snackbag.vera.core.VColor;

public class NText {
    public static Text of(String content) {
        return assemble().text(content).build();
    }

    public static Text of(String content, VColor color) {
        return assemble().text(content).color(color).build();
    }

    public static Text of(String content, String hover) {
        return assemble().text(content).hover(hover).build();
    }

    public static Text of(String content, VColor color, String hover) {
        return assemble().text(content).color(color).hover(hover).build();
    }

    public static Text of(String content, VColor color, String hover, VColor hoverColor) {
        return assemble().text(content).color(color).hover(hover).hover(NText.of(hover, hoverColor)).build();
    }

    public static TextAssembler assemble() {
        return new TextAssembler();
    }
}
