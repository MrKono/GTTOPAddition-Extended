package kono.ceu.gttopaextended.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import kono.ceu.gttopaextended.GTTOPAdditionExtendedConfig;

import keqing.gttopaddition.integration.theoneprobe.MultiblockPSSProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;

@Mixin(value = MultiblockPSSProvider.class, remap = false)
public class MultiblockPSSProviderMixin {

    @Inject(method = "addProbeInfo", at = @At("HEAD"), cancellable = true)
    public void addProbeInfoMixin(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer, World world,
                                  IBlockState iBlockState, IProbeHitData iProbeHitData, CallbackInfo ci) {
        if (GTTOPAdditionExtendedConfig.pss.displayBigInteger) {
            ci.cancel();
        }
    }
}
