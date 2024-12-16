package models.sorter.impl;

import models.sorter.Sorter;
import models.vo.Line;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AlphabetSorterImpl implements Sorter {
    List<Line> list;

    @Override
    public void sort(List<Line> list) {
        list.sort(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                int o1Index = o1.index(), o1Length = o1.words().length;
                int o2Index = o2.index(), o2Length = o2.words().length;
                while (Objects.equals(o1.words()[o1Index % o1Length], o2.words()[o2Index % o2Length])) {
                    o1Index++;
                    o2Index++;
                }
                return o1.words()[o1Index % o1Length].compareTo(o2.words()[o2Index % o2Length]);
            }
        });
    }
}
