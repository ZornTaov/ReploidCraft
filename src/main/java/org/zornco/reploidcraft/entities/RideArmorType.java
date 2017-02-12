package org.zornco.reploidcraft.entities;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum RideArmorType
{
	RIDEARMOR("rideArmor", "rideArmor_white", null, SoundEvents.ENTITY_IRONGOLEM_HURT, SoundEvents.ENTITY_IRONGOLEM_DEATH, LootTableList.ENTITIES_IRON_GOLEM),
	FROGARMOR("frogArmor", "frogArmor_white", null, SoundEvents.ENTITY_IRONGOLEM_HURT, SoundEvents.ENTITY_IRONGOLEM_DEATH, LootTableList.ENTITIES_IRON_GOLEM);
    
    /** The default name for this type of rideArmor */
    private final TextComponentTranslation rideArmorname;
    /** The default texture used by this type of rideArmor */
    private final ResourceLocation texture;
    private final SoundEvent hurtSound;
    private final SoundEvent ambientSound;
    private final SoundEvent deathSound;
    private ResourceLocation lootTable;

    private RideArmorType(String p_i46798_3_, String textureName, SoundEvent ambientSound, SoundEvent hurtSoundIn, SoundEvent deathSoundIn, ResourceLocation lootTableIn)
    {
        this.lootTable = lootTableIn;
        this.rideArmorname = new TextComponentTranslation("entity." + p_i46798_3_ + ".name", new Object[0]);
        this.texture = new ResourceLocation("textures/entity/rideArmor/" + textureName + ".png");
        this.hurtSound = hurtSoundIn;
        this.ambientSound = ambientSound;
        this.deathSound = deathSoundIn;
    }

    public SoundEvent getAmbientSound()
    {
        return this.ambientSound;
    }

    public SoundEvent getHurtSound()
    {
        return this.hurtSound;
    }

    public SoundEvent getDeathSound()
    {
        return this.deathSound;
    }

    /**
     * Gets the default name for rideArmors of this type
     */
    public TextComponentTranslation getDefaultName()
    {
        return this.rideArmorname;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture()
    {
        return this.texture;
    }
    
    public boolean isRideArmor()
    {
        return this == RIDEARMOR;
    }
    
    public boolean canWalk()
    {
        return this != FROGARMOR;
    }

    public int getOrdinal()
    {
        return this.ordinal();
    }

    public static RideArmorType getArmorType(int armorID)
    {
        return values()[armorID];
    }

    public ResourceLocation getLootTable()
    {
        return this.lootTable;
    }
}
