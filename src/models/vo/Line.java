package models.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public record Line(String[] words, Integer index) {
    @Override
    public String toString() {
        Stack<String> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i < index) {
                stack.push(words[i]);
            }
            else {
                builder.append(words[i]);
                builder.append(' ');
            }
        }
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
            builder.append(' ');
        }

        return builder.toString();
    }
}
