package zornco.reploidcraftenv.crafting;

import zornco.reploidcraftenv.ReploidCraftEnv;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler {

	public void registerRecipes() {
		/** Recipes **/
		//GameRegistry.addRecipe(new ItemStack(Item.monsterPlacer, 1, metID), new Object[] { "   ", " # ", "xxx", Character.valueOf('#'), Block.dirt , Character.valueOf('x'), Block.planks });

		GameRegistry.addShapelessRecipe(new ItemStack(ReploidCraftEnv.reploidPlate, 1, 16), // basic plate
				new Object[]{ Item.ingotIron, Item.redstone } );
		String[] dyes = 
			{
				"dyeBlack",
				"dyeRed",
				"dyeGreen",
				"dyeBrown",
				"dyeBlue",
				"dyePurple",
				"dyeCyan",
				"dyeLightGray",
				"dyeGray",
				"dyePink",
				"dyeLime",
				"dyeYellow",
				"dyeLightBlue",
				"dyeMagenta",
				"dyeOrange"
			};
		for(int i = 0; i < 15; i++)
			addShapelessOreRecipe(new ItemStack(ReploidCraftEnv.reploidPlate, 1, i), // blue plate
					new Object[]{new ItemStack(ReploidCraftEnv.reploidPlate, 1, 16), dyes[i] } );

		GameRegistry.addShapelessRecipe(new ItemStack(ReploidCraftEnv.reploidPlate, 1, 15), // white plate	
				new Object[]{ new ItemStack(ReploidCraftEnv.component, 1, 0), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 4) } );

		GameRegistry.addRecipe(new ItemStack(ReploidCraftEnv.component, 4, 0), //diamond dust
				new Object[] { 
			"ttt", "tdt", "ttt", 
			Character.valueOf('t'), Block.tnt, 
			Character.valueOf('d'), Item.diamond // diamond dust\
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraftEnv.component, 1, 1), // AEDS
				new Object[] { 
			"rdr", "dpd", "rdr", 
			Character.valueOf('r'), Item.redstone, 
			Character.valueOf('d'), new ItemStack(ReploidCraftEnv.component, 1, 0), // diamond dust
			Character.valueOf('p'), Item.netherQuartz // use quartz block
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraftEnv.healthTank, 1, 30), 
				new Object[] { 
			"ihi", "bHb", "bdb", 
			Character.valueOf('i'), Item.ingotIron, 
			Character.valueOf('b'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 4),
			Character.valueOf('h'), ReploidCraftEnv.healthBit,
			Character.valueOf('H'), ReploidCraftEnv.healthByte,
			Character.valueOf('d'), Item.diamond
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraftEnv.spikes, 8), 
				new Object[] { 
			" i ", " i ", "bbb", 
			Character.valueOf('i'), new ItemStack(ReploidCraftEnv.reploidPlate, 1, 7), 
			Character.valueOf('b'), Block.blockIron
		}
				);

		/*GameRegistry.addRecipe(new ItemStack(doorBossItem, 1), 
				new Object[] { 
			"ipi", "i i", "ipi", 
			Character.valueOf('i'), Item.ingotIron, 
			Character.valueOf('b'), Block.blockSteel
			}
		);*/

		/*GameRegistry.addRecipe(new ItemStack(weaponTank), 
		new Object[] { 
			"iwi", "bWb", "bdb", 
			Character.valueOf('i'), Item.ingotIron, 
			Character.valueOf('b'), blueReploidPlate,
			Character.valueOf('w'), weaponBit,
			Character.valueOf('W'), weaponByte,
			Character.valueOf('d'), Item.diamond
			}
		);*/
	}

	public static void addOreRecipe(ItemStack output, Object[] input) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, new Object[] { Boolean.valueOf(true), input }));
	}

	public static void addShapelessOreRecipe(ItemStack output, Object[] input)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, input));
	}
}
