package committee.nova.bombinghud.component;

import com.google.common.collect.ImmutableList;
import committee.nova.bombinghud.client.BombingHUDClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.torocraft.flighthud.Dimensions;
import net.torocraft.flighthud.HudComponent;

public class BombingComponent extends HudComponent {
    private final Dimensions dim;
    private final ImmutableList<Item> igniters = ImmutableList.of(Items.FLINT_AND_STEEL, Items.FIRE_CHARGE);

    public BombingComponent(Dimensions dim) {
        this.dim = dim;
    }

    @Override
    public void render(MatrixStack m, float partial, MinecraftClient mc) {
        final var player = mc.player;
        if (player == null) return;
        if (!igniters.contains(player.getStackInHand(Hand.MAIN_HAND).getItem())) return;
        int tnts = 0;
        for (final var stack : player.getInventory().main) {
            if (Items.TNT == stack.getItem()) tnts += stack.getCount();
        }
        final float x = dim.wScreen * BombingHUDClient.BOMBING.bombX;
        final float y = dim.hScreen * BombingHUDClient.BOMBING.bombY;
        final float cd = player.getItemCooldownManager().getCooldownProgress(Items.TNT, partial);

        drawBox(m, x - 3.5f, y - 1.5f, 30, 10);
        drawFont(mc, m, "T", x - 10, y);
        drawFont(mc, m, String.valueOf(tnts), x, y);
        drawFont(mc, m, String.format("%.1f", 100 * (1.0F - cd)) + "%", x + 30, y);
    }
}
