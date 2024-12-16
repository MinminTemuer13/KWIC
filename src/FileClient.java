import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        String filePath = "test1.txt"; // 发送的文件路径
        String serverAddress = "localhost"; // 服务器地址
        int serverPort = 52000; // 服务器端口

        try (Socket socket = new Socket(serverAddress, serverPort);
             FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             OutputStream outputStream = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(outputStream, true)) {

            System.out.println("Connecting to server...");

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                writer.println(line); // 将文件内容发送给服务器
            }

            System.out.println("File sent to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
