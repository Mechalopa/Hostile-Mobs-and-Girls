package hmag.client.model;

import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicalSlimeModel<T extends Entity> extends SlimeModel<T>
{
	public MagicalSlimeModel(int slimeBodyTexOffY)
	{
		super(slimeBodyTexOffY);
	}
}