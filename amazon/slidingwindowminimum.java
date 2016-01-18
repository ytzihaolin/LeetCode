 public  void sl(int[] a, int k) {
      ArrayList<Integer> res=new ArrayList<Integer>();
      Deque<Integer> q=new ArrayDeque<Integer>();
      for(int i=0;i<a.length;i++){
          while(!q.isEmpty()&&i-q.peek()>=k) q.poll();
          while(!q.isEmpty()&&a[i]<=a[q.peekLast()]) q.pollLast();
          q.offer(i);
          if(i>=k-1) res.add(a[q.peek()]);
      }

      for(int i:res) System.out.println(i);
  }