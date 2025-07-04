package net.snackbag.neilon;

import net.minecraft.text.Text;

public class NText {
    public static Text of(String content) {
        return assemble().text(content).build();
    }

    public static TextAssembler assemble() {
        return new TextAssembler();
    }
}
