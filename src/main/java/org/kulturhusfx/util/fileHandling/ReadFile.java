package org.kulturhusfx.util.fileHandling;

import org.kulturhusfx.base.Hall;
import org.kulturhusfx.base.Happening;
import org.kulturhusfx.model.HallModel;
import org.kulturhusfx.model.HappeningModel;

import java.io.IOException;
import java.util.List;

public abstract class ReadFile {

    public HallModel hallModel = HallModel.getInstance();
    public List<Hall> hallList = hallModel.getHallList();
    public HappeningModel happeningModel = HappeningModel.getInstance();
    public List<Happening> happeningList = happeningModel.getHappeningList();

    public abstract void readHappeningFromFile(String fileName) throws IOException, ClassNotFoundException;

    public abstract void readHallFromFile(String fileName) throws IOException, ClassNotFoundException;

}
