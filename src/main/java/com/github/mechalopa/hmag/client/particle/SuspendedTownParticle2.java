package com.github.mechalopa.hmag.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SuspendedTownParticle2 extends TextureSheetParticle
{
	private SuspendedTownParticle2(ClientLevel clientWorld, double xIn, double yIn, double zIn, double xdIn, double ydIn, double zdIn)
	{
		super(clientWorld, xIn, yIn, zIn, xdIn, ydIn, zdIn);
		float f = this.random.nextFloat() * 0.02F + 0.02F;
		this.rCol = f;
		this.gCol = f;
		this.bCol = f;
		this.setSize(0.02F, 0.02F);
		this.quadSize *= this.random.nextFloat() * 0.6F + 0.5F;
		this.xd *= (double)0.02F;
		this.yd *= (double)0.02F;
		this.zd *= (double)0.02F;
		this.lifetime = (int)(20.0D / (Math.random() * 0.8D + 0.2D));
	}

	@Override
	public ParticleRenderType getRenderType()
	{
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void move(double x, double y, double z)
	{
		this.setBoundingBox(this.getBoundingBox().move(x, y, x));
		this.setLocationFromBoundingbox();
	}

	@Override
	public void tick()
	{
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;

		if (this.lifetime-- <= 0)
		{
			this.remove();
		}
		else
		{
			this.move(this.xd, this.yd, this.zd);
			this.xd *= 0.99D;
			this.yd *= 0.99D;
			this.zd *= 0.99D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class NightwalkerParticleProvider implements ParticleProvider<SimpleParticleType>
	{
		private final SpriteSet sprite;

		public NightwalkerParticleProvider(SpriteSet spriteIn)
		{
			this.sprite = spriteIn;
		}

		@Override
		public Particle createParticle(SimpleParticleType typeIn, ClientLevel clientWorld, double x, double y, double z, double xd, double yd, double zd)
		{
			SuspendedTownParticle2 particle = new SuspendedTownParticle2(clientWorld, x, y, z, xd, yd, zd);
			particle.pickSprite(this.sprite);
			return particle;
		}
	}
}