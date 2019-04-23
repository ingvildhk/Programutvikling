package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveFileJobj extends SaveFile {

    @Override
    public void saveEventToFile(Event event, String path) throws IOException {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(event);
        }
    }

    @Override
    public void saveHallToFile(Hall hall, String path) throws IOException {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(hall);
        }
    }
}