package kono.ceu.gttopaextended.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import kono.ceu.gttopaextended.integration.top.gregtech.PowerSubStationProvider;

import keqing.gttopaddition.integration.theoneprobe.MultiblockPSSProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;

@Mixin(value = MultiblockPSSProvider.class, remap = false)
public class MultiblockPSSProviderMixin {

    /**
     * @author MrKono
     * @reason duplicate {@link PowerSubStationProvider}
     */
    @Overwrite
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, EntityPlayer entityPlayer,
                             World world, IBlockState iBlockState, IProbeHitData iProbeHitData) {}
}
