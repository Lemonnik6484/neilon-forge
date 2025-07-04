package net.snackbag.neilon.impl.test;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.snackbag.neilon.NText;
import net.snackbag.neilon.NType;
import net.snackbag.vera.core.VColor;

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
                .text("block.minecraft.grass_block", NType.KEYBINDING)
                .text("<-- keybinding (& stacked element)")
                .build()
        );

        source.sendMessage(NText.assemble()
                .text("red text")
                .color(VColor.MC_RED)
                .build()
        );

        return 1;
    }
}
