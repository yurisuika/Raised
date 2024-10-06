package dev.yurisuika.raised.client.gui.screens;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.serialization.Codec;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.components.IconToggleButton;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.properties.Element;
import dev.yurisuika.raised.util.properties.Position;
import dev.yurisuika.raised.util.properties.Sync;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.Items;

import java.util.Arrays;
import java.util.UUID;

public class RaisedScreen extends Screen {

    public StringWidget title;
    public StringWidget page;
    public IconToggleButton hotbar;
    public IconToggleButton chat;
    public IconToggleButton bossbar;
    public IconToggleButton sidebar;
    public IconToggleButton effects;
    public IconToggleButton players;
    public IconToggleButton toasts;
    public IconToggleButton other;
    public AbstractWidget x;
    public AbstractWidget y;
    public AbstractWidget position;
    public AbstractWidget sync;
    public double time = 0.0F;
    public double duration = 25.0F;
    public double distance = 0.0F;
    public static Element element = Element.HOTBAR;

    public RaisedScreen(Component title) {
        super(title);
    }

    @Override
    public void init() {
        title = new StringWidget(Component.translatable("options.raised.title"), font);
        page = new StringWidget(Component.translatable(element.getKey()), font);
        
        title.setWidth(width);
        title.setPosition(0, 16 + 6);
        page.setWidth(width);
        page.setPosition(0, 16 + 6 + 20 + 5);

        addRenderableWidget(title);
        addRenderableWidget(page);

        createLayersGrid();
        createPropertiesGrid();
    }

    public void createLayersGrid() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.defaultCellSetting().padding(0, 0, 10, 5);
        GridLayout.RowHelper rowHelper = gridLayout.createRowHelper(4);

        hotbar = createIconToggleButton(Element.HOTBAR);
        chat = createIconToggleButton(Element.CHAT);
        bossbar = createIconToggleButton(Element.BOSSBAR);
        sidebar = createIconToggleButton(Element.SIDEBAR);
        effects = createIconToggleButton(Element.EFFECTS);
        players = createIconToggleButton(Element.PLAYERS);
        toasts = createIconToggleButton(Element.TOASTS);
        other = createIconToggleButton(Element.OTHER);

        setIconToggleButton(hotbar);
        setIconToggleButton(chat);
        setIconToggleButton(bossbar);
        setIconToggleButton(sidebar);
        setIconToggleButton(effects);
        setIconToggleButton(players);
        setIconToggleButton(toasts);
        setIconToggleButton(other);

        rowHelper.addChild(hotbar);
        rowHelper.addChild(chat);
        rowHelper.addChild(bossbar);
        rowHelper.addChild(sidebar);
        rowHelper.addChild(effects);
        rowHelper.addChild(players);
        rowHelper.addChild(toasts);
        rowHelper.addChild(other);

