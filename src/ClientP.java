
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientP extends Thread{
    boolean runnable = true;
    int time;
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;
    byte[] buffer = new byte[102400];
    ClientP(Socket socketM) {
        socket = socketM;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        time = Time.getTime()+5;
    }
    public void run() {
        int bytes;
        while (runnable) {
            try {
                if ((bytes = inputStream.read(buffer)) > 0){
                    time = Time.getTime()+5;
                    ClientWork.Work(outputStream,buffer);
                }
            } catch (IOException e) {
                runnable = false;
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Server.deleteClient();
            }
        }
    }
}
