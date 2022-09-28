package committee.nova.bombinghud.client;

import committee.nova.bombinghud.config.BombingConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.torocraft.flighthud.FlightHud;
import net.torocraft.flighthud.config.loader.ConfigLoader;

@Environment(EnvType.CLIENT)
public class BombingHUDClient implements ClientModInitializer {
    public static BombingConfig BOMBING = new BombingConfig();

    public static ConfigLoader<BombingConfig> CONFIG_LOADER_BOMBING = new ConfigLoader<>(
            new BombingConfig(),
            FlightHud.MODID + ".bombing.json",
            config -> BOMBING = config);

    @Override
    public void onInitializeClient() {
        CONFIG_LOADER_BOMBING.load();
    }
}
