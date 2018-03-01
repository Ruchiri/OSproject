
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * 
 */
public class Process {
    public String Name;
    public int ArrivalTime;
    public int BurstTime;
    public int ExecutedTime;
    public Scheduler schedhulerf;
    public ArrayList<Integer> executedslots;
    public int waitedtime;


    public Process(String Name,int ArrivalTime,int BurstTime,Scheduler schedhulerf){
        this.executedslots = new ArrayList<Integer>();
        this.Name=Name;
        this.ArrivalTime=ArrivalTime;
        this.BurstTime=BurstTime;
        this.ExecutedTime=0;
        this.schedhulerf=schedhulerf;
        this.waitedtime=0;
    }

    public  void SystemTimeUp(){schedhulerf.SystemTime++;}

    public  void ExecutedTimeUp(){ExecutedTime++;}

    public void Execute(){
        boolean checker=true;
        for (int i=1;i<=schedhulerf.TimeQuantam;i=i+1) {
            this.executedslots.add(schedhulerf.SystemTime);
            //System.out.println("Process"+this.Name+" started executuing at time "+schedhulerf.SystemTime);
            SystemTimeUp();
            this.ExecutedTimeUp();
            for(Process p:schedhulerf.ProcessList){
                if(p.ArrivalTime==schedhulerf.SystemTime){
                    schedhulerf.ProcessQueue.add(p);
                    //System.out.println("Process "+p.Name+" arrived at "+schedhulerf.SystemTime);
                }

            }
            if(this.ExecutedTime==this.BurstTime){
                //System.out.println(this.Name+" BurstTime expired at "+schedhulerf.SystemTime);
                schedhulerf.ProcessQueue.remove(this);
                checker=false;
                break;
            }


        }
        if(checker) {
            //System.out.println("TimeQuantam allocated for process " + this.Name + " expired at " + schedhulerf.SystemTime);
            schedhulerf.ProcessQueue.remove(this);
            schedhulerf.ProcessQueue.add(this);
        }

    }



}
