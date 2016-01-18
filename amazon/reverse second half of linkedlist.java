reverse second half of linkedlist


public static JNode reorder1(JNode head) {
	if (head == null || head.next == null || head.next.next == null) {
		return head;
	}
	JNode fast = head.next;
	JNode slow = head;
	while (fast.next != null && fast.next.next != null) {
		fast = fast.next.next;
		slow = slow.next;
	}
	JNode pre = slow.next;
	JNode cur = pre.next;
	while (cur != null) {
		pre.next = cur.next;
		cur.next = slow.next;
		slow.next = cur;
		cur = pre.next;
	}
	return head;
}