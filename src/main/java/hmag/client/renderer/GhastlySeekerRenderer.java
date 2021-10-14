package hmag.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;

import hmag.HMaG;
import hmag.client.model.GhastlySeekerModel;
import hmag.client.renderer.layers.GhastlySeekerLayer;
import hmag.entity.GhastlySeekerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhastlySeekerRenderer extends MobRenderer<GhastlySeekerEntity, GhastlySeekerModel<GhastlySeekerEntity>>
{
	private static final ResourceLocation TEX0 = new ResourceLocation(HMaG.MODID, "textures/entity/ghastly_seeker.png");
	private static final ResourceLocation TEX1 = new ResourceLocation(HMaG.MODID, "textures/entity/ghastly_seeker_shooting.png");

	public GhastlySeekerRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new GhastlySeekerModel<>(), 0.8F);
		this.addLayer(new GhastlySeekerLayer(this));
	}

	@Override
	protected void scale(GhastlySeekerEntity entityIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		matrixStackIn.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	protected void setupRotations(GhastlySeekerEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
	{
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(-15.0F));
	}

	@Override
	public ResourceLocation getTextureLocation(GhastlySeekerEntity entityIn)
	{
		return entityIn.isAttacking() ? TEX1 : TEX0;
	}
}