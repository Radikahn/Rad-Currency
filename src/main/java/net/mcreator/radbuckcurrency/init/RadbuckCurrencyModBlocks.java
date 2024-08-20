
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.radbuckcurrency.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.radbuckcurrency.block.RadBucksBlock;
import net.mcreator.radbuckcurrency.RadbuckCurrencyMod;

public class RadbuckCurrencyModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, RadbuckCurrencyMod.MODID);
	public static final RegistryObject<Block> RAD_BUCKS = REGISTRY.register("rad_bucks", () -> new RadBucksBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
