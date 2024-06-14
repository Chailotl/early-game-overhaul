package com.chailotl.early_game_overhaul.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.chailotl.early_game_overhaul.Main.REQUIRE_AXE_TAG_ID;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class InjectAbstractBlock
{
	@Shadow public abstract Block getBlock();

	@Inject(at = @At("HEAD"), method = "isToolRequired", cancellable = true)
	private void requireAxe(CallbackInfoReturnable<Boolean> cir)
	{
		if (getBlock().getDefaultState().isIn(TagKey.of(RegistryKeys.BLOCK, REQUIRE_AXE_TAG_ID)))
		{
			cir.setReturnValue(true);
		}
	}
}