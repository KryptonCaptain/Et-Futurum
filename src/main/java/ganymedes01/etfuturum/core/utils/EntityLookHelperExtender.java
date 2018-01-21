package ganymedes01.etfuturum.core.utils;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityLookHelper;

public class EntityLookHelperExtender extends EntityLookHelper {
	
	private boolean isLooking;
    private double posX;
    private double posY;
    private double posZ;
	
	public EntityLookHelperExtender(EntityLiving p_i1613_1_) {
		super(p_i1613_1_);
		// TODO Auto-generated constructor stub
	}
	 public EntityLookHelper getLookHelper()
	    {
	        return this.getLookHelper();
	    }
	 
	public boolean getIsLooking()
    {
        return this.isLooking;
    }

    public double getLookPosX()
    {
        return this.posX;
    }

    public double getLookPosY()
    {
        return this.posY;
    }

    public double getLookPosZ()
    {
        return this.posZ;
    }

}
