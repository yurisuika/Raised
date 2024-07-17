package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Overlay;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RaisedGui {

    public static class Hotbar extends RaisedGui {

        public boolean translated = false;

        /**
         * Moves the {@code spectator menu}, {@code hotbar}, {@code health bar}, {@code armor bar}, {@code food bar},
         * {@code air bar}, {@code mount health bar}, {@code mount jump bar}, {@code experience bar},
         * {@code held item tooltip}, {@code spectator tooltip}, and {@code overlay message} for {@link Element.HOTBAR}.
         */
        public Hotbar() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startHotbarTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getHotbar().contains(event.getOverlay().id())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getPoseStack(), Element.HOTBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endHotbarTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getHotbar().contains(event.getOverlay().id()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endHotbarTranslate(RenderGuiOverlayEvent.Post event) {
            if (Overlay.getHotbar().contains(event.getOverlay().id())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

    }

    public static class Chat extends RaisedGui {

        public boolean translated = false;

        /**
         * Moves the {@code chat} for {@link Element.CHAT}.
         */
        public Chat() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startChatTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getChat().contains(event.getOverlay().id())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getPoseStack(), Element.CHAT);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endChatTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getChat().contains(event.getOverlay().id()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endChatTranslate(RenderGuiOverlayEvent.Post event) {
            if (Overlay.getChat().contains(event.getOverlay().id())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

    }

    public static class Bossbar extends RaisedGui {

        public boolean translated = false;

        /**
         * Moves the {@code bossbar} for {@link Element.BOSSBAR}.
         */
        public Bossbar() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startBossbarTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getBossbar().contains(event.getOverlay().id())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getPoseStack(), Element.BOSSBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endBossbarTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getBossbar().contains(event.getOverlay().id()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endBossbarTranslate(RenderGuiOverlayEvent.Post event) {
            if (Overlay.getBossbar().contains(event.getOverlay().id())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

    }

    public static class Sidebar extends RaisedGui {

        public boolean translated = false;

        /**
         * Moves the {@code sidebar} for {@link Element.SIDEBAR}.
         */
        public Sidebar() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startSidebarTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getSidebar().contains(event.getOverlay().id())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getPoseStack(), Element.SIDEBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endSidebarTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getSidebar().contains(event.getOverlay().id()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endSidebarTranslate(RenderGuiOverlayEvent.Post event) {
            if (Overlay.getSidebar().contains(event.getOverlay().id())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

    }

    public static class Effects extends RaisedGui {

        public boolean translated = false;

        /**
         * Moves the {@code status effects} for {@link Element.EFFECTS}.
         */
        public Effects() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startEffectsTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getEffects().contains(event.getOverlay().id())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getPoseStack(), Element.EFFECTS);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endEffectsTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getEffects().contains(event.getOverlay().id()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endEffectsTranslate(RenderGuiOverlayEvent.Post event) {
            if (Overlay.getEffects().contains(event.getOverlay().id())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

    }

    public static class Players extends RaisedGui {

        public boolean translated = false;

        /**
         * Moves the {@code player list} for {@link Element.PLAYERS}.
         */
        public Players() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startPlayersTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getPlayers().contains(event.getOverlay().id())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getPoseStack(), Element.PLAYERS);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endPlayersTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getPlayers().contains(event.getOverlay().id()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endPlayersTranslate(RenderGuiOverlayEvent.Post event) {
            if (Overlay.getPlayers().contains(event.getOverlay().id())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

    }

    public static class Other extends RaisedGui {

        public boolean translated = false;

        /**
         * Moves registered mod elements for {@link Element.OTHER}.
         */
        public Other() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startOtherTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getOther().contains(event.getOverlay().id())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getPoseStack(), Element.OTHER);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endOtherTranslate(RenderGuiOverlayEvent.Pre event) {
            if (Overlay.getOther().contains(event.getOverlay().id()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endOtherTranslate(RenderGuiOverlayEvent.Post event) {
            if (Overlay.getOther().contains(event.getOverlay().id())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getPoseStack());
                }
            }
        }

    }

}