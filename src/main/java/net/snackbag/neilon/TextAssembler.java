package net.snackbag.neilon;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TextAssembler {
    private final List<TextElement> elements = new ArrayList<>();

    protected TextAssembler() {}

    public TextAssembler text(String value) {
        return text(value, NType.LITERAL);
    }

    public TextAssembler text(String value, NType type) {
        elements.add(new TextElement(value, type));
        return this;
    }

    private void asLast(Consumer<TextElement> exec) {
        if (!elements.isEmpty()) exec.accept(elements.get(elements.size() - 1));
    }

    public Text build() {
        MutableText output = Text.literal("");

        for (TextElement elem : elements) {
            output.append(elem.convert());
        }

        return output;
    }
}
