package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlModel<T extends Zombie> extends AbstractAdvancedGirlModel<T>
{
//	private ModelPart clothPart;
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart tailHair1;
	private ModelPart tailHair2;
	private ModelPart tailHair3;
	private ModelPart tailHair4;
	private ModelPart ahoge;

	public ZombieGirlModel(ModelPart modelPart)
	{
		super(modelPart);
//		this.clothPart = this.body.getChild("cloth_part");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.body.getChild("skirt_2");
		this.tailHair1 = this.head.getChild("tail_hair_1");
		this.tailHair2 = this.tailHair1.getChild("tail_hair_2");
		this.tailHair3 = this.tailHair2.getChild("tail_hair_3");
		this.tailHair4 = this.tailHair3.getChild("tail_hair_4");
		this.ahoge = this.head.getChild("ahoge");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "cloth_part", 32, 48, -3.0F, 0.0F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, 7.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 6.0F, 5.0F, 0.0F, 12.0F, 0.0F);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition th1pd = ModClientUtils.addC(headpd, cd, "tail_hair_1", 0, 56, -1.0F, -1.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, -7.0F, 4.0F);
		PartDefinition th2pd = ModClientUtils.addC(th1pd, cd, "tail_hair_2", 8, 56, -1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, 3.0F, 0.0F, 0.001F);
		PartDefinition th3pd = ModClientUtils.addC(th2pd, cd, "tail_hair_3", 8, 56, -1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.25F, 0.0F, -0.25F);
		ModClientUtils.addC(th3pd, cd, "tail_hair_4", 16, 56, -0.5F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, 4.5F, 0.5F);
		ModClientUtils.addC(headpd, cd, "ahoge", 16, 30, -2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 1.0F, 0.0F, -7.75F, 0.0F, -0.25F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entity), this.attackTime, ageInTicks);

//		this.clothPart.xRot = 0.0F;

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}

		this.skirt1.xRot = 0.0F;

		this.tailHair1.xRot = (float)Math.PI / 8.0F;
		this.tailHair1.xRot += Mth.sin(ageInTicks * 0.03F) * 0.09F;
		this.tailHair2.xRot = -((float)Math.PI / 18.0F);
		this.tailHair2.xRot += Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 12.0F) * 0.045F;
		this.tailHair3.xRot = -((float)Math.PI / 18.0F);
		this.tailHair3.xRot += Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 6.0F) * 0.03F;
		this.tailHair4.xRot = -((float)Math.PI / 24.0F);
		this.tailHair4.xRot += Mth.sin(ageInTicks * 0.03F + (float)Math.PI / 4.0F) * 0.015F;

		this.ahoge.xRot = -((float)Math.PI / 12.0F);
		this.ahoge.xRot += Mth.cos(ageInTicks * 0.03F + (float)Math.PI) * 0.09F;
	}

	public boolean isAggressive(T entity)
	{
		return entity.isAggressive();
	}
}