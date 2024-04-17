package com.chailotl.early_game_overhaul;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EarlyGameOverhaul implements ModInitializer
{
	public static final String MOD_ID = "early_game_overhaul";
	public static final Logger LOGGER = LoggerFactory.getLogger("early_game_overhaul");

	public static final Identifier HAS_SURFACE_ORE_ID = new Identifier(MOD_ID, "has_surface_ore");
	public static final Identifier SURFACE_ORE_COMMON_FEATURE_ID = new Identifier(MOD_ID, "patch_surface_ore_common");
	public static final Identifier SURFACE_ORE_RARE_FEATURE_ID = new Identifier(MOD_ID, "patch_surface_ore_rare");
	public static final Identifier REQUIRE_AXE_TAG_ID = new Identifier(MOD_ID, "require_axe");

	// Items
	public static final Item FLINT_AXE_HEAD = new Item(new FabricItemSettings());
	public static final Item ROTTEN_LEATHER = new Item(new FabricItemSettings());

	public static final Item FLINT_AXE = new AxeItem(ToolMaterials.WOOD, 6.0F, -3.2F, new Item.Settings());
	public static final Item COPPER_SWORD = new SwordItem(ToolMaterials.STONE, 3, -2.4F, new Item.Settings());
	public static final Item COPPER_PICKAXE = new PickaxeItem(ToolMaterials.STONE, 1, -2.8F, new Item.Settings());
	public static final Item COPPER_AXE = new AxeItem(ToolMaterials.STONE, 7.0F, -3.2F, new Item.Settings());
	public static final Item COPPER_SHOVEL = new ShovelItem(ToolMaterials.STONE, 1.5F, -3.0F, new Item.Settings());
	public static final Item COPPER_HOE = new HoeItem(ToolMaterials.STONE, -1, -2.0F, new Item.Settings());

	// Blocks
	public static final Block COAL_SAMPLE = new OreSampleBlock(FabricBlockSettings.create().strength(0.5f));
	public static final Block COPPER_SAMPLE = new OreSampleBlock(FabricBlockSettings.create().strength(0.5f));

	@Override
	public void onInitialize()
	{
		// Items
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "flint_axe_head"), FLINT_AXE_HEAD);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "rotten_leather"), ROTTEN_LEATHER);

		// Tools
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "flint_axe"), FLINT_AXE);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "copper_sword"), COPPER_SWORD);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "copper_pickaxe"), COPPER_PICKAXE);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "copper_axe"), COPPER_AXE);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "copper_shovel"), COPPER_SHOVEL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "copper_hoe"), COPPER_HOE);

		// Blocks
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "coal_sample"), COAL_SAMPLE);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "coal_sample"), new BlockItem(COAL_SAMPLE, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "copper_sample"), COPPER_SAMPLE);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "copper_sample"), new BlockItem(COPPER_SAMPLE, new FabricItemSettings()));

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.addAfter(Items.WOODEN_HOE, FLINT_AXE, COPPER_SHOVEL, COPPER_PICKAXE, COPPER_AXE, COPPER_HOE);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
			content.addAfter(Items.STONE_SWORD, COPPER_SWORD);
			content.addAfter(Items.WOODEN_AXE, FLINT_AXE);
			content.addAfter(Items.STONE_AXE, COPPER_AXE);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
			content.addAfter(Items.FLINT, FLINT_AXE_HEAD);
			content.addAfter(Items.LEATHER, ROTTEN_LEATHER);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
			content.addAfter(Items.DEEPSLATE_COAL_ORE, COAL_SAMPLE);
			content.addAfter(Items.DEEPSLATE_COPPER_ORE, COPPER_SAMPLE);
		});

		// BiomeSelectors.tag() isn't working :/
		BiomeModifications.addFeature(
				  //BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, HAS_SURFACE_ORE_ID)),
				  BiomeSelectors.foundInOverworld(),
				  GenerationStep.Feature.VEGETAL_DECORATION,
				  RegistryKey.of(RegistryKeys.PLACED_FEATURE, SURFACE_ORE_COMMON_FEATURE_ID));

		BiomeModifications.addFeature(
				  //BiomeSelectors.tag(TagKey.of(RegistryKeys.BIOME, HAS_SURFACE_ORE_ID)),
				  BiomeSelectors.foundInOverworld(),
				  GenerationStep.Feature.VEGETAL_DECORATION,
				  RegistryKey.of(RegistryKeys.PLACED_FEATURE, SURFACE_ORE_RARE_FEATURE_ID));
	}
}