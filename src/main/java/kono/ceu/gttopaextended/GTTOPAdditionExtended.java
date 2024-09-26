package kono.ceu.gttopaextended;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import gregtech.GTInternalTags;

import gregicality.multiblocks.GregicalityMultiblocks;

import kono.ceu.gttopaextended.integration.top.TOPIntegration;

import keqing.gttopaddition.GTTOPAddition;

@Mod(modid = Tags.MODID,
     version = Tags.VERSION,
     name = Tags.MODNAME,
     acceptedMinecraftVersions = "[1.12.2]",
     dependencies = GTInternalTags.DEP_VERSION_STRING + "required-after:" + GTTOPAddition.MODID + ";" +
             "after:" + GregicalityMultiblocks.MODID + ";")
public class GTTOPAdditionExtended {

    @Mod.Instance
    public static GTTOPAdditionExtended instance;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        TOPIntegration.init();
    }
}
