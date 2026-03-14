import java.time.LocalDateTime;

public class Time extends Thread{
    boolean runnable = true;
    public static int getTime(){
        int time;
        LocalDateTime localDateTime = LocalDateTime.now();
        time = (localDateTime.getDayOfMonth()*24*60)+(localDateTime.getHour()*60)+localDateTime.getMinute();
        return  time;
    }
    public void run(){
        int time = getTime();
        PrintS.addPrint("Time start");
        while (runnable){
            if(time<getTime()){
                try {
                    Server.deleteClient();
                    Server.selectClient();
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
