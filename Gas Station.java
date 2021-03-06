Gas Station

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas stations index if you can travel around the circuit once, otherwise return -1.

//关键是如果sum(gas)>sum(cost)，那么一定存在start point完成全程。
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank=0, start=0,total=0;
        for(int i=0;i<gas.length;i++){
            tank+=gas[i]-cost[i];
            total+=tank;
            if(tank<0) {
                start=i+1;
                tank=0;
            }
        }
        return total<0?-1:start;
    }
}