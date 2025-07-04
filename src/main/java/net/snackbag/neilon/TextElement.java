package net.snackbag.neilon;

import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.snackbag.neilon.types.TextType;
import net.snackbag.vera.core.VColor;
import org.jetbrains.annotations.Nullable;

public class TextElement {
    public final String value;
    public final TextType type;

    public VColor color = VColor.white();

    public @Nullable Text hoverText;
    public @Nullable HoverEvent.ItemStackContent hoverItem;
    public @Nullable HoverEvent.EntityContent hoverEntity;

    protected TextElement(String value, TextType type) {
        this.value = value;
        this.type = type;
    }

    public Text applyStyles(MutableText text) {
        text.styled(style -> {
            style = style.withColor(color.toInt());

            //
            // Hover styles
            //
            if (hoverText != null) {
                style = style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText));
            } else if (hoverItem != null) {
                style = style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverItem));
            } else if (hoverEntity != null) {
                style = style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ENTITY, hoverEntity));
            }

            return style;
        });

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
