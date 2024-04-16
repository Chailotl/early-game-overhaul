package com.chailotl.early_game_overhaul;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class OreSampleBlock extends Block
{
	protected static final VoxelShape SHAPE;

	protected OreSampleBlock(Settings settings)
	{
		super(settings);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
	{
		return hasTopRim(world, pos.down());
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction,
		BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom)
	{
		return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
	{
		return SHAPE;
	}

	static {
		SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 4.0D, 12.0D);
	}
}