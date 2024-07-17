package dev.yurisuika.raised.util.config.options;

import dev.yurisuika.raised.util.properties.Position;
import dev.yurisuika.raised.util.properties.Sync;

public class Properties {

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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Sync getSync() {
        return sync;
    }

    public void setSync(Sync sync) {
        this.sync = sync;
    }

}