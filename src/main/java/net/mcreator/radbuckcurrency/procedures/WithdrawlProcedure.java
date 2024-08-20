package net.mcreator.radbuckcurrency.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.radbuckcurrency.network.RadbuckCurrencyModVariables;
import net.mcreator.radbuckcurrency.init.RadbuckCurrencyModBlocks;

import java.util.HashMap;

public class WithdrawlProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if ((entity.getCapability(RadbuckCurrencyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadbuckCurrencyModVariables.PlayerVariables())).bankAccount - new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:withdrawAmount") ? ((EditBox) guistate.get("text:withdrawAmount")).getValue() : "") >= 0) {
			{
				double _setval = (entity.getCapability(RadbuckCurrencyModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new RadbuckCurrencyModVariables.PlayerVariables())).bankAccount - new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(guistate.containsKey("text:withdrawAmount") ? ((EditBox) guistate.get("text:withdrawAmount")).getValue() : "");
				entity.getCapability(RadbuckCurrencyModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.bankAccount = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player) {
				ItemStack _setstack = new ItemStack(RadbuckCurrencyModBlocks.RAD_BUCKS.get()).copy();
				_setstack.setCount((int) Math.round(new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(guistate.containsKey("text:withdrawAmount") ? ((EditBox) guistate.get("text:withdrawAmount")).getValue() : "")));
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
		}
	}
}
