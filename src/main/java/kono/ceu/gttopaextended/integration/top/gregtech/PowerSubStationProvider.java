package kono.ceu.gttopaextended.integration.top.gregtech;

import static gregtech.client.utils.TooltipHelper.isCtrlDown;

import java.text.DecimalFormat;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityPowerSubstation;

import kono.ceu.gttopaextended.Tags;

import mcjty.theoneprobe.api.*;
import mcjty.theoneprobe.apiimpl.elements.ElementProgress;

public class PowerSubStationProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return Tags.MODID + ":gt_pss";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo info, EntityPlayer player, World world,
                             IBlockState state, IProbeHitData data) {
        if (state.getBlock().hasTileEntity(state)) {
            TileEntity tileEntity = world.getTileEntity(data.getPos());
            if (tileEntity instanceof IGregTechTileEntity) {
                MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
                if (metaTileEntity instanceof MetaTileEntityPowerSubstation &&
                        ((MetaTileEntityPowerSubstation) metaTileEntity).isStructureFormed()) {

                    long stored = ((MetaTileEntityPowerSubstation) metaTileEntity).getStoredLong();
                    long capacity = ((MetaTileEntityPowerSubstation) metaTileEntity).getCapacityLong();

                    info.progress(stored, capacity, info.defaultProgressStyle()
                            .numberFormat(
                                    player.isSneaking() || stored < 10000 ? NumberFormat.FULL : NumberFormat.COMPACT)
                            .suffix(" / " + (isCtrlDown() || capacity < 10000 ? capacity + " EU" :
                                    ElementProgress.format(capacity, NumberFormat.COMPACT, "EU")))
                            .filledColor(0xFFEEE600)
                            .alternateFilledColor(0xFFEEE600)
                            .borderColor(0xFF555555));

                    long in = ((MetaTileEntityPowerSubstation) metaTileEntity).getAverageInLastSec();
                    long out = ((MetaTileEntityPowerSubstation) metaTileEntity).getAverageOutLastSec();
                    long passive = ((MetaTileEntityPowerSubstation) metaTileEntity).getPassiveDrain();
                    long io = in - out;

                    info.text(TextFormatting.AQUA + "{*pss.IO*}" + " " +
                            (io > 0 ? TextFormatting.GREEN : TextFormatting.RED) + (isCtrlDown() ? io : format(io)) +
                            " EU/t");

                    if (player.isSneaking()) {
                        ITextComponent averageIn = TextComponentUtil.translationWithColor(
                                TextFormatting.GREEN,
                                "gregtech.multiblock.power_substation.average_in",
                                TextComponentUtil.stringWithColor(TextFormatting.WHITE,
                                        isCtrlDown() || in < 10_000 ?
                                                TextFormattingUtil.formatNumbers(in) + " EU/t" :
                                                ElementProgress.format(in, NumberFormat.COMPACT, "EU/t")));
                        info.text(averageIn.getFormattedText());

                        ITextComponent averageOut = TextComponentUtil.translationWithColor(
                                TextFormatting.RED,
                                "gregtech.multiblock.power_substation.average_out",
                                TextComponentUtil.stringWithColor(TextFormatting.WHITE,
                                        isCtrlDown() || out < 10_000 ?
                                                TextFormattingUtil.formatNumbers(out) + " EU/t" :
                                                ElementProgress.format(out, NumberFormat.COMPACT, "EU/t")));
                        info.text(averageOut.getFormattedText());

                        ITextComponent passiveDrain = TextComponentUtil.translationWithColor(
                                TextFormatting.DARK_RED,
                                "gregtech.multiblock.power_substation.passive_drain",
                                TextComponentUtil.stringWithColor(TextFormatting.WHITE,
                                        isCtrlDown() || passive < 10_000 ?
                                                TextFormattingUtil.formatNumbers(passive) + " EU/t" :
                                                ElementProgress.format(passive, NumberFormat.COMPACT, "EU/t")));
                        info.text(" " + passiveDrain.getFormattedText());
                    }
                }
            }
        }
    }

    public static String format(long value) {
        DecimalFormat df = new DecimalFormat("#.#");

        String[] suffixes = { "", "K", "M", "G", "T", "P", "E" };

        int suffixIndex = 0;
        double displayValue = value;
        if (value > 10000) {
            while (displayValue >= 1000 && suffixIndex < suffixes.length - 1) {
                displayValue /= 1000;
                suffixIndex++;
            }
        }
        return df.format(displayValue) + " " + suffixes[suffixIndex];
    }
}
