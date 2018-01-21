package ganymedes01.etfuturum.entities.ai;


import ganymedes01.etfuturum.core.utils.PathFinderExtender;
import ganymedes01.etfuturum.core.utils.PathNavigateExtender;



import ganymedes01.etfuturum.core.utils.SwimNodeProcessor;
import ganymedes01.etfuturum.core.utils.WorldExtender;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;


public class PathNavigateSwimmer extends PathNavigate {
    private EntityLiving theEntity;
    private PathNavigateExtender PNExtender;   
    private World worldObj;
    private WorldExtender WExtender;
    /** The PathEntity being followed. */
    private PathEntity currentPath;
    private double speed;
    /** The number of blocks (extra) +/- in each axis that get pulled out as cache for the pathfinder's search space */
    private IAttributeInstance pathSearchRange;
    private boolean noSunPathfind;
    /** Time, in number of ticks, following the current path */
    private int totalTicks;
    /** The time when the last position check was done (to detect successful movement) */
    private int ticksAtLastPos;
    /** Coordinates of the entity's position last time a check was done (part of monitoring getting 'stuck') */
    private Vec3 lastPosCheck = Vec3.createVectorHelper(0.0D, 0.0D, 0.0D);
    /** Specifically, if a wooden door block is even considered to be passable by the pathfinder */
    private boolean canPassOpenWoodenDoors = true;
    /** If door blocks are considered passable even when closed */
    private boolean canPassClosedWoodenDoors;
    /** If water blocks are avoided (at least by the pathfinder) */
    private boolean avoidsWater;

    /**
     * If the entity can swim. Swimming AI enables this and the pathfinder will also cause the entity to swim straight
     * upwards when underwater
     */
    private boolean canSwim;
    private static final String __OBFID = "CL_00001627";
    
	public PathNavigateSwimmer(EntityLiving entitylivingIn, World worldIn)
    {
        super(entitylivingIn, worldIn);
    }

    protected PathFinderExtender getPathFinder()
    {
        return new PathFinderExtender(WExtender, new SwimNodeProcessor(), canSwim, canSwim, canSwim, canSwim);
    }

    /**
     * If on ground or swimming and can swim
     */
    protected boolean canNavigate()
    {
        return this.PNExtender.isInLiquid();
    }

    protected Vec3i getEntityPosition()
    {
        return new Vec3i(this.theEntity.posX, this.theEntity.posY + (double)this.theEntity.height * 0.5D, this.theEntity.posZ);
    }

    protected void pathFollow()
    {
        Vec3i vec3 = this.getEntityPosition();
        float f = this.theEntity.width * this.theEntity.width;
        int i = 6;

        if ((vec3).squareDistanceTo(this.currentPath.getVectorFromIndex(this.theEntity, this.currentPath.getCurrentPathIndex())) < (double)f)
        {
            this.currentPath.incrementPathIndex();
        }

        for (int j = Math.min(this.currentPath.getCurrentPathIndex() + i, this.currentPath.getCurrentPathLength() - 1); j > this.currentPath.getCurrentPathIndex(); --j)
        {
            Vec3 vec31 = this.currentPath.getVectorFromIndex(this.theEntity, j);

            if (vec31.squareDistanceTo(vec3) <= 36.0D && this.isDirectPathBetweenPoints(vec3, vec31, 0, 0, 0))
            {
                this.currentPath.setCurrentPathIndex(j);
                break;
            }
        }

        this.PNExtender.checkForStuck(vec3);
    }

    /**
     * Trims path data from the end to the first sun covered block
     */
    protected void removeSunnyPath()
    {
    	this.PNExtender.removeSunnyPath();
    }

    /**
     * Returns true when an entity of specified size could safely walk in a straight line between the two points. Args:
     * pos1, pos2, entityXSize, entityYSize, entityZSize
     */
    protected boolean isDirectPathBetweenPoints(Vec3 posVec31, Vec3 posVec32, int sizeX, int sizeY, int sizeZ)
    {
        MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(posVec31, new Vec3i(posVec32.xCoord, posVec32.yCoord + (double)this.theEntity.height * 0.5D, posVec32.zCoord), false);
        return movingobjectposition == null || movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.MISS;
    }

}
