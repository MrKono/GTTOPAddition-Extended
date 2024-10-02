package kono.ceu.gttopaextended;

import java.util.List;

import com.google.common.collect.Lists;

import zone.rong.mixinbooter.ILateMixinLoader;

public class GTTOPAExtendedLateMixinLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        return Lists.newArrayList("mixins.gttopadditionextended.json");
    }
}
