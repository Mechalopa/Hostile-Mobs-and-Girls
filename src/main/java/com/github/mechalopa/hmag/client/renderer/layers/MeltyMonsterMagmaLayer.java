package com.github.mechalopa.hmag.client.renderer.layers;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.client.ModModelLayers;
import com.github.mechalopa.hmag.client.model.MeltyMonsterModel;
import com.github.mechalopa.hmag.world.entity.MeltyMonsterEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MeltyMonsterMagmaLayer extends RenderLayer<MeltyMonsterEntity, MeltyMonsterModel<MeltyMonsterEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/melty_monster_overlay.png");
	private final MeltyMonsterModel<MeltyMonsterEntity> layerModel;

	public MeltyMonsterMagmaLayer(RenderLayerParent<MeltyMonsterEntity, MeltyMonsterModel<MeltyMonsterEntity>> renderLayerParent, EntityModelSet modelSet)
	{
		super(renderLayerParent);
		this.layerModel = new MeltyMonsterModel<>(modelSet.bakeLayer(ModModelLayers.MELTY_MONSTER_OUTER_LAYER));
		this.layerModel.setLayer(true);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, MeltyMonsterEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		Minecraft minecraft = Minecraft.getInstance();
		boolean flag = minecraft.shouldEntityAppearGlowing(livingEntity) && livingEntity.isInvisible();

		if (!livingEntity.isInvisible() || flag)
		{
			float f = (float)livingEntity.tickCount + partialTicks;
			this.getParentModel().copyPropertiesTo(this.layerModel);
			this.layerModel.prepareMobModel(livingEntity, limbSwing, limbSwingAmount, partialTicks);
			this.layerModel.setupAnim(livingEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			ResourceLocation resourcelocation = this.getLayerTexture(livingEntity);

			VertexConsumer vertexconsumer;

			if (flag)
			{
				vertexconsumer = buffer.getBuffer(RenderType.outline(resourcelocation));
			}
			else
			{
				vertexconsumer = buffer.getBuffer(getMeltyMonsterOverlay(resourcelocation, 0.0F, f * -0.001F));
			}

			this.layerModel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	public ResourceLocation getLayerTexture(MeltyMonsterEntity entity)
	{
		return TEX;
	}

	public static RenderType getMeltyMonsterOverlay(ResourceLocation location, float xOffs, float yOffs)
	{
		RenderStateShard.TransparencyStateShard transparencyState = new RenderStateShard.TransparencyStateShard("translucent_transparency", () -> {
			RenderSystem.enableBlend();
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		}, () -> {
			RenderSystem.disableBlend();
			RenderSystem.defaultBlendFunc();
		});
		RenderType.CompositeState renderTypeState = RenderType.CompositeState.builder().setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEnergySwirlShader)).setTextureState(new RenderStateShard.TextureStateShard(location, false, false)).setTexturingState(new RenderStateShard.OffsetTexturingStateShard(xOffs, yOffs)).setTransparencyState(transparencyState).setCullState(new RenderStateShard.CullStateShard(false)).setLightmapState(new RenderStateShard.LightmapStateShard(true)).setOverlayState(new RenderStateShard.OverlayStateShard(true)).createCompositeState(false);

		return RenderType.create("melty_monster_overlay", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, renderTypeState);
	}
}