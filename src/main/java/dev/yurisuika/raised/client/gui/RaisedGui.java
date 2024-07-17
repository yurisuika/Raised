package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Overlay;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
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
        public void startHotbarTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getHotbar().contains(event.getOverlay())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getMatrixStack(), Element.HOTBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endHotbarTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getHotbar().contains(event.getOverlay()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endHotbarTranslate(RenderGameOverlayEvent.PostLayer event) {
            if (Overlay.getHotbar().contains(event.getOverlay())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
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
        public void startChatTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getChat().contains(event.getOverlay())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getMatrixStack(), Element.CHAT);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endChatTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getChat().contains(event.getOverlay()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endChatTranslate(RenderGameOverlayEvent.PostLayer event) {
            if (Overlay.getChat().contains(event.getOverlay())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
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
        public void startBossbarTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getBossbar().contains(event.getOverlay())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getMatrixStack(), Element.BOSSBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endBossbarTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getBossbar().contains(event.getOverlay()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endBossbarTranslate(RenderGameOverlayEvent.PostLayer event) {
            if (Overlay.getBossbar().contains(event.getOverlay())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
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
        public void startSidebarTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getSidebar().contains(event.getOverlay())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getMatrixStack(), Element.SIDEBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endSidebarTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getSidebar().contains(event.getOverlay()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endSidebarTranslate(RenderGameOverlayEvent.PostLayer event) {
            if (Overlay.getSidebar().contains(event.getOverlay())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
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
        public void startEffectsTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getEffects().contains(event.getOverlay())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getMatrixStack(), Element.EFFECTS);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endEffectsTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getEffects().contains(event.getOverlay()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endEffectsTranslate(RenderGameOverlayEvent.PostLayer event) {
            if (Overlay.getEffects().contains(event.getOverlay())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
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
        public void startPlayersTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getPlayers().contains(event.getOverlay())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getMatrixStack(), Element.PLAYERS);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endPlayersTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getPlayers().contains(event.getOverlay()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endPlayersTranslate(RenderGameOverlayEvent.PostLayer event) {
            if (Overlay.getPlayers().contains(event.getOverlay())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
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
        public void startOtherTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getOther().contains(event.getOverlay())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getMatrixStack(), Element.OTHER);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endOtherTranslate(RenderGameOverlayEvent.PreLayer event) {
            if (Overlay.getOther().contains(event.getOverlay()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endOtherTranslate(RenderGameOverlayEvent.PostLayer event) {
            if (Overlay.getOther().contains(event.getOverlay())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public void startOtherTranslate(RenderGameOverlayEvent.Pre event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                Translate.start(event.getMatrixStack(), Element.OTHER);
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endOtherTranslate(RenderGameOverlayEvent.Pre event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                Translate.end(event.getMatrixStack());
            }
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST)
        public void startOtherTranslate(RenderGameOverlayEvent.Post event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                Translate.start(event.getMatrixStack(), Element.OTHER);
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endOtherTranslate(RenderGameOverlayEvent.Post event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                Translate.end(event.getMatrixStack());
            }
        }

    }

}