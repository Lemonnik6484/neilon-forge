package net.snackbag.neilon;

import net.minecraft.text.Text;

public class TextAssembler {
    protected TextAssembler() {

    }

    public TextAssembler text(String value) { // TODO: actual logic
        return new TextAssembler();
    }

    public Text build() {
        return Text.literal("aaaa");
    }
}
