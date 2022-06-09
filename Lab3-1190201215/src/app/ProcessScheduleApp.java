package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import l.Process;
import check.IntervalConflictException;
import specific.ProcessIntervalSet;

public class ProcessScheduleApp {

    static Scanner input = new Scanner(System.in);
    static Map<Process, Long> processList = new HashMap<>();
    static Process current = null;
    static long start = 0;

    private static int readNum() {
        String str = input.next();
        while (!str.matches("[0-9]+")) {
            System.err.println("Illegal input! Please input again:");
            str = input.next();
        }
        return Integer.parseInt(str);
    }

    private static void disSet(ProcessIntervalSet<Process> timeline){
        if(processList.isEmpty()) {
            System.out.print("All processes have been executed!\n");
        }
        long min = -1;
        Process thisProcess = null;
        for(Process p : processList.keySet()) {
            if(min < 0) {
                thisProcess = p;
                min = p.getMaxTime() - processList.get(p);
            }
            else {
                long thisPeriod = p.getMaxTime() - processList.get(p);
                if(thisPeriod < min) {
                    min = thisPeriod;
                    thisProcess = p;
                }
            }
        }
        if(thisProcess != null) {
            long runtime = (long) (Math.random()*(thisProcess.getMaxTime()- processList.get(thisProcess))) + 1;
            try {
                timeline.Insert(start, start+runtime, thisProcess);
            } catch (IntervalConflictException e) {
                System.err.print(e.getMessage());
                System.out.print("\n");
            }
            start += runtime;
            current = thisProcess;
            processList.put(thisProcess, processList.get(thisProcess)+runtime);
            if(processList.get(thisProcess) >= thisProcess.getMinTime() && processList.get(thisProcess) <= thisProcess.getMaxTime()) {
                processList.remove(thisProcess);
            }
        }
    }

    private static void randomSet(ProcessIntervalSet<Process> timeline) {

        if(processList.isEmpty()) {
            System.out.print("All processes have been executed!\n");
        }

        ArrayList<Process> list = new ArrayList<Process>(processList.keySet());
        int randomIndex = new Random().nextInt(list.size());
        Process randomProcess = list.get(randomIndex);
        long time = (long) (Math.random()*(randomProcess.getMaxTime()- processList.get(randomProcess))) + 1;

        try {
            timeline.Insert(start, start+time, randomProcess);
        } catch (IntervalConflictException e) {
            System.err.print(e.getMessage());
            System.out.print("\n");
        }

        start += time;
        current = randomProcess;
        processList.put(randomProcess, processList.get(randomProcess)+time);
        if(processList.get(randomProcess) >= randomProcess.getMinTime() && processList.get(randomProcess) <= randomProcess.getMaxTime()) {
            processList.remove(randomProcess);
        }
    }

    public static void showString(ProcessIntervalSet<Process> processInterval) {
        System.out.printf("The current moment: %d\n", start);
        if(current != null) {
            System.out.printf("The current pid: %s\n", current.getPid());
            System.out.print("The current name of process: ");
            System.out.print(current.getPname());
        }
        else {
            System.out.print("There is no process at this moment");
        }
        System.out.print("\nThe result before the current process:\n");
        String str = new String();
        for(Process p : processInterval.labels()) {
            str = p.getPname() + "(" + p.getPid() + ")" + " = {";
            for(Integer t : processInterval.intervals(p).labels()) {
                str += "[" + processInterval.intervals(p).start(t)+","+processInterval.intervals(p).end(t)+"],";
            }
            str = str.substring(0, str.length()-1);
            str += "}\n";
        }
        System.out.print(str);
    }

    public static void main(String[] args) {
        System.out.print("Please input the number of processes:\n");
        int num = readNum();

        if(num == 0) {
            System.out.print("No process, exit\n");
            System.exit(0);
        }

        ProcessIntervalSet<Process> processInterval = new ProcessIntervalSet<Process>();

        for(int i = 0; i < num; i++) {
            System.out.printf("Please input the information of the %dth process:\n", i+1);
            System.out.print("ID: ");
            String id = input.next();
            System.out.print("Name: ");
            String name = input.next();
            System.out.print("The shortest time: ");
            int min = readNum();
            System.out.print("The longest time: ");
            int max = readNum();
            while(min >= max) {
                System.err.print("The shortest time should be shorter than the longest one, please input again: ");
                max = readNum();
            }
            Process process = new Process(id, name, (long)min, (long)max);
            processList.put(process, (long) 0);
        }
        while (true){
            System.out.printf("The current time: %d\nMenu:\n1.Choose process(shortest one first)\n" +
                    "2.Choose process at random\n3.Do not execute process\n4.Show the arrangement\n5.exit\n", start);
            int choose = readNum();
            while(choose <= 0 || choose >= 6) {
                System.err.print("Input illegal numbers\n");
                choose = readNum();
            }
            switch (choose) {
                case 1:
                    disSet(processInterval);
                    break;
                case 2:
                    randomSet(processInterval);
                    break;
                case 3:
                    System.out.printf("The current time: %d\nPlease input next startTime: ", start);
                    int next = readNum();
                    while(next <= start ) {
                        System.err.print("The time input is before the current moment\n");
                        next = readNum();
                    }
                    start = next;
                    current = null;
                    break;
                case 4:
                	showString(processInterval);
                    break;
                case 5:
                    System.out.print("exit\n");
                    System.exit(0);
                default:
                    break;
            }
            System.out.print("\n");
        }
    }
}
