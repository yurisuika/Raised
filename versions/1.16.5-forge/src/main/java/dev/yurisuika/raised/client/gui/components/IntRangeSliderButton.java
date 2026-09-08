package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public final class IntRangeSliderButton extends AbstractSliderButton {

    private final Component label;
    private final int min;
    private final int max;
    private final Function<Integer, Component> valueText;
    private final Consumer<Integer> onValueChanged;
    private final Function<Integer, List<FormattedCharSequence>> tooltip;

    private IntRangeSliderButton(Builder builder) {
        super(builder.x, builder.y, builder.width, builder.height, builder.label, normalize(builder.initialValue, builder.min, builder.max));

        this.label = builder.label;
        this.min = builder.min;
        this.max = builder.max;
        this.valueText = builder.valueText;
        this.onValueChanged = builder.onValueChanged;
        this.tooltip = builder.tooltip;

        this.updateMessage();
    }

    public static Builder builder(Component label, Consumer<Integer> onValueChanged) {
        return new Builder(label, onValueChanged);
    }

    public int getValue() {
        int value = min + (int) Math.round(this.value * (max - min));

        return Mth.clamp(value, min, max);
    }

    public void setValue(int value) {
        value = Mth.clamp(value, min, max);

        double normalizedValue = normalize(value, min, max);
        normalizedValue = Mth.clamp(normalizedValue, 0.0D, 1.0D);

        if (this.value != normalizedValue) {
            this.value = normalizedValue;
            applyValue();
            updateMessage();
        }
    }

    @Override
    protected void updateMessage() {
        setMessage(new TextComponent("").append(label).append(new TextComponent(": ")).append(valueText.apply(getValue())));
    }

    @Override
    public void renderToolTip(PoseStack poseStack, int mouseX, int mouseY) {
        if (tooltip != null && (isHovered() || isFocused())) {
            Minecraft minecraft = Minecraft.getInstance();

            if (minecraft.screen != null) {
                minecraft.screen.renderTooltip(poseStack, tooltip.apply(getValue()), mouseX, mouseY);
            }
        }
    }

    @Override
    protected void applyValue() {
        onValueChanged.accept(getValue());
    }

    private static double normalize(int value, int min, int max) {
        return (double) (value - min) / (max - min);
    }

    public static final class Builder {

        private final Component label;
        private final Consumer<Integer> onValueChanged;

        private int x;
        private int y;
        private int width = 150;
        private int height = 20;

        private int min = 0;
        private int max = 1;
        private int initialValue = 0;

        private Function<Integer, Component> valueText = value -> new TextComponent(String.format(value.toString()));

        private Function<Integer, List<FormattedCharSequence>> tooltip;

        private Builder(Component label, Consumer<Integer> onValueChanged) {
            this.label = Objects.requireNonNull(label);
            this.onValueChanged = Objects.requireNonNull(onValueChanged);
        }

        public Builder pos(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder bounds(int x, int y, int width, int height) {
            return pos(x, y).size(width, height);
        }

        public Builder range(int min, int max) {
            this.min = min;
            this.max = max;
            return this;
        }

        public Builder initialValue(int value) {
            this.initialValue = value;
            return this;
        }

        public Builder valueText(Function<Integer, Component> valueText) {
            this.valueText = Objects.requireNonNull(valueText);
            return this;
        }

        public Builder tooltip(Function<Integer, List<FormattedCharSequence>> tooltip) {
            this.tooltip = Objects.requireNonNull(tooltip);
            return this;
        }

        public IntRangeSliderButton build() {
            this.initialValue = Mth.clamp(initialValue, min, max);
            return new IntRangeSliderButton(this);
        }

    }

}