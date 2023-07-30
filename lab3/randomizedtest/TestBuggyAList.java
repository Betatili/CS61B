package randomizedtest;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> wrong = new BuggyAList<>();
        correct.addLast(4);
        correct.addLast(5);
        correct.addLast(6);

        wrong.addLast(4);
        wrong.addLast(5);
        wrong.addLast(6);

        assertEquals(correct.removeLast(), wrong.removeLast());
        assertEquals(correct.removeLast(), wrong.removeLast());
        assertEquals(correct.removeLast(), wrong.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> a = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                a.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int Lsize = L.size();
                int asize = a.size();
                assertEquals(Lsize, asize);
                System.out.println("L.size: " + Lsize + " a.size:" + asize);
            } else if ((L.size() > 0) & (a.size() > 0)){
                if (operationNumber == 2){
                    int LrandVal = L.getLast();
                    int arandVal = a.getLast();
                    assertEquals(LrandVal, arandVal);
                    System.out.println("L.getLast(" + LrandVal + ")"+ " a.getLast(" + arandVal + ")");
                } else {
                    int LrandVal = L.removeLast();
                    int arandVal = a.removeLast();
                    assertEquals(LrandVal, arandVal);
                    System.out.println("L.removeLast(" + LrandVal + ")" + " a.removeLast(" + arandVal + ")");
                }
            }
        }
    }
}