package committee.nova.bombinghud.mixin;

import committee.nova.bombinghud.component.BombingComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.torocraft.flighthud.Dimensions;
import net.torocraft.flighthud.HudRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(value = HudRenderer.class, remap = false)
public abstract class MixinHudRenderer {
    @Shadow
    @Final
    private Dimensions dim;
    private final Supplier<BombingComponent> bombing = () -> new BombingComponent(dim);

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V"))
    public void addComponent(MatrixStack m, float partial, MinecraftClient client, CallbackInfo ci) {
        bombing.get().render(m, partial, client);
    }
}
