
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.radbuckcurrency.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.radbuckcurrency.world.inventory.BankGUIMenu;
import net.mcreator.radbuckcurrency.RadbuckCurrencyMod;

public class RadbuckCurrencyModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, RadbuckCurrencyMod.MODID);
	public static final RegistryObject<MenuType<BankGUIMenu>> BANK_GUI = REGISTRY.register("bank_gui", () -> IForgeMenuType.create(BankGUIMenu::new));
}
