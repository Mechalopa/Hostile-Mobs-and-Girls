package hmag.client.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicalSlimeModel<T extends Entity> extends SegmentedModel<T>
{
	private final ModelRenderer cube;
	private final ModelRenderer eye0;
	private final ModelRenderer eye1;
	private final ModelRenderer mouth;
	private final boolean isLayer;

	public MagicalSlimeModel(int slimeBodyTexOffY)
	{
		this.cube = new ModelRenderer(this, 0, slimeBodyTexOffY);
		this.eye0 = new ModelRenderer(this, 32, 0);
		this.eye1 = new ModelRenderer(this, 32, 4);
		this.mouth = new ModelRenderer(this, 32, 8);

		if (slimeBodyTexOffY > 0)
		{
			this.cube.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, -0.75F);
			this.cube.setPos(0.0F, 20.0F, 0.0F);
			this.eye0.addBox(-3.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F);
			this.eye1.addBox(1.25F, 18.0F, -3.5F, 2.0F, 2.0F, 2.0F);
			this.mouth.addBox(0.0F, 21.0F, -3.5F, 1.0F, 1.0F, 1.0F);
			this.isLayer = false;
		}
		else
		{
			this.cube.addBox(-4.0F, 16.0F, -4.0F, 8.0F, 8.0F, 8.0F);
			this.isLayer = true;
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!this.isLayer)
		{
			this.cube.xRot = MathHelper.sin(ageInTicks * 0.09F) * 0.075F;
			this.cube.zRot = MathHelper.cos(ageInTicks * 0.06F) * 0.075F;
		}
	}

	@Override
	public Iterable<ModelRenderer> parts()
	{
		return ImmutableList.of(this.cube, this.eye0, this.eye1, this.mouth);
	}
}