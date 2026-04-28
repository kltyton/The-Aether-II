package com.aetherteam.aetherii.data.resources.registries;

import com.aetherteam.aetherii.AetherII;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ARGB;
import net.minecraft.util.EasingType;
import net.minecraft.util.TriState;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.modifier.BooleanModifier;
import net.minecraft.world.attribute.modifier.ColorModifier;
import net.minecraft.world.attribute.modifier.FloatModifier;
import net.minecraft.world.clock.ClockTimeMarkers;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.timeline.Timeline;

public class AetherIITimelines {
    public static final ResourceKey<Timeline> HOLY_ISLES_DAY = createKey("holy_isles_day");

    public static int NIGHT_SKY_LIGHT_COLOR = ARGB.colorFromFloat(1.0F, 0.48F, 0.48F, 1.0F);
    public static int NIGHT_FOG_COLOR_MULTIPLIER = ARGB.colorFromFloat(1.0F, 0.06F, 0.06F, 0.09F);
    public static int NIGHT_CLOUD_COLOR_MULTIPLIER = ARGB.colorFromFloat(1.0F, 0.1F, 0.1F, 0.15F);

    private static ResourceKey<Timeline> createKey(String name) {
        return ResourceKey.create(Registries.TIMELINE, Identifier.fromNamespaceAndPath(AetherII.MODID, name));
    }

