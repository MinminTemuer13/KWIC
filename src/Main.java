import models.kwic.impl.KWICImpl;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入本地文件名或者输入回车接受Socket输入:");
        String filename = scanner.nextLine();
        scanner.close();
        if (filename.length() > 4) {
            new KWICImpl(filename).execute();
        }
        else {
            try {
                new KWICImpl().remoteExecute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


