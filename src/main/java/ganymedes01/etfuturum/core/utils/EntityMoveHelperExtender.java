package ganymedes01.etfuturum.core.utils;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.MathHelper;

public class EntityMoveHelperExtender extends EntityMoveHelper {
	
    public EntityMoveHelperExtender(EntityLiving p_i1614_1_) {
		super(p_i1614_1_);
	}

	/**
     * Limits the given angle to a upper and lower limit.
     */
public float limitAngle(float p_75639_1_, float p_75639_2_, float p_75639_3_)
    {
        float f = MathHelper.wrapAngleTo180_float(p_75639_2_ - p_75639_1_);

        if (f > p_75639_3_)
        {
            f = p_75639_3_;
        }

        if (f < -p_75639_3_)
        {
            f = -p_75639_3_;
        }

        float f1 = p_75639_1_ + f;

        if (f1 < 0.0F)
        {
            f1 += 360.0F;
        }
        else if (f1 > 360.0F)
        {
            f1 -= 360.0F;
        }

        return f1;
    }

}
