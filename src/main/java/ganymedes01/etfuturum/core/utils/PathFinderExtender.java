package ganymedes01.etfuturum.core.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.world.IBlockAccess;

public class PathFinderExtender extends PathFinder {

	public PathFinderExtender(IBlockAccess p_i2137_1_, SwimNodeProcessor snp, boolean p_i2137_2_, boolean p_i2137_3_, boolean p_i2137_4_,
			boolean p_i2137_5_) {
		super(p_i2137_1_, p_i2137_2_, p_i2137_3_, p_i2137_4_, p_i2137_5_);
		// TODO Auto-generated constructor stub
	}

	/** The path being generated */
    private Path path = new Path();
    /** Selection of path points to add to the path */
    private PathPoint[] pathOptions = new PathPoint[32];
    private NodeProcessor nodeProcessor;
    


    
    /**
     * Creates a path from one entity to another within a minimum distance
     */
    public PathEntity createEntityPathTo(IBlockAccess blockaccess, Entity entityFrom, Entity entityTo, float dist)
    {
        return this.createEntityPathTo(blockaccess, entityFrom, entityTo.posX, entityTo.getBoundingBox().minY, entityTo.posZ, dist);
    }

    
    /**
     * Internal implementation of creating a path from an entity to a point
     */
    private PathEntity createEntityPathTo(IBlockAccess blockaccess, Entity entityIn, double x, double y, double z, float distance)
    {
        this.path.clearPath();
        this.nodeProcessor.initProcessor(blockaccess, entityIn);
        PathPoint pathpoint = this.nodeProcessor.getPathPointTo(entityIn);
        PathPoint pathpoint1 = this.nodeProcessor.getPathPointToCoords(entityIn, x, y, z);
        PathEntity pathentity = this.addToPath(entityIn, pathpoint, pathpoint1, distance);
        this.nodeProcessor.postProcess();
        return pathentity;
    }

    /**
     * Adds a path from start to end and returns the whole path
     */
    /** The distance along the path to this point */
    float totalPathDistance;
    /** The linear distance to the next point */
    float distanceToNext;
    /** The distance to the target */
    float distanceToTarget;
    /** The point preceding this in its assigned path */
    PathPoint previous;
    
    private PathEntity addToPath(Entity entityIn, PathPoint pathpointStart, PathPoint pathpointEnd, float maxDistance)
    {
    	totalPathDistance = 0.0F;
        distanceToNext = pathpointStart.distanceToSquared(pathpointEnd);
        distanceToTarget = distanceToNext;
        this.path.clearPath();
        this.path.addPoint(pathpointStart);
        PathPoint pathpoint = pathpointStart;

        while (!this.path.isPathEmpty())
        {
            PathPoint pathpoint1 = this.path.dequeue();

            if (pathpoint1.equals(pathpointEnd))
            {
                return this.createEntityPath(pathpointStart, pathpointEnd);
            }

            if (pathpoint1.distanceToSquared(pathpointEnd) < pathpoint.distanceToSquared(pathpointEnd))
            {
                pathpoint = pathpoint1;
            }

            pathpoint1.isFirst = true;
            int i = this.nodeProcessor.findPathOptions(this.pathOptions, entityIn, pathpoint1, pathpointEnd, maxDistance);

            for (int j = 0; j < i; ++j)
            {
                PathPoint pathpoint2 = this.pathOptions[j];
                float f = totalPathDistance + pathpoint1.distanceToSquared(pathpoint2);

                if (f < maxDistance * 2.0F && (!pathpoint2.isAssigned() || f < totalPathDistance))
                {
                    previous = pathpoint1;
                    totalPathDistance = f;
                    distanceToNext = pathpoint2.distanceToSquared(pathpointEnd);

                    if (pathpoint2.isAssigned())
                    {
                        this.path.changeDistance(pathpoint2, totalPathDistance + distanceToNext);
                    }
                    else
                    {
                        distanceToTarget = totalPathDistance + distanceToNext;
                        this.path.addPoint(pathpoint2);
                    }
                }
            }
        }

        if (pathpoint == pathpointStart)
        {
            return null;
        }
        else
        {
            return this.createEntityPath(pathpointStart, pathpoint);
        }
    }

    /**
     * Returns a new PathEntity for a given start and end point
     */
    private PathEntity createEntityPath(PathPoint start, PathPoint end)
    {
        int i = 1;

        for (PathPoint pathpoint = end; previous != null; pathpoint = previous)
        {
            ++i;
        }

        PathPoint[] apathpoint = new PathPoint[i];
        PathPoint pathpoint1 = end;
        --i;

        for (apathpoint[i] = end; previous != null; apathpoint[i] = pathpoint1)
        {
            pathpoint1 = previous;
            --i;
        }

        return new PathEntity(apathpoint);
    }
    
    private EntityLiving theEntity;
    
}
