package kono.ceu.gttopaextended;

import net.minecraftforge.common.config.Config;

@Config(modid = Tags.MODID)
public class GTTOPAdditionExtendedConfig {

    @Config.Comment("Config option for providing Power Substation Info ")
    @Config.Name("Power Substation Info")
    @Config.RequiresMcRestart
    public static PowerSubStationOption pss = new PowerSubStationOption();

    @Config.Comment("Config option for providing Computation Info ")
    @Config.Name("Computation Info")
    @Config.RequiresMcRestart
    public static ComputationOption comp = new ComputationOption();

    @Config.Comment("Config option for providing Cleaning Info ")
    @Config.Name("Cleaning Info")
    @Config.RequiresMcRestart
    public static CleaningOption clean = new CleaningOption();

    @Config.Comment("Config option for providing Fusion Reactor Info ")
    @Config.Name("Fusion Reactor Info")
    @Config.RequiresMcRestart
    public static FusionReactorOption fusion = new FusionReactorOption();

    public static class PowerSubStationOption {

        @Config.Comment({ "Display BigInteger values instead of GT TOP Addition's Long values.", "Default: true" })
        public boolean displayBigInteger = true;

        @Config.Comment({ "Display more information.", "Example: Average Input/Output", "Default: true" })
        public boolean displayMore = true;
    }

    public static class ComputationOption {

        @Config.Comment({ "Display how many CWU/t are supplied/used", "Default: true" })
        public boolean displayCWUt = true;
    }

    public static class CleaningOption {

        @Config.Comment({ "Display whether the machine is clean or not.", "Default: true" })
        public boolean displayClean = true;
    }

    public static class FusionReactorOption {

        @Config.Comment({ "Display FusionReactor's heat", "Default: true" })
        public boolean displayHeat = true;
    }
}
