
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
