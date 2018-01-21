package ganymedes01.etfuturum.core.utils;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

public class TessellatorExtender extends Tessellator {
	
    private static WorldRenderer worldRenderer;
	
	public static Tessellator getInstance()
    {
        /** The static instance of the Tessellator. */
        return instance;
    }

	public static WorldRenderer getWorldRenderer()
    {
        return worldRenderer;
    }
}
