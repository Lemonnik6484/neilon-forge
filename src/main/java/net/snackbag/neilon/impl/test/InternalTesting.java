package net.snackbag.neilon.impl.test;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.snackbag.neilon.Color;
import net.snackbag.neilon.NText;
import net.snackbag.neilon.types.ClickType;
import net.snackbag.neilon.types.TextType;

public class InternalTesting {
    public static void init() {
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new InternalTesting());
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("neilon")
                .then(Commands.literal("test").executes(this::executeTests)));
    }

    private int executeTests(CommandContext<CommandSourceStack> ctx) {
        var source = ctx.getSource();

        source.sendSuccess(() -> NText.of("basic text"), false);

        source.sendSuccess(() -> NText.assemble()
                .text("block.minecraft.grass_block", TextType.KEYBINDING)
                .text("<-- keybinding (& stacked element)")
                .build(), false);

        source.sendSuccess(() -> NText.of("red text", Color.RED), false);

        source.sendSuccess(() -> NText.assemble()
                .text("lime with red hover text")
                .color(Color.GREEN)
                .hover(NText.of("beautiful", Color.RED))
                .build(), false);

        source.sendSuccess(() -> NText.assemble()
                .text("5 diamonds hover")
                .hover(Items.DIAMOND, 5)
                .build(), false);

        var player = source.getPlayer();
        if (player != null && !player.getMainHandItem().isEmpty()) {
            source.sendSuccess(() -> NText.assemble()
                    .text("main hand stack")
                    .hover(player.getMainHandItem())
                    .build(), false);
        } else {
            source.sendSuccess(() -> NText.of("Need item in main hand to issue full test"), false);
        }

        source.sendSuccess(() -> NText.assemble()
                .text("self hover")
                .hover(player)
                .build(), false);

        source.sendSuccess(() -> NText.assemble()
                .text("click suggestion")
                .click(ClickType.SUGGEST, "hello there")
                .build(), false);

        source.sendSuccess(() -> NText.assemble()
                .text("click run + fix")
                .click(ClickType.RUN, "gamemode survival")
                .build(), false);

        source.sendSuccess(() -> NText.assemble()
                .text("click run + no fix")
                .click(ClickType.RUN, "/gamemode creative")
                .build(), false);

        source.sendSuccess(() -> NText.assemble()
                .text("click open url + fix")
                .click(ClickType.URL, "github.com/snackbag/neilon")
                .build(), false);

        source.sendSuccess(() -> NText.assemble()
                .text("click open url + no fix")
                .click(ClickType.URL, "https://github.com/snackbag/neilon")
                .build(), false);

        source.sendSuccess(() -> NText.assemble()
                .text("click copy")
                .click(ClickType.COPY, ":p")
                .build(), false);

        source.sendSuccess(() -> NText.assemble()
                .text("bold").bold()
                .text("italic").italic()
                .text("underlined").underlined()
                .text("strikethrough").strikethrough()
                .text("magic").magic()
                .build(), false);

        source.sendSuccess(() -> NText.assemble().text("repeat").repeat(3).build(), false);
        source.sendSuccess(() -> NText.assemble().text("repeat2").repeat(3).color(Color.BLACK).build(), false);

        return 1;
    }
}
