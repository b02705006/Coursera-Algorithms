import java.util.Iterator;
import java.util.NoSuchElementException;

//import edu.princeton.cs.algs4.StdRandom;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int size = 0;
    
    private class Node{
        Item item;
        Node next;
        Node prev;
    }
    
    public Deque(){};
    
    public boolean isEmpty(){
        return this.size == 0;
    }
    
    public int size(){
        return this.size;
    }
    
    public void addFirst(Item item){
        if(item == null){throw new NullPointerException();}
        else{
            Node newNode = new Node();
            newNode.item = item;
            if(this.size == 0){
                this.first = newNode;
                this.last = newNode;
            }
            else{
                Node tmpNode = this.first;
                this.first = newNode;
                newNode.next = tmpNode;
                tmpNode.prev = newNode;
            }
            this.size++;
        }
    }
    
    public void addLast(Item item){
        if(item == null){throw new NullPointerException();}
        else{
            Node newNode = new Node();
            newNode.item = item;
            if(this.size == 0){
                this.first = newNode;
                this.last = newNode;
            }
            else{
                Node tmpNode = this.last;
                this.last = newNode;
                newNode.prev = tmpNode;
                tmpNode.next = newNode;
            }
            this.size++;
        }
    }
    
    public Item removeFirst(){
        if(size == 0){throw new NoSuchElementException();}
        else{
            Item returnItem = this.first.item;
            this.first = this.first.next;
            if(size == 1){
                this.last = null;
            }
            else{
                this.first.prev = null;
            }
            size--;
            return returnItem;
        }
    }
    public Item removeLast(){
        if(size == 0){throw new NoSuchElementException();}
        else{
            Item returnItem = this.last.item;
            this.last = this.last.prev;
            if(size == 1){
                this.first = null;
            }
            else{
                this.last.next = null;
            }
            size--;
            return returnItem;
        }
    }
    public Iterator<Item> iterator() {
        return new ListIterator();
    }	
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            if(!hasNext()){throw new NoSuchElementException();}
            else{
                Item returnItem = current.item;
                current = current.next;
                return returnItem;
            }
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {
        /* Testing
        Deque<Integer> myDeck = new Deque<>();
        int a,b,c,d,e;
        a = 1;
        b = 2;
        c = 3;
        d = 4;
        e = 5;
        myDeck.addFirst(a);
        //myDeck.removeFirst();
        //myDeck.removeFirst();
        myDeck.addFirst(b);
        myDeck.addFirst(c);
        myDeck.addLast(d);
        myDeck.addLast(e);
        myDeck.removeFirst();
        myDeck.removeLast();
        Iterator<Integer> myIter = myDeck.iterator();
        //System.out.println(myIter.next());
        while(myIter.hasNext()){
            System.out.print(myIter.next());
        }
        //System.out.println("Hello");
        */
    }
}
