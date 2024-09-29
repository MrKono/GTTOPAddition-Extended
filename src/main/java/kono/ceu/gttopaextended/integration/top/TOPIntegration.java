package kono.ceu.gttopaextended.integration.top;

import net.minecraftforge.fml.common.Loader;

import gregicality.multiblocks.GregicalityMultiblocks;

import kono.ceu.gttopaextended.integration.top.gcym.*;
import kono.ceu.gttopaextended.integration.top.gregtech.*;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class TOPIntegration {

    public static void init() {
        ITheOneProbe probe = TheOneProbe.theOneProbeImp;
        probe.registerProvider(new PowerSubStationProvider());
        probe.registerProvider(new FusionReactorProvider());

        if (Loader.isModLoaded(GregicalityMultiblocks.MODID)) {
            probe.registerProvider(new TemperatureProvider());
        }
    }
}
