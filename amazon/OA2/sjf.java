PriorityQueue<int[]> processpq = new PriorityQueue<int[]>(req.length, new Comparator<int[]>() {
        @Override
        public int compare(int[] p1, int[] p2) {
        }
});

class process{
    int rtime;
    int ptime;
    process(int a, int b){
      this.rtime=a;
      this.ptime=b;
    }
  }

    public double sl(int[] req, int[] dur) {
       if(req==null||req.length==0||dur==null||req.length!=dur.length) return 0.0;
       int len=req.length;
       PriorityQueue<process> pq=new PriorityQueue<process>(len,new Comparator<process>(){
          @Override
          public int compare(process a, process b){
            if(a.ptime==b.ptime) return a.rtime-b.rtime;
            else return a.ptime-b.ptime;
          }
       });
       int currentTime=0;
       int waitingTimeSum=0;
       int index=0;
       while(!pq.isEmpty()||index<len){
          if(!pq.isEmpty()){
            process temp=pq.poll();
            waitingTimeSum+=currentTime-temp.rtime;
            currentTime+=temp.ptime;
            while(index<len && currentTime >= req[index]) 
              pq.offer(new process(req[index],dur[index++]));
          }else{
            pq.offer(new process(req[index],dur[index]));
            currentTime=req[index++];
          }
       }
       return (double) waitingTimeSum/len;
    }