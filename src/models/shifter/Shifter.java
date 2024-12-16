package models.shifter;

import models.vo.Line;

import java.util.List;

public interface Shifter {
    List<Line> shift(List<String> fileLines);
}
