import java.util.Iterator; // defines the Iterator interface

public class LinkedList<T> implements Iterable<T>, Queue<T> {
	public static class Node<T> {
		private Node<T> next;
		private T data;

		public Node(T item) {
			this.next = null;
			this.data = item;
		}
		public Node(T item, Node<T> next) {
			this.next = next;
			this.data = item;
		}
		public T getData() { return this.data; }
		public void setNext(Node<T> n) { this.next = n; }
		public Node<T> getNext() { return this.next; }
		public Node<T> getNext(int index) {
			if (index < 0 || this == null)
				return null;
			if (index == 0)
				return this;
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
	private Node<T> tail;
	private int size;

	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	public Iterator<T> iterator() { return new LLIterator(this.head); }
	public int size() { return this.size; }
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	public boolean isEmpty() { return (this.head == null && this.tail == null && this.size == 0); }
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
		Node<T> newNode = new Node<T>(item, head);
		this.head = newNode;
		this.tail = (this.tail == null ? newNode : this.tail);
		this.size++;
	}
	public void add(int index, T item) {
		if (index < 0 || index > this.size)
			return;
		if (index == 0) {
			add(item);
			return;
		}
		if (index == this.size - 1) {
			addLast(item);
			return;
		}
		Node<T> curr = this.head;
		Node<T> newNode = new Node<T>(item);
		for (int iterator = 0; iterator < index - 1; iterator++)
			curr = curr.getNext();
		newNode.setNext(curr.getNext());
		curr.setNext(newNode);
		this.size++;
	}

	public void addFirst(T item) {
		this.head = new Node<>(item, this.head);
		size++;
	}
	public void addLast(T item) {
		Node<T> tail_ = new Node<T>(item);
		if (this.tail == null) {
			this.head = this.tail = tail_;
			return;
		}
		this.tail.next = tail_;
		this.tail = tail_;
		size++;
	}
	public T getLast() {
		if (this.tail == null)
			return null;
		return this.tail.getData();
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
		if (index == 0)
			return this.head.getData();
		if (index == this.size - 1)
			return getLast();
		return this.head.getNext(index).getData();
	}
	public T remove() {
		if (this.head == null) {
			return null; // or throw an exception if preferred
		}
		T data = this.head.getData();
		this.head = this.head.getNext();
		if (this.head == null) {
			this.tail = null; // correctly set tail to null if the list is now empty
		}
		size--;
		return data;
	}
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		if (index == 0) {
			return remove();
		}
		Node<T> current = this.head;
		for (int i = 0; i < index - 1; i++) {
			current = current.getNext();
		}
		T data = current.getNext().getData();
		current.setNext(current.getNext().getNext());
		size--;
		return data;
	}
	public T removeLast() { return this.remove(this.size - 1); }

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

	public void offer(T item) { addLast(item); }
	public T peek() { return get(0); }
	public T poll() { return remove(); }
}
