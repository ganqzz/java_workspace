package demo.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class BuffersNativeOrderDemo {

    public static void main(String[] args) {
        int[] values = {1, 2, 3};

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(64);
        byteBuffer.order(ByteOrder.nativeOrder()); // x86: LITTLE_ENDIAN

        IntBuffer buffer = byteBuffer.asIntBuffer();
        buffer.put(values);
        buffer.position(0);
        System.out.println("Location = " + buffer.get(2));
    }
}
