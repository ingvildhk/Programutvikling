package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Hall;

import java.io.IOException;

public abstract class SaveFile {

    public abstract void saveHappeningToFile(Happening happening, String path) throws IOException;
    public abstract void saveHallToFile(Hall hall, String path) throws IOException;

}
