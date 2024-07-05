package dev.yurisuika.raised.client.gui.screens;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.components.IconToggleButton;
import dev.yurisuika.raised.mixin.client.OptionInvoker;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Position;
import dev.yurisuika.raised.util.type.Sync;
import net.minecraft.client.CycleOption;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.TooltipAccessor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.UUID;

public class RaisedScreen extends Screen {

    public ArrayList<IconToggleButton> elementsGrid = new ArrayList<>();
    public ArrayList<AbstractWidget> propertiesGrid = new ArrayList<>();
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
        createElementsGrid();
        createPropertiesGrid();

        for (IconToggleButton widget : elementsGrid) {
            addButton(widget);
        }
        for (AbstractWidget widget : propertiesGrid) {
            addButton(widget);
        }
    }

    public void createElementsGrid() {
        hotbar = createIconToggleButton(Element.HOTBAR, 16, 16);
        chat = createIconToggleButton(Element.CHAT, 16 + 20 + 10, 16);
        bossbar = createIconToggleButton(Element.BOSSBAR, 16 + 20 + 10 + 20 + 10, 16);
        sidebar = createIconToggleButton(Element.SIDEBAR, 16 + 20 + 10 + 20 + 10 + 20 + 10, 16);
        effects = createIconToggleButton(Element.EFFECTS, 16, 16 + 20 + 5);
        players = createIconToggleButton(Element.PLAYERS, 16 + 20 + 10, 16 + 20 + 5);
        toasts = createIconToggleButton(Element.TOASTS, 16 + 20 + 10 + 20 + 10, 16 + 20 + 5);
        other = createIconToggleButton(Element.OTHER, 16 + 20 + 10 + 20 + 10 + 20 + 10, 16 + 20 + 5);

        setIconToggleButton(hotbar);
        setIconToggleButton(chat);
        setIconToggleButton(bossbar);
        setIconToggleButton(sidebar);
        setIconToggleButton(effects);
        setIconToggleButton(players);
        setIconToggleButton(toasts);
        setIconToggleButton(other);

        elementsGrid.add(hotbar);
        elementsGrid.add(chat);
        elementsGrid.add(bossbar);
        elementsGrid.add(sidebar);
        elementsGrid.add(effects);
        elementsGrid.add(players);
        elementsGrid.add(toasts);
        elementsGrid.add(other);
    }

    public void createPropertiesGrid() {
        x = new ProgressOption("options.raised.x", 0, minecraft.getWindow().getGuiScaledWidth() / 4, 1.0F, gameOptions -> (double)Option.getX(element), (gameOptions, value) -> Option.setX(element, value.intValue()), (gameOptions, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.x.tooltip"), 200));
            return option.get(gameOptions) == 0 ? ((OptionInvoker)option).invokeGenericValueLabel(CommonComponents.OPTION_OFF) : ((OptionInvoker)option).invokeGenericValueLabel(new TextComponent(Math.round(Math.ceil(((float)option.get(gameOptions) / ((float)option.getMaxValue())) * 100)) + "%"));
        }).createButton(minecraft.options, width - 110 - 16, 16, 110);
        y = new ProgressOption("options.raised.y", 0, minecraft.getWindow().getGuiScaledHeight() / 4, 1.0F, gameOptions -> (double)Option.getY(element), (gameOptions, value) -> Option.setY(element, value.intValue()), (gameOptions, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.y.tooltip"), 200));
            return option.get(gameOptions) == 0 ? ((OptionInvoker)option).invokeGenericValueLabel(CommonComponents.OPTION_OFF) : ((OptionInvoker)option).invokeGenericValueLabel(new TextComponent(Math.round(Math.ceil(((float)option.get(gameOptions) / ((float)option.getMaxValue())) * 100)) + "%"));
        }).createButton(minecraft.options, width - 110 - 16, 16 + 20 + 5, 110);
        position = new CycleOption("options.raised.position", (gameOptions, integer) -> {
            int id = Option.getPosition(element).getId();
            Option.setPosition(element, Position.byId(id < Position.values().length - 1 ? id + 1 : 0));
        }, (gameOptions, CycleOption) -> {
            CycleOption.setTooltip(font.split(new TranslatableComponent("options.raised.position.tooltip"), 200));
            return ((OptionInvoker)CycleOption).invokeGenericValueLabel(new TranslatableComponent(Option.getPosition(element).getSerializedName()));
        }).createButton(minecraft.options, width - 110 - 16, 16 + 20 + 5 + 20 + 5, 110);
        sync = new CycleOption("options.raised.sync", (gameOptions, integer) -> {
            int id = Option.getSync(element).getId();
            Option.setSync(element, Sync.byId(id < Sync.values().length - 1 ? id + 1 : 0));
        }, (gameOptions, CycleOption) -> {
            CycleOption.setTooltip(font.split(new TranslatableComponent("options.raised.sync." + Option.getSync(element).getSerializedName() + ".tooltip", new TranslatableComponent(element.getKey())), 200));
            return ((OptionInvoker)CycleOption).invokeGenericValueLabel(new TranslatableComponent(Option.getSync(element).getSerializedName()));
        }).createButton(minecraft.options, width - 110 - 16, 16 + 20 + 5 + 20 + 5 + 20 + 5, 110);

        propertiesGrid.add(x);
        propertiesGrid.add(y);
        propertiesGrid.add(position);
        propertiesGrid.add(sync);
    }

    public IconToggleButton createIconToggleButton(Element element, int x, int y) {
        return new IconToggleButton(x, y, 20, 20, new TranslatableComponent(element.getKey()), 20, 20, new ResourceLocation("raised:textures/gui/icon/" + element.getSerializedName() + ".png"), button -> {
            RaisedScreen.element = element;
            minecraft.setScreen(new RaisedScreen(new TranslatableComponent("options.raised.title")));
        }, element == RaisedScreen.element);
    }

    public void setIconToggleButton(IconToggleButton widget) {
        widget.active = !widget.toggled;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        super.render(poseStack, mouseX, mouseY, partialTick);

        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);

        drawCenteredString(poseStack, font, new TranslatableComponent("options.raised.title"), width / 2, 16 + 6, 16777215);
        drawCenteredString(poseStack, font, new TranslatableComponent(element.getKey()), width / 2, 16 + 6 + 20 + 5, 16777215);

        x.active = Option.getSync(element) == Sync.NONE;
        y.active = Option.getSync(element) == Sync.NONE;

        if (Option.getX(element) > minecraft.getWindow().getGuiScaledWidth() / 4) {
            Option.setX(element, minecraft.getWindow().getGuiScaledWidth() / 4);
            minecraft.setScreen(new RaisedScreen(new TranslatableComponent("options.raised.title")));
        }
        if (Option.getY(element) > minecraft.getWindow().getGuiScaledHeight() / 4) {
            Option.setY(element, minecraft.getWindow().getGuiScaledHeight() / 4);
            minecraft.setScreen(new RaisedScreen(new TranslatableComponent("options.raised.title")));
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

        float percentX = (float)Math.round(Math.ceil(((float)x / ((float)minecraft.getWindow().getGuiScaledWidth() / 4)) * 100)) / 100;
        float percentY = (float)Math.round(Math.ceil(((float)y / ((float)minecraft.getWindow().getGuiScaledHeight() / 4)) * 100)) / 100;

        int offset = (int)((float)minecraft.getWindow().getGuiScaledHeight() / 2) - y;

        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        int widthX = font.width(stringX);
        int widthY = font.width(stringY);
        Component translatableX = new TranslatableComponent("options.raised.x");
        Component translatableY = new TranslatableComponent("options.raised.y");

        RenderSystem.pushMatrix();
        RenderSystem.scaled(2, 2, 1);
        RenderSystem.translated((distance * 91), 0, 300);
        RenderSystem.enableBlend();
        switch (element) {
            case HOTBAR: {
                int slot = (int)Mth.lerp(percentX, 5, 8) * 20;

                minecraft.getTextureManager().bind(new ResourceLocation("textures/gui/widgets.png"));
                blit(poseStack, -182, offset - 22, 0, 0, 182, 22);
                blit(poseStack, -182 - 1 + slot, offset - 23, 0, 22, 24, 24);

                itemRenderer.renderAndDecorateItem(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance(), -182 + 123, offset - 19);
                itemRenderer.renderAndDecorateItem(Items.GLISTERING_MELON_SLICE.getDefaultInstance(), -182 + 143, offset - 19);

                itemRenderer.renderGuiItemDecorations(font, Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance(), -182 + 123, offset - 19, stringX);
                itemRenderer.renderGuiItemDecorations(font, Items.GLISTERING_MELON_SLICE.getDefaultInstance(), -182 + 143, offset - 19, stringY);
                break;
            }
            case CHAT: {
                int backgroundOpacity = (int)(255.0 * minecraft.options.textBackgroundOpacity);
                int textOpacity = (int)(255.0F * minecraft.options.chatOpacity * 0.8999999761581421 + 0.10000000149011612);

                fill(poseStack, -91, offset - 9 - 9, -91 + 120 + 4 + 4, offset, backgroundOpacity << 24);

                font.drawShadow(poseStack, "<" + translatableX.getString() + "> " + x, -91 + 4, offset - 8 - 9, 16777215 + (textOpacity << 24));
                font.drawShadow(poseStack, "<" + translatableY.getString() + "> " + y, -91 + 4, offset - 8, 16777215 + (textOpacity << 24));
                break;
            }
            case BOSSBAR: {
                int width = Math.max(font.width(translatableX), font.width(translatableY));

                minecraft.getTextureManager().bind(new ResourceLocation("textures/gui/bars.png"));
                blit(poseStack,-182, offset - 5 - 19, 0, 20, 182, 5);
                if (x > 0) {
                    int progress = (int)Mth.lerp(percentX, 91, 182);
                    blit(poseStack, -182, offset - 5 - 19, progress, 5, 0, 25, progress, 5, 256, 256);
                }
                blit(poseStack,-182, offset - 5, 0, 60, 182, 5);
                if (y > 0) {
                    int progress = (int)Mth.lerp(percentY, 91, 182);
                    blit(poseStack, -182, offset - 5, progress, 5, 0, 65, progress, 5, 256, 256);
                }

                font.drawShadow(poseStack, translatableX, -91 + 8 + (width / 2) - (font.width(translatableX) / 2), offset - 5 - 9 - 19, 16777215);
                font.drawShadow(poseStack, translatableY, -91 + 8 + (width / 2) - (font.width(translatableY) / 2), offset - 5 - 9, 16777215);
                break;
            }
            case SIDEBAR: {
                Component title = new TranslatableComponent("options.raised.element.sidebar");
                int width = Math.max(2 + Math.max(font.width(translatableX), font.width(translatableY)) + 9 + Math.max(widthX, widthY), 2 + font.width(title));

                fill(poseStack, -91 + 1, offset - 1 - 9 - 10 - 10, -91 + 1 + width, offset - 1 - 10 - 10, minecraft.options.getBackgroundColor(0.4F));
                fill(poseStack, -91 + 1, offset - 1 - 10 - 10, -91 + 1 + width, offset - 1, minecraft.options.getBackgroundColor(0.3F));

                font.draw(poseStack, title, -91 + 1 + (width / 2) - (font.width(title) / 2), offset - 1 - 8 - 10 - 10, -1);

                font.draw(poseStack, translatableX, -91 + 1 + 2, offset - 1 - 9 - 10, -1);
                font.draw(poseStack, translatableY, -91 + 1 + 2, offset - 1 - 9, -1);

                font.draw(poseStack, String.valueOf(x), -91 + 1 + width - widthX, offset - 1 - 9 - 10, -2142128);
                font.draw(poseStack, String.valueOf(y), -91 + 1 + width - widthY, offset - 1 - 9, -2142128);
                break;
            }
            case EFFECTS: {
                minecraft.getTextureManager().bind(new ResourceLocation("textures/gui/container/inventory.png"));
                blit(poseStack, -91 + 1, offset - 24 - 1, 141, 166, 24, 24);
                blit(poseStack, -91 + 1 + 24 + 1, offset - 24 - 1, 141, 166, 24, 24);

                RenderSystem.color4f(1.0F, 1.0F, 1.0F, percentX);
                minecraft.getTextureManager().bind(new ResourceLocation("textures/mob_effect/luck.png"));
                blit(poseStack,-91 + 1 + 3, offset - 24 - 1 + 3, 0, 0, 18, 18, 18, 18);
                RenderSystem.color4f(1.0F, 1.0F, 1.0F, percentY);
                minecraft.getTextureManager().bind(new ResourceLocation("textures/mob_effect/unluck.png"));
                blit(poseStack,-91 + 1 + 3 + 24 + 1, offset - 24 - 1 + 3, 0, 0, 18, 18, 18, 18);

                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                break;
            }
            case PLAYERS: {
                GameProfile profilePlayer = minecraft.getMinecraftSessionService().fillProfileProperties(minecraft.player.getGameProfile(), false);
                GameProfile profileNotch = minecraft.getMinecraftSessionService().fillProfileProperties(new GameProfile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"), "Notch"), false);
                Player entityPlayer = minecraft.level.getPlayerByUUID(profilePlayer.getId());
                Player entityNotch = minecraft.level.getPlayerByUUID(profileNotch.getId());
                boolean shouldFlip = entityPlayer != null  && ("Dinnerbone".equals(profilePlayer.getName()) || "Grumm".equals(profilePlayer.getName()));

                fill(poseStack, -91 + 1, offset - 1 - 10 - 9, -91 + 1 + 144, offset - 1, Integer.MIN_VALUE);
                fill(poseStack, -91 + 1 + 1, offset - 1 - 9 - 9, -91 + 1 + 144, offset - 1 - 1 - 9, minecraft.options.getBackgroundColor(553648127));
                fill(poseStack, -91 + 1 + 1, offset - 1 - 9, -91 + 1 + 144, offset - 1 - 1, minecraft.options.getBackgroundColor(553648127));

                minecraft.getTextureManager().bind(minecraft.getSkinManager().registerTexture(minecraft.getSkinManager().getInsecureSkinInformation(profilePlayer).values().stream().findFirst().get(), MinecraftProfileTexture.Type.SKIN));
                GuiComponent.blit(poseStack, -91 + 1 + 1, offset - 1 - 9 - 9, 8, 8, 8.0F, 8 + (shouldFlip ? 8 : 0), 8, 8 * (shouldFlip ? -1 : 1), 64, 64);
                if (entityPlayer != null && entityPlayer.isModelPartShown(PlayerModelPart.HAT)) {
                    GuiComponent.blit(poseStack, -91 + 1 + 1, offset - 1 - 9 - 9, 8, 8, 40.0F, 8 + (shouldFlip ? 8 : 0), 8, 8 * (shouldFlip ? -1 : 1), 64, 64);
                }
                minecraft.getTextureManager().bind(minecraft.getSkinManager().registerTexture(minecraft.getSkinManager().getInsecureSkinInformation(profileNotch).values().stream().findFirst().get(), MinecraftProfileTexture.Type.SKIN));
                GuiComponent.blit(poseStack, -91 + 1 + 1, offset - 1 - 9, 8, 8, 8.0F, 8.0F, 8, 8, 64, 64);
                if (entityNotch != null && entityNotch.isModelPartShown(PlayerModelPart.HAT)) {
                    GuiComponent.blit(poseStack, -91 + 1 + 1, offset - 1 - 9, 8, 8, 40.0F, 8.0F, 8, 8, 64, 64);
                }

                font.drawShadow(poseStack, translatableX, -91 + 1 + 10, offset - 1 - 9 - 9, -1);
                font.drawShadow(poseStack, translatableY, -91 + 1 + 10, offset - 1 - 9, -1);

                font.drawShadow(poseStack, stringX, -91 + 1 + 144 - 1 - 10 - 1 - widthX, offset - 1 - 9 - 9, -171);
                font.drawShadow(poseStack, stringY, -91 + 1 + 144 - 1 - 10 - 1 - widthY, offset - 1 - 9, -171);

                minecraft.getTextureManager().bind(new ResourceLocation("textures/gui/icons.png"));
                blit(poseStack, -91 + 1 + 144 - 1 - 10, offset - 1 - 9 - 9, 0, 176 + getSignal(percentX) * 8, 10, 8);
                blit(poseStack, -91 + 1 + 144 - 1 - 10, offset - 1 - 9, 0, 176 + getSignal(percentY) * 8, 10, 8);
                break;
            }
            case TOASTS: {
                minecraft.getTextureManager().bind(new ResourceLocation("textures/gui/toasts.png"));
                blit(poseStack, -91 - 40, offset - 32, 0, 0, 160, 32);

                itemRenderer.renderAndDecorateFakeItem(Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance(), -91 + 8, offset - 32 + 8);

                font.draw(poseStack, translatableX.getString() + ": " + x, -91 + 30, offset - 32 + 7, 16776960 | -16777216);
                font.draw(poseStack, translatableY.getString() + ": " + y, -91 + 30, offset - 32 + 18, -1);
                break;
            }
            case OTHER: {
                font.drawShadow(poseStack, translatableX.getString() + ": " + x, -91 + 8, offset - 8 - 8 - 4 - 8, -1);
                font.drawShadow(poseStack, translatableY.getString() + ": " + y, -91 + 8, offset - 8 - 8, -1);
                break;
            }
        }
        RenderSystem.disableBlend();
        RenderSystem.popMatrix();

        for (IconToggleButton widget : elementsGrid) {
            if (widget != null && widget.isMouseOver(mouseX, mouseY)) {
                Element widgetElement = element;
                if (widget.equals(hotbar)) {
                    widgetElement = Element.HOTBAR;
                } else if (widget.equals(chat)) {
                    widgetElement = Element.CHAT;
                } else if (widget.equals(bossbar)) {
                    widgetElement = Element.BOSSBAR;
                } else if (widget.equals(sidebar)) {
                    widgetElement = Element.SIDEBAR;
                } else if (widget.equals(effects)) {
                    widgetElement = Element.EFFECTS;
                } else if (widget.equals(players)) {
                    widgetElement = Element.PLAYERS;
                } else if (widget.equals(toasts)) {
                    widgetElement = Element.TOASTS;
                } else if (widget.equals(other)) {
                    widgetElement = Element.OTHER;
                }
                renderTooltip(poseStack, font.split(new TranslatableComponent(widgetElement.getSerializedName()), 200), mouseX, mouseY);
            }
        }
        for (AbstractWidget widget : propertiesGrid) {
            if (widget != null && widget.isMouseOver(mouseX, mouseY)) {
                renderTooltip(poseStack, ((TooltipAccessor)widget).getTooltip().orElse(null), mouseX, mouseY);
            }
        }
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
    public void renderBackground(PoseStack poseStack) {
        fillGradient(poseStack, 0, 0, width, height, -1072689136, -804253680);
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