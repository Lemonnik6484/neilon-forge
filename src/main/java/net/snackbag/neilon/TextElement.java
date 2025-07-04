package net.snackbag.neilon;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.snackbag.vera.core.VColor;

public class TextElement {
    public final String value;
    public final NType type;

    public VColor color = VColor.white();

    protected TextElement(String value, NType type) {
        this.value = value;
        this.type = type;
    }

    public Text applyStyles(MutableText text) {
        text.styled(style -> style.withColor(color.toInt()));
        return text;
    }

    public Text convert() {
        return applyStyles(switch (type) {
            case LITERAL -> Text.literal(value);
            case TRANSLATION -> Text.translatable(value);
            case KEYBINDING -> Text.keybind(value);
        });
    }
}
