package deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * @author Betatily
 * @version 1.0
 */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private class TNode {
        public T item;
        public TNode prev;
        public TNode next;
        public TNode (T i, TNode m, TNode n) {
            item = i;
            prev = m;
            next = n;
        }
    }
    private TNode sentinel;
    public LinkedListDeque() {
        initializedSentinel();
    }
    public LinkedListDeque(T x) {
        initializedSentinel();
        addFirst(x);
    }
    private void initializedSentinel(){
        sentinel = new TNode (null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    private class LinkedListDequeIterator implements Iterator<T>{
        TNode currentNode = sentinel.next;
        @Override
        public boolean hasNext(){
            return !isEmpty() && currentNode != sentinel;
        }
        @Override
        public T next(){
            if (!hasNext()){
                return null;
            }
            T item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }
    }
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
    @Override
    public void printDeque(){
        for (TNode node = sentinel.next; node != sentinel; node = node.next){
            if (node.next == sentinel){
                System.out.println(node.item);
            }else {
                System.out.println(node.item + " ");
            }
        }
    }
    @Override
    public boolean equals(Object other){
        if (other == null){
            return false;
        }else if (other == this){
            return true;
        }else if (other instanceof Deque<?>){
            Deque castOther = (Deque) other;
            if (castOther.size() != this.size){
                return false;
            }
            for (int i = 0; i < size; i++){
                if (!castOther.get(i).equals(this.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    @Override
    public void addFirst(T item){
        size++;
        TNode newNode = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next =newNode;
    }
    @Override
    public void addLast(T item){
        size++;
        TNode newNode = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }
    @Override
    public int size(){
        return this.size;
    }
    @Override
    public T removeFirst(){
        if (sentinel.next == sentinel){
            return null;
        }else {
            size--;
            TNode removedNode = sentinel.next;
            TNode nextAfterRemoved = sentinel.next.next;
            nextAfterRemoved.prev = sentinel;
            sentinel.next = nextAfterRemoved;
            return removedNode.item;
        }
    }
    @Override
    public T removeLast(){
        if (sentinel.prev == sentinel){
            return null;
        }else {
            size--;
            TNode removedNode = sentinel.prev;
            TNode lastAfterremoved = sentinel.prev.prev;
            lastAfterremoved.next = sentinel;
            sentinel.prev = lastAfterremoved;
            return removedNode.item;
        }
    }
   @Override
    public T get (int index){
        if (index > size || index < 0){
            return null;
        }
        Iterator iterator = this.iterator();
        while (index > 0){
           index--;
           iterator.next();
       }
        T item = (T) iterator.next();
        return item;
   }
    public T getRecursive(int index){
        if (index > size || index < 0){
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }
    private T getRecursiveHelper(int index, TNode node){
        if (index == 0){
            return node.item;
        } else {
            return getRecursiveHelper(index-1, node.next);
        }
    }
}
