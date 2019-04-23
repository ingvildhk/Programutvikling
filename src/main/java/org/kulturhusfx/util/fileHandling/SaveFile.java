package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.util.SceneUtils;

import java.io.IOException;
import java.util.List;

public abstract class SaveFile {

    public abstract void saveEventToFile(Event event, String path) throws IOException;
    public abstract void saveHallToFile(Hall hall, String path) throws IOException;

}
