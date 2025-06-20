package dev.yurisuika.raised.util;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.util.config.Option;
import net.minecraft.resources.ResourceLocation;

public class Translate {

    public static int getX(ResourceLocation name) {
        return getX(name.toString());
    }

    public static int getX(String name) {
        return Option.getLayer(name) == null ? 0 : Option.getDisplacementX(Option.getSync(name)) * Option.getDirectionX(name).getX();
    }

    public static int getY(ResourceLocation name) {
        return getY(name.toString());
    }

    public static int getY(String name) {
        return Option.getLayer(name) == null ? 0 : Option.getDisplacementY(Option.getSync(name)) * Option.getDirectionY(name).getY();
    }

    public static void start(PoseStack poseStack, ResourceLocation name) {
        start(poseStack, name.toString());
    }

    public static void start(PoseStack poseStack, String name) {
        poseStack.pushPose();
        poseStack.translate(getX(name), getY(name), 0);
    }

    public static void end(PoseStack poseStack) {
        poseStack.popPose();
    }

}