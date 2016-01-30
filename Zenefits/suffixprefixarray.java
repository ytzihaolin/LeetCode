package zenefits;
import java.util.*;
. From 1point 3acres bbspublic class PrefixSurfixArray {

        public static long getPrefixSurfixPair(int [] a){
                if(a==null||a.length==0) return 0;
                int[] leftSetCount = new int[a.length];
                int[] rightSetCount = new int[a.length];
                int left=0,right=0;
                long result=0;
                HashSet<Integer> diff = new HashSet<Integer> ();
                HashSet<Integer> leftSet = new HashSet<Integer> ();
                HashSet<Integer> rightSet = new HashSet<Integer> ();
                HashSet<Integer> pairIndex = new HashSet<Integer> ();. from: 1point3acres.com/bbs 
                while(left<a.length||right<a.length){
                        int temp=0;
                        if(leftSet.size()<=rightSet.size()&&left<a.length){
                                temp=a[left];
                                if(!leftSet.contains(temp)){
                                        leftSet.add(temp);
                                        leftSetCount[leftSet.size()-1]=1;
                                        if(diff.contains(temp)){
                                                diff.remove(temp);.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
                                        }else{
                                                diff.add(temp); 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
                                        }                        
                                }else{. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
                                        leftSetCount[leftSet.size()-1]++;        
                                }                        
                                left++;
                        }else {. from: 1point3acres.com/bbs 
                                temp=a[a.length-1-right];
                                if(!rightSet.contains(temp)){
                                        rightSet.add(temp);
                                        rightSetCount[leftSet.size()-1]=1;
                                        if(diff.contains(temp)){
                                                diff.remove(temp);
                                        }else{
                                                diff.add(temp);
                                        } 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
                                }else{
                                        rightSetCount[rightSet.size()-1]++;-google 1point3acres
                                }                                
                                right++;
                        }
                        if(diff.isEmpty()&&!pairIndex.contains(leftSet.size())){
                                pairIndex.add(leftSet.size());
                        }
                }        
                for(Integer i: pairIndex){
                        result+=leftSetCount[i-1]*rightSetCount[i-1];
                }-google 1point3acres
                return result;
        }
        public static void main(String[] args) {
                // TODO Auto-generated method stub
                int[][] tests={
                                {1,1,1},
                                {1,2, 3, 1, 2},
                                {1,2,3,4,5}
                };
                for(int[] test:tests){
                        System.out.println("The number of prefix-surfix pair for array " +Arrays.toString(test)+" is "+getPrefixSurfixPair(test));. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
                }
        }
.1point3acres缃�
}