# Concept

The general concept of where we want to go to.

```java
Text text = NText.of("im a text");

Text text = NText.of("im a text", VColor.MC_RED);

Text text = NText.of("im a text", "hover!");

Text text = NText.assemble()
        .text("im a text")
        .hover("with a hover effect")
        .clickCmd("/example")
        .color(VColor.MC_RED)
        .build();
```

```java
Text text = NText.translation("block.minecraft.grass_block");

Text text = NText.assemble()
        .text(Type.TRANSLATION, "block.minecraft.grass_block")
        .clickCopy("test")
        .build();
```