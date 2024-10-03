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
		public Node getNext(int index) {
			if (index < 0 || this == null)
				return null;
			Node current = this;
			for (int i = 0; i < index; i++) {
				current = current.getNext();
				if (current == null)
					return null;
			}
			return current;
		}
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
		while (curr != null) {
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
		Node current = this.head;
		Node newNode = new Node(item);
		int currentIndex = 0;
		for (currentIndex = 0; currentIndex < index - 1; currentIndex++) {
			current = current.getNext();
		}
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		this.size++;
	}
	public boolean contains(Object o) {
		Node current = this.head;
		while (current != null) {
			T item = current.getData();
			if (item.equals(o))
				return true;
			current = current.getNext();
		}
		return false;
	}
	public T get(int index) {
		if (index < 0 || index >= this.size)
			return null;
		return this.head.getNext(index).getData();
	}
	public T remove() {
		T data = this.head.getData();
		this.head = this.head.getNext();
		return data;
	}
	public T remove(int index) {
        Node current = this.head.getNext(index - 1);
		T data = current.getNext().getData();
		current.setNext(current.getNext(2));
		return data;
	}
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(3);
        list.add(5);
        list.add(7);
		System.out.println(list);
        System.out.println(list.remove(2));
        System.out.println(list);
	}
}
