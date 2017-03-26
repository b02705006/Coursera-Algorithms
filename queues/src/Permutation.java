import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        RandomizedQueue<String> myQueue = new RandomizedQueue<>();
        while(!StdIn.isEmpty()){myQueue.enqueue(StdIn.readString());}
        Iterator<String> myIter = myQueue.iterator();
        //System.out.println(myIter.next());
        for(int i = 0; i < num; i++){
            System.out.println(myIter.next());
        }
    }

}
