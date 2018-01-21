package ganymedes01.etfuturum.entities.ai;
//package thaumcraft.common.entities.ai.misc;

import net.minecraft.util.Vec3;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;

public class AIWander extends EntityAIBase
{
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private boolean field_179482_g;
    private static final String __OBFID = "CL_00001608";
    
    public AIWander(final EntityCreature p_i1648_1_, final double p_i1648_2_) {
        this.entity = p_i1648_1_;
        this.speed = p_i1648_2_;
        this.setMutexBits(1);
    }
    
    public boolean shouldExecute() {
        if (!this.field_179482_g) {
            if (this.entity.getAge() >= 100) {
                return false;
            }
            if (this.entity.getRNG().nextInt(120) != 0) {
                return false;
            }
        }
        final Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
        if (vec3 == null) {
            return false;
        }
        this.xPosition = vec3.xCoord;
        this.yPosition = vec3.yCoord;
        this.zPosition = vec3.zCoord;
        this.field_179482_g = false;
        return true;
    }
    
    public boolean continueExecuting() {
        return !this.entity.getNavigator().noPath();
    }
    
    public void setWander() {
        this.field_179482_g = true;
    }
    
    public void startExecuting() {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}
