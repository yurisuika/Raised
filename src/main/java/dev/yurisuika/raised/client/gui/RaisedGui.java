package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Overlay;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

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
        public void startHotbarTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getHotbar().contains(event.getName())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getGuiGraphics().pose(), Element.HOTBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endHotbarTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getHotbar().contains(event.getName()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endHotbarTranslate(RenderGuiLayerEvent.Post event) {
            if (Overlay.getHotbar().contains(event.getName())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
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
        public void startChatTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getChat().contains(event.getName())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getGuiGraphics().pose(), Element.CHAT);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endChatTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getChat().contains(event.getName()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endChatTranslate(RenderGuiLayerEvent.Post event) {
            if (Overlay.getChat().contains(event.getName())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
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
        public void startBossbarTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getBossbar().contains(event.getName())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getGuiGraphics().pose(), Element.BOSSBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endBossbarTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getBossbar().contains(event.getName()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endBossbarTranslate(RenderGuiLayerEvent.Post event) {
            if (Overlay.getBossbar().contains(event.getName())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
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
        public void startSidebarTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getSidebar().contains(event.getName())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getGuiGraphics().pose(), Element.SIDEBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endSidebarTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getSidebar().contains(event.getName()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endSidebarTranslate(RenderGuiLayerEvent.Post event) {
            if (Overlay.getSidebar().contains(event.getName())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
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
        public void startEffectsTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getEffects().contains(event.getName())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getGuiGraphics().pose(), Element.EFFECTS);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endEffectsTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getEffects().contains(event.getName()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endEffectsTranslate(RenderGuiLayerEvent.Post event) {
            if (Overlay.getEffects().contains(event.getName())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
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
        public void startPlayersTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getPlayers().contains(event.getName())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getGuiGraphics().pose(), Element.PLAYERS);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endPlayersTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getPlayers().contains(event.getName()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endPlayersTranslate(RenderGuiLayerEvent.Post event) {
            if (Overlay.getPlayers().contains(event.getName())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
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
        public void startOtherTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getOther().contains(event.getName())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getGuiGraphics().pose(), Element.OTHER);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endOtherTranslate(RenderGuiLayerEvent.Pre event) {
            if (Overlay.getOther().contains(event.getName()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endOtherTranslate(RenderGuiLayerEvent.Post event) {
            if (Overlay.getOther().contains(event.getName())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }

    }

}