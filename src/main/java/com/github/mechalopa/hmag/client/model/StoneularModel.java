package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.entity.StoneularEntity;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StoneularModel<T extends StoneularEntity> extends HierarchicalModel<T>
{
	private final ModelPart root;

	public StoneularModel(ModelPart modelPart)
	{
		this.root = modelPart;
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
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){}
}