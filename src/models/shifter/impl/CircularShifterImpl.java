package models.shifter.impl;

import models.shifter.Shifter;
import models.vo.Line;

import java.util.ArrayList;
import java.util.List;

public class CircularShifterImpl implements Shifter {
    @Override
    public List<Line> shift(List<String> fileLines) {
        ArrayList<Line> list = new ArrayList<>();

        for (String line : fileLines) {
            String[] split = line.split(" ");
            for (int i = 0; i < split.length; i++) {
                list.add(new Line(split, i));
            }
        }

        return list;
    }
}
