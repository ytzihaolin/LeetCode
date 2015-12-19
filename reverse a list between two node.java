
//reverse the list between nodes begin and end


public ListNode reverse(ListNode begin, ListNode end){
    ListNode curr = begin.next;
    ListNode next, first;
    ListNode prev = begin;
    first = curr;
    while (curr!=end){
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    begin.next = prev;
    first.next = curr;
    return first;//first is the last node in the reversed list, begin and end is two edge node.
}