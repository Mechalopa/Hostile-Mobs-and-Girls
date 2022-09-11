package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.world.entity.SavagefangEntity;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SavagefangModel<T extends SavagefangEntity> extends HierarchicalModel<T>
{
	private final ModelPart root;
//	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart upperJaw;
//	private final ModelPart upperJawPart;
	private final ModelPart upperFang;
	private final ModelPart lowerJaw;
	private final ModelPart lowerFang;
	private final ModelPart barbel0;
	private final ModelPart barbel1;
//	private final ModelPart nose1;
//	private final ModelPart nose2;
	private final ModelPart sideFin0;
	private final ModelPart sideFin1;
	private final ModelPart tailFinBase;
//	private final ModelPart tailFin;
//	private final ModelPart topFin;
//	private final ModelPart bottomFin;
	private float animationAmount;

	public SavagefangModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.upperJaw = this.head.getChild("upper_jaw");
		this.upperFang = this.upperJaw.getChild("upper_fang");
		this.lowerJaw = this.head.getChild("lower_jaw");
		this.lowerFang = this.lowerJaw.getChild("lower_fang");
		this.barbel0 = this.lowerJaw.getChild("barbel_0");
		this.barbel1 = this.lowerJaw.getChild("barbel_1");
		this.sideFin0 = modelPart.getChild("side_fin_0");
		this.sideFin1 = modelPart.getChild("side_fin_1");
		this.tailFinBase = modelPart.getChild("tail_fin_base");
	}

	public static LayerDefinition createBodyLayer()
	{
		float f = 21.0F;
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, "body", 0, 10, -2.5F, -4.0F, 0.0F, 5.0F, 8.0F, 4.0F, 0.0F, f, -1.0F);
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -2.0F, -3.5F, -2.0F, 4.0F, 7.0F, 2.0F, 0.0F, f, -1.0F, 0.001F);
		PartDefinition upjawpd = ModClientUtils.addC(headpd, "upper_jaw", 12, 0, -2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 3.0F, 0.0F, 0.0F, -1.5F);
		ModClientUtils.addC(upjawpd, "upper_jaw_part", 12, 6, -2.0F, -2.0F, -1.0F, 4.0F, 2.0F, 1.0F, 0.0F, 0.0F, -3.0F);
		ModClientUtils.addC(upjawpd, "upper_fang", 26, 0, -2.0F, 0.0F, -4.0F, 4.0F, 1.0F, 4.0F, 0.0F, -0.5F, 0.0F, -0.001F);
		PartDefinition lowjawpd = ModClientUtils.addC(headpd, "lower_jaw", 24, 8, -2.5F, 0.0F, -4.75F, 5.0F, 2.0F, 5.0F, 0.0F, 0.0F, -1.75F);
		ModClientUtils.addC(lowjawpd, "lower_fang", 24, 16, -2.5F, -2.0F, -4.75F, 5.0F, 2.0F, 5.0F, 0.0F, 0.5F, 0.0F, -0.001F);
		ModClientUtils.addC(lowjawpd, "barbel_0", 48, 0, 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 1.0F, -1.0F, 2.0F, -2.75F);
		ModClientUtils.addC(lowjawpd, "barbel_1", 48, 2, 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 2.0F, -2.75F);
		PartDefinition nose1pd = ModClientUtils.addC(pd, "nose_1", 0, 22, -2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, f, 3.0F);
		ModClientUtils.addC(nose1pd, "nose_2", 10, 22, -2.0F, -3.0F, 0.0F, 4.0F, 5.0F, 1.0F, 0.0F, 0.0F, 1.0F);
		ModClientUtils.addC(pd, "side_fin_0", 40, 32, -4.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, -2.5F, f, 0.0F);
		ModClientUtils.addC(pd, "side_fin_1", 40, 40, 0.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, 2.5F, f, 0.0F);
		PartDefinition tailfinbasepd = ModClientUtils.addC(pd, "tail_fin_base", 48, 8, -0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, f - 1.0F, 5.0F);
		ModClientUtils.addC(tailfinbasepd, "tail_fin", 48, 12, 0.0F, -3.5F, 0.0F, 0.0F, 8.0F, 5.0F, 0.0F, 0.0F, 1.0F);
		ModClientUtils.addC(pd, "top_fin", 0, 30, 0.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, 0.0F, f - 4.0F, 2.0F);
		ModClientUtils.addC(pd, "bottom_fin", 24, 24, 0.0F, -1.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, f + 4.0F, 3.0F);
		return LayerDefinition.create(md, 64, 64);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
		this.animationAmount = Mth.clamp(entityIn.getAttackAnimationScale(partialTick), 0.0F, 1.0F);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		float f = 1.0F;

		if (!entityIn.isInWater())
		{
			f = 1.5F;
		}

		this.tailFinBase.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
		this.sideFin0.zRot = -(((float)Math.PI / 5.0F) + (0.15F * Mth.cos(0.6F * ageInTicks)));
		this.sideFin1.zRot = -this.sideFin0.zRot;

		this.upperJaw.xRot = -0.12F * Math.abs(Mth.cos(0.45F * ageInTicks)) * this.animationAmount;
		this.lowerJaw.xRot = -((float)Math.PI / 30.0F);
		this.lowerJaw.xRot += 0.9F * Math.abs(Mth.cos(0.45F * ageInTicks)) * this.animationAmount;

		this.upperFang.y = -0.5F + (0.375F * this.animationAmount);
		this.lowerFang.y = 0.5F - (0.375F * this.animationAmount);

		this.barbel0.zRot = ((float)Math.PI / 15.0F) + (0.12F * Mth.cos(0.12F * ageInTicks));
		this.barbel1.zRot = -this.barbel0.zRot;
	}
}