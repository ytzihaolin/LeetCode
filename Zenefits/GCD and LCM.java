GCD and LCM

int GCD(int a, int b){
  if (a < b) return GCD(b, a);
  return b == 0 ? a : GCD(b, a % b);
}           


int LCM(int a, int b){
   return a * b / GCD(a, b);
}





  public void sl(int[] a, int[] b) {
       int divisor=1;
       for(int t:b){
          divisor=LCM(divisor,t);
       }

       int sum=0;
       for(int i=0;i<a.length;i++){
          sum+=a[i]*(divisor/b[i]);
       }

       int gcd=GCD(sum,divisor);

       sum=sum/gcd;
       divisor=divisor/gcd;
       System.out.println(sum+"  "+divisor);
    }
