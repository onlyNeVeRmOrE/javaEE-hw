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
            isr = new InputStreamReader(is); //将字节流转化为字符流
            br = new BufferedReader(isr); //添加缓冲
            String info = null;
            while ((info = br.readLine()) != null){
                System.out.println("客户端说: " +info);
                
                fuck = new StringBuilder(info).reverse().toString();//反转字符
                os = socket.getOutputStream();
                pw = new PrintWriter(os); //包装为打印流
                pw.write(fuck);
                pw.flush();  //将缓存输出
            }
 
            socket.shutdownInput(); //关闭输入流
 
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