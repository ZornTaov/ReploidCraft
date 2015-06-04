package zornco.reploidcraft.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import zornco.reploidcraft.ReploidCraft;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler {

	public void registerRecipes() {
		/** Recipes **/
		//GameRegistry.addRecipe(new ItemStack(Item.monsterPlacer, 1, metID), new Object[] { "   ", " # ", "xxx", Character.valueOf('#'), Block.dirt , Character.valueOf('x'), Block.planks });

		GameRegistry.addShapelessRecipe(new ItemStack(ReploidCraft.reploidPlate, 1, 16), // basic plate
				new Object[]{ Items.iron_ingot, Items.redstone } );
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
			addShapelessOreRecipe(new ItemStack(ReploidCraft.reploidPlate, 1, i), // blue plate
					new Object[]{new ItemStack(ReploidCraft.reploidPlate, 1, 16), dyes[i] } );

		GameRegistry.addShapelessRecipe(new ItemStack(ReploidCraft.reploidPlate, 1, 15), // white plate	
				new Object[]{ new ItemStack(ReploidCraft.component, 1, 0), new ItemStack(ReploidCraft.reploidPlate, 1, 4) } );

		GameRegistry.addRecipe(new ItemStack(ReploidCraft.component, 4, 0), //diamond dust
				new Object[] { 
			"ttt", "tdt", "ttt", 
			Character.valueOf('t'), Blocks.tnt, 
			Character.valueOf('d'), Items.diamond // diamond dust\
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraft.component, 1, 1), // AEDS
				new Object[] { 
			"rdr", "dpd", "rdr", 
			Character.valueOf('r'), Items.redstone, 
			Character.valueOf('d'), new ItemStack(ReploidCraft.component, 1, 0), // diamond dust
			Character.valueOf('p'), Items.quartz // use quartz block
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraft.healthTank, 1, 30), 
				new Object[] { 
			"ihi", "bHb", "bdb", 
			Character.valueOf('i'), Items.iron_ingot, 
			Character.valueOf('b'), new ItemStack(ReploidCraft.reploidPlate, 1, 4),
			Character.valueOf('h'), ReploidCraft.healthBit,
			Character.valueOf('H'), ReploidCraft.healthByte,
			Character.valueOf('d'), Items.diamond
		}
				);

		GameRegistry.addRecipe(new ItemStack(ReploidCraft.spikes, 8), 
				new Object[] { 
			" i ", " i ", "bbb", 
			Character.valueOf('i'), new ItemStack(ReploidCraft.reploidPlate, 1, 7), 
			Character.valueOf('b'), Blocks.iron_block
		}
				);
		
		GameRegistry.addRecipe(new ItemStack(ReploidCraft.component, 1, 9), 
				new Object[] { 
			" i ", " b ", "i i", 
			Character.valueOf('i'), new ItemStack(ReploidCraft.reploidPlate, 1, 7), 
			Character.valueOf('b'), Items.iron_ingot
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

	@SuppressWarnings("unchecked")
	public static void addOreRecipe(ItemStack output, Object[] input) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, new Object[] { Boolean.valueOf(true), input }));
	}

	@SuppressWarnings("unchecked")
	public static void addShapelessOreRecipe(ItemStack output, Object[] input)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, input));
	}
}
