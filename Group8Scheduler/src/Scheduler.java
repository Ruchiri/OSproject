/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 */
public class Scheduler implements Runnable{
    public   int SystemTime;

    public   int TimeQuantam;
    
    public boolean ranonce=false;

    public  ArrayList<Process> ProcessList=new ArrayList<Process>();

    public   ArrayList<Process>  ProcessQueue= new ArrayList<Process>();

    public  void AddProcess(Process P){ProcessList.add(P);}

    public  void SetTimeQuantam(int TimeDelimetre){TimeQuantam=TimeDelimetre;}

    public ArrayList<Process>SortProcessList(ArrayList<Process> PList){
        ArrayList<Process>ReturnList=new ArrayList<Process>();
        Process[] arr=new Process[PList.size()];
        int count=0;
        for(Process p:PList){
            arr[count]=p;
            count=count+1;
        }
        int n = arr.length;
        Process temp = arr[0];
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr[j-1].ArrivalTime > arr[j].ArrivalTime){
                    //swap elements
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }

            }
        }
        for(int m=0;m<n;m++){ReturnList.add(arr[m]);}
        return ReturnList;

    }
    public Scheduler(){
        SystemTime=0;


    }

    @Override
    public  void run() {
        
        ProcessList=SortProcessList(ProcessList);
        boolean isempty=true;
        while(true){
            if(isempty) {
                for (Process p : ProcessList) {
                    if (p.ArrivalTime == SystemTime) {
                        ProcessQueue.add(p);
                        isempty = false;

                    }

                }
                if(isempty){SystemTime=SystemTime+1;}
            }
            else {
                if (ProcessQueue.size()>0){
                    ProcessQueue.get(0).Execute();
                }
                else{
                    boolean checkfinish=true;
                    int tempf=SystemTime;;
                    for(Process p:ProcessList){
                        if(p.ArrivalTime>=SystemTime){
                            ProcessQueue.add(p);
                            SystemTime=p.ArrivalTime;
                            checkfinish=false;
                            break;
                        }
                    }
                    if(!checkfinish){
                        for(int timef=tempf+1;timef<=SystemTime;timef=timef+1){
                            //System.out.println("No process is executing at time "+timef);
                        }

                    }
                    if(checkfinish) {
                        //System.out.println("All the process have completely executed.");
                        ranonce=true;
                        break;
                        //System.exit(0);
                    }
                }

            }
        }
        

    }



}
