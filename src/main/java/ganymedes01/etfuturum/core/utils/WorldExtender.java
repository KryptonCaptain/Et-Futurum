package ganymedes01.etfuturum.core.utils;


import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;


import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;


public class WorldExtender extends World {
	
    /** If -1 there is no maximum distance */
	IChunkProviderExtender chunkProviderExtender;
    private float maximumHomeDistance = -1.0F;
	public final List<EntityPlayer> playerEntities = Lists.<EntityPlayer>newArrayList();
	private WorldSettings.GameType gameType = WorldSettings.GameType.NOT_SET;
	private int seaLevel = 63;

	public WorldExtender(ISaveHandler p_i45368_1_, String p_i45368_2_, WorldProvider p_i45368_3_,
			WorldSettings p_i45368_4_, Profiler p_i45368_5_) {
		super(p_i45368_1_, p_i45368_2_, p_i45368_3_, p_i45368_4_, p_i45368_5_);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isBlockLoaded(BlockPos pos, boolean b)
    {
        return this.isBlockLoaded(pos, true);
    }

	public float getLightBrightness(BlockPos pos)
    {
        return this.getLightBrightnessTable()[this.getLightFromNeighbors(pos)];
    }
	
	protected final float[] lightBrightnessTable = new float[16];
	
	public float[] getLightBrightnessTable()
    {
        return this.lightBrightnessTable;
    }
	    public int getLightFromNeighbors(BlockPos pos)
    {
        return this.getLight(pos, true);
    }
	
	public IBlockState getBlockState(BlockPos pos)
    {
        if (!this.isValid(pos))
        {
            return (IBlockState) Blocks.air;
        }
        else
        {
            Chunk chunk = this.getChunkFromBlockCoords(pos);
            return getBlockState(pos);
        }
    }
	
	private int skylightSubtracted;
	private BlockPos homePosition;
	
	
	public int getLight(BlockPos pos, boolean checkNeighbors)
    {
        if (pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000)
        {
            if (checkNeighbors && this.getBlockState(pos).getBlock().getUseNeighborBrightness())
            {
                int i1 = this.getLight(pos.up(), false);
                int i = this.getLight(pos.east(), false);
                int j = this.getLight(pos.west(), false);
                int k = this.getLight(pos.south(), false);
                int l = this.getLight(pos.north(), false);

                if (i > i1)
                {
                    i1 = i;
                }

                if (j > i1)
                {
                    i1 = j;
                }

                if (k > i1)
                {
                    i1 = k;
                }

                if (l > i1)
                {
                    i1 = l;
                }

                return i1;
            }
            else if (pos.getY() < 0)
            {
                return 0;
            }
            else
            {
                if (pos.getY() >= 256)
                {
                    pos = new BlockPos(pos.getX(), 255, pos.getZ());
                }

                Chunk chunk = this.getChunkFromBlockCoords(pos);
                return ChunkExtender.getLightSubtracted(pos, this.skylightSubtracted);
            }
        }
        else
        {
            return 15;
        }
    }
	/**
     * Check if the given BlockPos has valid coordinates
     */
    private boolean isValid(BlockPos pos)
    {
        return pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000 && pos.getY() >= 0 && pos.getY() < 256;
    }

	@Override
	protected IChunkProvider createChunkProvider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int func_152379_p() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Entity getEntityByID(int p_73045_1_) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Chunk getChunkFromBlockCoords(BlockPos pos)
    {
        return this.getChunkFromChunkCoords(pos.getX() >> 4, pos.getZ() >> 4);
    }
	
	private void spawnParticle(int particleID, boolean shouldIgnoreRange, double xCoord, double yCoord, double zCoord,
			double xOffset, double yOffset, double zOffset, int[] p_175688_14_) {
		
	}
	
	

	/**
     * Checks if a block's material is opaque, and that it takes up a full cube
     */
    public boolean isBlockNormalCube(BlockPos pos, boolean _default)
    {
        if (!this.isValid(pos))
        {
            return _default;
        }
        else
        {
            Chunk chunk = this.chunkProviderExtender.provideChunk(pos);

            if (chunk.isEmpty())
            {
                return _default;
            }
            else
            {
                Block block = this.getBlockState(pos).getBlock();
                return block.isNormalCube();
            }
        }
    }
    
    public <T extends Entity> List<T> getPlayers(Class <? extends T > playerType, Predicate <? super T > filter)
    {
        List<T> list = Lists.<T>newArrayList();

        for (Entity entity : this.playerEntities)
        {
            if (playerType.isAssignableFrom(entity.getClass()) && filter.apply((T)entity))
            {
                list.add((T)entity);
            }
        }

        return list;
    }
	

	
	
	 /**
     * Sets home position and max distance for it
     */
    public void setHomePosAndDistance(BlockPos pos, int distance)
    {
        this.homePosition = pos;
        this.maximumHomeDistance = (float)distance;
    }
    
	private EnumDifficulty difficulty;
	/**
     * Returns the world's WorldInfo object
     */
    public WorldInfo getWorldInfo()
    {
        return this.worldInfo;
    }
	
	public EnumDifficulty getDifficulty()
    {
        return ((IChunkProviderExtender) this.getWorldInfo()).getDifficulty();
    }
}
