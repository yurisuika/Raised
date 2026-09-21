package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CycleButton<T> extends AbstractButton {

    private final List<T> values;
    private final Function<T, Component> valueLines;
    private final Consumer<CycleButton<T>> onValueChange;
    private final Function<T, List<FormattedCharSequence>> tooltipSupplier;
    private int index;

    public CycleButton(int x, int y, int width, int height, Component title, List<T> values, int initialIndex, Function<T, Component> valueLines, Consumer<CycleButton<T>> onValueChange, Function<T, List<FormattedCharSequence>> tooltipSupplier) {
        super(x, y, width, height, title);
        this.values = values;
        this.index = initialIndex;
        this.valueLines = valueLines;
        this.onValueChange = onValueChange;
        this.tooltipSupplier = tooltipSupplier;
        this.updateMessage();
    }

    public static <T> Builder<T> builder(List<T> values, Function<T, Component> valueLines) {
        return new Builder<>(values, valueLines);
    }

    @Override
    public void onPress() {
        index = (index + 1) % values.size();
        updateMessage();
        onValueChange.accept(this);
    }

    public T getValue() {
        return values.get(index);
    }

    public void setValue(T value) {
        int nextIndex = values.indexOf(value);
        if (nextIndex != -1) {
            index = nextIndex;
            updateMessage();
        }
    }

    public List<FormattedCharSequence> getTooltip() {
        return tooltipSupplier.apply(getValue());
    }

    @Override
    public void renderToolTip(PoseStack poseStack, int mouseX, int mouseY) {
        if (tooltipSupplier != null && (isHovered() || isFocused())) {
            Minecraft minecraft = Minecraft.getInstance();

            if (minecraft.screen != null) {
                minecraft.screen.renderTooltip(poseStack, getTooltip(), mouseX, mouseY);
            }
        }
    }

    public void updateMessage() {
        setMessage(valueLines.apply(getValue()));
    }

    public static class Builder<T> {

        private final List<T> values;
        private final Function<T, Component> valueLines;
        private int initialIndex = 0;
        private Function<T, List<FormattedCharSequence>> tooltipSupplier = (val) -> Collections.emptyList();

        public Builder(List<T> values, Function<T, Component> valueLines) {
            this.values = new ArrayList<>(values);
            this.valueLines = valueLines;
        }

        public Builder<T> withInitialValue(T value) {
            initialIndex = Math.max(0, values.indexOf(value));
            return this;
        }

        public Builder<T> withTooltip(Function<T, List<FormattedCharSequence>> tooltipSupplier) {
            this.tooltipSupplier = tooltipSupplier;
            return this;
        }

        public CycleButton<T> create(int x, int y, int width, int height, Component title, Consumer<CycleButton<T>> onValueChange) {
            return new CycleButton<>(x, y, width, height, title, values, initialIndex, valueLines, onValueChange, tooltipSupplier);
        }

    }

}