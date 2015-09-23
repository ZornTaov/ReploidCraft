package zornco.reploidcraft.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.items.ItemComponent.Components;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler {

	public void registerRecipes() {
		/** Recipes **/
		//GameRegistry.addRecipe(new ItemStack(Item.monsterPlacer, 1, metID), new Object[] { "   ", " # ", "xxx", Character.valueOf('#'), Block.dirt , Character.valueOf('x'), Block.planks });
		/** Plates **/
		addShapelessOreRecipe(new ItemStack(ReploidCraft.reploidPlate, 1, 16), // basic plate
			new Object[]{ "ingotIron", "dustRedstone" } );
		
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
			addShapelessOreRecipe(new ItemStack(ReploidCraft.reploidPlate, 1, i), // colored plate
				new Object[] { new ItemStack(ReploidCraft.reploidPlate, 1, 16), dyes[i] } );

		GameRegistry.addShapelessRecipe(new ItemStack(ReploidCraft.reploidPlate, 1, 15), // white(enhanced) plate	
			new Object[] { new ItemStack(ReploidCraft.component, 1, Components.diamondDust.getIndex()), new ItemStack(ReploidCraft.reploidPlate, 1, 4) } );

		/** Components **/
		addOreRecipe(new ItemStack(ReploidCraft.component, 4, Components.diamondDust.getIndex()),
			new Object[] { 
				"ttt", 
				"tdt", 
				"ttt", 
				Character.valueOf('t'), Blocks.tnt, 
				Character.valueOf('d'), "gemDiamond"
			}
		);

		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.AEGD.getIndex()),
			new Object[] { 
				"rdr", 
				"dpd", 
				"rdr", 
				Character.valueOf('r'), "dustRedstone", 
				Character.valueOf('d'), new ItemStack(ReploidCraft.component, 1, Components.diamondDust.getIndex()), // diamond dust
				Character.valueOf('p'), Items.quartz // use quartz block
			} 
		);
		
		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.motor.getIndex()), 
			new Object[] { 
				"brb", 
				"rir", 
				"brb", 
				Character.valueOf('b'), new ItemStack( ReploidCraft.reploidPlate, 1, 16 ),
				Character.valueOf('r'), "dustRedstone", 
				Character.valueOf('i'), "ingotIron"
			} 
		);
		
		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.joint.getIndex()), 
			new Object[] { 
				" bi", 
				"bmb", 
				" bi", 
				Character.valueOf('b'), new ItemStack( ReploidCraft.reploidPlate, 1, 16 ),
				Character.valueOf('m'), new ItemStack( ReploidCraft.component, 1, Components.motor.getIndex() ), 
				Character.valueOf('i'), "ingotIron"
			} 
		);
		
		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.socket.getIndex()), 
			new Object[] { 
				"i ", 
				"sb", 
				"i ", 
				Character.valueOf('b'), new ItemStack( ReploidCraft.reploidPlate, 1, 16 ), 
				Character.valueOf('i'), "ingotIron", 
				Character.valueOf('s'), Blocks.sticky_piston
			} 
		);
		
		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.coupling.getIndex()), 
			new Object[] { 
				"ibs", 
				Character.valueOf('b'), new ItemStack( ReploidCraft.reploidPlate, 1, 16 ), 
				Character.valueOf('i'), "ingotIron", 
				Character.valueOf('s'), Blocks.sticky_piston
			} 
		);

		//Character.valueOf('g'), new ItemStack( ReploidCraft.reploidPlate, 1, 10 ), // lime plate
		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.cockpit.getIndex()), 
			new Object[] { 
				" w ", 
				"lwl", 
				"p p", 
				Character.valueOf('w'), Blocks.wool, 
				Character.valueOf('l'), Blocks.lever,
				Character.valueOf('p'), Blocks.heavy_weighted_pressure_plate
			} 
		);
			
		GameRegistry.addRecipe(new ItemStack(ReploidCraft.component, 1, Components.vent.getIndex()), 
			new Object[] { 
				" i ", 
				"ibi", 
				" i ", 
				Character.valueOf('i'), new ItemStack( ReploidCraft.reploidPlate, 1, 16 ), 
				Character.valueOf('b'), Blocks.iron_bars
			} 
		);
			
		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.propeller.getIndex()), 
			new Object[] { 
				" i ", 
				" b ", 
				"i i", 
				Character.valueOf('b'), new ItemStack( ReploidCraft.reploidPlate, 1, 16 ), 
				Character.valueOf('i'), "ingotIron" 
			} 
		);
			
		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.fist.getIndex()),
			new Object[] { 
				"ii ", 
				"imc", 
				"ii ", 
				Character.valueOf('i'), "ingotIron", 
				Character.valueOf('c'), new ItemStack( ReploidCraft.component, 1, Components.coupling.getIndex() ),
				Character.valueOf('m'), new ItemStack( ReploidCraft.component, 1, Components.motor.getIndex() )
			} 
		);
		
		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.spikedFist.getIndex()),
			new Object[] { 
				"si ", 
				"imc", 
				"si ", 
				Character.valueOf('i'), "ingotIron", 
				Character.valueOf('c'), new ItemStack( ReploidCraft.component, 1, Components.coupling.getIndex() ),
				Character.valueOf('s'), ReploidCraft.spikes,
				Character.valueOf('m'), new ItemStack( ReploidCraft.component, 1, Components.motor.getIndex() )
			} 
		);
		
		addOreRecipe(new ItemStack(ReploidCraft.component, 1, Components.drill.getIndex()),
			new Object[] { 
				" d ", 
				"sms", 
				"pcp", 
				Character.valueOf('d'), "gemDiamond", 
				Character.valueOf('c'), new ItemStack( ReploidCraft.component, 1, Components.coupling.getIndex() ),
				Character.valueOf('s'), ReploidCraft.spikes,
				Character.valueOf('p'), new ItemStack( ReploidCraft.reploidPlate, 1, 16 ), 
				Character.valueOf('m'), new ItemStack( ReploidCraft.component, 1, Components.motor.getIndex() )
			} 
		);
		
		/** Tanks **/
		addOreRecipe( new ItemStack( ReploidCraft.healthTank, 1, 30 ), 
			new Object[] {
				"ihi", 
				"bHb", 
				"bdb", 
				Character.valueOf('i'), "ingotIron", 
				Character.valueOf('b'), new ItemStack( ReploidCraft.reploidPlate, 1, 4 ), 
				Character.valueOf('h'), ReploidCraft.healthBit, 
				Character.valueOf('H'), ReploidCraft.healthByte, 
				Character.valueOf('d'), Items.diamond 
			} 
		);

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
		
		/** Blocks **/
		addOreRecipe( new ItemStack( ReploidCraft.spikes, 8 ), 
			new Object[] {
				"i", "b", 
				Character.valueOf('i'), new ItemStack( ReploidCraft.reploidPlate, 1, 7 ), 
				Character.valueOf('b'), "blockIron"
			} 
		);
		
		/*GameRegistry.addRecipe(new ItemStack(doorBossItem, 1), 
				new Object[] { 
			"ipi", "i i", "ipi", 
			Character.valueOf('i'), Item.ingotIron, 
			Character.valueOf('b'), Block.blockSteel
			}
		);*/
	}
	
	public static void addOreRecipe(ItemStack output, Object[] recipe) {
		RecipeHandler.addOreRecipe(output, recipe, true);
	}

	@SuppressWarnings("unchecked")
	public static void addOreRecipe(ItemStack output, Object[] recipe, boolean mirror) {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, new Object[] { Boolean.valueOf(mirror), recipe }));
	}

	@SuppressWarnings("unchecked")
	public static void addShapelessOreRecipe(ItemStack output, Object[] input)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, input));
	}

	
}
