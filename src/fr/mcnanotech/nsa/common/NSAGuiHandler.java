package fr.mcnanotech.nsa.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import fr.mcnanotech.nsa.client.GuiHyperCrystalizer;
import fr.mcnanotech.nsa.common.container.ContainerHyperCrystalizer;
import fr.mcnanotech.nsa.common.tileentity.TileEntityHyperCrystalizer;

public class NSAGuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityHyperCrystalizer)
        {
            return new ContainerHyperCrystalizer((TileEntityHyperCrystalizer)tile, player.inventory, world);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityHyperCrystalizer)
        {
            return new GuiHyperCrystalizer((TileEntityHyperCrystalizer)tile, player, world);
        }
        return null;
    }
}