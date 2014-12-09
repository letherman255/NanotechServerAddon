package fr.mcnanotech.nsa.common.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandler
{
    @SubscribeEvent
    public void onVillagerDeath(LivingDropsEvent event)
    {
        if(event.entity instanceof EntityVillager)
        {
            event.drops.add(new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(Items.emerald)));
        }
    }
}