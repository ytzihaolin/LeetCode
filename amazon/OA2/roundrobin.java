

public static float waitingTimeRobin(int[] arrival, int[] run, int q) {
                if (arrival == null || run == null || arrival.length != run.length) {
                        return 0;
                }
                int waitTime = 0;
                int curTime = 0;
                int index = 0;
                int len = run.length;
                LinkedList<Proccess> queue = new LinkedList<Proccess>();
                while (!queue.isEmpty() || index < len) {
                        if (!queue.isEmpty()) {
                                Proccess curProccess = queue.poll();
                                waitTime += curTime - curProccess.arrTime;
                                curTime += Math.min(q, curProccess.runTime);
                                while (index < len && arrival[index] <= curTime) {
                                        queue.offer(new Proccess(arrival[index], run[index]));
                                        index++;
                                }
                                if (curProccess.runTime > q) {
                                        queue.offer(new Proccess(curTime, curProccess.runTime - q));
                                }
                        } else {
                                queue.offer(new Proccess(arrival[index], run[index]));
                                curTime = arrival[index++];
                        }
                }
                return (float) waitTime / len;
        }

        private static class Proccess {
                int arrTime;
                int runTime;

                public Proccess(int arrTime, int runTime) {
                        this.arrTime = arrTime;
                        this.runTime = runTime;
                }
        }
注意arrival[index] <= curTime 不是  < ，否则test case过不了