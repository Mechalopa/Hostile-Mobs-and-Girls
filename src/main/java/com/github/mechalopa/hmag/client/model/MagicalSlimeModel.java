package com.github.mechalopa.hmag.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicalSlimeModel<T extends Entity> extends HierarchicalModel<T>
{
	protected boolean isOuterLayer = false;
	private final ModelPart root;
	private final ModelPart cube;

	public MagicalSlimeModel(ModelPart modelPart)
	{
		this.root = modelPart;
		this.cube = this.root.getChild("cube");
	}

	public static LayerDefinition createOuterBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	public static LayerDefinition createInnerBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-0.75F)), PartPose.offset(0.0F, 20.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(32, 0).addBox(-3.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(32, 4).addBox(1.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(32, 8).addBox(0.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F), PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public ModelPart root()
	{
		return this.root;
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!this.isOuterLayer)
		{
			this.cube.xRot = Mth.sin(ageInTicks * 0.09F) * 0.075F;
			this.cube.zRot = Mth.cos(ageInTicks * 0.06F) * 0.075F;
		}
	}

	public void setOuterLayer(boolean flag)
	{
		this.isOuterLayer = flag;
	}
}