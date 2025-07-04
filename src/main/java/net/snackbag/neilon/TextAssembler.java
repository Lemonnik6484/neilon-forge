package net.snackbag.neilon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.snackbag.neilon.types.ClickType;
import net.snackbag.neilon.types.TextType;
import net.snackbag.vera.core.VColor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TextAssembler {
    private final List<TextElement> elements = new ArrayList<>();

    protected TextAssembler() {}

    public TextAssembler text(String value) {
        return text(value, TextType.LITERAL);
    }

    public TextAssembler text(String value, TextType type) {
        elements.add(new TextElement(value, type));
        return this;
    }

    public TextAssembler color(VColor color) {
        TextElement last = getLastOrThrow("color");
        last.color = color;

        return this;
    }

    public TextAssembler hover(String text) {
        return hover(NText.of(text));
    }

    public TextAssembler hover(Text text) {
        TextElement last = getLastOrThrow("hover-text");

        last.hoverText = text;
        last.hoverItem = null;
        last.hoverEntity = null;

        return this;
    }

    public TextAssembler hover(Item item) {
        return hover(item, 1);
    }

    public TextAssembler hover(Item item, int count) {
        return hover(new ItemStack(item, count));
    }

    public TextAssembler hover(ItemStack stack) {
        TextElement last = getLastOrThrow("hover-item");

        last.hoverText = null;
        last.hoverItem = new HoverEvent.ItemStackContent(stack);
        last.hoverEntity = null;

        return this;
    }

    public TextAssembler hover(Entity entity) {
        return hover(entity.getType(), entity.getUuid(), entity.getName());
    }

    public TextAssembler hover(EntityType<?> type, UUID uuid, Text name) {
        TextElement last = getLastOrThrow("hover-entity");

        last.hoverText = null;
        last.hoverItem = null;
        last.hoverEntity = new HoverEvent.EntityContent(type, uuid, name);

        return this;
    }

    public TextAssembler click(ClickType type, String action) {
        TextElement last = getLastOrThrow("click");

        last.clickType = type;
        last.clickAction = action;

        return this;
    }

    public TextAssembler bold() {
        getLastOrThrow("bold").bold = true;
        return this;
    }

    public TextAssembler italic() {
        getLastOrThrow("italic").italic = true;
        return this;
    }

    public TextAssembler underlined() {
        getLastOrThrow("underlined").underlined = true;
        return this;
    }

    public TextAssembler strikethrough() {
        getLastOrThrow("strikethrough").strikethrough = true;
        return this;
    }

    public TextAssembler magic() {
        getLastOrThrow("magic").magic = true;
        return this;
    }

    private TextElement getLastOrThrow(String attempted) {
        if (elements.isEmpty()) throw new RuntimeException("Cannot apply %s if no text is available".formatted(attempted));
        return elements.get(elements.size() - 1);
    }

    public Text build() {
        MutableText output = Text.literal("");

        for (TextElement elem : elements) {
            output.append(elem.convert());
        }

        return output;
    }
}
