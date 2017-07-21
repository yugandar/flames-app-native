package com.example.goa.myflames;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<E> {

	// Head
	private Node head = null;

	// Current node. After which a new node will be added.
	private Node tail = null;

	// Counter
	private int nodeCounter = 0;

	public CircularLinkedList() {
		tail = head;
	}

	public void add(E value) {
		Node newNode = new Node(value);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		tail.next = head;
		nodeCounter++;
	}

	public int size() {
		return nodeCounter;
	}

	public boolean isEmpty() {
		return nodeCounter == 0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node t = head;
		int index = 0;
		while (index < nodeCounter) {
			if (builder.toString().length() == 0) {
				builder.append(t.toString());
			} else {
				builder.append(" -> ");
				builder.append(t.toString());
			}
			t = t.next;
			index++;
		}
		return builder.toString();
	}

	private class Node {
		private E value;
		private Node next;

		Node(E value) {
			this.value = value;
		}

		E getValue() {
			return value;
		}

		@Override
		public String toString() {
			return new StringBuilder().append("node(").append(value != null ? value.toString() : "").append(")")
					.toString();
		}
	}

	public Iterator<E> iterator() {
		return new CircularLinkedListIterator();
	}

	private class CircularLinkedListIterator implements Iterator<E> {
		private Node currentNode = null;
		boolean isNextCalled = true;


		@Override
		public boolean hasNext() {
			return nodeCounter > 0;
		}

		/**
		 * Returns the next element, if available. Invoking next() on an empty
		 * MyCircularLinkedList() will throw NoSuchElementException as with any
		 * Iterator.
		 */
		@Override
		public E next() {
			if (head == null) {
				throw new NoSuchElementException();
			}
			if (currentNode == null) {
				currentNode = head;
			} else {
				currentNode = currentNode.next;
			}
			E value = currentNode.getValue();
			isNextCalled = true;
			return value;
		}

		/**
		 * Removes the last element visited by the next() method. Invoking
		 * remove before the first invocation of next() thrown
		 * IllegalStateException as with any other Iterator.
		 * 
		 */
		@Override
		public void remove() {
			if (!isNextCalled) {
				throw new IllegalStateException();
			}
			Node t = currentNode;
			while (t.next != currentNode) {
				t = t.next;
			}
			t.next = currentNode.next;
			if (currentNode == head){
				head = t.next;
			}
			currentNode = t;
			isNextCalled = false;
			nodeCounter--;
		}
	}

	public static void main(String[] args) {
		CircularLinkedList<String> l = new CircularLinkedList<String>();
		l.add("F");
		l.add("L");
		l.add("A");
		l.add("M");
		l.add("E");
		l.add("S");
		System.out.println(l.toString());

		Iterator<String> iter = l.iterator();
		System.out.println(iter.next());
		iter.remove();
		System.out.println(l);
	}

}
