import java.util.ArrayList;
import java.util.List;

public class RecursiveLinkedList {

	class Node {
		int i;

		public Node(int i) {
			this.i = i;
		}

		Node next;
	}

	public void create(int count) {
		List<Node> nlist = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			nlist.add(new Node(i));
		}
		for (int i = 0; i < nlist.size() - 1; i++) {
			Node node = nlist.get(i);
			node.next = nlist.get(i + 1);
		}

		Node node = nlist.get(0);
		while (node.next != null) {
			System.out.print(node.i + "->");
			node = node.next;
		}
		System.out.println(node.i);
		System.out.println("---------------------");
		System.out.println();
		reverse(nlist.get(0));

	}

	private void reverse(Node node) {
		if (node == null)
			return;
		Node rest = node.next;
		if (rest == null)
			return;
		reverse(node.next);
		System.out.println(rest.i + " b " + node.i);
		node.next.next = node;
		node.next = null;
		node = rest;

		// rest.next = node;
		System.out.println(rest.i + " " + node.i);

		// rest.next.next = null;
		// while (node.next != null) {
		// System.out.print(node.i + "->");
		// node = node.next;
		// }

	}

	public static void main(String[] args) {
		new RecursiveLinkedList().create(10);
	}
}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 2 Jun 2015
 */