package net.snackbag.neilon;

import net.minecraft.text.Text;
import net.snackbag.neilon.types.TextType;
import net.snackbag.vera.core.VColor;

public class NText {
    public static Text of(String content) {
        return assemble().text(content).build();
    }

    public static Text of(String content, VColor color) {
        return assemble().text(content).color(color).build();
    }

    public static Text keybinding(String key) {
        return assemble().text(key, TextType.KEYBINDING).build();
    }

    public static Text keybinding(String key, VColor color) {
        return assemble().text(key, TextType.KEYBINDING).color(color).build();
    }

    public static Text translation(String key) {
        return assemble().text(key, TextType.TRANSLATION).build();
    }

    public static Text translation(String key, VColor color) {
        return assemble().text(key, TextType.TRANSLATION).color(color).build();
    }

    public static TextAssembler assemble() {
        return new TextAssembler();
    }
}
