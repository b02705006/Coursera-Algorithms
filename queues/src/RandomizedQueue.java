import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // Create array
    private Item[] rndQueue;
    // Integer to store the current size of the array
    private int size;
    // Integer to store the current maximum capacity of the array
    private int cap;
    // Boolean value to decide upgrade(1) or downgrade(0) of capacity
    private boolean capChange; 
    
    @SuppressWarnings("unchecked")
    public RandomizedQueue(){
        // Initialize size to 0, capacity to 1 and the size of the randomized queue to the capacity
        this.size = 0;
        this.cap = 1;
        this.capChange = true;
        this.rndQueue = (Item[]) new Object [cap];
    };
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public int size(){
        return size;
    }
    
    public void enqueue(Item item){
        // Throw null pointer exception if client attempts to add null item
        if(item == null){throw new NullPointerException();}
        else{
            // Upgrade the capacity if size is larger than capacity
            if(size + 1 > cap){
                capChange = true;
                capUpdate(capChange);
            }
            // Add item to randomized queue
            rndQueue[size] = item;
            // Increase the size
            size++;
            // Can write as rndQueue[size++] = item;
        }
    }
    
    public Item dequeue(){
        // Throw NoSuchElementException if queue is empty
        if(isEmpty()){throw new NoSuchElementException();}
        else{
            // Pick a random number(index) from 0 to size - 1
            int rand = StdRandom.uniform(size);
            // Save the selected item
            Item returnItem = rndQueue[rand];
            // Decrease the size by one
            size--;
            // Replace the selected item by the item from the final index
            rndQueue[rand] = rndQueue[size];
            // Delete the final item;
            rndQueue[size] = null;
            if(size * 4 < cap){
                capChange = false;
                capUpdate(capChange);
            }
            return returnItem;
        }
    }
    
    private void capUpdate(boolean capChange){
        int newCap = 0;
        // Set new capacity to cap * 2 if capChange is true, cap / 2 otherwise
        newCap = capChange ? cap * 2 : cap / 2;
        //System.out.print("new capacity = " + newCap);
        @SuppressWarnings("unchecked")
        Item[] newQueue = (Item[]) new Object[newCap];
        for(int i = 0; i < cap; i++){
            newQueue[i] = rndQueue[i];
        }
        cap = newCap;
        rndQueue = newQueue;
        
    }
    
    public Item sample(){
        
        // Pick a random number(index) from 0 to size - 1
        int rand = StdRandom.uniform(size);
        // Save the selected item
        Item returnItem = rndQueue[rand];
        return returnItem;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }   
    private class ListIterator implements Iterator<Item>{
        // Integer to keep track of currentIndex
        private int current;
        // New integer array the size of "size" (to randomize access order)
        private int[] shuffledIndices;;
        private ListIterator(){
            current = 0;
            shuffledIndices = new int [size];
            // Initialize the integer array
            for(int i = 0; i < size; i++){
                shuffledIndices[i] = i;
            }
            StdRandom.shuffle(shuffledIndices);
        }
        
        public boolean hasNext(){
            return current < size;
        }
        public Item next(){
            if(!hasNext()){throw new NoSuchElementException();}
            else{
                if (current >= size || size == 0) throw new java.util.NoSuchElementException();
                return rndQueue[shuffledIndices[current++]];
            }
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {
        /* Testing
        RandomizedQueue<Integer> myQueue = new RandomizedQueue<>();
        int a,b,c,d,e;
        a = 1;
        b = 2;
        c = 3;
        d = 4;
        e = 5;
        myQueue.enqueue(a);
        //myQueue.removeFirst();
        //myQueue.removeFirst();
        myQueue.enqueue(b);
        myQueue.enqueue(c);
        myQueue.enqueue(d);
        myQueue.enqueue(e);
        //myQueue.removeFirst();
        //myQueue.removeLast();
        Iterator<Integer> myIter = myQueue.iterator();
        //System.out.println(myIter.next());
        while(myIter.hasNext()){
            System.out.print(myIter.next());
        }
        for(int i = 0; i < 20; i++){
            System.out.print(myQueue.sample());
        }
        
        //System.out.println("Hello");
        */
    }
}
