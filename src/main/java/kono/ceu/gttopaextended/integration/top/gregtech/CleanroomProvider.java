package kono.ceu.gttopaextended.integration.top.gregtech;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.metatileentity.multiblock.ICleanroomProvider;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;

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
        IProbeInfo horizontal = info.horizontal(info.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));

        if (state.getBlock().hasTileEntity(state)) {
            TileEntity tileEntity = world.getTileEntity(data.getPos());
            if (tileEntity instanceof IGregTechTileEntity) {
                MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
                if (metaTileEntity instanceof RecipeMapMultiblockController &&
                        ((RecipeMapMultiblockController) metaTileEntity).isStructureFormed()) {
                    ICleanroomProvider provider = ((RecipeMapMultiblockController) metaTileEntity).getCleanroom();
                    if (provider == null) return;
                    if (provider.checkCleanroomType(CleanroomType.CLEANROOM) ||
                            provider.checkCleanroomType(CleanroomType.STERILE_CLEANROOM)) {
                        horizontal.text(TextStyleClass.INFO + "" + TextFormatting.GREEN +  "{*gttopadditionextended.clean*}");
                    }
                }
            }
        }
    }
}
