package kono.ceu.gttopaextended.integration.top;

import net.minecraftforge.fml.common.Loader;

import gregicality.multiblocks.GregicalityMultiblocks;

import kono.ceu.gttopaextended.integration.top.gcym.TemperatureProvider;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class TOPIntegration {

    public static void init() {
        ITheOneProbe probe = TheOneProbe.theOneProbeImp;
        if (Loader.isModLoaded(GregicalityMultiblocks.MODID)) {
            probe.registerProvider(new TemperatureProvider());
        }
    }
}
