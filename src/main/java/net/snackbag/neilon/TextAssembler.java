package net.snackbag.neilon;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.snackbag.neilon.types.ClickType;
import net.snackbag.neilon.types.TextType;

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

    public TextAssembler color(Color color) {
        TextElement last = getLastOrThrow("color");
        last.color = color;

        return this;
    }

    public TextAssembler hover(String text) {
        return hover(NText.of(text));
    }

    public TextAssembler hover(Component text) {
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
        last.hoverItem = new HoverEvent.ItemStackInfo(stack);
        last.hoverEntity = null;

        return this;
    }

    public TextAssembler hover(LivingEntity entity) {
        return hover(entity.getType(), entity.getUUID(), entity.getName());
    }

    public TextAssembler hover(EntityType<?> type, UUID uuid, Component name) {
        TextElement last = getLastOrThrow("hover-entity");

        last.hoverText = null;
        last.hoverItem = null;
        last.hoverEntity = new HoverEvent.EntityTooltipInfo(type, uuid, name);

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

    public TextAssembler repeat(int amount) {
        TextElement last = getLastOrThrow("repeat");

        for (int i = 0; i < amount; i++) {
            elements.add(last);
        }

        return this;
    }

    private TextElement getLastOrThrow(String attempted) {
        if (elements.isEmpty()) throw new RuntimeException("Cannot apply %s if no text is available".formatted(attempted));
        return elements.get(elements.size() - 1);
    }

    public Component build() {
        MutableComponent output = Component.literal("");

        for (TextElement elem : elements) {
            output.append(elem.convert());
        }

        return output;
    }
}
