package ganymedes01.etfuturum.core.utils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;

public interface IBlockAccessExtender extends IBlockAccess{
	
	TileEntity getTileEntity(BlockPos pos);

    @SideOnly(Side.CLIENT)
    int getCombinedLight(BlockPos pos, int lightValue);

    IBlockState getBlockState(BlockPos pos);

    /**
     * Checks to see if an air block exists at the provided location. Note that this only checks to see if the blocks
     * material is set to air, meaning it is possible for non-vanilla blocks to still pass this check.
     */
    boolean isAirBlock(BlockPos pos);

    @SideOnly(Side.CLIENT)
    BiomeGenBase getBiomeGenForCoords(BlockPos pos);

    /**
     * set by !chunk.getAreLevelsEmpty
     */
    @SideOnly(Side.CLIENT)
    boolean extendedLevelsInChunkCache();

    int getStrongPower(BlockPos pos, EnumFacing direction);

    @SideOnly(Side.CLIENT)
    WorldType getWorldType();

    /**
     * FORGE: isSideSolid, pulled up from {@link World}
     *
     * @param pos Position
     * @param side Side
     * @param _default default return value
     * @return if the block is solid on the side
     */
    boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default);

}
