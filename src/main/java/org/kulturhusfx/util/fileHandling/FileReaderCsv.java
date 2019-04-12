package org.kulturhusfx.util.fileHandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileReaderCsv extends FileReader {

    public static Object readFile(String path)throws IOException, ClassNotFoundException {
        try (FileInputStream fin = new FileInputStream(path);
             ObjectInputStream oin = new ObjectInputStream(fin)) {
            return oin.readObject();
        }
    }
}