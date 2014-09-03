package fr.mcnanotech.nsa.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHyperCrystalizer extends TileEntity {
	
	private int value;

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);
		this.value=compound.getInteger("value");
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {

		super.writeToNBT(compound);
		compound.setInteger("value", this.value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	

}
