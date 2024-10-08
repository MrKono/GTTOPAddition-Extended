package kono.ceu.gttopaextended;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gregtech.GTInternalTags;

import kono.ceu.gttopaextended.api.Mods;
import kono.ceu.gttopaextended.integration.top.TOPIntegration;

@Mod(modid = Tags.MODID,
     version = Tags.VERSION,
     name = Tags.MODNAME,
     acceptedMinecraftVersions = "[1.12.2]",
     dependencies = GTInternalTags.DEP_VERSION_STRING + "required-after:" + Mods.Names.MIXINBOOTER + ";" +
             "required-after:" + Mods.Names.GT_TOP_ADDITION + ";")
public class GTTOPAdditionExtended {

    @Mod.Instance
    public static GTTOPAdditionExtended instance;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        TOPIntegration.init();
    }

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Tags.MODID)) {
            ConfigManager.sync(Tags.MODID, Config.Type.INSTANCE);
        }
    }
}
