package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlModel<T extends Zombie> extends AbstractGirlModel<T>
{
	private ModelPart clothPart;
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
		this.clothPart = this.body.getChild("cloth_part");
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.skirt1.getChild("skirt_2");
		this.tailHair1 = this.head.getChild("tail_hair_1");
		this.tailHair2 = this.tailHair1.getChild("tail_hair_2");
		this.tailHair3 = this.tailHair3.getChild("tail_hair_3");
		this.tailHair4 = this.tailHair4.getChild("tail_hair_4");
		this.ahoge = this.head.getChild("ahoge");

//		if (!isArmor)
//		{
//			this.clothPart = new ModelRenderer(this, 32, 48);
//			this.clothPart.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 3.0F, 3.0F, modelSize);
//			this.clothPart.setPos(0.0F, 7.0F, 0.0F);
//			this.body.addChild(this.clothPart);
//			this.skirt1 = new ModelRenderer(this, 0, 38);
//			this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSize);
//			this.skirt1.setPos(0.0F, 11.0F, 0.0F);
//			this.body.addChild(this.skirt1);
//			this.skirt2 = new ModelRenderer(this, 0, 44);
//			this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 6.0F, 5.0F, modelSize);
//			this.skirt2.setPos(0.0F, 12.0F, 0.0F);
//			this.body.addChild(this.skirt2);
//			this.tailHair1 = new ModelRenderer(this, 0, 56);
//			this.tailHair1.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 4.0F, 2.0F, modelSize);
//			this.tailHair1.setPos(0.0F, -7.0F, 4.0F);
//			this.head.addChild(this.tailHair1);
//			this.tailHair2 = new ModelRenderer(this, 8, 56);
//			this.tailHair2.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, modelSize + 0.001F);
//			this.tailHair2.setPos(0.0F, 3.0F, 0.0F);
//			this.tailHair1.addChild(this.tailHair2);
//			this.tailHair3 = new ModelRenderer(this, 8, 56);
//			this.tailHair3.addBox(-1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, modelSize - 0.25F);
//			this.tailHair3.setPos(0.0F, 4.25F, 0.0F);
//			this.tailHair2.addChild(this.tailHair3);
//			this.tailHair4 = new ModelRenderer(this, 16, 56);
//			this.tailHair4.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, modelSize);
//			this.tailHair4.setPos(0.0F, 4.5F, 0.5F);
//			this.tailHair3.addChild(this.tailHair4);
//			this.ahoge = new ModelRenderer(this, 16, 32);
//			this.ahoge.addBox(-2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 1.0F, modelSize - 0.25F);
//			this.ahoge.setPos(0.0F, -7.75F, 0.0F);
//			this.head.addChild(this.ahoge);
//		}
	}

	public static MeshDefinition createMesh(CubeDeformation cd, float yOffset)
	{
		MeshDefinition md = AbstractGirlModel.createMesh(cd, yOffset);
		PartDefinition pd = md.getRoot();
		PartDefinition bodypd = pd.getChild("body");
		ModClientUtils.addC(bodypd, cd, "cloth_part", 32, 48, -3.0F, 0.0F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, 7.0F, 0.0F);
		PartDefinition skirt1pd = ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(skirt1pd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 6.0F, 5.0F, 0.0F, 12.0F, 0.0F);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition th1pd = ModClientUtils.addC(headpd, cd, "tail_hair_1", 0, 56, -1.0F, -1.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, -7.0F, 4.0F);
		PartDefinition th2pd = ModClientUtils.addC(th1pd, cd, "tail_hair_2", 8, 56, -1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, 3.0F, 0.0F, 0.001F);
		PartDefinition th3pd = ModClientUtils.addC(th2pd, cd, "tail_hair_3", 8, 56, -1.0F, -0.5F, 0.0F, 2.0F, 5.0F, 2.0F, 0.0F, 4.25F, 0.0F, -0.25F);
		ModClientUtils.addC(th3pd, cd, "tail_hair_4", 16, 56, -0.5F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, 4.5F, 0.5F);
		ModClientUtils.addC(headpd, cd, "ahoge", 16, 32, -2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 1.0F, 0.0F, -7.75F, 0.0F, -0.25F);
		return md;
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);

		this.clothPart.xRot = 0.0F;

//		if (!this.isArmor)
//		{
			if (this.riding)
			{
				this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
			}
			else
			{
				this.skirt2.xRot = this.body.xRot;
			}

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
//		}
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}