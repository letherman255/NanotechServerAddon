package fr.mcnanotech.nsa.common;

import fr.mcnanotech.nsa.common.tileentity.TileEntityHyperCrystalizer;
import fr.mcnanotech.nsa.proxy.CommonProxy;
import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import ic2.core.IC2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = NanotechServerAddon.MODID, name = "Nanotech Server Addon", version = "@VERSION@")
public class NanotechServerAddon
{

	public static final String MODID = "NSA";
	public static boolean isICLoaded;
	public static Block overworldQuartz;
	public static Block hyperCrystalizer;

	@Instance(MODID)
	public static NanotechServerAddon instence;

	@SidedProxy(clientSide = "fr.mcnanotech.nsa.proxy.ClientProxy", serverSide = "fr.mcnanotech.nsa.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.registerRender();
		overworldQuartz = new OverWorldQuartz(Material.rock).setBlockName("overWorldQuartz").setHardness(3.0F).setResistance(5.0F).setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName(MODID + ":overWorldQuartz");
		hyperCrystalizer = new HyperCrystalizer(Material.iron).setBlockName("hyperCrystalizer").setHardness(3.0F).setResistance(5.0F).setCreativeTab(CreativeTabs.tabRedstone);
		GameRegistry.registerBlock(overworldQuartz, "overWorldQuartz");
		GameRegistry.registerBlock(hyperCrystalizer, "hyperCrystalizer");
		isICLoaded = Loader.isModLoaded("IC2");
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerTileEntity(TileEntityHyperCrystalizer.class, MODID + "HyperCrystalizer");
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
		if(isICLoaded)
		{
			this.loadIC2();
		}
		GameRegistry.addRecipe(IC2Items.getItem("reactorCoolantSimple"), new Object[]{" X ", "XcX", " X ",'X', IC2Items.getItem("platetin"), 'c', IC2Items.getItem("waterCell")});
	}

	public void loadIC2()
	{
		Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(Blocks.quartz_ore)), null, new ItemStack(Items.quartz, 5));
		Recipes.macerator.addRecipe(new RecipeInputItemStack(new ItemStack(overworldQuartz)), null, new ItemStack(Items.quartz, 5));
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		GameRegistry.addSmelting(overworldQuartz, new ItemStack(Items.quartz), 0.5f);
	}

}
