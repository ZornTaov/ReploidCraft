package zornco.reploidcraftenv.blocks;

import zornco.reploidcraftenv.ReploidCraftEnv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerMechBay extends Container {
	private TileEntityMechBay mechBay;
	public ContainerMechBay(IInventory tileEntity, IInventory ip) {
		if(((TileMultiBlock) tileEntity).hasMaster() && !((TileMultiBlock) tileEntity).isMaster())
		{
			this.mechBay = (TileEntityMechBay) ReploidCraftEnv.proxy.getClientWorld().getTileEntity(((TileMultiBlock) tileEntity).getMasterX(), ((TileMultiBlock) tileEntity).getMasterY(), ((TileMultiBlock) tileEntity).getMasterZ());
		}
		else
		{
			this.mechBay = (TileEntityMechBay) tileEntity;
		}
		this.mechBay.openInventory();
		this.mechBay.eventHandler = this;
		//this.addSlotToContainer(new SlotCrafting(ip.player, this.mechBay, this.mechBay, 0, 124, 35));
        int l;
        int i1;
 
        for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new Slot(this.mechBay, i1 + l * 3, 8 + i1 * 18, 17 + l * 18));
            }
        }

        for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 9; ++i1)
            {
                this.addSlotToContainer(new Slot(ip, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l)
        {
            this.addSlotToContainer(new Slot(ip, l, 8 + l * 18, 142));
        }
        
        this.onCraftMatrixChanged(this.mechBay);
	}
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return this.mechBay.isUseableByPlayer(var1);
	}
	@Override
    public ItemStack transferStackInSlot(EntityPlayer p, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(i);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i < 1)
            {
                if (!mergeItemStack(itemstack1, 1, inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, 0, 1, false))
            {
                return null;
            }
            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		
		if(!this.mechBay.getWorldObj().isRemote)
		{
			//this.mechBay.closeInventory();
			this.mechBay.eventHandler = null;
		}
	}
	public TileEntityMechBay getMechBay() {
		return mechBay;
	}

}
