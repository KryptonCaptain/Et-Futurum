package ganymedes01.etfuturum.core.utils;

import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;


public abstract class RenderLivingExtender<T extends EntityLiving> extends RendererLivingEntity{

	public RenderLivingExtender(ModelBase p_i1262_1_, float p_i1262_2_) {
		super(p_i1262_1_, p_i1262_2_);
		}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}
	
	public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ)
    {
        if (this.shouldRender(livingEntity, camera, camX, camY, camZ))
        {
            return true;
        }
        else if (livingEntity.getLeashed() && livingEntity.getLeashedToEntity() != null)
        {
            Entity entity = livingEntity.getLeashedToEntity();
            return camera.isBoundingBoxInFrustum(entity.getBoundingBox());
        }
        else
        {
            return false;
        }
    }

}
