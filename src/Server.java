
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<ClientP> arrayList = new ArrayList<>();
    private static Time time = new Time();
    private static Thread timeTh = new Thread(time);
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(FileW.getParam(0,"Port:")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintS.addPrint("ServerStarted");
        timeTh.start();
        while (true) {
            try {
                socket = serverSocket.accept();
                addClient(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void addClient(Socket socket){
            ClientP clientP = new ClientP(socket);
            clientP.start();
            arrayList.add(clientP);
            PrintS.addPrint(""+arrayList);
    }
    public static void selectClient(){
        int time = Time.getTime();
        for(int i = 0;i<arrayList.size();i++){
            if(arrayList.get(i).time < time){
                arrayList.get(i).runnable = false;
            }
        }
    }
    public static void deleteClient(){
            for(int i = 0;i < arrayList.size();i++){
                if(arrayList.get(i).runnable == false) {
                    arrayList.remove(i);
                }
            }
            PrintS.addPrint(""+arrayList+"\n");
    }
}
