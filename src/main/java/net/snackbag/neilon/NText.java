package net.snackbag.neilon;

import net.minecraft.network.chat.Component;
import net.snackbag.neilon.types.TextType;

public class NText {
    public static Component of(String content) {
        return assemble().text(content).build();
    }

    public static Component of(String content, Color color) {
        return assemble().text(content).color(color).build();
    }

    public static Component keybinding(String key) {
        return assemble().text(key, TextType.KEYBINDING).build();
    }

    public static Component keybinding(String key, Color color) {
        return assemble().text(key, TextType.KEYBINDING).color(color).build();
    }

    public static Component translation(String key) {
        return assemble().text(key, TextType.TRANSLATION).build();
    }

    public static Component translation(String key, Color color) {
        return assemble().text(key, TextType.TRANSLATION).color(color).build();
    }

    public static TextAssembler assemble() {
        return new TextAssembler();
    }
}
