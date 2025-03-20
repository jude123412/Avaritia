package morph.avaritia.compat.thaumcraft;

import static morph.avaritia.compat.Compat.*;
import static morph.avaritia.compat.Compat.ThaumcraftIsLoaded;
import static morph.avaritia.init.ModItems.registerItem;

import morph.avaritia.init.ModBlocks;
import morph.avaritia.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import morph.avaritia.Avaritia;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;

public class Thaumcraft {

    public static Aspect LIMITLESS;
    public static Aspect ULTRA_DEATH;


    public static ItemDarkhold darkhold;
    public static ItemBigPearl bigPearl;
    public static ItemAkashicRecord akashicRecord;

    public static void preInit() throws ItemNotFoundException {
        ULTRA_DEATH = new Aspect("terminus", 0xb90000, new Aspect[]{Aspect.DEATH, Aspect.ELDRITCH}, new ResourceLocation("avaritia", "textures/misc/terminus.png"), 771);
        LIMITLESS = new Aspect("ascension", 0x000087, new Aspect[]{Aspect.DESIRE, ULTRA_DEATH}, new ResourceLocation("avaritia", "textures/misc/ascension.png"), 771);

        darkhold = registerItem(new ItemDarkhold());
        bigPearl = registerItem(new ItemBigPearl());
        akashicRecord = registerItem(new ItemAkashicRecord());

        OreDictionary.registerOre("pearlExtreme", new ItemStack(bigPearl, 1));
    }

