import java.util.Iterator; // defines the Iterator interface

public class LinkedList<T> implements Iterable<T> {
	public static class Node<T> {
		private Node<T> next;
		private T data;

		public Node(T item) {
			this.next = null;
			this.data = item;
		}
		public T getData() { return this.data; }
		public void setNext(Node<T> n) { this.next = n; }
		public Node<T> getNext() { return this.next; }
		public Node<T> getNext(int index) {
			if (index < 0 || this == null)
				return null;
			Node<T> current = this;
			for (int i = 0; i < index; i++) {
				current = current.getNext();
				if (current == null)
					return null;
			}
			return current;
		}
		public String toString() { return "[" + this.data + "] ->"; }
	}
	private class LLIterator implements Iterator<T> {

		Node<T> head;

		public LLIterator(Node<T> head) { this.head = head; }
		public boolean hasNext() { return this.head != null; }
		public T next() {
			if (!hasNext())
				return null;
			T data = head.getData();
			head = head.getNext();
			return data;
		}
		public void remove() {}
	}
	private Node<T> head;
	private int size;

	public LinkedList() {
		this.head = null;
		this.size = 0;
	}
	public Iterator<T> iterator() { return new LLIterator(this.head); }
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
		Node<T> curr = this.head;
		while (curr != null) {
			str += curr;
			curr = curr.getNext();
		}
		return str;
	}
	public void add(T item) {
		Node<T> nHead = new Node<T>(item);
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
		Node<T> curr = this.head;
		Node<T> newNode = new Node<T>(item);
		for (int iterator = 0; iterator < iterator - 1; iterator++)
			curr = curr.getNext();
		newNode.setNext(curr.getNext());
		curr.setNext(newNode);
		this.size++;
	}
	public boolean contains(Object o) {
		Node<T> current = this.head;
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
        if (index == 0)
            return remove();
		Node<T> current = this.head.getNext(index - 1);
		T data = current.getNext().getData();
		current.setNext(current.getNext(2));
		return data;
	}

	public boolean equals(Object o) {
		if (!(o instanceof LinkedList)) {
			return false;
		}
		@SuppressWarnings("unchecked") LinkedList<T> oButLinkedList = (LinkedList<T>)o;

		Node<T> thisCurr = this.head;
		Node<T> oCurr = oButLinkedList.head;
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
		list.add(1);
		list.add(2);
        list.add(3);
	}
}
