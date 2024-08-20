package net.mcreator.radbuckcurrency.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.radbuckcurrency.network.RadbuckCurrencyModVariables;

import java.util.function.Supplier;
import java.util.Map;

public class DepositProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = (entity.getCapability(RadbuckCurrencyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadbuckCurrencyModVariables.PlayerVariables())).bankAccount + new Object() {
				public int getAmount(int sltid) {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack stack = ((Slot) _slots.get(sltid)).getItem();
						if (stack != null)
							return stack.getCount();
					}
					return 0;
				}
			}.getAmount(0);
			entity.getCapability(RadbuckCurrencyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.bankAccount = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
			((Slot) _slots.get(0)).remove((int) (entity.getCapability(RadbuckCurrencyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadbuckCurrencyModVariables.PlayerVariables())).bankAccount);
			_player.containerMenu.broadcastChanges();
		}
	}
}
