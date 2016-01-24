public static JNode reorder2(JNode head) {
    if (head == null || head.next == null || head.next.next ==
    null) {
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


//my style
  public CNode Solution(CNode head) {
        if (head == null || head.next == null || head.next.next ==null) {
            return head;
        }
        CNode fast = head.next;
        CNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        CNode firstTail = slow;
        CNode cur=slow.next;
        CNode newHead=null;
        while (cur != null) {
            CNode next = cur.next;
            cur.next=newHead;
            newHead=cur;
            cur=next;
        }
        firstTail.next=newHead;
        return head;
    }