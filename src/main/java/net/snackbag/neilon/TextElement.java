package net.snackbag.neilon;

import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.snackbag.neilon.types.ClickType;
import net.snackbag.neilon.types.TextType;
import org.jetbrains.annotations.Nullable;

public class TextElement {
    public final String value;
    public final TextType type;

    public Color color = Color.WHITE;

    public @Nullable Component hoverText;
    public @Nullable HoverEvent.ItemStackInfo hoverItem;
    public @Nullable HoverEvent.EntityTooltipInfo hoverEntity;

    public @Nullable ClickType clickType;
    public @Nullable String clickAction;

    public boolean bold = false;
    public boolean italic = false;
    public boolean underlined = false;
    public boolean strikethrough = false;
    public boolean magic = false;

    protected TextElement(String value, TextType type) {
        this.value = value;
        this.type = type;
    }

    public Component applyStyles(MutableComponent text) {
        text.withStyle(style -> {
            style = style.withColor(color.toInt());

            //
            // Basic styling
            //
            style = style.withBold(bold);
            style = style.withItalic(italic);
            style = style.withUnderlined(underlined);
            style = style.withStrikethrough(strikethrough);
            style = style.withObfuscated(magic);

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

            potentiallyFixClick();

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

    private void potentiallyFixClick() {
        if (clickType == ClickType.RUN && !clickAction.startsWith("/")) {
            clickAction = "/" + clickAction;
        } else if (clickType == ClickType.URL && !clickAction.matches("[a-zA-Z][a-zA-Z0-9+.-]*://.*")) {
            clickAction = "https://" + clickAction;
        }
    }

    private ClickEvent createClickEvent(ClickEvent.Action action) {
        return new ClickEvent(action, clickAction);
    }

    public Component convert() {
        return applyStyles(switch (type) {
            case LITERAL -> Component.literal(value);
            case TRANSLATION -> Component.translatable(value);
            case KEYBINDING -> Component.keybind(value);
        });
    }
}
