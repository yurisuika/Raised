package dev.yurisuika.raised.client.gui.screen;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.serialization.Codec;
import dev.yurisuika.raised.client.gui.widget.IconToggleButtonWidget;
import dev.yurisuika.raised.client.option.RaisedKeyBindings;
import dev.yurisuika.raised.mixin.client.gui.DrawContextInvoker;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.config.*;
import dev.yurisuika.raised.util.type.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.PlayerSkinDrawer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.UUID;

@Environment(EnvType.CLIENT)
public class RaisedScreen extends Screen {

    public TextWidget title;
    public TextWidget page;
    public IconToggleButtonWidget hotbar;
    public IconToggleButtonWidget chat;
    public IconToggleButtonWidget bossbar;
    public IconToggleButtonWidget sidebar;
    public IconToggleButtonWidget effects;
    public IconToggleButtonWidget players;
    public IconToggleButtonWidget toasts;
    public IconToggleButtonWidget other;
    public ClickableWidget x;
    public ClickableWidget y;
    public ClickableWidget position;
    public ClickableWidget sync;
    public ClickableWidget texture;
    public double time = 0.0F;
    public double duration = 25.0F;
    public double distance = 0.0F;
    public static Element element = Element.HOTBAR;

    public RaisedScreen(Text title) {
        super(title);
    }

    @Override
    public void init() {
        title = new TextWidget(Text.translatable("options.raised.title"), textRenderer);
        page = new TextWidget(Text.translatable(element.getTranslationKey()), textRenderer);

        title.setDimensionsAndPosition(width, 20, 0, 16 + 1);
        page.setDimensionsAndPosition(width, 20, 0, 16 + 1 + 20 + 5);

        addDrawableChild(title);
        addDrawableChild(page);

        createElementsGrid();
        createPropertiesGrid();
        createToggleGrid();
    }

