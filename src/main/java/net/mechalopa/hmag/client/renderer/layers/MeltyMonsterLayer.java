package net.mechalopa.hmag.client.renderer.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.mechalopa.hmag.HMaG;
import net.mechalopa.hmag.client.model.MeltyMonsterModel;
import net.mechalopa.hmag.entity.MeltyMonsterEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderState.TransparencyState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeltyMonsterLayer extends LayerRenderer<MeltyMonsterEntity, MeltyMonsterModel<MeltyMonsterEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/melty_monster_overlay.png");
	private final MeltyMonsterModel<MeltyMonsterEntity> modelOuterLayer = new MeltyMonsterModel<>(0.0F, true);

	public MeltyMonsterLayer(IEntityRenderer<MeltyMonsterEntity, MeltyMonsterModel<MeltyMonsterEntity>> entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, MeltyMonsterEntity livingEntityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!livingEntityIn.isInvisible())
		{
			float f = (float)livingEntityIn.tickCount + partialTicks;
			this.getParentModel().copyPropertiesTo(this.modelOuterLayer);
			this.modelOuterLayer.prepareMobModel(livingEntityIn, limbSwing, limbSwingAmount, partialTicks);
			this.modelOuterLayer.setupAnim(livingEntityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			ResourceLocation resourcelocation = this.getLayerTexture(livingEntityIn);
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(getMeltyMonsterOverlay(resourcelocation, 0.0F, f * -0.001F));
			this.modelOuterLayer.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	public ResourceLocation getLayerTexture(MeltyMonsterEntity entity)
	{
		return TEX;
	}

	public static RenderType getMeltyMonsterOverlay(ResourceLocation locationIn, float uIn, float vIn)
	{
		TransparencyState transparencyState = new TransparencyState("translucent_transparency", () -> {
			RenderSystem.enableBlend();
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		}, () -> {
			RenderSystem.disableBlend();
			RenderSystem.defaultBlendFunc();
		});
		RenderType.State renderTypeState = RenderType.State.builder().setTextureState(new RenderState.TextureState(locationIn, false, false)).setTexturingState(new RenderState.OffsetTexturingState(uIn, vIn)).setTransparencyState(transparencyState).setDiffuseLightingState(new RenderState.DiffuseLightingState(true)).setCullState(new RenderState.CullState(false)).setLightmapState(new RenderState.LightmapState(false)).setOverlayState(new RenderState.OverlayState(true)).createCompositeState(true);

		return RenderType.create("melty_monster_overlay", DefaultVertexFormats.NEW_ENTITY, 7, 256, false, true, renderTypeState);
	}
}