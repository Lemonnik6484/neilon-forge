package net.snackbag.neilon;

import net.minecraft.text.Text;

public class TextElement {
    public final String value;
    public final NType type;

    protected TextElement(String value, NType type) {
        this.value = value;
        this.type = type;
    }

    public Text convert() {
        return switch (type) {
            case LITERAL -> Text.literal(value);
            case TRANSLATION -> Text.translatable(value);
            case KEYBINDING -> Text.keybind(value);
        };
    }
}
