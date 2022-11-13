package uaslp.objetos.list.LinkedList;

import uaslp.objetos.list.Iterator;
import uaslp.objetos.list.List;

public class LinkedList implements List {
    private Node head;
    private Node tail;
    private int size;

    public LinkedListIterator getIterator() {
        return new LinkedListIterator();
    }

    public void addAtTail(String data) {
        Node node = new Node(data);

        node.setPrevious(tail);
        tail = node;

        if(head == null) {
            head = node;
        } else {
            node.getPrevious().setNext(node);
        }
        size++;
    }

    public void addAtFront(String data) {
        Node node = new Node(data);
        node.setNext(head);
        head = node;

        if(tail == null) {
            tail = node;
        }else {
            node.getNext().setPrevious(node);
        }
        size++;
    }

    private Node findNodeByIndex(int index) {
        Node iteratorNode = head;
        int indexIteratorNode = 0;

        while(indexIteratorNode < index) {
            iteratorNode = iteratorNode.getNext();
            indexIteratorNode++;
        }
        return iteratorNode;
    }

    public boolean remove(int indexToRemove) {

        if(indexToRemove < 0 || indexToRemove >= size) {
            return false;
        }

        if(size == 1) {
            head = null;
            tail = null;
        } else if(indexToRemove == 0) {
            head = head.getNext();
            head.setPrevious(null);
        } else if(indexToRemove == size -1) {
            tail = tail.getPrevious();
            tail.setNext(null);
        }else {
            Node iteratorNode = findNodeByIndex(indexToRemove);
            iteratorNode.getPrevious().setNext(iteratorNode.getNext());
            iteratorNode.getNext().setPrevious(iteratorNode.getPrevious());
        }

        size--;
        return true;
    }

    public boolean setAt(int index, String data) {
        if(index < 0 || index >= size) {
            return false;
        }
        Node node = findNodeByIndex(index);
        node.setData(data);
        return true;
    }

    public int getSize() {
        return size;
    }

    public void removeAll() {
        head = null;
        tail = null;
        size = 0;
    }

    public String getAt(int index){
        if(index < 0 || index >= size){
            return null;
        }
        Node node = findNodeByIndex(index);
        return node.getData();
    }

    public void removeAllWithValue(String value){
        Node current = head;

        while(current != null){
            if(current.data.equals(value)){
                if(current == head){
                    head = current.next;
                    if(head == null){
                        tail = null;
                    }else{
                        head.previous = null;
                    }
                }else{
                    current.previous.next = current.next;
                    if (current == tail){
                        tail = current.previous;
                    }else{
                        current.next.previous = current.previous;
                    }
                }
            }
            current = current.next;
        }
    }

    private static class Node {
        Node next;
        Node previous;
        String data;

        public Node(String data) {
            this.data = data;
        }
        public void setNext(Node next) {
            this.next = next;
        }
        public void setPrevious(Node previous) {
            this.previous = previous;
        }
        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }
        public Node getPrevious() {
            return previous;
        }
        public String getData() {
            return data;
        }
    }

    public class LinkedListIterator implements Iterator {
        private Node current;

        public LinkedListIterator() {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public String next() {
            String data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
