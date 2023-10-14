package com.mineblock11.armorful.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.village.raid.Raid;

public class RaidWaveCondition implements LootCondition {
    public static final Codec<RaidWaveCondition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.INT.fieldOf("wave").forGetter(RaidWaveCondition::getWave)
    ).apply(instance, RaidWaveCondition::new));

    public static final LootConditionType WAVE = new LootConditionType(CODEC);

    public int getWave() {
        return wave;
    }

    public final int wave;

    public RaidWaveCondition(int wave) {
        this.wave = wave;
    }

    @Override
    public LootConditionType getType() {
        return WAVE;
    }

    @Override
    public boolean test(LootContext lootContext) {
        RaiderEntity raider = (RaiderEntity) lootContext.get(LootContextParameters.THIS_ENTITY);
        return raider.getRaid().getGroupsSpawned() == wave;
    }

//    public static class WaveSerializer implements JsonSerializer<RaidWaveCondition> {
//        @Override
//        public void toJson(JsonObject json, RaidWaveCondition condition, JsonSerializationContext serializer) {
//            json.addProperty("wave", condition.wave);
//        }
//
//        @Override
//        public RaidWaveCondition fromJson(JsonObject jsonObject, JsonDeserializationContext deserializationContext) {
//            return new RaidWaveCondition(jsonObject.get("wave").getAsInt());
//        }
//    }
}
