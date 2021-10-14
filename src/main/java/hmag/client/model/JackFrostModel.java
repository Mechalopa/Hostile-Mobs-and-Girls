package hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JackFrostModel<T extends MobEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer hatBase;
	private ModelRenderer hat1;
	private ModelRenderer hat2a;
	private ModelRenderer hat2b;

	public JackFrostModel()
	{
		this(0.0F);
	}

	public JackFrostModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);
		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.rightLeg.setPos(-1.75F, 12.0F, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, modelSize);
		this.leftLeg.setPos(1.75F, 12.0F, 0.0F);

		this.skirt1 = new ModelRenderer(this, 0, 66);
		this.skirt1.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 4.0F, 5.0F, modelSize);
		this.skirt1.setPos(0.0F, 6.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 80);
		this.skirt2.addBox(-5.0F, 0.0F, -3.5F, 10.0F, 6.0F, 7.0F, modelSize);
		this.skirt2.setPos(0.0F, 4.0F, 0.0F);
		this.skirt1.addChild(this.skirt2);

		this.hatBase = new ModelRenderer(this, 0, 52);
		this.hatBase.addBox(-6.0F, 1.0F, -6.0F, 12.0F, 2.0F, 12.0F, modelSize);
		this.hatBase.setPos(0.0F, -9.0F, 0.0F);
		this.head.addChild(this.hatBase);
		this.hat1 = new ModelRenderer(this, 0, 40);
		this.hat1.addBox(-4.0F, 1.0F, -4.0F, 8.0F, 3.0F, 8.0F, modelSize);
		this.hat1.setPos(0.0F, -12.0F, 0.0F);
		this.head.addChild(this.hat1);
		this.hat2a = new ModelRenderer(this, 16, 32);
		this.hat2a.addBox(-3.0F, 1.0F, -3.0F, 2.0F, 2.0F, 6.0F, modelSize);
		this.hat2a.setPos(0.0F, -14.0F, 0.0F);
		this.head.addChild(this.hat2a);
		this.hat2b = new ModelRenderer(this, 16, 32);
		this.hat2b.mirror = true;
		this.hat2b.addBox(1.0F, 1.0F, -3.0F, 2.0F, 2.0F, 6.0F, modelSize);
		this.hat2b.setPos(0.0F, -14.0F, 0.0F);
		this.head.addChild(this.hat2b);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.rightArm.zRot = (float)Math.PI / 6.0F;
		this.leftArm.zRot = -((float)Math.PI / 6.0F);
		this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.12F) * 0.06F;
		this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.12F) * 0.06F;

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