    public void createElementsGrid() {
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().margin(0, 0, 10, 5);
        GridWidget.Adder adder = gridWidget.createAdder(4);

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

        adder.add(hotbar);
        adder.add(chat);
        adder.add(bossbar);
        adder.add(sidebar);
        adder.add(effects);
        adder.add(players);
        adder.add(toasts);
        adder.add(other);

        gridWidget.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget, 16, 16, width, height, 0.0F, 0.0F);
        gridWidget.forEachChild(this::addDrawableChild);
    }

    public void createPropertiesGrid() {
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().margin(10, 0, 0, 5);
        GridWidget.Adder adder = gridWidget.createAdder(4);

        x = new SimpleOption<>("options.raised.x", SimpleOption.constantTooltip(Text.translatable("options.raised.x.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(Math.round(Math.ceil((value.floatValue() / ((float)client.getWindow().getScaledWidth() / 4)) * 100)) + "%")), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledWidth() / 4), Option.getX(element), value -> Option.setX(element, value)).createWidget(client.options, 0, 0, 110);
        y = new SimpleOption<>("options.raised.y", SimpleOption.constantTooltip(Text.translatable("options.raised.y.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(Math.round(Math.ceil((value.floatValue() / ((float)client.getWindow().getScaledHeight() / 4)) * 100)) + "%")), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 4), Option.getY(element), value -> Option.setY(element, value)).createWidget(client.options, 0, 0, 110);
        position = new SimpleOption<>("options.raised.position", value -> Tooltip.of(Text.translatable("options.raised.position.tooltip")), SimpleOption.enumValueText(), new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(Position.values()), Codec.INT.xmap(Position::byId, Position::getId)), Position.byName(Option.getPosition(element).asString()), value -> Option.setPosition(element, value)).createWidget(client.options, 0, 0, 110);
        sync = new SimpleOption<>("options.raised.sync", value -> Tooltip.of(Text.translatable("options.raised.sync." + value.asString() + ".tooltip", Text.translatable(element.getTranslationKey()))), SimpleOption.enumValueText(), new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(Sync.values()), Codec.INT.xmap(Sync::byId, Sync::getId)), Sync.byName(Option.getSync(element).asString()), value -> Option.setSync(element, value)).createWidget(client.options, 0, 0, 110);

        adder.add(x, 4);
        adder.add(y, 4);
        adder.add(position, 4);
        adder.add(sync, 4);

        gridWidget.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget, -16, 16, width, height, 1.0F, 0.0F);
        gridWidget.forEachChild(this::addDrawableChild);
    }

    public void createToggleGrid() {
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().margin(10, 5, 0, 0);
        GridWidget.Adder adder = gridWidget.createAdder(4);

        texture = new SimpleOption<>("options.raised.texture", value -> Tooltip.of(Text.translatable(value.getTranslationKey() + ".tooltip")), SimpleOption.enumValueText(), new SimpleOption.PotentialValuesBasedCallbacks<>(Arrays.asList(Texture.values()), Codec.INT.xmap(Texture::byId, Texture::getId)), Texture.byName(Option.getTexture().asString()), Option::setTexture).createWidget(client.options, 0, 0, 110);

        adder.add(texture, 4);

        gridWidget.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget, -16, -16, width, height, 1.0F, 1.0F);
        gridWidget.forEachChild(this::addDrawableChild);
    }

    public IconToggleButtonWidget createIconToggleButton(Element element) {
        return IconToggleButtonWidget.builder(Text.translatable(element.getTranslationKey()), button -> {
            RaisedScreen.element = element;
            client.setScreen(new RaisedScreen(Text.translatable("options.raised.title")));
        }, element == RaisedScreen.element).size(20, 20).texture(Identifier.of("raised:icon/" + element.asString()), 20, 20).tooltip(Tooltip.of(Text.translatable(element.getTranslationKey()))).build();
    }

    public void setIconToggleButton(IconToggleButtonWidget widget) {
        widget.active = !widget.toggled;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        x.active = Option.getSync(element) == Sync.NONE;
        y.active = Option.getSync(element) == Sync.NONE;

        if (Option.getX(element) > client.getWindow().getScaledWidth() / 4) {
            Option.setX(element, client.getWindow().getScaledWidth() / 4);
            client.setScreen(new RaisedScreen(Text.translatable("options.raised.title")));
        }
        if (Option.getY(element) > client.getWindow().getScaledHeight() / 4) {
            Option.setY(element, client.getWindow().getScaledHeight() / 4);
            client.setScreen(new RaisedScreen(Text.translatable("options.raised.title")));
        }

        if (time < duration) {
            time += delta;
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

        float percentX = (float)Math.round(Math.ceil(((float)x / ((float)client.getWindow().getScaledWidth() / 4)) * 100)) / 100;
        float percentY = (float)Math.round(Math.ceil(((float)y / ((float)client.getWindow().getScaledHeight() / 4)) * 100)) / 100;

        int offset = (int)((float)context.getScaledWindowHeight() / 2) - y;

        String stringX = String.valueOf(x);
        String stringY = String.valueOf(y);
        int widthX = textRenderer.getWidth(stringX);
        int widthY = textRenderer.getWidth(stringY);
        Text translatableX = Text.translatable("options.raised.x");
        Text translatableY = Text.translatable("options.raised.y");

        context.getMatrices().push();
        context.getMatrices().scale(2, 2, 1);
        context.getMatrices().translate((distance * 91), 0, 300);
        RenderSystem.enableBlend();
        switch (element) {
            case HOTBAR -> {
                int slot = MathHelper.lerpPositive(percentX, 5, 8) * 20;
                Texture texture = Option.getTexture();

                context.drawGuiTexture(Identifier.of("hud/hotbar"), -182, offset - 22, 182, 22);
                if (texture == Texture.REPLACE || (texture == Texture.AUTO && Pack.getPack())) {
                    context.drawGuiTexture(Identifier.of("raised:hud/hotbar_selection"), -182 - 1 + slot, offset - 23, 24, 24);
                } else {
                    context.drawGuiTexture(Identifier.of("hud/hotbar_selection"), -182 - 1 + slot, offset - 23, 24, 23);
                    if (texture == Texture.PATCH || (texture == Texture.AUTO && !Pack.getPack())) {
                        ((DrawContextInvoker) context).invokeDrawTexturedQuad(Identifier.of("textures/gui/sprites/hud/hotbar_selection.png"), -182 - 1 + slot, -182 - 1 + slot + 24, offset, offset + 1, 0, 0, 1, 1 / 23.0F, 0);
                    }
                }

                context.drawItem(Items.ENCHANTED_GOLDEN_APPLE.getDefaultStack(), -182 + 123, offset - 19);
                context.drawItem(Items.GLISTERING_MELON_SLICE.getDefaultStack(), -182 + 143, offset - 19);

                context.drawItemInSlot(textRenderer, Items.ENCHANTED_GOLDEN_APPLE.getDefaultStack(), -182 + 123, offset - 19, stringX);
                context.drawItemInSlot(textRenderer, Items.GLISTERING_MELON_SLICE.getDefaultStack(), -182 + 143, offset - 19, stringY);
            }
            case CHAT -> {
                int backgroundOpacity = (int) (255.0 * client.options.getTextBackgroundOpacity().getValue());
                int textOpacity = (int) (255.0F * client.options.getChatOpacity().getValue() * 0.8999999761581421 + 0.10000000149011612);

                context.fill(-91, offset - 9 - 9, -91 + 120 + 4 + 4, offset, backgroundOpacity << 24);

                context.drawTextWithShadow(textRenderer, "<" + translatableX.getString() + "> " + x, -91 + 4, offset - 8 - 9, 16777215 + (textOpacity << 24));
                context.drawTextWithShadow(textRenderer, "<" + translatableY.getString() + "> " + y, -91 + 4, offset - 8, 16777215 + (textOpacity << 24));
            }
            case BOSSBAR -> {
                int width = Math.max(textRenderer.getWidth(translatableX), textRenderer.getWidth(translatableY));

                context.drawGuiTexture(Identifier.of("boss_bar/red_background"), -182, offset - 5 - 19, 182, 5);
                if (x > 0) {
                    int progress = MathHelper.lerpPositive(percentX, 91, 182);
                    context.drawTexture(Identifier.of("textures/gui/sprites/boss_bar/red_progress.png"), -182, offset - 5 - 19, progress, 5, 0, 0, progress, 5, 182, 5);
                }
                context.drawGuiTexture(Identifier.of("boss_bar/white_background"), -182, offset - 5, 182, 5);
                if (y > 0) {
                    int progress = MathHelper.lerpPositive(percentY, 91, 182);
                    context.drawTexture(Identifier.of("textures/gui/sprites/boss_bar/white_progress.png"), -182, offset - 5, progress, 5, 0, 0, progress, 5, 182, 5);
                }

                context.drawTextWithShadow(textRenderer, translatableX, -91 + 8 + (width / 2) - (textRenderer.getWidth(translatableX) / 2), offset - 5 - 9 - 19, 16777215);
                context.drawTextWithShadow(textRenderer, translatableY, -91 + 8 + (width / 2) - (textRenderer.getWidth(translatableY) / 2), offset - 5 - 9, 16777215);
            }
            case SIDEBAR -> {
                Text title = Text.translatable("options.raised.element.sidebar");
                int width = Math.max(2 + Math.max(textRenderer.getWidth(translatableX), textRenderer.getWidth(translatableY)) + 9 + Math.max(widthX, widthY), 2 + textRenderer.getWidth(title));

                context.fill(-91 + 1, offset - 1 - 9 - 10 - 10, -91 + 1 + width, offset - 1 - 10 - 10, client.options.getTextBackgroundColor(0.4F));
                context.fill(-91 + 1, offset - 1 - 10 - 10, -91 + 1 + width, offset - 1, client.options.getTextBackgroundColor(0.3F));

                context.drawText(textRenderer, title, -91 + 1 + (width / 2) - (textRenderer.getWidth(title) / 2), offset - 1 - 8 - 10 - 10, Colors.WHITE, false);

                context.drawText(textRenderer, translatableX, -91 + 1 + 2, offset - 1 - 9 - 10, Colors.WHITE, false);
                context.drawText(textRenderer, translatableY, -91 + 1 + 2, offset - 1 - 9, Colors.WHITE, false);

                context.drawText(textRenderer, String.valueOf(x), -91 + 1 + width - widthX, offset - 1 - 9 - 10, Colors.LIGHT_RED, false);
                context.drawText(textRenderer, String.valueOf(y), -91 + 1 + width - widthY, offset - 1 - 9, Colors.LIGHT_RED, false);
            }
            case EFFECTS -> {
                context.drawGuiTexture(Identifier.of("hud/effect_background"), -91 + 1, offset - 24 - 1, 24, 24);
                context.drawGuiTexture(Identifier.of("hud/effect_background"), -91 + 1 + 24 + 1, offset - 24 - 1, 24, 24);

                context.setShaderColor(1.0F, 1.0F, 1.0F, percentX);
                context.drawSprite(-91 + 1 + 3, offset - 24 - 1 + 3, 0, 18, 18, client.getStatusEffectSpriteManager().getSprite(StatusEffects.LUCK));
                context.setShaderColor(1.0F, 1.0F, 1.0F, percentY);
                context.drawSprite(-91 + 1 + 3 + 24 + 1, offset - 24 - 1 + 3, 0, 18, 18, client.getStatusEffectSpriteManager().getSprite(StatusEffects.UNLUCK));

                context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
            case PLAYERS -> {
                UUID uuidPlayer = client.getGameProfile().getId();
                UUID uuidNotch = UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5");
                GameProfile profilePlayer = client.getSessionService().fetchProfile(uuidPlayer, false).profile();
                GameProfile profileNotch = client.getSessionService().fetchProfile(uuidNotch, false).profile();

                context.fill(-91 + 1, offset - 1 - 10 - 9, -91 + 1 + 144, offset - 1, Integer.MIN_VALUE);
                context.fill(-91 + 1 + 1, offset - 1 - 9 - 9, -91 + 1 + 144, offset - 1 - 1 - 9, client.options.getTextBackgroundColor(553648127));
                context.fill(-91 + 1 + 1, offset - 1 - 9, -91 + 1 + 144, offset - 1 - 1, client.options.getTextBackgroundColor(553648127));

                PlayerSkinDrawer.draw(context, client.getSkinProvider().getSkinTextures(profilePlayer).texture(), -91 + 1 + 1, offset - 1 - 9 - 9, 8, client.world.getPlayerByUuid(uuidPlayer).isPartVisible(PlayerModelPart.HAT), "Dinnerbone".equals(profilePlayer.getName()) || "Grumm".equals(profilePlayer.getName()));
                PlayerSkinDrawer.draw(context, client.getSkinProvider().getSkinTextures(profileNotch).texture(), -91 + 1 + 1, offset - 1 - 9, 8, true, false);

                context.drawTextWithShadow(textRenderer, translatableX, -91 + 1 + 10, offset - 1 - 9 - 9, Colors.WHITE);
                context.drawTextWithShadow(textRenderer, translatableY, -91 + 1 + 10, offset - 1 - 9, Colors.WHITE);

                context.drawTextWithShadow(textRenderer, stringX, -91 + 1 + 144 - 1 - 10 - 1 - widthX, offset - 1 - 9 - 9, Colors.LIGHT_YELLOW);
                context.drawTextWithShadow(textRenderer, stringY, -91 + 1 + 144 - 1 - 10 - 1 - widthY, offset - 1 - 9, Colors.LIGHT_YELLOW);

                context.drawGuiTexture(getSignal(percentX), -91 + 1 + 144 - 1 - 10, offset - 1 - 9 - 9, 10, 8);
                context.drawGuiTexture(getSignal(percentY), -91 + 1 + 144 - 1 - 10, offset - 1 - 9, 10, 8);
            }
            case TOASTS -> {
                context.drawGuiTexture(Identifier.of("toast/advancement"), -91 - 40, offset - 32, 160, 32);

                context.drawItemWithoutEntity(Items.ENCHANTED_GOLDEN_APPLE.getDefaultStack(), -91 + 8, offset - 32 + 8);

                context.drawText(textRenderer, translatableX.getString() + ": " + x, -91 + 30, offset - 32 + 7, 16776960 | Colors.BLACK, false);
                context.drawText(textRenderer, translatableY.getString() + ": " + y, -91 + 30, offset - 32 + 18, Colors.WHITE, false);
            }
            case OTHER -> {
                context.drawTextWithShadow(textRenderer, translatableX.getString() + ": " + x, -91 + 8, offset - 8 - 8 - 4 - 8, Colors.WHITE);
                context.drawTextWithShadow(textRenderer, translatableY.getString() + ": " + y, -91 + 8, offset - 8 - 8, Colors.WHITE);
            }
        }
        RenderSystem.disableBlend();
        context.getMatrices().pop();
    }

    public static Identifier getSignal(float percent) {
        Identifier identifier;
        if (percent > 0.8F) {
            identifier = Identifier.of("icon/ping_5");
        } else if (percent > 0.6F) {
            identifier = Identifier.of("icon/ping_4");
        } else if (percent > 0.4F) {
            identifier = Identifier.of("icon/ping_3");
        } else if (percent > 0.2F) {
            identifier = Identifier.of("icon/ping_2");
        } else if (percent > 0.0F) {
            identifier = Identifier.of("icon/ping_1");
        } else {
            identifier = Identifier.of("icon/ping_unknown");
        }
        return identifier;
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        applyBlur(delta);
        renderDarkening(context);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        if (RaisedKeyBindings.options.matchesKey(keyCode, scanCode)) {
            close();
            return true;
        }
        return true;
    }

}