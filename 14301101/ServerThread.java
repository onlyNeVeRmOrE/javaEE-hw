import java.io.*;
import java.net.Socket;
 

public class ServerThread extends Thread {
 
    Socket socket = null;
    public ServerThread(Socket socket){
        this.socket = socket;
    }
 
    public void run(){
 
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
 
        OutputStream os = null;
        PrintWriter pw = null;
        String fuck = null;
        try {
 
            is = socket.getInputStream();
            isr = new InputStreamReader(is); //���ֽ���ת��Ϊ�ַ���
            br = new BufferedReader(isr); //��ӻ���
            String info = null;
            while ((info = br.readLine()) != null){
                System.out.println("�ͻ���˵: " +info);
                
                fuck = new StringBuilder(info).reverse().toString();//��ת�ַ�
                os = socket.getOutputStream();
                pw = new PrintWriter(os); //��װΪ��ӡ��
                pw.write(fuck);
                pw.flush();  //���������
            }
 
            socket.shutdownInput(); //�ر�������
 
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
 
 
                try {
                    if (pw != null)
                        pw.close();
                    if (os != null)
                        os.close();
                    if (is != null)
                        is.close();
                    if (isr != null)
                        isr.close();
                    if (br != null)
                        br.close();
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {
                    e.printStackTrace(); 
                } 
        }

    }
}