Maximum Gap 

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.


public int maximumGap(int[] num) {
          if (num == null || num.length < 2)
            return 0;
        // get the max and min value of the array
        int min = num[0];
        int max = num[0];
        for (int i:num) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        // the minimum possibale gap, ceiling of the integer division
        int gap = (int)Math.ceil((double)(max - min)/(2*num.length-1));//get ceiling is very import! here we let number of buckets be 2*N since increase bucket number is a safe operation.
        if(gap==0) return 0;
        int[] bucketsMIN = new int[2*num.length]; // store the min value in that bucket
        int[] bucketsMAX = new int[2*num.length]; // store the max value in that bucket
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        for(int i:num){
            int ind=(i-min)/gap;
            bucketsMIN[ind]=Math.min(bucketsMIN[ind],i);
            bucketsMAX[ind]=Math.max(bucketsMAX[ind],i);
        }
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        
        for (int i = 0; i < 2*num.length ; i++) {
            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
                    // empty bucket
                    continue;
                // min value minus the previous value is the current gap
                maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
                // update previous bucket value
                previous = bucketsMAX[i];
        }
        // updata the final max value gap
        return maxGap;
 }