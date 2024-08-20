package net.mcreator.radbuckcurrency.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.radbuckcurrency.world.inventory.BankGUIMenu;
import net.mcreator.radbuckcurrency.procedures.GetMoneyProcedure;
import net.mcreator.radbuckcurrency.network.BankGUIButtonMessage;
import net.mcreator.radbuckcurrency.RadbuckCurrencyMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class BankGUIScreen extends AbstractContainerScreen<BankGUIMenu> {
	private final static HashMap<String, Object> guistate = BankGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox withdrawAmount;
	Button button_empty;

	public BankGUIScreen(BankGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 316;
		this.imageHeight = 196;
	}

	private static final ResourceLocation texture = new ResourceLocation("radbuck_currency:textures/screens/bank_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		withdrawAmount.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 197 && mouseX < leftPos + 221 && mouseY > topPos + 67 && mouseY < topPos + 91)
			guiGraphics.renderTooltip(font, Component.translatable("gui.radbuck_currency.bank_gui.tooltip_insert_money_to_deposit_to_bank"), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (withdrawAmount.isFocused())
			return withdrawAmount.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		withdrawAmount.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String withdrawAmountValue = withdrawAmount.getValue();
		super.resize(minecraft, width, height);
		withdrawAmount.setValue(withdrawAmountValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.radbuck_currency.bank_gui.label_deposit_rad_bucks"), 61, 75, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.radbuck_currency.bank_gui.label_rad_bank"), 73, 17, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.radbuck_currency.bank_gui.label_empty"), 206, 74, -12829636, false);
		guiGraphics.drawString(this.font,

				GetMoneyProcedure.execute(entity), 191, 17, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.radbuck_currency.bank_gui.label_withdraw_max_64"), 14, 44, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		withdrawAmount = new EditBox(this.font, this.leftPos + 123, this.topPos + 40, 118, 18, Component.translatable("gui.radbuck_currency.bank_gui.withdrawAmount")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.radbuck_currency.bank_gui.withdrawAmount").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.radbuck_currency.bank_gui.withdrawAmount").getString());
				else
					setSuggestion(null);
			}
		};
		withdrawAmount.setSuggestion(Component.translatable("gui.radbuck_currency.bank_gui.withdrawAmount").getString());
		withdrawAmount.setMaxLength(32767);
		guistate.put("text:withdrawAmount", withdrawAmount);
		this.addWidget(this.withdrawAmount);
		button_empty = Button.builder(Component.translatable("gui.radbuck_currency.bank_gui.button_empty"), e -> {
			if (true) {
				RadbuckCurrencyMod.PACKET_HANDLER.sendToServer(new BankGUIButtonMessage(0, x, y, z));
				BankGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 252, this.topPos + 39, 25, 20).build();
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
	}
}
