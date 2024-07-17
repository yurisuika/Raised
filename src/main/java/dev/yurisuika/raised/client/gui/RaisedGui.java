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
        public void startHotbarTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getHotbar().contains(event.getType())) {
                if (!translated) {
                    translated = true;
                    Translate.start(Element.HOTBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endHotbarTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getHotbar().contains(event.getType()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endHotbarTranslate(RenderGameOverlayEvent.Post event) {
            if (Overlay.getHotbar().contains(event.getType())) {
                if (translated) {
                    translated = false;
                    Translate.end();
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
        public void startChatTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getChat().contains(event.getType())) {
                if (!translated) {
                    translated = true;
                    Translate.start(Element.CHAT);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endChatTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getChat().contains(event.getType()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endChatTranslate(RenderGameOverlayEvent.Post event) {
            if (Overlay.getChat().contains(event.getType())) {
                if (translated) {
                    translated = false;
                    Translate.end();
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
        public void startBossbarTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getBossbar().contains(event.getType())) {
                if (!translated) {
                    translated = true;
                    Translate.start(Element.BOSSBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endBossbarTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getBossbar().contains(event.getType()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endBossbarTranslate(RenderGameOverlayEvent.Post event) {
            if (Overlay.getBossbar().contains(event.getType())) {
                if (translated) {
                    translated = false;
                    Translate.end();
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
        public void startSidebarTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getSidebar().contains(event.getType())) {
                if (!translated) {
                    translated = true;
                    Translate.start(Element.SIDEBAR);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endSidebarTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getSidebar().contains(event.getType()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endSidebarTranslate(RenderGameOverlayEvent.Post event) {
            if (Overlay.getSidebar().contains(event.getType())) {
                if (translated) {
                    translated = false;
                    Translate.end();
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
        public void startEffectsTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getEffects().contains(event.getType())) {
                if (!translated) {
                    translated = true;
                    Translate.start(Element.EFFECTS);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endEffectsTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getEffects().contains(event.getType()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endEffectsTranslate(RenderGameOverlayEvent.Post event) {
            if (Overlay.getEffects().contains(event.getType())) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }

    }

    public static class Players extends RaisedGui {

        public boolean translated = false;

        /**
         * Moves the {@code p list} for {@link Element.PLAYERS}.
         */
        public Players() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startPsTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getPlayers().contains(event.getType())) {
                if (!translated) {
                    translated = true;
                    Translate.start(Element.PLAYERS);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endPsTranslate(RenderGameOverlayEvent.Pre event) {
            if (Overlay.getPlayers().contains(event.getType()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endPsTranslate(RenderGameOverlayEvent.Post event) {
            if (Overlay.getPlayers().contains(event.getType())) {
                if (translated) {
                    translated = false;
                    Translate.end();
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
        public void startOtherTranslate(RenderGameOverlayEvent.Pre event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                if (!translated) {
                    translated = true;
                    Translate.start(Element.OTHER);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endOtherTranslate(RenderGameOverlayEvent.Pre event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startOtherTranslate(RenderGameOverlayEvent.Post event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                if (!translated) {
                    translated = true;
                    Translate.start(Element.OTHER);
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endOtherTranslate(RenderGameOverlayEvent.Post event) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }

    }

}