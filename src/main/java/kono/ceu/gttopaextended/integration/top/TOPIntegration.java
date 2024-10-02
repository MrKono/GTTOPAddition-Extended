package kono.ceu.gttopaextended.integration.top;

import kono.ceu.gttopaextended.integration.top.gregtech.*;

import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;

public class TOPIntegration {

    public static void init() {
        ITheOneProbe probe = TheOneProbe.theOneProbeImp;
        probe.registerProvider(new FusionReactorProvider());
        probe.registerProvider(new ComputationProvider());
        probe.registerProvider(new CleanroomProvider());
        probe.registerProvider(new PowerSubStationProvider());
    }
}
