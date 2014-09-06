package fr.mcnanotech.nsa.common.container;

import fr.mcnanotech.nsa.common.tileentity.TileEntityHyperCrystalizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;

public class ContainerHyperCrystalizer extends Container
{

    protected TileEntityHyperCrystalizer tileHyperCrystalizer;
    private World worldObj;

    public ContainerHyperCrystalizer(TileEntityHyperCrystalizer tileEntity, InventoryPlayer inventoryPlayer, World world)
    {
        this.worldObj = world;
        this.tileHyperCrystalizer = tileEntity;
        bindPlayerInventory(inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return tileHyperCrystalizer.isUseableByPlayer(player);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        for(int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }
    
    public TileEntityHyperCrystalizer getTile()
    {
        return tileHyperCrystalizer;
    }
}