package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.DullahanEntity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DullahanModel<T extends DullahanEntity> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart skirt1;
	private ModelPart skirt2;
//	private ModelPart neck1;
//	private ModelPart neck2;
	private ModelPart cloak;

	public DullahanModel(ModelPart modelPart)
	{
		super(modelPart);
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
//		this.neck1 = this.body.getChild("neck_1");
//		this.neck2 = this.neck1.getChild("neck_2");
		this.cloak = this.body.getChild("cloak");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 5.0F, 5.0F, 0.0F, 12.0F, 0.0F);
		PartDefinition neck1pd = ModClientUtils.addC(bodypd, cd, "neck_1", 0, 56, -3.0F, 0.0F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, -1.0F, 0.0F);
		ModClientUtils.addC(neck1pd, cd, "neck_2", 0, 64, -3.5F, 0.0F, -3.0F, 7.0F, 3.0F, 6.0F, 0.0F, -3.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "cloak", 32, 56, -4.5F, 0.0F, -1.0F, 9.0F, 15.0F, 1.0F, 0.0F, -0.5F, 2.0F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f = Mth.sin(this.attackTime * (float)Math.PI);
		float f1 = Mth.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

		if (entity.getVariant() == DullahanEntity.Variant.HEADLESS)
		{
			this.head.visible = false;
			this.hat.visible = false;
		}
		else
		{
			boolean flag = entity.getMainArm() == HumanoidArm.LEFT;

			if (flag)
			{
				this.rightArm.zRot = 0.0F;
				this.rightArm.yRot = -(0.1F - f * 0.6F);
				this.rightArm.xRot = (float)Math.PI / 12.0F;
				this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
				this.rightArm.zRot += Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
				this.rightArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
			}
			else
			{
				this.leftArm.zRot = 0.0F;
				this.leftArm.yRot = 0.1F - f * 0.6F;
				this.leftArm.xRot = (float)Math.PI / 12.0F;
				this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
				this.leftArm.zRot -= Mth.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
				this.leftArm.xRot -= Mth.sin(ageInTicks * 0.067F) * 0.05F;
			}

			this.head.visible = true;
			this.hat.visible = true;
			float f2 = 1.0F;
			this.head.x = f2 * 7.0F * (flag ? -1.0F : 1.0F);
			this.head.y = f2 * 17.5F;
			this.head.z = f2 * 1.5F;
			this.hat.copyFrom(this.head);
		}

		this.cloak.xRot = (float)Math.PI / 6.0F;
		this.cloak.xRot -= Mth.cos(limbSwing * 0.45F) * 2.0F * limbSwingAmount * 0.21F;
		this.cloak.xRot += Mth.sin(ageInTicks * 0.067F) * 0.036F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}

		this.skirt1.xRot = 0.0F;
	}
}