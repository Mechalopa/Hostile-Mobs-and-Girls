package hmag.client.renderer;

import hmag.entity.projectile.ThrowableBottleEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModSpriteRenderer extends SpriteRenderer<ProjectileItemEntity>
{
	public ModSpriteRenderer(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, Minecraft.getInstance().getItemRenderer());
	}

	@Override
	protected int getBlockLightLevel(ProjectileItemEntity entityIn, BlockPos partialTicks)
	{
		return (entityIn != null && entityIn instanceof ThrowableBottleEntity) ? 15 : super.getBlockLightLevel(entityIn, partialTicks);
	}
}