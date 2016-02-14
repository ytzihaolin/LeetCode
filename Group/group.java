import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class Schedule {
	/**
	 * wrapper for a shceduleObject used to get the listofmap
	 */
	class scheduleWrapper {
		boolean startEnd;
		public scheduleRequest originalObject;

		scheduleWrapper(scheduleRequest s, boolean se) {
			originalObject = s;
			startEnd = se;
		}
	}

	final int LocationNum = 20;
	Map<String, scheduleRequest> setOfContents;
	List<TreeMap<Integer, List<scheduleWrapper>>> listOfSchedule;
	List<scheduleRequest> result;
	List<scheduleRequest> abandon;

	public Schedule() {
		setOfContents = new HashMap<>();
		listOfSchedule = new ArrayList<>();
		result = new LinkedList<>();
		abandon = new LinkedList<>();
	}

	/**
	 * This function is used for initializing the list of map the map is used to
	 * store the schedule of locations one element in this list stands for a
	 * location.
	 */
	public void initializeSchedule(List<scheduleRequest> scheduleList) {// ÷ª”–’‚“ª∏ˆµÿ∑Ω“¿¿µ ‰»Î£¨µΩ ±∫Ú–Ë“™∏ƒ“ªœ¬°£
		for (int i = 0; i < LocationNum; i++) {
			listOfSchedule.add(new TreeMap<>());
		}
		for (scheduleRequest s : scheduleList) {
			if (listOfSchedule.get(s.location).containsKey(s.starttime)) {
				listOfSchedule.get(s.location).get(s.starttime).add(new scheduleWrapper(s, true));
			} else {
				List<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, true));
				listOfSchedule.get(s.location).put(s.starttime, newList);
			}
			if (listOfSchedule.get(s.location).containsKey(s.endtime)) {
				listOfSchedule.get(s.location).get(s.endtime).add(new scheduleWrapper(s, false));
			} else {
				List<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, false));
				listOfSchedule.get(s.location).put(s.endtime, newList);
			}
		}
	}

	/* µ±«∞∞Ê±æ‘›≤ªøº¬«µ›πÈ«ÛΩ‚ */
	public void schedule(int location) {
		for (Entry<Integer, List<scheduleWrapper>> entry : listOfSchedule.get(location).entrySet()) {
			for (scheduleWrapper sW : entry.getValue()) {
				if (!sW.startEnd && setOfContents.containsKey(sW.originalObject.id)) {
					//当前播放的广告结束了
					result.add(setOfContents.get(sW.originalObject.id));// ÷ª”–∆Ω∞≤Ω· ¯µƒ≤≈ª·º∆»ÎΩ·π˚
					setOfContents.remove(sW.originalObject.id);// ¥”set÷–»•µÙ
				}
			}
			/* ¥À¥¶ «Œ™¡ÀÕÍ≥…œ»≥ˆ∫ÛΩ¯ */
			for (scheduleWrapper sW : entry.getValue()) {
				if (sW.startEnd) {// 
					if (setOfContents.containsKey(sW.originalObject.id)) {//当前已有一个相同的广告正在播放
						if (calculateDuration(setOfContents.get(sW.originalObject.id)) < calculateDuration(
								sW.originalObject)) {// 选长的，同一个id现在对应两个object，一个在set里，一个是刚刚读到的
							abandon.add(setOfContents.get(sW.originalObject.id));// »Ù‘≠”–µƒvalueΩœ–°£¨…æ≥˝‘≠”–≤¢±£¡ÙµΩabandon÷–
							setOfContents.remove(sW.originalObject.id);
							setOfContents.put(sW.originalObject.id, sW.originalObject);// 原来存在的小就把原来的扔了换现在的
						} else
							abandon.add(sW.originalObject);// 否则直接扔
					} else if (setOfContents.size() >= 3) {//不能同时超过三个广告都候选
						String keyToRemove = findSmallest(setOfContents);// 选最小的时长扔
						if (calculateDuration(setOfContents.get(keyToRemove)) < calculateDuration(sW.originalObject)) {
							abandon.add(setOfContents.get(keyToRemove));// »Áπ˚◊Ó–°µƒƒ«∏ˆvalue–°”⁄µ±«∞“™≤Â»Îµƒ£¨
							setOfContents.remove(keyToRemove);// …æ≥˝◊Ó–°valueµƒ“ª∏ˆ
							setOfContents.put(sW.originalObject.id, sW.originalObject);// Ω´–¬¿¥µƒº”»Îset
						} else {
							abandon.add(sW.originalObject);// ∑Ò‘Ú÷±Ω”…·∆˙–¬¿¥µƒ°£
						}
					} else {
						setOfContents.put(sW.originalObject.id, sW.originalObject);//将遍历到的放到set里面，setofcontent指的是现在还没结束的广告，因为是treemap存放的所以挨个看
					}
				}
			}
		}
	}

	/**
	 * this function is used to find the element with smallest value in a set of
	 * scheduleObjects the function is called in schedule function
	 */
	public String findSmallest(Map<String, scheduleRequest> set2) {
		int smallest = Integer.MAX_VALUE;
		String resultName = null;
		for (Map.Entry<String, scheduleRequest> entry : set2.entrySet()) {
			if (calculateDuration(entry.getValue()) < smallest) {
				smallest = calculateDuration(entry.getValue());
				resultName = entry.getValue().id;
			}
		}
		return resultName;
	}

	/**
	 * this function is used to calculate a duration of a scheduleObject
	 */
	public int calculateDuration(scheduleRequest a) {
		return a.endtime - a.starttime;
	}
}

