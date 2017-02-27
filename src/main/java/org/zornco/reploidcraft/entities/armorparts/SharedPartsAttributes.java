package org.zornco.reploidcraft.entities.armorparts;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import org.zornco.reploidcraft.ReploidCraft;

public class SharedPartsAttributes {
	public static final IAttribute maxHealth = (new RangedAttribute((IAttribute)null, "maxHealth", 20.0D, 0.0D, Double.MAX_VALUE)).setDescription("Max Health").setShouldWatch(true);
	public static final IAttribute range = (new RangedAttribute((IAttribute)null, "range", 32.0D, 0.0D, 2048.0D)).setDescription("Range");
	public static final IAttribute knockback = (new RangedAttribute((IAttribute)null, "knockback", 0.0D, 0.0D, 1.0D)).setDescription("Knockback");
	public static final IAttribute speed = (new RangedAttribute((IAttribute)null, "speed", 0.699999988079071D, 0.0D, Double.MAX_VALUE)).setDescription("Speed").setShouldWatch(true);
	public static final IAttribute attackDamage = new RangedAttribute((IAttribute)null, "attackDamage", 2.0D, 0.0D, Double.MAX_VALUE);

	
	/**
     * Creates an NBTTagList from a BaseAttributeMap, including all its AttributeInstances
     */
    public static NBTTagList writeBaseAttributeMapToNBT(AbstractAttributeMap map)
    {
        NBTTagList nbttaglist = new NBTTagList();

        for (IAttributeInstance iattributeinstance : map.getAllAttributes())
        {
            nbttaglist.appendTag(writeAttributeInstanceToNBT(iattributeinstance));
        }

        return nbttaglist;
    }

    /**
     * Creates an NBTTagCompound from an AttributeInstance, including its AttributeModifiers
     */
    private static NBTTagCompound writeAttributeInstanceToNBT(IAttributeInstance instance)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        IAttribute iattribute = instance.getAttribute();
        nbttagcompound.setString("Name", iattribute.getName());
        nbttagcompound.setDouble("Base", instance.getBaseValue());
        Collection<AttributeModifier> collection = instance.getModifiers();

        if (collection != null && !collection.isEmpty())
        {
            NBTTagList nbttaglist = new NBTTagList();

            for (AttributeModifier attributemodifier : collection)
            {
                if (attributemodifier.isSaved())
                {
                    nbttaglist.appendTag(writeAttributeModifierToNBT(attributemodifier));
                }
            }

            nbttagcompound.setTag("Modifiers", nbttaglist);
        }

        return nbttagcompound;
    }

    /**
     * Creates an NBTTagCompound from an AttributeModifier
     */
    public static NBTTagCompound writeAttributeModifierToNBT(AttributeModifier modifier)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setString("Name", modifier.getName());
        nbttagcompound.setDouble("Amount", modifier.getAmount());
        nbttagcompound.setInteger("Operation", modifier.getOperation());
        nbttagcompound.setUniqueId("UUID", modifier.getID());
        return nbttagcompound;
    }

    public static void setAttributeModifiers(AbstractAttributeMap map, NBTTagList list)
    {
        for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = list.getCompoundTagAt(i);
            IAttributeInstance iattributeinstance = map.getAttributeInstanceByName(nbttagcompound.getString("Name"));

            if (iattributeinstance == null)
            {
                ReploidCraft.logger.warn("Ignoring unknown attribute \'{}\'", new Object[] {nbttagcompound.getString("Name")});
            }
            else
            {
                applyModifiersToAttributeInstance(iattributeinstance, nbttagcompound);
            }
        }
    }

    private static void applyModifiersToAttributeInstance(IAttributeInstance instance, NBTTagCompound compound)
    {
        instance.setBaseValue(compound.getDouble("Base"));

        if (compound.hasKey("Modifiers", 9))
        {
            NBTTagList nbttaglist = compound.getTagList("Modifiers", 10);

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                AttributeModifier attributemodifier = readAttributeModifierFromNBT(nbttaglist.getCompoundTagAt(i));

                if (attributemodifier != null)
                {
                    AttributeModifier attributemodifier1 = instance.getModifier(attributemodifier.getID());

                    if (attributemodifier1 != null)
                    {
                        instance.removeModifier(attributemodifier1);
                    }

                    instance.applyModifier(attributemodifier);
                }
            }
        }
    }

    /**
     * Creates an AttributeModifier from an NBTTagCompound
     */
    @Nullable
    public static AttributeModifier readAttributeModifierFromNBT(NBTTagCompound compound)
    {
        UUID uuid = compound.getUniqueId("UUID");

        try
        {
            return new AttributeModifier(uuid, compound.getString("Name"), compound.getDouble("Amount"), compound.getInteger("Operation"));
        }
        catch (Exception exception)
        {
        	ReploidCraft.logger.warn("Unable to create attribute: {}", new Object[] {exception.getMessage()});
            return null;
        }
    }
}
