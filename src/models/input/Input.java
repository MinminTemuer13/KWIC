package models.input;

import java.util.List;

public interface Input {
    List<String> readFile(String fileName);
}
