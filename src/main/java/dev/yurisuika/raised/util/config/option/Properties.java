package dev.yurisuika.raised.util.config.option;

import dev.yurisuika.raised.util.type.Position;
import dev.yurisuika.raised.util.type.Sync;

public abstract class Properties {

    public int x;
    public int y;
    public Position position;
    public Sync sync;

    public Properties(int x, int y, Position position, Sync sync) {
        this.x = x;
        this.y = y;
        this.position = position;
        this.sync = sync;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPosition() {
        return position;
    }

    public Sync getSync() {
        return sync;
    }

    public static class Hotbar extends Properties {

        public Hotbar(int x, int y, Position position, Sync sync) {
            super(x, y, position, sync);
        }

    }

    public static class Chat extends Properties {

        public Chat(int x, int y, Position position, Sync sync) {
            super(x, y, position, sync);
        }

    }

    public static class Bossbar extends Properties {

        public Bossbar(int x, int y, Position position, Sync sync) {
            super(x, y, position, sync);
        }

    }

    public static class Sidebar extends Properties {

        public Sidebar(int x, int y, Position position, Sync sync) {
            super(x, y, position, sync);
        }

    }

    public static class Effects extends Properties {

        public Effects(int x, int y, Position position, Sync sync) {
            super(x, y, position, sync);
        }

    }

    public static class Players extends Properties {

        public Players(int x, int y, Position position, Sync sync) {
            super(x, y, position, sync);
        }

    }

    public static class Toasts extends Properties {

        public Toasts(int x, int y, Position position, Sync sync) {
            super(x, y, position, sync);
        }

    }

    public static class Other extends Properties {

        public Other(int x, int y, Position position, Sync sync) {
            super(x, y, position, sync);
        }

    }

}