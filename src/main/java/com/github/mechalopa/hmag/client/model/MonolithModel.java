package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.entity.MonolithEntity;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonolithModel<T extends MonolithEntity> extends HierarchicalModel<T>
{
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart headPart1;
	private final ModelPart headPart2;
	private final ModelPart topRightPart;
	private final ModelPart topLeftPart;
	private final ModelPart bottomRightPart;
	private final ModelPart bottomLeftPart;

	public MonolithModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.head = modelPart.getChild("head");
		this.headPart1 = this.head.getChild("head_part_1");
		this.headPart2 = this.head.getChild("head_part_2");
		this.topRightPart = this.head.getChild("top_right_part");
		this.topLeftPart = this.head.getChild("top_left_part");
		this.bottomRightPart = this.head.getChild("bottom_right_part");
		this.bottomLeftPart = this.head.getChild("bottom_left_part");
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition md = new MeshDefinition();
		PartDefinition pd = md.getRoot();
		PartDefinition headpd = ModClientUtils.addC(pd, "head", 0, 0, -5.0F, -4.0F, -2.0F, 10.0F, 18.0F, 4.0F, 0.0F, 4.0F, 0.0F);
		ModClientUtils.addC(headpd, "head_part_1", 32, 0, -2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, -0.1F, 0.0F, -4.0F, 0.0F);
		ModClientUtils.addC(headpd, "head_part_2", 32, 16, -2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, -0.1F, 0.0F, 14.0F, 0.0F);
		ModClientUtils.addC(headpd, "top_right_part", 0, 24, -1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, -2.0F, -4.0F, 0.0F);
		ModClientUtils.addC(headpd, "top_left_part", 0, 24, -1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 2.0F, -4.0F, 0.0F, true);
		ModClientUtils.addC(headpd, "bottom_right_part", 8, 24, -1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, -2.0F, 14.0F, 0.0F);
		ModClientUtils.addC(headpd, "bottom_left_part", 8, 24, -1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, 2.0F, 14.0F, 0.0F, true);
		return LayerDefinition.create(md, 64, 32);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.headPart1.zRot = (float)Math.PI / 4.0F;
		this.headPart2.zRot = (float)Math.PI / 4.0F;
		this.topRightPart.zRot = -((float)Math.PI / 32.0F);
		this.topLeftPart.zRot = (float)Math.PI / 32.0F;
		this.bottomRightPart.zRot = (float)Math.PI / 32.0F;
		this.bottomLeftPart.zRot = -((float)Math.PI / 32.0F);
	}
}