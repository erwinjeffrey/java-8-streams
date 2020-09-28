package org.functionalprograming.chapter5;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterARM  implements  AutoCloseable{
    private final FileWriter writer;

    public FileWriterARM(final String fileName) throws IOException{
        writer = new FileWriter(fileName);
    }

    public void writeStuff(final String message) throws IOException{
        writer.write(message);
    }

    @Override
    public void close() throws IOException {
        System.out.println("close called automatically...");
        writer.close();
    }
}
