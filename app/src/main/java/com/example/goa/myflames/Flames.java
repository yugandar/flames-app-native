package com.example.goa.myflames;
import java.util.Iterator;

public class Flames {

	private boolean _debug = false;
	private final CircularLinkedList<String> flames = new CircularLinkedList<String>();

	public Flames() {
		flames.add("FRIENDS");
		flames.add("LOVE");
		flames.add("AFFECTION");
		flames.add("MARRIAGE");
		flames.add("ENEMY");
		flames.add("SIBLING");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			// return;
		}
		Flames flames = new Flames();
		String result = flames.evaluateFlames("naveen", "naveen");
		System.out.println(result);
	}

	public String evaluateFlames(String name1, String name2) {
		String result;
		if (name1 != null && name2 != null && name1.length() > 0 && name2.length() > 0) {
			name1 = name1.trim().toLowerCase();
			name2 = name2.trim().toLowerCase();
			if (name1.equals(name2)) {
				result = "IDENTICAL";
			} else {
				int rem;
				if (name1.length() <= name2.length()) {
					rem = getRemainder(name1, name2);
				} else {
					rem = getRemainder(name2, name1);
				}
				result = getFlameType1(rem);
			}
		} else {
			result = "<  Enter valid names  > ";
		}
		return result;
	}

	private String getFlameType1(int step) {
		String flameType = "";
		debug(flames);
		Iterator<String> flameIterator = flames.iterator();
		int counter = 0;
		while (flameIterator.hasNext() && flames.size() != 1) {
			flameIterator.next();
			counter++;
			if (counter == step) {
				flameIterator.remove();
				counter = 0;
				debug(flames);
			}
		}
		flameIterator = flames.iterator();
		if (flameIterator.hasNext()) {
			flameType = flameIterator.next();
		}
		return flameType;
	}

	private void debug(Object msg) {
		if (_debug){
			System.out.println(msg != null ? msg.toString() : msg);
		}
	}

	private int getRemainder(String op1, String op2) {
		int remainder = 0;
		int l = op1.length();
		char c;
		for (int index = 0; index < l; index++) {
			c = op1.charAt(index);
			if (op2.indexOf(c) != -1) {
				op2 = op2.substring(0, op2.indexOf(c)) + op2.substring(op2.indexOf(c) + 1, op2.length());
			} else {
				remainder++;
			}
		}
		remainder += op2.length();
		return remainder;
	}
}
