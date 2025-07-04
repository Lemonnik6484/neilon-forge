package net.snackbag.neilon;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.snackbag.vera.core.VColor;

import java.util.ArrayList;
import java.util.List;

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
        getLastOrThrow("hover").hoverText = text;
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
