package models.kwic.impl;

import models.input.Input;
import models.input.impl.InputImpl;
import models.kwic.KWIC;
import models.shifter.Shifter;
import models.shifter.impl.CircularShifterImpl;
import models.sorter.Sorter;
import models.sorter.impl.AlphabetSorterImpl;
import models.vo.Line;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class KWICImpl implements KWIC {
    String fileName;
    Input input;
    Shifter shifter;
    Sorter sorter;

    int port = 52000;

    public KWICImpl(String fileName) {
        this.fileName = fileName;
        input = new InputImpl();
        shifter = new CircularShifterImpl();
        sorter = new AlphabetSorterImpl();
    }

    // Socket方法
    public KWICImpl() throws IOException {
        this(null);
    }

    @Override
    public void remoteExecute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // 等待客户端连接
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // 获取输入流，接收客户端传送的文件
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 将接收到的文件内容保存到本地
            File outputFile = new File("received_file.txt");
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();

            // 获取输入
            List<String> strings = input.readFile("received_file.txt");
            // 循环推进
            List<Line> lines = shifter.shift(strings);
            // 字母表排序
            sorter.sort(lines);
            // 输出结果
            System.out.println("运行结果：");
            for (Line line1 : lines) {
                System.out.println(line1.toString());
            }

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute() {
        // 获取输入
        List<String> strings = input.readFile(fileName);
        // 循环推进
        List<Line> lines = shifter.shift(strings);
        // 字母表排序
        sorter.sort(lines);
        // 输出结果
        System.out.println("运行结果：");
        for (Line line : lines) {
            System.out.println(line.toString());
        }
    }
}
