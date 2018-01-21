package ganymedes01.etfuturum.core.utils;

import ganymedes01.etfuturum.entities.ai.Vec3i;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class PathNavigateExtender extends PathNavigate {
	
    /** Time, in number of ticks, following the current path */
    private int totalTicks;
    /** The time when the last position check was done (to detect successful movement) */
    private int ticksAtLastPos;
    /** Coordinates of the entity's position last time a check was done (part of monitoring getting 'stuck') */
    private Vec3 lastPosCheck = new Vec3i(0.0D, 0.0D, 0.0D);
	
    private EntityLiving theEntity;
	
	 public PathNavigateExtender(EntityLiving p_i1671_1_, World p_i1671_2_) {
		super(p_i1671_1_, p_i1671_2_);
		// TODO Auto-generated constructor stub
	}
	/**
     * Returns true if the entity is in water or lava, false otherwise
     */
    public boolean isInLiquid()
    {
        return this.theEntity.isInWater();
    }
	/**
     * Trims path data from the end to the first sun covered block
     */
    public void removeSunnyPath()
    {
    }
    
	public void checkForStuck(Vec3i positionVec3)
    {
        if (this.totalTicks - this.ticksAtLastPos > 100)
        {
            if (positionVec3.squareDistanceTo(this.lastPosCheck) < 2.25D)
            {
                this.clearPathEntity();
            }

            this.ticksAtLastPos = this.totalTicks;
            this.lastPosCheck = positionVec3;
        }
    }
}
