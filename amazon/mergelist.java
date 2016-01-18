public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res=new ListNode(0);
        ListNode cursor=res;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                res.next=l1;
                l1=l1.next;
                res=res.next;
            }else{
                res.next=l2;
                l2=l2.next;
                res=res.next;
            }
        }
        ListNode temp=new ListNode(0);
        if(l1==null) temp=l2;
        else temp=l1;
        while(temp!=null){
            res.next=temp;
            temp=temp.next;
            res=res.next;
        }
        res.next=null;
        return cursor.next;
    }