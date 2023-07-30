package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int N = 1000;
        int Limits = 128000;
        int M = 10000;
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new  AList<>();
        while (N <= Limits){
            SLList<Integer> a = new SLList<>();
            for (int i = 1; i <= N; i++){
                a.addLast(i);
            }
            Stopwatch sw = new Stopwatch();
            for (int i = 1; i <= M; i++){
                a.getLast();
            }
            double TimesInSeconds = sw.elapsedTime();
            Ns.addLast(N);
            opCounts.addLast(M);
            times.addLast(TimesInSeconds);
            N *= 2;
        }
        System.out.println("Timing table for getLast");
        printTimingTable(Ns, times, opCounts);
    }

}
