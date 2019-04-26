package org.kulturhusfx.util.fileHandling;

import javafx.concurrent.Task;
import org.kulturhusfx.base.Event;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.EventModel;
import org.kulturhusfx.model.HallModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public abstract class ReadFile{

    HallModel hallModel = HallModel.getInstance();
    List<Hall> hallList = hallModel.getHallList();
    EventModel eventModel = EventModel.getInstance();
    List<Event> eventList = eventModel.getEventList();

    public abstract void readEventFromFile(String fileName) throws IOException, ClassNotFoundException;
    public abstract void readHallFromFile (String fileName) throws IOException, ClassNotFoundException;

}
