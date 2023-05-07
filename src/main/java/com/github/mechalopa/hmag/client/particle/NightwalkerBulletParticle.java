package com.github.mechalopa.hmag.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NightwalkerBulletParticle extends TextureSheetParticle
{
	protected final SpriteSet sprites;

	private NightwalkerBulletParticle(ClientLevel clientWorld, SpriteSet sprites, double xIn, double yIn, double zIn, double xdIn, double ydIn, double zdIn)
	{
		super(clientWorld, xIn, yIn, zIn, xdIn, ydIn, zdIn);
		this.sprites = sprites;
		this.rCol = Mth.nextFloat(this.random, 0.40F, 0.55F);
		this.gCol = Mth.nextFloat(this.random, 0.402F, 0.57F);
		this.bCol = Mth.nextFloat(this.random, 0.85F, 0.98F);
		this.setSize(0.001F, 0.001F);
		this.quadSize *= this.random.nextFloat() * 0.6F + 0.6F;
		this.lifetime = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
		this.hasPhysics = false;
		this.friction = 1.0F;
		this.gravity = 0.0F;
		this.setSpriteFromAge(this.sprites);
	}

	@Override
	public ParticleRenderType getRenderType()
	{
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public int getLightColor(float f)
	{
		int i = super.getLightColor(f);
		int j = 240;
		int k = i >> 16 & 255;
		return j | k << 16;
	}

	@Override
	public void tick()
	{
		super.tick();

		this.setSpriteFromAge(this.sprites);

		if (this.age > this.lifetime / 2)
		{
			this.setAlpha(1.0F - ((float)this.age - (float)(this.lifetime / 2)) / (float)this.lifetime);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet sprite;

		public Provider(SpriteSet spriteIn)
		{
			this.sprite = spriteIn;
		}

		@Override
		public Particle createParticle(SimpleParticleType typeIn, ClientLevel clientWorld, double x, double y, double z, double xd, double yd, double zd)
		{
			double d0 = (double)clientWorld.getRandom().nextFloat() * -1.9D * (double)clientWorld.getRandom().nextFloat() * 0.1D;
			NightwalkerBulletParticle particle = new NightwalkerBulletParticle(clientWorld, this.sprite, x, y, z, 0.0D, d0, 0.0D);
			return particle;
		}
	}
}