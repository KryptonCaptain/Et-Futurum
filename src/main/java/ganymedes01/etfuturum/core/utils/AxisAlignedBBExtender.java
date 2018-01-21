package ganymedes01.etfuturum.core.utils;

import net.minecraft.util.AxisAlignedBB;

public class AxisAlignedBBExtender extends AxisAlignedBB {
	
	public static AxisAlignedBBExtender AABBExtender;
	
	public double minX;
    public double minY;
    public double minZ;
    public double maxX;
    public double maxY;
    public double maxZ;	
    
	protected AxisAlignedBBExtender(double p_i2300_1_, double p_i2300_3_, double p_i2300_5_, double p_i2300_7_,
			double p_i2300_9_, double p_i2300_11_) {
		super(p_i2300_1_, p_i2300_3_, p_i2300_5_, p_i2300_7_, p_i2300_9_, p_i2300_11_);
		// TODO Auto-generated constructor stub
	}

	public void AxisAlignedBB(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        this.minX = Math.min(x1, x2);
        this.minY = Math.min(y1, y2);
        this.minZ = Math.min(z1, z2);
        this.maxX = Math.max(x1, x2);
        this.maxY = Math.max(y1, y2);
        this.maxZ = Math.max(z1, z2);
    }
	


	/**
     * returns an AABB with corners x1, y1, z1 and x2, y2, z2
     */
    public static AxisAlignedBBExtender fromBounds(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        double d0 = Math.min(x1, x2);
        double d1 = Math.min(y1, y2);
        double d2 = Math.min(z1, z2);
        double d3 = Math.max(x1, x2);
        double d4 = Math.max(y1, y2);
        double d5 = Math.max(z1, z2);
        return new ganymedes01.etfuturum.core.utils.AxisAlignedBB(d0, d1, d2, d3, d4, d5);
    }

}
