package hmag.client.model;

import hmag.entity.AbstractFlyingMonsterEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhostModel<T extends AbstractFlyingMonsterEntity> extends BipedModel<T>
{
	protected boolean isArmor = false;

	public GhostModel()
	{
		this(0.0F, false);
	}

	public GhostModel(float modelSize)
	{
		this(modelSize, 0.0F);
	}

	public GhostModel(boolean isArmor)
	{
		this(0.0F, isArmor);
	}

	public GhostModel(float modelSize, float yOffsetIn)
	{
		this(modelSize, yOffsetIn, 64, 64, false);
	}

	public GhostModel(float modelSize, boolean isArmor)
	{
		this(modelSize, 0.0F, isArmor);
	}

	public GhostModel(float modelSize, float yOffsetIn, boolean isArmor)
	{
		this(modelSize, yOffsetIn, 64, isArmor ? 32 : 64, isArmor);
	}

	public GhostModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn, boolean isArmor)
	{
		super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
		this.isArmor = isArmor;

		if (!isArmor)
		{
			this.body = new ModelRenderer(this, 16, 16);
			this.body.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 20.0F, 4.0F, modelSize);
			this.body.setPos(0.0F, 0.0F, 0.0F);
			this.rightLeg = new ModelRenderer(this, 0, 16);
			this.rightLeg.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, modelSize);
			this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
			this.leftLeg = new ModelRenderer(this, 0, 16);
			this.leftLeg.mirror = true;
			this.leftLeg.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, modelSize);
			this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		ModelHelper.animateZombieArms(this.leftArm, this.rightArm, entityIn.isCharging(), this.attackTime, ageInTicks);

		float f = MathHelper.sin(this.attackTime * (float)Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
		this.rightLeg.xRot = (float)Math.PI / 24.0F;
		this.leftLeg.xRot = (float)Math.PI / 24.0F;
		this.rightLeg.zRot = (float)Math.PI / 27.0F;
		this.leftLeg.zRot = -(float)Math.PI / 27.0F;
		this.rightLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.leftLeg.xRot -= f * 1.2F - f1 * 0.4F;
		this.rightLeg.zRot += MathHelper.cos(ageInTicks * 0.067F) * 0.05F;
		this.leftLeg.zRot -= MathHelper.cos(ageInTicks * 0.067F) * 0.05F;
		this.rightLeg.xRot += MathHelper.sin(ageInTicks * 0.033F) * 0.025F;
		this.leftLeg.xRot += MathHelper.sin(ageInTicks * 0.033F) * 0.025F;
	}
}