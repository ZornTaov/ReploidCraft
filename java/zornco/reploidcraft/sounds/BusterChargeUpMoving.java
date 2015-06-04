package zornco.reploidcraft.sounds;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import zornco.reploidcraft.items.IKeyBound;
import zornco.reploidcraft.items.ItemBuster;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BusterChargeUpMoving extends MovingSound
{

	EntityPlayer player;
	
	public BusterChargeUpMoving(EntityPlayer player)
	{
		super(new ResourceLocation(Sounds.CHARGEUP));
		this.player = player;
		this.repeat = false;
	}

	@Override
	public void update()
	{
		ItemStack buster = player.getHeldItem();
		if (!this.player.isDead && buster != null && buster.getItem() != null && buster.getItem() instanceof IKeyBound && !((ItemBuster)buster.getItem()).stopUp)
        {
			this.xPosF = (float)this.player.posX;
            this.yPosF = (float)this.player.posY;
            this.zPosF = (float)this.player.posZ;
            //float f = MathHelper.sqrt_double(this.player.motionX * this.player.motionX + this.player.motionZ * this.player.motionZ);

        }
        else
        {
            this.donePlaying = true;
        }
	}

}
