package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JackFrostModel<T extends Mob> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart skirt1;
	private ModelPart skirt2;
//	private ModelPart hatBase;
//	private ModelPart hat1;
//	private ModelPart hat2a;
//	private ModelPart hat2b;

	public JackFrostModel(ModelPart modelPart)
	{
		super(modelPart);
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.skirt1.getChild("skirt_2");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, 2.0F, 0.0F);
		ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, -1.75F, 12.0F, 0.0F);
		ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 1.75F, 12.0F, 0.0F, true);
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition s1pd = ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 66, -4.0F, 0.0F, -2.5F, 8.0F, 4.0F, 5.0F, 0.0F, 6.0F, 0.0F);
		ModClientUtils.addC(s1pd, cd, "skirt_2", 0, 80, -5.0F, 0.0F, -3.5F, 10.0F, 6.0F, 7.0F, 0.0F, 4.0F, 0.0F);
		PartDefinition headpd = pd.getChild("head");
		ModClientUtils.addC(headpd, cd, "head_part_1", 0, 52, -6.0F, 1.0F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, -9.0F, 0.0F);
		ModClientUtils.addC(headpd, cd, "head_part_2", 0, 40, -4.0F, 1.0F, -4.0F, 8.0F, 3.0F, 8.0F, 0.0F, -12.0F, 0.0F);
		ModClientUtils.addC(headpd, cd, "head_part_3a", 16, 32, -3.0F, 1.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, -14.0F, 0.0F);
		ModClientUtils.addC(headpd, cd, "head_part_3b", 16, 32, 1.0F, 1.0F, -3.0F, 2.0F, 2.0F, 6.0F, 0.0F, -14.0F, 0.0F, true);
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

		this.rightArm.zRot = (float)Math.PI / 6.0F;
		this.leftArm.zRot = -((float)Math.PI / 6.0F);
		this.rightArm.zRot += Mth.cos(ageInTicks * 0.12F) * 0.06F;
		this.leftArm.zRot -= Mth.cos(ageInTicks * 0.12F) * 0.06F;

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