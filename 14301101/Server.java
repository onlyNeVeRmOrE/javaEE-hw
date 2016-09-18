import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
 

public class Server {
 
    public static void main(String[] args) {
 
        try {
            ServerSocket serverSocket = new ServerSocket(3333);
            Socket socket = null;
            int count = 0;
            System.out.println("服务器启动");          
            while (true){
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start(); 
                count++;
                System.out.println("客户端的数量: " + count);
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP ： " + address.getHostAddress());
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}