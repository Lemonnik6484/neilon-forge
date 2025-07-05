package net.snackbag.neilon.impl.test;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.snackbag.neilon.Color;
import net.snackbag.neilon.NText;
import net.snackbag.neilon.types.ClickType;
import net.snackbag.neilon.types.TextType;

public class InternalTesting {
    public static void init() {
        CommandRegistrationCallback.EVENT.register(InternalTesting::command);
    }

    private static void command(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess registryAccess,
                                CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("neilon")
                .then(CommandManager.literal("test").executes(InternalTesting::executeTests)));
    }

    private static int executeTests(CommandContext<ServerCommandSource> ctx) {
        var source = ctx.getSource();

        source.sendMessage(NText.of("basic text"));

        source.sendMessage(NText.assemble()
                .text("block.minecraft.grass_block", TextType.KEYBINDING)
                .text("<-- keybinding (& stacked element)")
                .build()
        );

        source.sendMessage(NText.of("red text", Color.RED));

        source.sendMessage(NText.assemble()
                .text("lime with red hover text")
                .color(Color.GREEN)
                .hover("beautiful")
                .color(Color.RED)
                .build()
        );

        source.sendMessage(NText.assemble()
                .text("5 diamonds hover")
                .hover(Items.DIAMOND, 5)
                .build()
        );

        if (source.getPlayer().getMainHandStack() != null) {
            source.sendMessage(
                    NText.assemble()
                            .text("main hand stack")
                            .hover(source.getPlayer().getMainHandStack())
                            .build()
            );
        } else {
            source.sendMessage(NText.of("Need item in main hand to issue full test"));
        }

        source.sendMessage(NText.assemble()
                .text("self hover")
                .hover(source.getPlayer())
                .build());

        source.sendMessage(NText.assemble()
                .text("click suggestion")
                .click(ClickType.SUGGEST, "hello there")
                .build());

        source.sendMessage(NText.assemble()
                .text("click run + fix")
                .click(ClickType.RUN, "gamemode survival")
                .build());

        source.sendMessage(NText.assemble()
                .text("click run + no fix")
                .click(ClickType.RUN, "/gamemode creative")
                .build());

        source.sendMessage(NText.assemble()
                .text("click open url + fix")
                .click(ClickType.URL, "github.com/snackbag/neilon")
                .build());

        source.sendMessage(NText.assemble()
                .text("click open url + no fix")
                .click(ClickType.URL, "https://github.com/snackbag/neilon")
                .build());

        source.sendMessage(NText.assemble()
                .text("click copy")
                .click(ClickType.COPY, ":p")
                .build());

        source.sendMessage(NText.assemble()
                .text("bold").bold()
                .text("italic").italic()
                .text("underlined").underlined()
                .text("strikethrough").strikethrough()
                .text("magic").magic()
                .build()
        );

        source.sendMessage(NText.assemble().text("repeat").repeat(3).build());
        source.sendMessage(NText.assemble().text("repeat2").repeat(3).color(Color.BLACK).build());

        return 1;
    }
}
