package net.snackbag.neilon;

import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.snackbag.neilon.types.ClickType;
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

    public @Nullable ClickType clickType;
    public @Nullable String clickAction;

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

            //
            // Click actions
            //

            potentiallyFixCommand();

            if (clickType != null) {
                switch (clickType) {
                    case RUN -> style = style.withClickEvent(createClickEvent(ClickEvent.Action.RUN_COMMAND));
                    case URL -> style = style.withClickEvent(createClickEvent(ClickEvent.Action.OPEN_URL));
                    case SUGGEST -> style = style.withClickEvent(createClickEvent(ClickEvent.Action.SUGGEST_COMMAND));
                    case COPY -> style = style.withClickEvent(createClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD));
                }
            }

            return style;
        });

        return text;
    }

    private void potentiallyFixCommand() {
        if (clickType == ClickType.RUN && !clickAction.startsWith("/")) {
            clickAction = "/" + clickAction;
        }
    }

    private ClickEvent createClickEvent(ClickEvent.Action action) {
        return new ClickEvent(action, clickAction);
    }

    public Text convert() {
        return applyStyles(switch (type) {
            case LITERAL -> Text.literal(value);
            case TRANSLATION -> Text.translatable(value);
            case KEYBINDING -> Text.keybind(value);
        });
    }
}
