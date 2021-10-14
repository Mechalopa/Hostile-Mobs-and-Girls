package hmag.client.renderer;

import hmag.HMaG;
import hmag.client.model.FortressKeeperModel;
import hmag.client.renderer.layers.FortressKeeperFireLayer;
import hmag.entity.FortressKeeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FortressKeeperRenderer extends MobRenderer<FortressKeeperEntity, FortressKeeperModel<FortressKeeperEntity>>
{
	private static final ResourceLocation TEX = new ResourceLocation(HMaG.MODID, "textures/entity/fortress_keeper.png");

	public FortressKeeperRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new FortressKeeperModel<>(), 0.7F);
		this.addLayer(new FortressKeeperFireLayer(this));
	}

	@Override
	protected int getBlockLightLevel(FortressKeeperEntity entityIn, BlockPos pos)
	{
		return 15;
	}

	@Override
	public ResourceLocation getTextureLocation(FortressKeeperEntity entityIn)
	{
		return TEX;
	}
}