package org.zornco.reploidcraft.item;

import net.minecraft.item.ItemStack;

public interface IColorable {

	/**
	 * Return the color for the specified armor ItemStack.
	 */
	float[] getColorF3(ItemStack stack);

	/**
	 * Return the color for the specified armor ItemStack.
	 */
	int getColorIndex(ItemStack stack);

	/**
	 * Sets the color of the specified armor ItemStack
	 */
	void setColorIndex(ItemStack stack, int color);

}