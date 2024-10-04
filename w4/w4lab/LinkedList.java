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
	public LinkedList(Object o) {}
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
		Node curr = this.head;
		Node newNode = new Node(item);
		for (int iterator = 0; iterator < iterator - 1; iterator++)
			curr = curr.getNext();
		newNode.setNext(curr.getNext());
		curr.setNext(newNode);
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

	public boolean equals(Object o) {
		if (!(o instanceof LinkedList)) {
			return false;
		}
		@SuppressWarnings("unchecked") LinkedList<T> oButLinkedList = (LinkedList<T>)o;

		Node thisCurr = this.head;
		Node oCurr = oButLinkedList.head;
		while (thisCurr != null && oCurr != null) {
			if (thisCurr.getData() != oCurr.getData()) {
				return false;
			}
			thisCurr = thisCurr.getNext();
			oCurr = oCurr.getNext();
		}
		return true;
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		LinkedList<Integer> list2 = new LinkedList<>();
		list.add(1);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(5);
		list2.add(1);
		list2.add(1);
		list2.add(2);
		list2.add(3);
		list2.add(5);
        list2.add(5);
		System.out.println(list);
		System.out.println(list2);
		System.out.println(list.equals(list2));
	}
}
