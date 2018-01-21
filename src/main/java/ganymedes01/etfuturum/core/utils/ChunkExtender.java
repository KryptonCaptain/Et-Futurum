package ganymedes01.etfuturum.core.utils;

import net.minecraft.block.Block;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public abstract class ChunkExtender extends Chunk {

	abstract Chunk provideChunk(BlockPos blockPosIn);
	
	public ChunkExtender(World p_i45447_1_, Block[] p_i45447_2_, byte[] p_i45447_3_, int p_i45447_4_, int p_i45447_5_) {
		super(p_i45447_1_, p_i45447_2_, p_i45447_3_, p_i45447_4_, p_i45447_5_);
		// TODO Auto-generated constructor stub
	}


	private static ExtendedBlockStorage[] storageArrays;
	
	
	public static int getLightSubtracted(BlockPos pos, int amount)
    {
		
		
		
        int i = pos.getX() & 15;
        int j = pos.getY();
        int k = pos.getZ() & 15;
        ExtendedBlockStorage extendedblockstorage = storageArrays[j >> 4];
		return k;

}
	

}
