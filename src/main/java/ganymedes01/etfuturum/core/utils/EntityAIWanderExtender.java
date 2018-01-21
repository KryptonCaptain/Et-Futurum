package ganymedes01.etfuturum.core.utils;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIWanderExtender extends EntityAIWander {
	
	 private EntityCreature entity;
	    private double xPosition;
	    private double yPosition;
	    private double zPosition;
	    private double speed;
	    private int executionChance;
	    private boolean mustUpdate;
    
    public EntityAIWanderExtender(EntityCreature creatureIn, double speedIn)
    {
        this(creatureIn, speedIn, 120);
    }
	    
    public EntityAIWanderExtender(EntityCreature p_i1648_1_, double p_i1648_2_, int i) {
		super(p_i1648_1_, p_i1648_2_);
	       this.executionChance = i;
	}

    
    
    @Override
    public boolean shouldExecute()
    {
        if (!this.mustUpdate)
        {
            if (this.entity.getAge() >= 100)
            {
                return false;
            }

            if (this.entity.getRNG().nextInt(this.executionChance) != 0)
            {
                return false;
            }
        }

        Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);

        if (vec3 == null)
        {
            return false;
        }
        else
        {
            this.xPosition = vec3.xCoord;
            this.yPosition = vec3.yCoord;
            this.zPosition = vec3.zCoord;
            this.mustUpdate = false;
            return true;
        }
    }
    /**
     * Changes task random possibility for execution
     */
    public void setExecutionChance(int newchance)
    {
    this.executionChance = newchance;
    }

    public void makeUpdate()
    {
        this.mustUpdate = true;
    }


}