    public static void registerInit() throws ItemNotFoundException {
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Avaritia.MOD_ID, "darkhold"), new InfusionRecipe(
                "DARKHOLD",
                new ItemStack(darkhold),
                12,
                new AspectList().add(Aspect.MAGIC, 500).add(Aspect.AURA, 250).add(Aspect.FLUX, 250).add(Aspect.DARKNESS, 250).add(Aspect.ELDRITCH, 250)
                        .add(Aspect.SOUL, 250).add(Aspect.MAN, 250).add(ULTRA_DEATH, 125).add(LIMITLESS, 125),
                Ingredient.fromItem(ItemsTC.thaumonomicon),
                "gemInfinityCatalyst",
                "gemInfinityCatalyst",
                "blockVoid",
                "blockVoid",
                "blockInfinity",
                "blockInfinity",
                "blockCosmicNeutronium",
                "blockCosmicNeutronium",
                Ingredient.fromItem(ItemsTC.primordialPearl),
                Ingredient.fromItem(ItemsTC.primordialPearl)));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Avaritia.MOD_ID, "big_pearl"), new InfusionRecipe(
                "BIG_PEARL",
                new ItemStack(bigPearl),
                12,
                new AspectList().add(Aspect.AIR, 250).add(Aspect.FIRE, 250).add(Aspect.WATER, 250).add(Aspect.EARTH, 250).add(Aspect.ORDER, 250).add(Aspect.ENTROPY, 250).add(Aspect.MAGIC, 250).add(Aspect.ELDRITCH, 250).add(ULTRA_DEATH, 125),
                Ingredient.fromItem(ItemsTC.primordialPearl),
                Ingredient.fromItem(ItemsTC.primordialPearl),
                Ingredient.fromItem(ItemsTC.primordialPearl),
                Ingredient.fromItem(ItemsTC.primordialPearl),
                Ingredient.fromItem(ItemsTC.primordialPearl),
                Ingredient.fromItem(ItemsTC.primordialPearl),
                Ingredient.fromItem(ItemsTC.primordialPearl),
                Ingredient.fromItem(ItemsTC.primordialPearl)
        ));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Avaritia.MOD_ID, "akashic_record"), new InfusionRecipe(
                "AKASHIC_RECORD",
                new ItemStack(akashicRecord),
                12,
                new AspectList().add(Aspect.MIND, 250).add(Aspect.MAGIC, 250).add(ULTRA_DEATH, 125),
                new ItemStack(ItemsTC.curio, 1, 4),
                "ingotInfinity",
                "ingotCrystalMatrix",
                "ingotInfinity",
                "ingotCrystalMatrix",
                "ingotInfinity",
                "ingotCrystalMatrix",
                "ingotInfinity",
                "ingotCrystalMatrix"
        ));

        ThaumcraftApi.registerResearchLocation(new ResourceLocation(Avaritia.MOD_ID, "research/research.json"));
    }

    public static void aspectInit() throws ItemNotFoundException {
        AspectEventProxy aspectEventProxy = new AspectEventProxy();

        // Item Aspects
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.resource, 1, 4), (new AspectList()).add(ULTRA_DEATH, 2).add(Aspect.METAL, 12).add(Aspect.ENERGY, 12));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.resource, 1, 5), (new AspectList()).add(ULTRA_DEATH, 5).add(Aspect.EXCHANGE, 12));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.resource, 1, 6), (new AspectList()).add(ULTRA_DEATH, 16).add(LIMITLESS, 32).add(Aspect.ELDRITCH, 64));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 0), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.METAL, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 1), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.METAL, 100).add(Aspect.DESIRE, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 2), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.SENSES, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 3), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.MECHANISM, 100).add(Aspect.ENERGY, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 4), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.CRYSTAL, 100).add(Aspect.ORDER, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 5), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.METAL, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 6), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.METAL, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 7), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.UNDEAD, 10).add(Aspect.METAL, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 8), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.ALCHEMY, 10).add(Aspect.METAL, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 9), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.METAL, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 10), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.CRYSTAL, 100).add(Aspect.DESIRE, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 11), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.CRYSTAL, 100).add(Aspect.DESIRE, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 12), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.ENERGY, 100).add(Aspect.DESIRE, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 13), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.METAL, 100).add(Aspect.DESIRE, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.singularity, 1, 14), (new AspectList()).add(ULTRA_DEATH, 3).add(Aspect.METAL, 100).add(Aspect.DESIRE, 100));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_sword), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_bow), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_pickaxe), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_shovel), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_axe), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_hoe), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_helmet), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_chestplate), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_pants), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.infinity_boots), (new AspectList()).add(ULTRA_DEATH, 50).add(LIMITLESS, 50).add(Aspect.ELDRITCH, 200).add(Aspect.ENERGY, 200).add(Aspect.EXCHANGE, 200));
        aspectEventProxy.registerComplexObjectTag(new ItemStack(ModItems.skull_sword), (new AspectList()).add(ULTRA_DEATH, 1).add(Aspect.FIRE, 2).add(Aspect.DEATH, 4).add(Aspect.CRYSTAL, 16).add(Aspect.PROTECT, 32));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.endest_pearl), (new AspectList()).add(ULTRA_DEATH, 5).add(Aspect.MOTION, 30).add(Aspect.ELDRITCH, 15).add(Aspect.DARKNESS, 15).add(Aspect.AURA, 15));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.ultimate_stew), (new AspectList()).add(ULTRA_DEATH, 1).add(Aspect.PLANT, 100).add(Aspect.LIFE, 100).add(Aspect.AURA, 15));
        aspectEventProxy.registerObjectTag(new ItemStack(ModItems.cosmic_meatballs), (new AspectList()).add(ULTRA_DEATH, 1).add(Aspect.BEAST, 100).add(Aspect.LIFE, 100).add(Aspect.AURA, 15));
        aspectEventProxy.registerObjectTag(new ItemStack(darkhold), (new AspectList()).add(ULTRA_DEATH, 20).add(Aspect.DARKNESS, 100).add(Aspect.ELDRITCH, 100).add(Aspect.FLUX, 50));

        // Block Aspects
        aspectEventProxy.registerObjectTag(new ItemStack(ModBlocks.compressedCraftingTable), (new AspectList()).add(Aspect.CRAFT, 59).add(Aspect.PLANT, 21));
        aspectEventProxy.registerObjectTag(new ItemStack(ModBlocks.doubleCompressedCraftingTable), (new AspectList()).add(Aspect.CRAFT, 127).add(Aspect.PLANT, 52));
        aspectEventProxy.registerObjectTag(new ItemStack(ModBlocks.resource, 1, 2), (new AspectList()).add(Aspect.CRYSTAL, 250).add(Aspect.DESIRE, 150).add(Aspect.MAGIC, 50).add(Aspect.ORDER, 50).add(Aspect.ELDRITCH, 25).add(Aspect.AURA, 25));
        aspectEventProxy.registerObjectTag(new ItemStack(ModBlocks.neutron_collector), (new AspectList()).add(Aspect.METAL, 64).add(Aspect.ENERGY, 64).add(Aspect.MECHANISM, 64));
        aspectEventProxy.registerObjectTag(new ItemStack(ModBlocks.neutronium_compressor), (new AspectList()).add(ULTRA_DEATH, 32).add(Aspect.METAL, 64).add(Aspect.ENERGY, 64).add(Aspect.MECHANISM, 64));
    }

    public static void registerRecipes() throws ItemNotFoundException {

    }
}
