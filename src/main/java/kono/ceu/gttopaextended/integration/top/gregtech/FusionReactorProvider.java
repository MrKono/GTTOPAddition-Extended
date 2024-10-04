package kono.ceu.gttopaextended.integration.top.gregtech;

import kono.ceu.gttopaextended.Tags;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityFusionReactor;
import gregtech.integration.theoneprobe.provider.ElectricContainerInfoProvider;

import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;

public class FusionReactorProvider extends ElectricContainerInfoProvider {

    @Override
    public String getID() {
        return Tags.MODID + ":gt_fusion_reactor";
    }

    protected void addProbeInfo(@NotNull IEnergyContainer capability, @NotNull IProbeInfo probeInfo,
                                EntityPlayer player, @NotNull TileEntity tileEntity, @NotNull IProbeHitData data) {
        super.addProbeInfo(capability, probeInfo, player, tileEntity, data);
        if (tileEntity instanceof IGregTechTileEntity) {
            MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
            if (metaTileEntity instanceof MetaTileEntityFusionReactor &&
                    ((MetaTileEntityFusionReactor) metaTileEntity).isStructureFormed()) {
                long heat = ((MetaTileEntityFusionReactor) metaTileEntity).getHeat();
                long capacity = capability.getEnergyCapacity();
                probeInfo.text(TextFormatting.RED + "{*gttopadditionextended.fusion_reactor.heat*}");
                probeInfo.progress(heat, capacity, probeInfo.defaultProgressStyle()
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
