package ecs.component;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class DataBufferFactory {

    public static ByteBuffer createDataBuffer(int size) {
        return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder());
    }

}
