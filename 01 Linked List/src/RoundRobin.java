import java.util.*;

class Process {
    int pid;
    int burstTime;
    int priority;
    int remainingTime;
    int waitingTime = 0;
    int turnaroundTime = 0;
    Process next;

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    Process head = null;

    public void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (head == null) {
            head = newProcess;
            head.next = head;
        } else {
            Process temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newProcess;
            newProcess.next = head;
        }
        System.out.println("Process " + pid + " added.");
    }

    public void removeProcess(int pid) {
        if (head == null) return;

        Process current = head, prev = null;
        do {
            if (current.pid == pid) {
                if (current == head && current.next == head) {
                    head = null;
                } else {
                    if (current == head) head = head.next;
                    if (prev != null) prev.next = current.next;
                    else {
                        Process temp = head;
                        while (temp.next != current) temp = temp.next;
                        temp.next = current.next;
                        head = current.next;
                    }
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void simulate(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to simulate.");
            return;
        }

        List<Process> allProcesses = new ArrayList<>();
        Process current = head;
        do {
            allProcesses.add(current);
            current = current.next;
        } while (current != head);

        int time = 0;
        current = head;
        while (head != null) {
            if (current.remainingTime > 0) {
                int execTime = Math.min(timeQuantum, current.remainingTime);
                time += execTime;
                current.remainingTime -= execTime;

                for (Process p : allProcesses) {
                    if (p != current && p.remainingTime > 0) {
                        p.waitingTime += execTime;
                    }
                }

                System.out.println("Executed Process " + current.pid + " for " + execTime + " units. Remaining: " + current.remainingTime);

                if (current.remainingTime == 0) {
                    current.turnaroundTime = time;
                    System.out.println("Process " + current.pid + " completed.");
                    int pidToRemove = current.pid;
                    current = current.next;
                    removeProcess(pidToRemove);
                    if (head == null) break;
                } else {
                    current = current.next;
                }
            } else {
                current = current.next;
            }

            displayProcesses();
        }

        double totalWaiting = 0, totalTurnaround = 0;
        System.out.println("\nFinal Process Statistics:");
        for (Process p : allProcesses) {
            System.out.println("PID: " + p.pid + ", Waiting Time: " + p.waitingTime + ", Turnaround Time: " + p.turnaroundTime);
            totalWaiting += p.waitingTime;
            totalTurnaround += p.turnaroundTime;
        }

        System.out.println("Average Waiting Time: " + (totalWaiting / allProcesses.size()));
        System.out.println("Average Turnaround Time: " + (totalTurnaround / allProcesses.size()));
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in queue.");
            return;
        }

        Process temp = head;
        System.out.print("Current Process Queue: ");
        do {
            System.out.print("[PID: " + temp.pid + ", Remaining: " + temp.remainingTime + "] -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(back to head)");
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter PID, Burst Time, and Priority for process " + (i + 1) + ": ");
            int pid = sc.nextInt();
            int bt = sc.nextInt();
            int pr = sc.nextInt();
            scheduler.addProcess(pid, bt, pr);
        }

        System.out.print("Enter time quantum: ");
        int tq = sc.nextInt();

        scheduler.simulate(tq);
    }
}
