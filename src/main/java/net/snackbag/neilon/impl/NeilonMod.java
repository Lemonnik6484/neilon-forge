package net.snackbag.neilon.impl;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.snackbag.neilon.impl.test.InternalTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NeilonMod implements ModInitializer {
	public static final String MOD_ID = "neilon-lib";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Neilon is installed");

		if (FabricLoader.getInstance().isDevelopmentEnvironment()) InternalTesting.init();
	}
}