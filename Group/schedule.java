import java.util.*;
import java.io.*;


class schedule{
	class wapper{
		scheduleRequest originalObject;
		boolean isEnd;
		wapper(scheduleRequest _originalObject, boolean _isEnd){
			this.originalObject=_originalObject;
			this.isEnd=_isEnd;
		}
	}

	HashMap<String, TreeMap<Integer,List<scheduleRequest>>> scheduleList;
	public void initializeSchedule(List<scheduleRequest> scheduleList) {
		for(int i=0;i<n;i++) scheduleList.put(i,new TreeMap<Integer,List<wapper>>());

		for(scheduleRequest s:scheduleList){
			if(scheduleList.get(s.location).containsKey(s.startTime))
				scheduleList.get(s.location).get(s.startTime).add(new wapper(s,true));
			else{
				List<scheduleRequest> temp=new ArrayList<scheduleRequest>();
				temp.add(new wapper(s,true));
				scheduleList.get(s.location).put(s.startTime,temp);
			}

			if(scheduleList.get(s.location).containsKey(s.startTime))
				scheduleList.get(s.location).get(s.startTime).add(new wapper(s.false));
			else{
				List<scheduleRequest> temp=new ArrayList<scheduleRequest>();
				temp.add(new wapper(s,false));
				scheduleList.get(s.location).put(s.endTime,temp);
			}
		}
	}



	public void schedule(int location){
		for(Entry<Integer,List<wapper>> entry:scheduleList.get(location).entrySet()){
			for(wapper sW:entry.getValue()){//先处理end，再处理start，以防start马上接end的情况
				if(isEnd&&currentSet.containsKey(sW.id)){

				}
			}

			for(wapper sW:entry.getValue()){
				if(!isEnd&&currentSet.containsKey(sW.id)){
					if(currentSet.containsKey(sW.oringinalObject.id)){

					}else if(currentSet.size()>=3){

					}else{
						
					}
				}
			}

		}
	}
}