    public static void bootstrap(BootstrapContext<Timeline> context) {
        HolderGetter<WorldClock> clocks = context.lookup(Registries.WORLD_CLOCK);
        Holder.Reference<WorldClock> overworldClock = clocks.getOrThrow(WorldClocks.OVERWORLD);
        EasingType skyAngleEase = EasingType.symmetricCubicBezier(0.362F, 0.241F);
        int nightStart = 12600;
        int nightEnd = 23401;
        int noon = 6000;
        context.register(
                HOLY_ISLES_DAY,
                Timeline.builder(overworldClock)
                        .setPeriodTicks(24000)
//                        .addTimeMarker(ClockTimeMarkers.DAY, 1000, true)
//                        .addTimeMarker(ClockTimeMarkers.NOON, 6000, true)
//                        .addTimeMarker(ClockTimeMarkers.NIGHT, 13000, true)
//                        .addTimeMarker(ClockTimeMarkers.MIDNIGHT, 18000, true)
//                        .addTimeMarker(ClockTimeMarkers.WAKE_UP_FROM_SLEEP, 0)
//                        .addTimeMarker(ClockTimeMarkers.ROLL_VILLAGE_SIEGE, 18000)
                        .addTrack(EnvironmentAttributes.SUN_ANGLE, track -> track.setEasing(skyAngleEase).addKeyframe(6000, 360.0F).addKeyframe(6000, 0.0F))
                        .addTrack(EnvironmentAttributes.MOON_ANGLE, track -> track.setEasing(skyAngleEase).addKeyframe(6000, 540.0F).addKeyframe(6000, 180.0F))
                        .addTrack(EnvironmentAttributes.STAR_ANGLE, track -> track.setEasing(skyAngleEase).addKeyframe(6000, 360.0F).addKeyframe(6000, 0.0F))
                        .addModifierTrack(
                                EnvironmentAttributes.FIREFLY_BUSH_SOUNDS, BooleanModifier.OR, track -> track.addKeyframe(12600, true).addKeyframe(23401, false)
                        )
                        .addModifierTrack(
                                EnvironmentAttributes.FOG_COLOR,
                                ColorModifier.MULTIPLY_RGB,
                                track -> track.addKeyframe(133, -1)
                                        .addKeyframe(11867, -1)
                                        .addKeyframe(13670, NIGHT_FOG_COLOR_MULTIPLIER)
                                        .addKeyframe(22330, NIGHT_FOG_COLOR_MULTIPLIER)
                        )
                        .addModifierTrack(
                                EnvironmentAttributes.SKY_COLOR,
                                ColorModifier.MULTIPLY_RGB,
                                track -> track.addKeyframe(133, -1).addKeyframe(11867, -1).addKeyframe(13670, -16777216).addKeyframe(22330, -16777216)
                        )
                        .addModifierTrack(
                                EnvironmentAttributes.SKY_LIGHT_COLOR,
                                ColorModifier.MULTIPLY_RGB,
                                track -> track.addKeyframe(730, -1)
                                        .addKeyframe(11270, -1)
                                        .addKeyframe(13140, NIGHT_SKY_LIGHT_COLOR)
                                        .addKeyframe(22860, NIGHT_SKY_LIGHT_COLOR)
                        )
                        .addModifierTrack(
                                EnvironmentAttributes.SKY_LIGHT_FACTOR,
                                FloatModifier.MULTIPLY,
                                track -> track.addKeyframe(730, 1.0F).addKeyframe(11270, 1.0F).addKeyframe(13140, 0.24F).addKeyframe(22860, 0.24F)
                        )
                        .addModifierTrack(
                                EnvironmentAttributes.SKY_LIGHT_LEVEL,
                                FloatModifier.MULTIPLY,
                                track -> track.addKeyframe(133, 1.0F).addKeyframe(11867, 1.0F).addKeyframe(13670, 0.26666668F).addKeyframe(22330, 0.26666668F)
                        )
                        .addTrack(
                                EnvironmentAttributes.SUNRISE_SUNSET_COLOR,
                                track -> track.addKeyframe(71, 2032886374)
                                        .addKeyframe(310, 2083217254)
                                        .addKeyframe(565, -2094376602)
                                        .addKeyframe(730, -1977002906)
                                        .addKeyframe(11270, -1955684762)
                                        .addKeyframe(11397, -2056413082)
                                        .addKeyframe(11522, -2140298906)
                                        .addKeyframe(11690, 2087494502)
                                        .addKeyframe(11929, 2037163110)
                                        .addKeyframe(12243, 2070717286)
                                        .addKeyframe(12358, 2104271462)
                                        .addKeyframe(12512, -2106744474)
                                        .addKeyframe(12613, -2056413338)
                                        .addKeyframe(12732, -1955684762)
                                        .addKeyframe(12841, -1871799194)
                                        .addKeyframe(13035, -1670408090)
                                        .addKeyframe(13252, -1418620058)
                                        .addKeyframe(13775, -730430618)
                                        .addKeyframe(13888, -579370906)
                                        .addKeyframe(14039, -411468954)
                                        .addKeyframe(14192, -293898906)
                                        .addKeyframe(21807, -300094362)
                                        .addKeyframe(21961, -417399962)
                                        .addKeyframe(22112, -585103258)
                                        .addKeyframe(22225, -736030106)
                                        .addKeyframe(22748, -1423558298)
                                        .addKeyframe(22965, -1675082138)
                                        .addKeyframe(23159, -1876340890)
                                        .addKeyframe(23272, -1977002906)
                                        .addKeyframe(23488, -2111153306)
                                        .addKeyframe(23642, 2099994214)
                                        .addKeyframe(23757, 2066440294)
                        )
                        .addModifierTrack(
                                EnvironmentAttributes.STAR_BRIGHTNESS,
                                FloatModifier.MAXIMUM,
                                track -> track.addKeyframe(92, 0.037F)
                                        .addKeyframe(627, 0.0F)
                                        .addKeyframe(11373, 0.0F)
                                        .addKeyframe(11732, 0.016F)
                                        .addKeyframe(11959, 0.044F)
                                        .addKeyframe(12399, 0.143F)
                                        .addKeyframe(12729, 0.258F)
                                        .addKeyframe(13228, 0.5F)
                                        .addKeyframe(22772, 0.5F)
                                        .addKeyframe(23032, 0.364F)
                                        .addKeyframe(23356, 0.225F)
                                        .addKeyframe(23758, 0.101F)
                        )
                        .addModifierTrack(
                                EnvironmentAttributes.CLOUD_COLOR,
                                ColorModifier.MULTIPLY_ARGB,
                                track -> track.addKeyframe(133, -1)
                                        .addKeyframe(11867, -1)
                                        .addKeyframe(13670, NIGHT_CLOUD_COLOR_MULTIPLIER)
                                        .addKeyframe(22330, NIGHT_CLOUD_COLOR_MULTIPLIER)
                        )
                        .addTrack(EnvironmentAttributes.EYEBLOSSOM_OPEN, track -> track.addKeyframe(12600, TriState.TRUE).addKeyframe(23401, TriState.FALSE))
                        .addModifierTrack(EnvironmentAttributes.CREAKING_ACTIVE, BooleanModifier.OR, track -> track.addKeyframe(12600, true).addKeyframe(23401, false))
                        .addModifierTrack(
                                EnvironmentAttributes.TURTLE_EGG_HATCH_CHANCE,
                                FloatModifier.MAXIMUM,
                                track -> track.setEasing(EasingType.CONSTANT).addKeyframe(21062, 1.0F).addKeyframe(21905, 0.002F)
                        )
                        .addModifierTrack(
                                EnvironmentAttributes.CAT_WAKING_UP_GIFT_CHANCE,
                                FloatModifier.MAXIMUM,
                                track -> track.setEasing(EasingType.CONSTANT).addKeyframe(362, 0.0F).addKeyframe(23667, 0.7F)
                        )
                        .addModifierTrack(
                                EnvironmentAttributes.BEES_STAY_IN_HIVE, BooleanModifier.OR, track -> track.addKeyframe(12542, true).addKeyframe(23460, false)
                        )
                        .addModifierTrack(EnvironmentAttributes.MONSTERS_BURN, BooleanModifier.OR, track -> track.addKeyframe(12542, false).addKeyframe(23460, true))
                        .build()
        );
    }
}
