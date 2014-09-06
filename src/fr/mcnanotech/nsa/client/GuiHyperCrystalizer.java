package fr.mcnanotech.nsa.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import fr.mcnanotech.nsa.common.container.ContainerHyperCrystalizer;
import fr.mcnanotech.nsa.common.tileentity.TileEntityHyperCrystalizer;

public class GuiHyperCrystalizer extends GuiContainer
{
    private TileEntityHyperCrystalizer tile;
    private EntityPlayer player;
    private static final ResourceLocation field_147017_u = new ResourceLocation("textures/gui/container/generic_54.png");
    
    public GuiHyperCrystalizer(TileEntityHyperCrystalizer tile, EntityPlayer player, World world)
    {
        super(new ContainerHyperCrystalizer(tile, player.inventory, world));
        this.player = player;
    }
    
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
       // this.fontRendererObj.drawString(this.tile.hasCustomInventoryName() ? this.tile.getInventoryName() : I18n.format(this.tile.getInventoryName()), 8, 6, 4210752);
        // this.fontRendererObj.drawString(this.tile.hasCustomInventoryName() ? this.tile.getInventoryName() : I18n.format(this.tile.getInventoryName()), 8, 6, 4210752);
        // this.fontRendererObj.drawString(this.tile.hasCustomInventoryName() ? this.tile.getInventoryName() : I18n.format(this.tile.getInventoryName()), 8, 6, 4210752);
    }
       // this.fontRendererObj.drawString(this.player.inventory.hasCustomInventoryName() ? this.player.inventory.getInventoryName() : I18n.format(this.player.inventory.getInventoryName(), new Object[0]), 8, this.ySize - 96 + 2, 4210752);

    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(field_147017_u);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 3 * 18 + 17);
        this.drawTexturedModalRect(k, l + 3 * 18 + 17, 0, 126, this.xSize, 96);
    }
}