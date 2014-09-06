package fr.mcnanotech.nsa.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mcnanotech.nsa.common.tileentity.TileEntityHyperCrystalizer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class HyperCrystalizer extends Block
{
    private IIcon bottom, front, side;

    protected HyperCrystalizer(Material material)
    {
        super(material);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            player.openGui(NanotechServerAddon.instance, 0, world, x, y, z);
        }
        return true;
    }

    public void registerBlockIcons(IIconRegister iiconregisters)
    {
        this.blockIcon = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer");
        this.bottom = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer/bottom");
        this.front = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer/front");
        this.side = iiconregisters.registerIcon(NanotechServerAddon.MODID + ":hypercrystalizer/side");
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        if(stack.hasDisplayName())
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if(tile instanceof TileEntityHyperCrystalizer)
            {
                ((TileEntityHyperCrystalizer)tile).setCustomName(stack.getDisplayName());
            }
        }
        int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        if((side == 3 && metadata == 0) || (side == 4 && metadata == 1) || (side == 2 && metadata == 2) || (side == 5 && metadata == 3))
        {
            return this.front;
        }

        if(side == 0 || side == 1)
        {
            return this.bottom;
        }
        return this.side;
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {

        return new TileEntityHyperCrystalizer();
    }

}
