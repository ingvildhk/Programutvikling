package org.kulturhusfx.util.fileHandling;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterJobj extends FileWriter {

    public FileWriterJobj(String fileName) throws IOException {
        super(fileName);
    }
}
