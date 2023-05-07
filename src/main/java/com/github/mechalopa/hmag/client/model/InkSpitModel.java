package com.github.mechalopa.hmag.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InkSpitModel<T extends Entity> extends HierarchicalModel<T>
{
	private final ModelPart root;

	public InkSpitModel(ModelPart modelPart)
	{
		this.root = modelPart;
	}

	public static LayerDefinition createBodyLayer()
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F), PartPose.ZERO);
		float f = (float)Math.PI / 4.0F;
		partdefinition.addOrReplaceChild("part_1", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 0.0F), PartPose.offsetAndRotation(-2.0F, 0.0F, -2.0F, 0.0F, -f, 0.0F));
		partdefinition.addOrReplaceChild("part_2", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -1.5F, 0.0F, 1.0F, 3.0F, 0.0F), PartPose.offsetAndRotation(-2.0F, 0.0F, 2.0F, 0.0F, f, 0.0F));
		partdefinition.addOrReplaceChild("part_3", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 0.0F), PartPose.offsetAndRotation(2.0F, 0.0F, -2.0F, 0.0F, f, 0.0F));
		partdefinition.addOrReplaceChild("part_4", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -1.5F, 0.0F, 1.0F, 3.0F, 0.0F), PartPose.offsetAndRotation(2.0F, 0.0F, 2.0F, 0.0F, -f, 0.0F));
		partdefinition.addOrReplaceChild("part_5", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 0.0F, 3.0F), PartPose.offsetAndRotation(-2.0F, -2.0F, 0.0F, 0.0F, 0.0F, f));
		partdefinition.addOrReplaceChild("part_6", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, 0.0F, -1.5F, 1.0F, 0.0F, 3.0F), PartPose.offsetAndRotation(-2.0F, 2.0F, 0.0F, 0.0F, 0.0F, -f));
		partdefinition.addOrReplaceChild("part_7", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 0.0F, -1.5F, 1.0F, 0.0F, 3.0F), PartPose.offsetAndRotation(2.0F, -2.0F, 0.0F, 0.0F, 0.0F, -f));
		partdefinition.addOrReplaceChild("part_8", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, 0.0F, -1.5F, 1.0F, 0.0F, 3.0F), PartPose.offsetAndRotation(2.0F, 2.0F, 0.0F, 0.0F, 0.0F, f));
		partdefinition.addOrReplaceChild("part_9", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 0.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -2.0F, -2.0F, -f, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("part_10", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 1.0F), PartPose.offsetAndRotation(0.0F, -2.0F, 2.0F, f, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("part_11", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 0.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, f, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("part_12", CubeListBuilder.create().texOffs(0, 16).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 2.0F, 2.0F, -f, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){}

	@Override
	public ModelPart root()
	{
		return this.root;
	}
}