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
            System.out.println("����������");          
            while (true){
                socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start(); 
                count++;
                System.out.println("�ͻ��˵�����: " + count);
                InetAddress address = socket.getInetAddress();
                System.out.println("��ǰ�ͻ��˵�IP �� " + address.getHostAddress());
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}