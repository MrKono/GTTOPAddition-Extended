package kono.ceu.gttopaextended.integration.top.gregtech;

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
import gregtech.common.metatileentities.multi.electric.MetaTileEntityHPCA;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityNetworkSwitch;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityResearchStation;

import kono.ceu.gttopaextended.Tags;

import mcjty.theoneprobe.api.*;

public class ComputationProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return Tags.MODID + ":gt_computation";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo info, EntityPlayer player, World world,
                             IBlockState state, IProbeHitData data) {
        if (state.getBlock().hasTileEntity(state)) {
            TileEntity tileEntity = world.getTileEntity(data.getPos());
            if (tileEntity instanceof IGregTechTileEntity) {
                MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
                // HPCA
                if (metaTileEntity instanceof MetaTileEntityHPCA &&
                        ((MetaTileEntityHPCA) metaTileEntity).isStructureFormed()) {
                    int CWUt = ((MetaTileEntityHPCA) metaTileEntity).getMaxCWUt();
                    ITextComponent provide = TextComponentUtil.translationWithColor(
                            TextFormatting.AQUA,
                            "gregtech.multiblock.computation.max",
                            TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                                    TextFormattingUtil.formatNumbers(CWUt) + " CWU/t"));
                    info.text(provide.getFormattedText());
                }
                // Research Station
                if (metaTileEntity instanceof MetaTileEntityResearchStation &&
                        ((MetaTileEntityResearchStation) metaTileEntity).isStructureFormed()) {
                    ITextComponent CWUtInfo = TextComponentUtil.translationWithColor(
                            TextFormatting.AQUA,
                            "gregtech.multiblock.computation.usage",
                            TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                                    TextFormattingUtil.formatNumbers(
                                            ((MetaTileEntityResearchStation) metaTileEntity).getRecipeMapWorkable()
                                                    .getCurrentDrawnCWUt()) +
                                            " CWU/t"));
                    info.text(CWUtInfo.getFormattedText());
                }
                // Network Switch
                if (metaTileEntity instanceof MetaTileEntityNetworkSwitch &&
                        ((MetaTileEntityNetworkSwitch) metaTileEntity).isStructureFormed()) {
                    int CWUt = ((MetaTileEntityNetworkSwitch) metaTileEntity).getMaxCWUt();
                    ITextComponent provide = TextComponentUtil.translationWithColor(
                            TextFormatting.AQUA,
                            "gregtech.multiblock.computation.max",
                            TextComponentUtil.stringWithColor(TextFormatting.AQUA,
                                    TextFormattingUtil.formatNumbers(CWUt) + " CWU/t"));
                    info.text(provide.getFormattedText());

                }
            }
        }
    }
}
