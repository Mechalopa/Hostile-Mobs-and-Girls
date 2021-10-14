package hmag.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;

import hmag.HMaG;
import hmag.client.model.MeltyMonsterModel;
import hmag.entity.MeltyMonsterEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class MeltyMonsterClothingLayer<T extends MeltyMonsterEntity, M extends MeltyMonsterModel<T>> extends AbstractEyesLayer<T, M>
{
	private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation(HMaG.MODID, "textures/entity/melty_monster_clothing.png"));

	public MeltyMonsterClothingLayer(IEntityRenderer<T, M> rendererIn)
	{
		super(rendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!livingEntityIn.isInvisible())
		{
			super.render(matrixStackIn, bufferIn, packedLightIn, livingEntityIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
		}
	}

	@Override
	public RenderType renderType()
	{
		return RENDER_TYPE;
	}
}