
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.radbuckcurrency.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.radbuckcurrency.network.OpenBankKeyMessage;
import net.mcreator.radbuckcurrency.network.CloseBankKeyMessage;
import net.mcreator.radbuckcurrency.RadbuckCurrencyMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class RadbuckCurrencyModKeyMappings {
	public static final KeyMapping OPEN_BANK_KEY = new KeyMapping("key.radbuck_currency.open_bank_key", GLFW.GLFW_KEY_APOSTROPHE, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				OPEN_BANK_KEY_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - OPEN_BANK_KEY_LASTPRESS);
				RadbuckCurrencyMod.PACKET_HANDLER.sendToServer(new OpenBankKeyMessage(1, dt));
				OpenBankKeyMessage.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping CLOSE_BANK_KEY = new KeyMapping("key.radbuck_currency.close_bank_key", GLFW.GLFW_KEY_MINUS, "key.categories.misc") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				CLOSE_BANK_KEY_LASTPRESS = System.currentTimeMillis();
			} else if (isDownOld != isDown && !isDown) {
				int dt = (int) (System.currentTimeMillis() - CLOSE_BANK_KEY_LASTPRESS);
				RadbuckCurrencyMod.PACKET_HANDLER.sendToServer(new CloseBankKeyMessage(1, dt));
				CloseBankKeyMessage.pressAction(Minecraft.getInstance().player, 1, dt);
			}
			isDownOld = isDown;
		}
	};
	private static long OPEN_BANK_KEY_LASTPRESS = 0;
	private static long CLOSE_BANK_KEY_LASTPRESS = 0;

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(OPEN_BANK_KEY);
		event.register(CLOSE_BANK_KEY);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				OPEN_BANK_KEY.consumeClick();
				CLOSE_BANK_KEY.consumeClick();
			}
		}
	}
}
