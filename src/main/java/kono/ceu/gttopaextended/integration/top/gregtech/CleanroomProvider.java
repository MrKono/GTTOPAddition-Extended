package kono.ceu.gttopaextended.integration.top.gregtech;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.metatileentity.multiblock.FuelMultiblockController;
import gregtech.api.metatileentity.multiblock.ICleanroomProvider;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.common.metatileentities.multi.MetaTileEntityCokeOven;
import gregtech.common.metatileentities.multi.MetaTileEntityPrimitiveBlastFurnace;
import gregtech.common.metatileentities.multi.MetaTileEntityPrimitiveWaterPump;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityCleanroom;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityFluidDrill;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityLargeMiner;
import gregtech.common.metatileentities.multi.electric.centralmonitor.MetaTileEntityCentralMonitor;

import kono.ceu.gttopaextended.GTTOPAdditionExtendedConfig;
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
        if (!GTTOPAdditionExtendedConfig.clean.displayClean) return;
        if (state.getBlock().hasTileEntity(state)) {
            TileEntity tileEntity = world.getTileEntity(data.getPos());
            if (tileEntity instanceof IGregTechTileEntity) {
                MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
                if (metaTileEntity instanceof SimpleGeneratorMetaTileEntity) return;
                if (metaTileEntity instanceof FuelMultiblockController) return;
                if (metaTileEntity instanceof MetaTileEntityLargeMiner) return;
                if (metaTileEntity instanceof MetaTileEntityFluidDrill) return;
                if (metaTileEntity instanceof MetaTileEntityCentralMonitor) return;
                if (metaTileEntity instanceof MetaTileEntityCleanroom) return;
                if (metaTileEntity instanceof MetaTileEntityCokeOven) return;
                if (metaTileEntity instanceof MetaTileEntityPrimitiveBlastFurnace) return;
                if (metaTileEntity instanceof MetaTileEntityPrimitiveWaterPump) return;
                if (metaTileEntity instanceof RecipeMapMultiblockController &&
                        ((RecipeMapMultiblockController) metaTileEntity).isStructureFormed()) {
                    if (((RecipeMapMultiblockController) metaTileEntity).hasMufflerMechanics()) return;
                    ICleanroomProvider provider = ((RecipeMapMultiblockController) metaTileEntity).getCleanroom();
                    if (provider == null) return;
                    if (provider.checkCleanroomType(CleanroomType.CLEANROOM) ||
                            provider.checkCleanroomType(CleanroomType.STERILE_CLEANROOM)) {
                        horizontal.text(
                                TextStyleClass.INFO + "" + TextFormatting.AQUA + "{*gttopadditionextended.clean*}");
                    }
                }
            }
        }
    }
}
