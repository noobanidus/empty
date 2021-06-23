package noobanidus.mods.mysticalbiomes.init;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.CoralPlantBlock;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.ColumnBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import noobanidus.libs.noobutil.registry.ConfiguredRegistry;
import noobanidus.libs.noobutil.types.LazyStateSupplier;
import noobanidus.libs.noobutil.world.gen.config.*;
import noobanidus.mods.mysticalbiomes.MysticalBiomes;
import noobanidus.mods.mysticalbiomes.world.SupplierBlockStateProvider;
import noobanidus.mods.mysticalbiomes.world.placer.ColumnBasePlacer;

import java.sql.Blob;
import java.util.Arrays;

public class ConfiguredFeatures {
  public static final ConfiguredRegistry<ConfiguredFeature<?, ?>> REGISTRY = new ConfiguredRegistry<>(MysticalBiomes.MODID, WorldGenRegistries.CONFIGURED_FEATURE);

  private static ConfiguredFeature<?, ?> EMPTY = register("empty", ModFeatures.EMPTY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

  private static ConfiguredFeature<?, ?> STANDARD_BOULDER = register("standard_boulder", ModFeatures.BIG_ROCK.withConfiguration(new BlockStateRadiusFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()), 2)));
  private static ConfiguredFeature<?, ?> ORE_BOULDER = register("ore_boulder", ModFeatures.ROCK_ORE.withConfiguration(new TwoBlockStateRadiusFeatureConfig(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()),
      new WeightedBlockStateProvider()
          .addWeightedBlockstate(Blocks.IRON_ORE.getDefaultState(), 3)
          .addWeightedBlockstate(Blocks.COAL_ORE.getDefaultState(), 30)
          .addWeightedBlockstate(Blocks.GOLD_ORE.getDefaultState(), 1)
          .addWeightedBlockstate(Blocks.COBBLESTONE.getDefaultState(), 10),
      2)));
  public static ConfiguredFeature<?, ?> BOULDER_FEATURE = register("boulder", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(ORE_BOULDER.withChance(0.4f), STANDARD_BOULDER.withChance(0.1f)), EMPTY))
      .withPlacement(Features.Placements.PATCH_PLACEMENT)); // HEIGHTMAP_PLACEMENT).func_242732_c(1));

  public static ConfiguredFeature<?, ?> ORE_WEIGHTED_BOULDER_FEATURE = register("ore_weighted_boulder", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(ORE_BOULDER.withChance(0.8f), STANDARD_BOULDER.withChance(0.1f)), EMPTY))
      .withPlacement(Features.Placements.PATCH_PLACEMENT)); // HEIGHTMAP_PLACEMENT).func_242732_c(1));

  public static final ConfiguredFeature<?, ?> TALL_RED_MUSHROOM = register("tall_red_mushroom", ModFeatures.TALL_RED_MUSHROOM.withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(Blocks.RED_MUSHROOM_BLOCK.getDefaultState().with(HugeMushroomBlock.DOWN, false)), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 2)));

  public static final ConfiguredFeature<?, ?> WIDE_TALL_RED_MUSHROOM = register("wide_tall_red_mushroom", ModFeatures.TALL_RED_MUSHROOM.withConfiguration(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(Blocks.RED_MUSHROOM_BLOCK.getDefaultState().with(HugeMushroomBlock.DOWN, false)), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 3)));

  public static final ConfiguredFeature<?, ?> TALL_RED_MUSHROOMS = register("tall_red_mushrooms", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(WIDE_TALL_RED_MUSHROOM.withChance(0.3f)), TALL_RED_MUSHROOM)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.1f, 1))));

  public static final ConfiguredFeature<?, ?> SPREAD_OAK_TREES = register("spread_oak_trees", Feature.RANDOM_SELECTOR.withConfiguration(new MultipleRandomFeatureConfig(ImmutableList.of(Features.FANCY_OAK.withChance(0.5f), Features.FANCY_OAK_BEES_0002.withChance(0.05f), Features.OAK_BEES_0002.withChance(0.3f)), Features.OAK)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));

  public static final ConfiguredFeature<?, ?> PATCH_WILD_AUBERGINE = register("patch_wild_aubergine", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(new SupplierBlockStateProvider("mysticalworld", "wild_aubergine_crop"), SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).replaceable().func_227317_b_().build()).withPlacement(Features.Placements.PATCH_PLACEMENT));

  public static final ConfiguredFeature<?, ?> DISK_GRAVEL = register("supplier_disk_gravel", ModFeatures.SUPPLIER_DISK.withConfiguration(new SupplierSphereReplaceConfig(Blocks.GRAVEL.getDefaultState(), FeatureSpread.func_242253_a(1, 4), 1, ImmutableList.of(new LazyStateSupplier("mysticalworld", "soft_stone")))).func_242732_c(3).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));

  public static final ConfiguredFeature<?, ?> DISK_ANDESITE = register("supplier_disk_andesite", ModFeatures.SUPPLIER_DISK.withConfiguration(new SupplierSphereReplaceConfig(Blocks.ANDESITE.getDefaultState(), FeatureSpread.func_242253_a(1, 4), 1, ImmutableList.of(new LazyStateSupplier("mysticalworld", "soft_stone")))).func_242732_c(1).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));

  public static final ConfiguredFeature<?, ?> SMALL_COBBLE_TREE = register("small_cobble_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration(new SupplierBlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()), new ColumnBasePlacer(2, 5, 1, 3, Arrays.asList(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()), new LazyStateSupplier(Blocks.ANDESITE.getDefaultState())), 8)).tries(8).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()))).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.9F, 1))));

  public static final ConfiguredFeature<?, ?> SMALL_SOFT_STONE_TREE = register("small_soft_stone_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration((new SupplierBlockClusterFeatureConfig.Builder(new SupplierBlockStateProvider("mysticalworld", "soft_stone"), new ColumnBlockPlacer(2, 5))).tries(10).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"))).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));

  public static final ConfiguredFeature<?, ?> COBBLE_TREE = register("cobble_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration(new SupplierBlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()), new ColumnBasePlacer(3, 8, 3, 5, Arrays.asList(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()), new LazyStateSupplier(Blocks.ANDESITE.getDefaultState())), 8)).tries(10).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()))).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));

  public static final ConfiguredFeature<?, ?> SOFT_STONE_TREE = register("soft_stone_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration((new SupplierBlockClusterFeatureConfig.Builder(new SupplierBlockStateProvider("mysticalworld", "soft_stone"), new ColumnBlockPlacer(3, 8))).tries(12).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"))).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.3F, 1))));

  public static final ConfiguredFeature<?, ?> LARGE_COBBLE_TREE = register("large_cobble_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration(new SupplierBlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.COBBLESTONE.getDefaultState()), new ColumnBasePlacer(5, 15, 3, 6, Arrays.asList(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()), new LazyStateSupplier(Blocks.ANDESITE.getDefaultState())), 6)).tries(10).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"), new LazyStateSupplier(Blocks.GRAVEL.getDefaultState()))).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.3F, 1))));

  public static final ConfiguredFeature<?, ?> LARGE_SOFT_STONE_TREE = register("large_soft_stone_tree", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration((new SupplierBlockClusterFeatureConfig.Builder(new SupplierBlockStateProvider("mysticalworld", "soft_stone"), new ColumnBlockPlacer(5, 15))).tries(12).noProjection().whitelist(Sets.newHashSet(new LazyStateSupplier("mysticalworld", "soft_stone"))).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.6F, 1))));

  public static final ConfiguredFeature<?, ?> REGULAR_LAVA = register("regular_lake_lava", Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.LAVA.getDefaultState())).withPlacement(ModPlacers.LAVA_CENTER.configure(new ChanceConfig(30))));

  public static final ConfiguredFeature<?, ?> CORAL_LAKE = register("coral_lake", ModFeatures.WEIGHTED_LAKE.withConfiguration(new WeightedBlockStateFeatureConfig(new WeightedBlockStateProvider()
      .addWeightedBlockstate(Blocks.DEAD_BRAIN_CORAL_BLOCK.getDefaultState(), 20)
      .addWeightedBlockstate(Blocks.DEAD_BUBBLE_CORAL_BLOCK.getDefaultState(), 20)
      .addWeightedBlockstate(Blocks.DEAD_FIRE_CORAL_BLOCK.getDefaultState(), 20)
      .addWeightedBlockstate(Blocks.DEAD_HORN_CORAL_BLOCK.getDefaultState(), 20)
      .addWeightedBlockstate(Blocks.DEAD_TUBE_CORAL_BLOCK.getDefaultState(), 20)
      .addWeightedBlockstate(Blocks.DEAD_TUBE_CORAL.getDefaultState().with(CoralPlantBlock.WATERLOGGED, true), 2)
      .addWeightedBlockstate(Blocks.DEAD_BRAIN_CORAL.getDefaultState().with(CoralPlantBlock.WATERLOGGED, true), 2)
      .addWeightedBlockstate(Blocks.DEAD_BUBBLE_CORAL.getDefaultState().with(CoralPlantBlock.WATERLOGGED, true), 2)
      .addWeightedBlockstate(Blocks.DEAD_FIRE_CORAL.getDefaultState().with(CoralPlantBlock.WATERLOGGED, true), 2)
      .addWeightedBlockstate(Blocks.DEAD_HORN_CORAL.getDefaultState().with(CoralPlantBlock.WATERLOGGED, true), 2)
      .addWeightedBlockstate(Blocks.BONE_BLOCK.getDefaultState(), 8))).withPlacement(ModPlacers.WATER_CENTER.configure(new ChanceConfig(5))));


  public static final ConfiguredFeature<?, ?> PATCH_STONEPETAL = register("patch_stonepetal", ModFeatures.SUPPLIER_RANDOM_PATCH.withConfiguration((new SupplierBlockClusterFeatureConfig.Builder(new SupplierBlockStateProvider("mysticalworld", "stonepetal"), SimpleBlockPlacer.PLACER)).tries(18).build()).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2));

  public static final ConfiguredFeature<?, ?> SURFACE_FOSSIL = register("surface_fossil", ModFeatures.SURFACE_FOSSIL.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).chance(75));

  public static final ConfiguredFeature<?, ?> SMALL_SURFACE_GOLD = register("small_surface_gold", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, Blocks.GOLD_ORE.getDefaultState(), 4)).range(48).square().func_242731_b(8));

  public static final ConfiguredFeature<?, ?> MEGA_DARK = register("spooky_mega_dark_tree", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.DARK_OAK_LEAVES.getDefaultState()), new MegaPineFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), FeatureSpread.func_242253_a(9, 0)), new GiantTrunkPlacer(9, 2, 4), new TwoLayerFeature(1, 1, 2))).build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.4F, 1))));

  public static final ConfiguredFeature<?, ?> DARK_TREE = register("dark_tree", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()), new SimpleBlockStateProvider(Blocks.DARK_OAK_LEAVES.getDefaultState()), new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(1, 2), FeatureSpread.func_242253_a(4, 0)), new StraightTrunkPlacer(6, 3, 0), new TwoLayerFeature(2, 0, 1))).setIgnoreVines().build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(11, 1.0F, 2))));

  private static WeightedBlockStateProvider PUMPKINS = null;

  public static WeightedBlockStateProvider getPumpkins() {
    if (PUMPKINS == null) {
      PUMPKINS = new WeightedBlockStateProvider();
      PUMPKINS.addWeightedBlockstate(Blocks.JACK_O_LANTERN.getDefaultState().with(CarvedPumpkinBlock.FACING, Direction.NORTH), 1);
      PUMPKINS.addWeightedBlockstate(Blocks.JACK_O_LANTERN.getDefaultState().with(CarvedPumpkinBlock.FACING, Direction.EAST), 1);
      PUMPKINS.addWeightedBlockstate(Blocks.JACK_O_LANTERN.getDefaultState().with(CarvedPumpkinBlock.FACING, Direction.WEST), 1);
      PUMPKINS.addWeightedBlockstate(Blocks.JACK_O_LANTERN.getDefaultState().with(CarvedPumpkinBlock.FACING, Direction.SOUTH), 1);
    }
    return PUMPKINS;
  }

  public static final ConfiguredFeature<?, ?> LANTERNS = register("lantern", Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(getPumpkins(), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new BlobFoliagePlacer(FeatureSpread.func_242253_a(1, 1), FeatureSpread.func_242253_a(1, 1), 1), new StraightTrunkPlacer(1, 0, 0), new TwoLayerFeature(2, 0, 1))).setIgnoreVines().build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(2, 0.8F, 1))));

/*  public static final ConfiguredFeature<?, ?> PATCH_JACK_O_LANTERNS = register("patch_jack_o_lantern", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(getPumpkins(), SimpleBlockPlacer.PLACER)).*//*tries(16).*//*whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK)).replaceable().func_227317_b_().build()).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.5F, 1))));*/

  private static ConfiguredFeature<?, ?> register(String id, ConfiguredFeature<?, ?> feature) {
    return REGISTRY.register(id, feature);
  }

  public static void load() {
  }
}
