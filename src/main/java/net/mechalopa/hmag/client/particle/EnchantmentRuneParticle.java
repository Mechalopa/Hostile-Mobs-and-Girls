package net.mechalopa.hmag.client.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnchantmentRuneParticle extends SpriteTexturedParticle
{
	private final double xStart;
	private final double yStart;
	private final double zStart;

	private EnchantmentRuneParticle(ClientWorld clientWorld, double xIn, double yIn, double zIn, double xdIn, double ydIn, double zdIn)
	{
		super(clientWorld, xIn, yIn, zIn);
		this.xd = xdIn;
		this.yd = ydIn;
		this.zd = zdIn;
		this.x = xIn;
		this.y = yIn;
		this.z = zIn;
		this.xStart = this.x;
		this.yStart = this.y;
		this.zStart = this.z;
		this.quadSize = 0.1F * (this.random.nextFloat() * 0.3F + 0.5F);
		float f = this.random.nextFloat() * 0.6F + 0.4F;
		this.rCol = f * 0.9F;
		this.gCol = f * 0.9F;
		this.bCol = f;
		this.hasPhysics = false;
		this.lifetime = (int)(Math.random() * 10.0D) + 40;
	}

	@Override
	public IParticleRenderType getRenderType()
	{
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double x, double y, double z)
	{
		this.setBoundingBox(this.getBoundingBox().move(x, y, x));
		this.setLocationFromBoundingbox();
	}

	@Override
	public int getLightColor(float f)
	{
		int i = super.getLightColor(f);
		float f0 = (float)this.age / (float)this.lifetime;
		f0 = f0 * f0;
		f0 = f0 * f0;
		int j = i & 255;
		int k = i >> 16 & 255;
		k = k + (int)(f0 * 15.0F * 16.0F);

		if (k > 240)
		{
			k = 240;
		}

		return j | k << 16;
	}

	@Override
	public void tick()
	{
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;

		if (this.age++ >= this.lifetime)
		{
			this.remove();
		}
		else
		{
			float f = (float)this.age / (float)this.lifetime;
			float f1 = -f + f * f * 2.0F;
			float f2 = 1.0F - f1;
			this.x = this.xStart + this.xd * (double)f2;
			this.y = this.yStart + this.yd * (double)f2 + (double)(1.0F - f);
			this.z = this.zStart + this.zd * (double)f2;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType>
	{
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite spriteIn)
		{
			this.sprite = spriteIn;
		}

		@Override
		public Particle createParticle(BasicParticleType typeIn, ClientWorld clientWorld, double x, double y, double z, double xd, double yd, double zd)
		{
			EnchantmentRuneParticle particle = new EnchantmentRuneParticle(clientWorld, x, y, z, xd, yd, zd);
			particle.pickSprite(this.sprite);
			return particle;
		}
	}
}