package fr.mcnanotech.nsa.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class TileEntityHyperCrystalizer extends TileEntity implements IInventory
{
    private int value;
    private ItemStack[] contents = new ItemStack[3];
    private String customName;

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.value = compound.getInteger("value");

        if(compound.hasKey("customName", Constants.NBT.TAG_STRING))
        {
            this.customName = compound.getString("customName");
        }

        NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if(j >= 0 && j < this.contents.length)
            {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("value", this.value);

        if(this.customName != null)
        {
            compound.setString("customName", this.customName);
        }

        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < this.contents.length; ++i)
        {
            if(this.contents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        compound.setTag("Items", nbttaglist);
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public int getSizeInventory()
    {
        return this.contents.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotId)
    {
        return this.contents[slotId];
    }

    @Override
    public ItemStack decrStackSize(int slotId, int amount)
    {
        if(this.contents[slotId] != null)
        {
            ItemStack itemstack;

            if(this.contents[slotId].stackSize <= amount)
            {
                itemstack = this.contents[slotId];
                this.contents[slotId] = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.contents[slotId].splitStack(amount);

                if(this.contents[slotId].stackSize == 0)
                {
                    this.contents[slotId] = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotId)
    {
        if(this.contents[slotId] != null)
        {
            ItemStack itemstack = this.contents[slotId];
            this.contents[slotId] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotId, ItemStack stack)
    {
        this.contents[slotId] = stack;

        if(stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return this.customName;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return true;
    }

}
