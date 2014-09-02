package fr.mcnanotech.nsa.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mcnanotech.nsa.common.tileentity.TileEntityHyperCrystalizer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class HyperCrystalizer extends Block
{
	private IIcon top, bottom, front, left, right, back;
	

	protected HyperCrystalizer(Material material) {
		
		super(material);
		
	}
	
    public void registerBlockIcons(IIconRegister iiconregisters)
    {
    	this.blockIcon = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer");
    	this.top = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer/top");
    	this.bottom = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer/bottom");
    	this.front = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer/front");
    	this.left = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer/left");
    	this.right = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer/right");
    	this.back = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer/back");
    }
    
    public IIcon getIcon(int side, int metadata)
    {
        if(side == 0)
        {
        	return this.bottom;
        }
        else if(side == 1)
        {
        	return this.top;
        }
        else if(side == 2)
        {
        	return this.front;
        }
        else if(side == 3)
        {
        	return this.back;
        }
        else if(side == 4)
        {
        	return this.right;
        }
        else if(side == 5)
        {
        	return this.left;
        }
        return this.blockIcon;
    }

	@Override
	public boolean hasTileEntity(int metadata) {

		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {

		return new TileEntityHyperCrystalizer();
	}

	public boolean onBlockActivated(World world, int x,int y, int z, EntityPlayer player,int side, float hitX, float hitY,float hitZ) {
		if(!world.isRemote)
		{
		
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileEntityHyperCrystalizer)
		{
			TileEntityHyperCrystalizer TileEntity = (TileEntityHyperCrystalizer)tile;
			if(side == 0)
			{
				TileEntity.decrease();
			}
			else if(side == 1)
			{
				TileEntity.increase();
			}
			else
			{
				player.addChatMessage(new ChatComponentText("Number = " + TileEntity.getNumber()));
				
				return true;
			}
		}
		}
		return false;
	}

}
