package kono.ceu.gttopaextended.integration.top.gregtech;

import kono.ceu.gttopaextended.GTTOPAdditionExtendedConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityFusionReactor;
import gregtech.integration.theoneprobe.provider.CapabilityInfoProvider;

import kono.ceu.gttopaextended.Tags;

import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;

public class FusionReactorProvider extends CapabilityInfoProvider<IEnergyContainer> {

    @Override
    public String getID() {
        return Tags.MODID + ":gt_fusion_reactor";
    }

    @Override
    protected @NotNull Capability<IEnergyContainer> getCapability() {
        return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER;
    }

    @Override
    protected void addProbeInfo(@NotNull IEnergyContainer capability, @NotNull IProbeInfo info,
                                EntityPlayer player, @NotNull TileEntity tileEntity, @NotNull IProbeHitData data) {
        if (!GTTOPAdditionExtendedConfig.fusion.displayHeat) return;
        if (tileEntity instanceof IGregTechTileEntity) {
            MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
            if (metaTileEntity instanceof MetaTileEntityFusionReactor &&
                    ((MetaTileEntityFusionReactor) metaTileEntity).isStructureFormed()) {
                long heat = ((MetaTileEntityFusionReactor) metaTileEntity).getHeat();
                long capacity = capability.getEnergyCapacity();
                info.text(TextFormatting.RED + "{*gttopadditionextended.fusion_reactor.heat*}");
                info.progress(heat, capacity, info.defaultProgressStyle()
                        .numberFormat(
                                player.isSneaking() || heat < 10000 ? NumberFormat.FULL : NumberFormat.COMPACT)
                        .suffix(" / " + (player.isSneaking() || capacity < 10000 ? capacity :
                                ElementProgress.format(capacity, NumberFormat.COMPACT, "")))
                        .filledColor(0xFFEEE600)
                        .alternateFilledColor(0xFFEEE600)
                        .borderColor(0xFF555555));
            }
        }
    }
}
