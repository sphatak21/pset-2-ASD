import java.util.List;
import java.util.NoSuchElementException;
public class SimpleLinkedList {
    private Node first;
    private Node last;
    private int size;
    public SimpleLinkedList(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    public SimpleLinkedList(List<String> list){
        for(int i=0; i<list.size(); i++){
            add(i, list.get(i));
        }
        size=list.size();
    }
    public void add(int index, String s){
        if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        else if (index == 0) {
            addFirst(s);
        }
        else if (index == size) {
            addLast(s);
        }
        else {
            Node current = getNode(index);
            Node newNode = new Node(current.prev, s, current);
            current.prev.next = newNode;
            current.prev = newNode;
            size ++;
        }
    }
    public void addFirst(String s){
        Node current = first;
        Node newNode = new Node(null, s, current);
        first = newNode;
        if (current == null){
            last=newNode;
        }
        else {
            current.prev = newNode;
        }
        size ++;
    }
    public void addLast(String s) {
        Node current = last;
        Node newNode = new Node(current, s, null);

        last = newNode;
        if (current == null) {
            first = newNode;
        } else {
            current.next = newNode;
        }
        size++;
    }
    public void clear(){
        this.first=null;
        this.last=null;
        size=0;
    }
    public boolean contains(String s){
        if(indexOf(s)==-1) {
            return false;
        }
        return true;
    }
    public String get(int index){
        checkSize();
        Node current = getNode(index);
        return current.data;
    }
    public String getFirst(){
        checkSize();
        Node current = getNode(0);
        return current.data;
    }
    public String getLast(){
        checkSize();
        Node current = getNode(size-1);
        return current.data;
    }
    public int indexOf(String s){
        for(int i = 0; i<size; i++){
            Node current = getNode(i);
            if (current.data==s){
                return i;
            }
        }
        return -1;
    }
    public String remove(int index){
        checkIndex(index);
        if(index==0){
            String data = removeFirst();
            return data;
        }
        else if (index == size-1){
            String data = removeLast();
            return data;
        }
        else {
            Node current = getNode(index);
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
            return current.data;
        }
    }
    public boolean remove(String s){
        if(contains(s)==false){
            return false;
        }
        for(int i =0; i<size; i++){
            Node current = getNode(i);
            if(current.data == s){
                remove(i);
            }
        }
        return true;
    }
    public String removeFirst(){
        checkSize();
        Node current = first;
        String el = current.data;
        Node nextCurrent = first.next;
        current.next = null;
        nextCurrent.prev = null;
        first = nextCurrent;
        size--;
        return el;
    }
    public String removeLast(){
        checkSize();
        Node current = last;
        String el = current.data;
        Node nextCurrent = last.prev;
        current.prev = null; 
        nextCurrent.next = null;
        last = nextCurrent;
        size--;
        return el;
    }
    public String set(int index, String s){
        checkIndex(index);
        Node current = getNode(index);
        String el = current.data;
        current.data = s;
        return el;

    }
    public int size(){
        return size;
    }
    public String toString(){
        String mainString = "[";
        String addString;
        for(int i=0; i<size; i++){
            Node current = getNode(i);
            addString= current.data;
            if(i==size-1){
                mainString=mainString + addString;
            }
            else {
                mainString = mainString + addString + ", ";
            }
        }
        return mainString + "]";
    }
    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int count = 0;
        Node current = first;

        while (current != null) {
            if (count++ == index) {
                return current;
            }

            current = current.next;
        }
        return null;
    }
    private void checkSize() {
        if(size == 0) {
            throw new NoSuchElementException();
        }
    }
    private void checkIndex(int index){
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
