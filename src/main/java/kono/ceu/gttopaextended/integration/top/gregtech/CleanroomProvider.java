package kono.ceu.gttopaextended.integration.top.gregtech;

import java.util.List;

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
import gregtech.common.metatileentities.multi.electric.MetaTileEntityCleanroom;

import kono.ceu.gttopaextended.Tags;

import mcjty.theoneprobe.api.*;

public class CleanroomProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return Tags.MODID + ":gt_cleanroom";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo info, EntityPlayer player, World world,
                             IBlockState state, IProbeHitData data) {
        if (state.getBlock().hasTileEntity(state)) {
            TileEntity tileEntity = world.getTileEntity(data.getPos());
            if (tileEntity instanceof IGregTechTileEntity) {
                MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
                if (metaTileEntity instanceof MetaTileEntityCleanroom &&
                        ((MetaTileEntityCleanroom) metaTileEntity).isStructureFormed()) {
                    List<ITextComponent> test = ((MetaTileEntityCleanroom) metaTileEntity).getDataInfo();
                    boolean clean = ((MetaTileEntityCleanroom) metaTileEntity).isClean();

                    ITextComponent text = TextComponentUtil.translationWithColor(
                            clean ? TextFormatting.GREEN : TextFormatting.RED,
                            String.valueOf(test.get(0)),
                            TextComponentUtil.stringWithColor(TextFormatting.WHITE,
                                    TextFormattingUtil.formatNumbers(test.get(1))));
                    info.text(text.getFormattedText());

                }
            }
        }
    }
}
