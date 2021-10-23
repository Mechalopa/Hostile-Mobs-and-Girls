package hmag.client.model;

import net.minecraft.entity.MobEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DodomekiModel<T extends MobEntity> extends AbstractGirlModel<T>
{
	public DodomekiModel()
	{
		this(0.0F);
	}

	public DodomekiModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
}