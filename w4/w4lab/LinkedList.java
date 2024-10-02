import java.util.Iterator; // defines the Iterator interface

public class LinkedList<T> {
	public class Node {
		private Node next;
		private T data;

		public Node(T item) {
			this.next = null;
			this.data = item;
		}
		public T getData() { return this.data; }
		public void setNext(Node n) { this.next = n; }
		public Node getNext() { return this.next; }
		public String toString() { return "[" + this.data + "] ->"; }
	}
	private Node head;
	private int size;

	public LinkedList() {
		this.head = null;
		this.size = 0;
	}
	public int size() { return this.size; }
	public void clear() {
		this.head = null;
		this.size = 0;
	}
	public boolean isEmpty() { return (this.head == null && this.size == 0); }
	public String toString() {
		if (isEmpty())
			return null;
		String str = "";
		Node curr = this.head;
		for (int index = 0; index < this.size - 1; index++) {
			str += curr;
			curr = curr.getNext();
		}
		return str;
	}
	public void add(T item) {
		Node nHead = new Node(item);
		nHead.setNext(this.head);
		this.head = nHead;
		this.size++;
	}
	public void add(int index, T item) {
		if (index < 0 || index > this.size)
			return;
		if (index == 0) {
			add(item);
			return;
		}
		Node currentNode = this.head;
		Node newNode = new Node(item);
		int currentIndex = 0;
		for (currentIndex = 0; currentIndex < index - 1; currentIndex++) {
			currentNode = currentNode.getNext();
		}
		newNode.setNext(currentNode.getNext());
		currentNode.setNext(newNode);
		this.size++;
	}
	public boolean contains(Object o) {
		Node currentNode = this.head;
		while (currentNode != null) {
            T item = currentNode.getData();
            if (item.equals(o))
                return true;
			currentNode = currentNode.getNext();
		}
		return false;
	}
	public T get(int index) {
		if (index > this.size)
			return null;
		Node currentNode = this.head;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		return currentNode.getData();
	}
    public T remove() {
        Node head = new Node(this.head);
    }
}

// not al
