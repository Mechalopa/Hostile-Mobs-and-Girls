package hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImpModel<T extends MobEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;

	public ImpModel()
	{
		this(0.0F);
	}

	public ImpModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.skirt1 = new ModelRenderer(this, 0, 38);
		this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
		this.skirt1.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 44);
		this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 5.0F, 5.0F, modelSize);
		this.skirt2.setPos(0.0F, 12.0F, 0.0F);
		this.body.addChild(this.skirt2);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}
	}
}