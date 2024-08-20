package net.mcreator.radbuckcurrency.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.radbuckcurrency.network.RadbuckCurrencyModVariables;
import net.mcreator.radbuckcurrency.init.RadbuckCurrencyModBlocks;

import java.util.function.Supplier;
import java.util.Map;

public class GetMoneyProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == RadbuckCurrencyModBlocks.RAD_BUCKS.get()
				.asItem()) {
			return "$" + ((entity.getCapability(RadbuckCurrencyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadbuckCurrencyModVariables.PlayerVariables())).bankAccount == new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(0));
		}
		return "$" + (entity.getCapability(RadbuckCurrencyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadbuckCurrencyModVariables.PlayerVariables())).bankAccount;
	}
}
