package zornco.reploidcraftenv.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class ContainerMechBay extends Container {
	private TileEntityMechBay mechBay;
	private InventoryPlayer ip;
	public ContainerMechBay(TileEntityMechBay tileEntity, InventoryPlayer ip) {
		this.mechBay = tileEntity;
		this.mechBay.eventHandler = this;
		this.ip = ip;
		this.addSlotToContainer(new SlotCrafting(ip.player, this.mechBay, this.mechBay, 0, 124, 35));
        int l;
        int i1;

        for (l = 0; l < 3; ++l)
        {
            for (i1 = 0; i1 < 3; ++i1)
            {
                this.addSlotToContainer(new Slot(this.mechBay, i1 + l * 3, 30 + i1 * 18, 17 + l * 18));
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
		return this.mechBay.getWorldObj().getTileEntity(this.mechBay.xCoord, this.mechBay.yCoord, this.mechBay.zCoord) == this.mechBay;
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		// TODO Auto-generated method stub
		return super.transferStackInSlot(par1EntityPlayer, par2);
	}
	@Override
	public ItemStack slotClick(int par1, int par2, int par3,
			EntityPlayer par4EntityPlayer) {
		// TODO Auto-generated method stub
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
	}
	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		super.onContainerClosed(par1EntityPlayer);
		
		if(!this.mechBay.getWorldObj().isRemote)
		{
			this.mechBay.eventHandler = null;
		}
	}
	@Override
	public void onCraftMatrixChanged(IInventory par1iInventory) {
		// TODO Auto-generated method stub
		super.onCraftMatrixChanged(par1iInventory);
	}

}
