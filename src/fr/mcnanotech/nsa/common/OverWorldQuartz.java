package fr.mcnanotech.nsa.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class OverWorldQuartz extends Block
{

	protected OverWorldQuartz(Material material)
	{
		super(material);
		// TODO Auto-generated constructor stub
	}

	public int quantityDropped(Random rand)
	{
		return 1 + rand.nextInt(3);
	}

	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return Items.quartz;
	}

	protected boolean canSilkHarvest()
	{
		return true;
	}
	
	private Random rand = new Random();
    public int getExpDrop(IBlockAccess world, int metadata, int fortune)
    {
    	int j1 = MathHelper.getRandomIntegerInRange(rand, 2, 5);
        return j1;
    }

}
