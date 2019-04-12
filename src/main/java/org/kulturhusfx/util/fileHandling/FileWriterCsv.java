package org.kulturhusfx.util.fileHandling;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterCsv extends FileWriter {

    public FileWriterCsv(String fileName) throws IOException {
        super(fileName);
    }
}
