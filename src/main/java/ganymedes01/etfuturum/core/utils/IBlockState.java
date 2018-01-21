package ganymedes01.etfuturum.core.utils;

import java.util.Collection;

import net.minecraft.block.Block;

import com.google.common.collect.ImmutableMap;


public interface IBlockState {
    Collection<IProperty> getPropertyNames();

    <T extends Comparable<T>> T getValue(IProperty<T> property);

    <T extends Comparable<T>, V extends T> IBlockState withProperty(IProperty<T> property, V value);

    <T extends Comparable<T>> IBlockState cycleProperty(IProperty<T> property);

    ImmutableMap<IProperty, Comparable> getProperties();

    Block getBlock();
}