        gridLayout.arrangeElements();
        FrameLayout.alignInRectangle(gridLayout, 16, 16, width, height, 0.0F, 0.0F);
        gridLayout.visitWidgets(this::addRenderableWidget);
    }

    public void createPropertiesGrid() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.defaultCellSetting().padding(10, 0, 0, 5);
        GridLayout.RowHelper rowHelper = gridLayout.createRowHelper(4);

        x = new OptionInstance<>("options.raised.x", OptionInstance.cachedConstantTooltip(Component.translatable("options.raised.x.tooltip")), (prefix, value) -> value == 0 ? Options.genericValueLabel(prefix, CommonComponents.OPTION_OFF) : Options.genericValueLabel(prefix, Component.literal(Option.getX(element) + "px (" + Math.round(Math.ceil((value.floatValue() / ((float) minecraft.getWindow().getGuiScaledWidth() / 4)) * 100)) + "%)")), new OptionInstance.IntRange(0, minecraft.getWindow().getGuiScaledWidth() / 4), Option.getX(element), value -> Option.setX(element, value)).createButton(minecraft.options, 0, 0, 110);
        y = new OptionInstance<>("options.raised.y", OptionInstance.cachedConstantTooltip(Component.translatable("options.raised.y.tooltip")), (prefix, value) -> value == 0 ? Options.genericValueLabel(prefix, CommonComponents.OPTION_OFF) : Options.genericValueLabel(prefix, Component.literal(Option.getY(element) + "px (" + Math.round(Math.ceil((value.floatValue() / ((float) minecraft.getWindow().getGuiScaledHeight() / 4)) * 100)) + "%)")), new OptionInstance.IntRange(0, minecraft.getWindow().getGuiScaledHeight() / 4), Option.getY(element), value -> Option.setY(element, value)).createButton(minecraft.options, 0, 0, 110);
        position = new OptionInstance<>("options.raised.position", value -> Tooltip.create(Component.translatable("options.raised.position.tooltip")), OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(Arrays.asList(Position.values()), Codec.INT.xmap(Position::byId, Position::getId)), Position.byName(Option.getPosition(element).getSerializedName()), value -> Option.setPosition(element, value)).createButton(minecraft.options, 0, 0, 110);
        sync = new OptionInstance<>("options.raised.sync", value -> Tooltip.create(Component.translatable("options.raised.sync." + value.getSerializedName() + ".tooltip", Component.translatable(element.getKey()))), OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(Arrays.asList(Sync.values()), Codec.INT.xmap(Sync::byId, Sync::getId)), Sync.byName(Option.getSync(element).getSerializedName()), value -> Option.setSync(element, value)).createButton(minecraft.options, 0, 0, 110);

        rowHelper.addChild(x, 4);
        rowHelper.addChild(y, 4);
        rowHelper.addChild(position, 4);
        rowHelper.addChild(sync, 4);

        gridLayout.arrangeElements();
        FrameLayout.alignInRectangle(gridLayout, -16, 16, width, height, 1.0F, 0.0F);
        gridLayout.visitWidgets(this::addRenderableWidget);
    }

    public IconToggleButton createIconToggleButton(Element element) {
        return IconToggleButton.builder(Component.translatable(element.getKey()), button -> {
            RaisedScreen.element = element;
            minecraft.setScreen(new RaisedScreen(Component.translatable("options.raised.title")));
        }, element == RaisedScreen.element).size(20, 20).texture(ResourceLocation.tryParse("raised:textures/gui/icon/" + element.getSerializedName() + ".png"), 20, 20).tooltip(Tooltip.create(Component.translatable(element.getKey()))).build();
    }

    public void setIconToggleButton(IconToggleButton widget) {
        widget.active = !widget.toggled;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        x.active = Option.getSync(element) == Sync.NONE;
        y.active = Option.getSync(element) == Sync.NONE;

        if (Option.getX(element) > minecraft.getWindow().getGuiScaledWidth() / 4) {
            Option.setX(element, minecraft.getWindow().getGuiScaledWidth() / 4);
            minecraft.setScreen(new RaisedScreen(Component.translatable("options.raised.title")));
        }
        if (Option.getY(element) > minecraft.getWindow().getGuiScaledHeight() / 4) {
            Option.setY(element, minecraft.getWindow().getGuiScaledHeight() / 4);
            minecraft.setScreen(new RaisedScreen(Component.translatable("options.raised.title")));
        }

        if (time < duration) {
            time += partialTick;
        }

        double animation = Math.min(time / duration, duration);

        if (animation < 1 / 2.75D) {
            distance = 7.5625D * animation * animation;
        } else if (animation < 2 / 2.75D) {
            distance = 7.5625D * (animation -= 1.5D / 2.75D) * animation + 0.75D;
        } else if (animation < 2.5D / 2.75D) {
            distance = 7.5625D * (animation -= 2.25D / 2.75D) * animation + 0.9375D;
        } else {
            distance = 7.5625D * (animation -= 2.625D / 2.75D) * animation + 0.984375D;
        }

        int x = Option.getX(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element);
        int y = Option.getY(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element);

        float percentX = (float) Math.round(Math.ceil(((float) x / ((float) minecraft.getWindow().getGuiScaledWidth() / 4)) * 100)) / 100;
        float percentY = (float) Math.round(Math.ceil(((float) y / ((float) minecraft.getWindow().getGuiScaledHeight() / 4)) * 100)) / 100;

        int offset = (int) ((float) guiGraphics.guiHeight() / 2) - y;

        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        int widthX = font.width(stringX);
        int widthY = font.width(stringY);
        Component translatableX = Component.translatable("options.raised.x");
        Component translatableY = Component.translatable("options.raised.y");

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(2, 2, 1);
        guiGraphics.pose().translate((distance * 91), 0, 300);
        RenderSystem.enableBlend();
        switch (element) {
            case HOTBAR -> {
                guiGraphics.blit(ResourceLocation.tryParse("textures/gui/widgets.png"), -182, offset - 22, 0, 0, 182, 22);
                guiGraphics.blit(ResourceLocation.tryParse("textures/gui/widgets.png"), -182 - 1 + Mth.lerpInt(percentX, 5, 8) * 20, offset - 23, 0, 22, 24, 24);

                guiGraphics.renderItem(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance(), -182 + 123, offset - 19);
                guiGraphics.renderItem(Items.GLISTERING_MELON_SLICE.getDefaultInstance(), -182 + 143, offset - 19);

                guiGraphics.renderItemDecorations(font, Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance(), -182 + 123, offset - 19, stringX);
                guiGraphics.renderItemDecorations(font, Items.GLISTERING_MELON_SLICE.getDefaultInstance(), -182 + 143, offset - 19, stringY);
            }
            case CHAT -> {
                int backgroundOpacity = (int) (255.0 * minecraft.options.textBackgroundOpacity().get());
                int textOpacity = (int) (255.0F * minecraft.options.chatOpacity().get() * 0.8999999761581421 + 0.10000000149011612);

                guiGraphics.fill(-91, offset - 9 - 9, -91 + 120 + 4 + 4, offset, backgroundOpacity << 24);

                guiGraphics.drawString(font, "<" + translatableX.getString() + "> " + x, -91 + 4, offset - 8 - 9, 16777215 + (textOpacity << 24));
                guiGraphics.drawString(font, "<" + translatableY.getString() + "> " + y, -91 + 4, offset - 8, 16777215 + (textOpacity << 24));
            }
            case BOSSBAR -> {
                int width = Math.max(font.width(translatableX), font.width(translatableY));

                guiGraphics.blit(ResourceLocation.tryParse("textures/gui/bars.png"), -182, offset - 5 - 19, 0, 20, 182, 5);
                if (x > 0) {
                    int progress = Mth.lerpInt(percentX, 91, 182);
                    guiGraphics.blit(ResourceLocation.tryParse("textures/gui/bars.png"), -182, offset - 5 - 19, progress, 5, 0, 25, progress, 5, 256, 256);
                }
                guiGraphics.blit(ResourceLocation.tryParse("textures/gui/bars.png"), -182, offset - 5, 0, 60, 182, 5);
                if (y > 0) {
                    int progress = Mth.lerpInt(percentY, 91, 182);
                    guiGraphics.blit(ResourceLocation.tryParse("textures/gui/bars.png"), -182, offset - 5, progress, 5, 0, 65, progress, 5, 256, 256);
                }

                guiGraphics.drawString(font, translatableX, -91 + 8 + (width / 2) - (font.width(translatableX) / 2), offset - 5 - 9 - 19, 16777215);
                guiGraphics.drawString(font, translatableY, -91 + 8 + (width / 2) - (font.width(translatableY) / 2), offset - 5 - 9, 16777215);
            }
            case SIDEBAR -> {
                Component title = Component.translatable("options.raised.element.sidebar");
                int width = Math.max(2 + Math.max(font.width(translatableX), font.width(translatableY)) + 9 + Math.max(widthX, widthY), 2 + font.width(title));

                guiGraphics.fill(-91 + 1, offset - 1 - 9 - 10 - 10, -91 + 1 + width, offset - 1 - 10 - 10, minecraft.options.getBackgroundColor(0.4F));
                guiGraphics.fill(-91 + 1, offset - 1 - 10 - 10, -91 + 1 + width, offset - 1, minecraft.options.getBackgroundColor(0.3F));

                guiGraphics.drawString(font, title, -91 + 1 + (width / 2) - (font.width(title) / 2), offset - 1 - 8 - 10 - 10, CommonColors.WHITE, false);

                guiGraphics.drawString(font, translatableX, -91 + 1 + 2, offset - 1 - 9 - 10, CommonColors.WHITE, false);
                guiGraphics.drawString(font, translatableY, -91 + 1 + 2, offset - 1 - 9, CommonColors.WHITE, false);

                guiGraphics.drawString(font, String.valueOf(x), -91 + 1 + width - widthX, offset - 1 - 9 - 10, -2142128, false);
                guiGraphics.drawString(font, String.valueOf(y), -91 + 1 + width - widthY, offset - 1 - 9, -2142128, false);
            }
            case EFFECTS -> {
                guiGraphics.blit(ResourceLocation.tryParse("textures/gui/container/inventory.png"), -91 + 1, offset - 24 - 1, 141, 166, 24, 24);
                guiGraphics.blit(ResourceLocation.tryParse("textures/gui/container/inventory.png"), -91 + 1 + 24 + 1, offset - 24 - 1, 141, 166, 24, 24);

                guiGraphics.setColor(1.0F, 1.0F, 1.0F, percentX);
                guiGraphics.blit(-91 + 1 + 3, offset - 24 - 1 + 3, 0, 18, 18, minecraft.getMobEffectTextures().get(MobEffects.LUCK));
                guiGraphics.setColor(1.0F, 1.0F, 1.0F, percentY);
                guiGraphics.blit(-91 + 1 + 3 + 24 + 1, offset - 24 - 1 + 3, 0, 18, 18, minecraft.getMobEffectTextures().get(MobEffects.UNLUCK));

                guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
            case PLAYERS -> {
                GameProfile profilePlayer = minecraft.getMinecraftSessionService().fillProfileProperties(minecraft.player.getGameProfile(), false);
                GameProfile profileNotch = minecraft.getMinecraftSessionService().fillProfileProperties(new GameProfile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"), "Notch"), false);

                guiGraphics.fill(-91 + 1, offset - 1 - 10 - 9, -91 + 1 + 144, offset - 1, Integer.MIN_VALUE);
                guiGraphics.fill(-91 + 1 + 1, offset - 1 - 9 - 9, -91 + 1 + 144, offset - 1 - 1 - 9, minecraft.options.getBackgroundColor(553648127));
                guiGraphics.fill(-91 + 1 + 1, offset - 1 - 9, -91 + 1 + 144, offset - 1 - 1, minecraft.options.getBackgroundColor(553648127));

                PlayerFaceRenderer.draw(guiGraphics, minecraft.getSkinManager().getInsecureSkinLocation(profilePlayer), -91 + 1 + 1, offset - 1 - 9 - 9, 8, minecraft.player.isModelPartShown(PlayerModelPart.HAT), "Dinnerbone".equals(profilePlayer.getName()) || "Grumm".equals(profilePlayer.getName()));
                PlayerFaceRenderer.draw(guiGraphics, minecraft.getSkinManager().getInsecureSkinLocation(profileNotch), -91 + 1 + 1, offset - 1 - 9, 8, true, false);

                guiGraphics.drawString(font, translatableX, -91 + 1 + 10, offset - 1 - 9 - 9, CommonColors.WHITE);
                guiGraphics.drawString(font, translatableY, -91 + 1 + 10, offset - 1 - 9, CommonColors.WHITE);

                guiGraphics.drawString(font, stringX, -91 + 1 + 144 - 1 - 10 - 1 - widthX, offset - 1 - 9 - 9, -171);
                guiGraphics.drawString(font, stringY, -91 + 1 + 144 - 1 - 10 - 1 - widthY, offset - 1 - 9, -171);

                guiGraphics.blit(ResourceLocation.tryParse("textures/gui/icons.png"), -91 + 1 + 144 - 1 - 10, offset - 1 - 9 - 9, 0, 176 + getSignal(percentX) * 8, 10, 8);
                guiGraphics.blit(ResourceLocation.tryParse("textures/gui/icons.png"), -91 + 1 + 144 - 1 - 10, offset - 1 - 9, 0, 176 + getSignal(percentY) * 8, 10, 8);
            }
            case TOASTS -> {
                guiGraphics.blit(ResourceLocation.tryParse("textures/gui/toasts.png"), -91 - 40, offset - 32, 0, 0, 160, 32);

                guiGraphics.renderFakeItem(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance(), -91 + 8, offset - 32 + 8);

                guiGraphics.drawString(font, translatableX.getString() + ": " + x, -91 + 30, offset - 32 + 7, 16776960 | CommonColors.BLACK, false);
                guiGraphics.drawString(font, translatableY.getString() + ": " + y, -91 + 30, offset - 32 + 18, CommonColors.WHITE, false);
            }
            case OTHER -> {
                guiGraphics.drawString(font, translatableX.getString() + ": " + x, -91 + 8, offset - 8 - 8 - 4 - 8, CommonColors.WHITE);
                guiGraphics.drawString(font, translatableY.getString() + ": " + y, -91 + 8, offset - 8 - 8, CommonColors.WHITE);
            }
        }
        RenderSystem.disableBlend();
        guiGraphics.pose().popPose();
    }

    public static int getSignal(float percent) {
        int i;
        if (percent > 0.8F) {
            i = 0;
        } else if (percent > 0.6F) {
            i = 1;
        } else if (percent > 0.4F) {
            i = 2;
        } else if (percent > 0.2F) {
            i = 3;
        } else if (percent > 0.0F) {
            i = 4;
        } else {
            i = 5;
        }
        return i;
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics) {
        guiGraphics.fillGradient(0, 0, width, height, -1072689136, -804253680);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        if (RaisedOptions.options.matches(keyCode, scanCode)) {
            onClose();
            return true;
        }
        return true;
    }

}