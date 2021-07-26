package demo.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public class BuffersTypedDemo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Allocate each of the Typed Buffers
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        CharBuffer charBuffer = CharBuffer.allocate(64);
        ShortBuffer shortBuffer = ShortBuffer.allocate(64);
        IntBuffer intBuffer = IntBuffer.allocate(64);
        LongBuffer longBuffer = LongBuffer.allocate(64);
        FloatBuffer floatBuffer = FloatBuffer.allocate(64);
        DoubleBuffer doubleBuffer = DoubleBuffer.allocate(64);

        ByteBuffer byteBufferSample = ByteBuffer.allocate(1024);
        IntBuffer intBufferSample = byteBufferSample.asIntBuffer();

        // Store an array of int:
        intBufferSample.put(new int[]{13, 48, 3, 12, 5, 144});
        // Absolute location read and write:
        System.out.println("Absolute Location, value in position 3 is = " + intBufferSample.get(3));
        intBufferSample.put(3, 2013);
        intBufferSample.rewind();
        while (intBufferSample.hasRemaining()) {
            int i = intBufferSample.get();
            if (i == 0) break; // Else we'll get the entire buffer
            System.out.println(i);
        }
    }
}
