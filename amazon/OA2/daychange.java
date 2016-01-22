 public int[] sl(int[] days, int n){
      if(days==null||days.length==0||n<=0) return days;
      
      for(int k=0;k<n;k++){
        int pre=days[0];
        for(int i=0;i<days.length;i++){
          if(i==0){
            days[i]=0^days[i+1];
          }else if(i==days.length-1){
            days[i]=pre^0;
          }else{
            int temp=days[i];
            days[i]=pre^days[i+1];
            pre=temp;
          }
        }
      }
      return days;
    }