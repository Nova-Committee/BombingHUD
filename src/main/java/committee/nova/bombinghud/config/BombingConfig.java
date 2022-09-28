package committee.nova.bombinghud.config;

import net.torocraft.flighthud.config.loader.IConfig;

public class BombingConfig implements IConfig {
    public float bombX = .6f;
    public float bombY = .8f;

    @Override
    public void update() {

    }

    @Override
    public boolean shouldWatch() {
        return true;
    }
}
