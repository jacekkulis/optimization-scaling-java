package pl.dmcs.utils;

import sun.nio.ch.DirectBuffer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Reader {
    private String fileName;

    public Reader(String fileName) {
        this.fileName = fileName;
    }

    public void bufferedReader() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((bufferedReader.readLine()) != null) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nioRead() {
        try {
            FileChannel inChannel = (FileChannel) Files.newByteChannel(Paths.get(fileName), EnumSet.of(StandardOpenOption.READ));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                buffer.clear();
            }
            inChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mappedRead() {
        try {
            FileChannel inChannel = (FileChannel) Files.newByteChannel(Paths.get(fileName), EnumSet.of(StandardOpenOption.READ));
            MappedByteBuffer mappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            CharBuffer charBuffer;

            if (mappedByteBuffer != null) {
                charBuffer = Charset.forName("UTF-8").decode(mappedByteBuffer);
                //System.out.println("File content: " + charBuffer.toString());
                mappedByteBuffer.clear();
            }
            inChannel.close();
            ((DirectBuffer) mappedByteBuffer).cleaner().clean();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

