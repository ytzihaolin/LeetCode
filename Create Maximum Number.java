Create Maximum Number

Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]



两个array列举每个array贡献的元素个数，然后merge两个结果到一起，最后比较所有可能的结果找到最大的


 	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        List<int[]> one=generateList(nums1,nums2,k);//give all possible result
        return compare(one,k);//find the maximum among all possible result;
    }
    
    public List<int[]> generateList(int[] n1,int[] n2,int k){
        List<int[]> res=new ArrayList<int[]>();
        if(n1==null||n2==null||k==0) return res;
        for(int i=0;i<=k;i++){//find out how many elements each array would contribute to the final result
            int[] a=helper(n1,i);
            int[] b=helper(n2,k-i);
            if(a!=null&&b!=null) res.add(merge(a,b));//merge two array into one and add it to the possible result list
        }
        return res;
    }
    
    public int[] helper(int[] arr,int k){//generate maximum number out of one array
        if(k==0) return new int[0];
        else if(k>arr.length) return null;
        else if(k==arr.length) return arr;
        
        int[] res=new int[k];
        int ind=0;
        for(int i=0;i<k;i++){
            int max=ind;
            for(int j=ind;j<=arr.length-(k-i);j++){//within the range which allow at least k-i numbers following, find the maximum
                if(arr[max]<arr[j]) max=j;
            }
            res[i]=arr[max];
            ind=max+1;
        }
        return res;
    }
    
    public int[] merge(int[] a,int[] b){//merge two array into one with maximum possible number
        int[] res=new int[a.length+b.length];
        int aind=0,bind=0;
        while(aind<a.length&&bind<b.length){
            if(a[aind]>b[bind]) res[aind+bind]=a[aind++];
            else if(a[aind]==b[bind]){//the only trick part is here
                int at=aind;
                int bt=bind;
                while(at<a.length&&bt<b.length&&a[at]==b[bt]){
                    at++;
                    bt++;
                }
                if(at<a.length&&bt<b.length){//both pointer didn't reach the end, means there's unequal character behind
                    if(a[at]<b[bt]) res[aind+bind]=b[bind++];
                    else res[aind+bind]=a[aind++];
                }else{
                    if(at==a.length) res[aind+bind]=b[bind++];
                    else if(bt==b.length) res[aind+bind]=a[aind++];
                }
            }
            else res[aind+bind]=b[bind++];
        }
        while(aind<a.length) res[aind+bind]=a[aind++];
        while(bind<b.length) res[aind+bind]=b[bind++];
        return res;
    }
    
    public int[] compare(List<int[]> list,int k){
        for(int i=0;i<k;i++){//position of the current number
            int max=list.get(0)[i];
            for(int j=0;j<list.size();j++){//find the maximum number for this position
                max=Math.max(list.get(j)[i],max);
            }

            for(int j=0;j<list.size();j++) {//remove all other result if it doesn't have the maximum number for this position
                if(list.get(j)[i]!=max) {
                    list.remove(j);
                    j--;
                }
                if(list.size()==1) break;
             }
        }
        return list.get(0);//the remaining int[] is the result we want;
    }
