package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Happening;
import org.kulturhusfx.base.Hall;
import org.kulturhusfx.model.HappeningModel;
import org.kulturhusfx.model.HallModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ReadFile{

    HallModel hallModel = HallModel.getInstance();
    List<Hall> hallList = hallModel.getHallList();
    HappeningModel happeningModel = HappeningModel.getInstance();
    List<Happening> happeningList = happeningModel.getHappeningList();

    public abstract void readEventFromFile(File file) throws IOException, ClassNotFoundException;
    public abstract void readHallFromFile (File file) throws IOException, ClassNotFoundException;

}
