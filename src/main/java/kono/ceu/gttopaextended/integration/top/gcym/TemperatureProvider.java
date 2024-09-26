package kono.ceu.gttopaextended.integration.top.gcym;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;

import gregicality.multiblocks.common.metatileentities.multiblock.standard.MetaTileEntityAlloyBlastSmelter;
import gregicality.multiblocks.common.metatileentities.multiblock.standard.MetaTileEntityMegaBlastFurnace;

import kono.ceu.gttopaextended.Tags;

import mcjty.theoneprobe.api.*;

public class TemperatureProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return Tags.MODID + "gcym_temperature";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo info, EntityPlayer player, World world,
                             IBlockState state, IProbeHitData data) {
        IProbeInfo horizontal = info.horizontal(info.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));

        if (state.getBlock().hasTileEntity(state)) {
            TileEntity tileEntity = world.getTileEntity(data.getPos());
            if (tileEntity instanceof IGregTechTileEntity) {
                MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();
                if (metaTileEntity instanceof MetaTileEntityAlloyBlastSmelter &&
                        ((MetaTileEntityAlloyBlastSmelter) metaTileEntity).isStructureFormed()) {
                    int temp = ((MetaTileEntityAlloyBlastSmelter) metaTileEntity).getCurrentTemperature();
                    horizontal.text(TextStyleClass.INFO + "{*gtqt.top.temperature*}");
                    horizontal.text(TextStyleClass.INFO + " " + TextFormatting.RED + temp + " K");

                } else if (metaTileEntity instanceof MetaTileEntityMegaBlastFurnace &&
                        ((MetaTileEntityMegaBlastFurnace) metaTileEntity).isStructureFormed()) {
                            int temp = ((MetaTileEntityMegaBlastFurnace) metaTileEntity).getCurrentTemperature();
                            horizontal.text(TextStyleClass.INFO + "{*gtqt.top.temperature*}");
                            horizontal.text(TextStyleClass.INFO + " " + TextFormatting.RED + temp + " K");
                        }
            }
        }
    }
}
