package net.snackbag.neilon.impl;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.snackbag.neilon.impl.test.InternalTesting;
import org.slf4j.Logger;

@Mod(NeilonMod.MODID)
public class NeilonMod
{
    public static final String MODID = "neilon_lib";
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public NeilonMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Neilon is installed");

        if (!FMLEnvironment.production) {
            InternalTesting.init();
        }
    }
}
