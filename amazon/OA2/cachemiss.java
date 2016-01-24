
    public int sl(int[] array, int size) {
      if (array == null)  return 0;
      List<Integer> cache = new LinkedList<Integer>();
      int count = 0;
      for (int i = 0; i < array.length; i++) {
        if (cache.contains(array[i])) {
          cache.remove(new Integer(array[i]));//very important, not remove index, but remove first occurance
          //of this object in the list;
        }
        else {
          count++;
          if (size == cache.size())
            cache.remove(0);
        }
        cache.add(array[i]);
      }
      return count;
  }


public int cal(int[] a, int t){
    if(a==null||a.length==0||t<=0) return 0;   
    ArrayList<Integer> list=new ArrayList<Integer>();
    int count=0;
    for(int n:a){
        int index=list.indexOf(n);
        if(index==-1) count++;
        else list.remove(index);
        list.add(n);
        if(list.size()>t) list.remove(0);
    }
    return count;
  }