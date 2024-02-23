package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractAdvancedGirlModel<T extends LivingEntity> extends AbstractGirlModel<T>
{
	protected ModelPart bodyPart1;
	protected ModelPart bodyPart2;
	protected ModelPart bust;

	public AbstractAdvancedGirlModel(ModelPart modelPart)
	{
		super(modelPart);
		this.bodyPart1 = this.body.getChild("body_part_1");
		this.bodyPart2 = this.bodyPart1.getChild("body_part_2");
		this.bust = this.body.getChild("bust");
	}

	public static MeshDefinition createMesh(CubeDeformation cd, float yOffset)
	{
		return createMesh(cd, yOffset, 7);
	}

	public static MeshDefinition createMesh(CubeDeformation cd, float yOffset, int bodyHeight)
	{
		MeshDefinition md = AbstractGirlModel.createMesh(cd, yOffset);
		PartDefinition pd = md.getRoot();
		PartDefinition bodypd = ModClientUtils.addC(pd, cd, "body", 16, 16, -3.0F, 0.0F, -1.5F, 6.0F, (float)bodyHeight, 3.0F, 0.0F, 0.0F + yOffset, 0.0F);
		PartDefinition bodypart1pd = ModClientUtils.addC(bodypd, cd, "body_part_1", 32, 32, -2.5F, 0.0F, -1.0F, 5.0F, 3.0F, 2.0F, 0.0F, (float)bodyHeight, 0.0F);
		ModClientUtils.addC(bodypart1pd, cd, "body_part_2", 32, 40, -3.0F, 0.0F, -1.5F, 6.0F, 2.0F, 3.0F, 0.0F, 3.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "bust", 0, 32, -3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, 3.5F, -1.1F, -0.005F);
		return md;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.bust.xRot = ((float)Math.PI / 4.0F) + ((float)Math.PI / 18.0F);
	}
}