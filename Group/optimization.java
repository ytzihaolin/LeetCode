package test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Optimization {
	class scheduleWrapper {
		boolean startEnd;
		public scheduleRequest originalObject;

		scheduleWrapper(scheduleRequest s, boolean se) {
			originalObject = s;
			startEnd = se;
		}
	}
	final int locationNum = 3;
	List<TreeMap<Integer, List<scheduleWrapper>>> listOfSchedule = new ArrayList<>();// ∫Õschedule“ª—˘µƒ“ª∏ˆmap”√¿¥º«¬º ±º‰¥¡
	List<TreeMap<Integer, Map<String, scheduleRequest>>> listofGaps = new ArrayList<>();
	List<Double> valueList = new ArrayList<>();
	Map<String, Integer> mapOfValue = new HashMap<>();

	public Optimization() {
		valueList.add(1.0);
		valueList.add(0.8);
		valueList.add(0.6);
		mapOfValue.put("1", 10);
		mapOfValue.put("2", 8);
		mapOfValue.put("3", 6);
	}// ’‚∏ˆ≤ø∑÷÷Æ∫Ûª·∏¯≥ˆ£¨À˘“‘≤ª”√∏ƒ

	/**
	 * This function is used for initializing the list of map the map is used to
	 * store the schedule of locations one element in this list stands for a
	 * location.
	 */
	public void initializeSchedule(List<scheduleRequest> scheduleList) {
		for (int i = 0; i < locationNum; i++) {
			listOfSchedule.add(new TreeMap<>());
		}
		for (scheduleRequest s : scheduleList) {
			if (listOfSchedule.get(s.location).containsKey(s.starttime)) {
				listOfSchedule.get(s.location).get(s.starttime).add(new scheduleWrapper(s, true));
			} else {
				ArrayList<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, true));
				listOfSchedule.get(s.location).put(s.starttime, newList);
			}
			if (listOfSchedule.get(s.location).containsKey(s.endtime)) {
				listOfSchedule.get(s.location).get(s.endtime).add(new scheduleWrapper(s, false));
			} else {
				ArrayList<scheduleWrapper> newList = new ArrayList<>();
				newList.add(new scheduleWrapper(s, false));
				listOfSchedule.get(s.location).put(s.endtime, newList);
			}
		}
	}

	public void generateGapList() {
		for (int i = 0; i < listOfSchedule.size(); ++i) {// “ª∏ˆ“ª∏ˆ«¯”Úµƒ±£¥Ê
			listofGaps.add(new TreeMap<Integer, Map<String, scheduleRequest>>());// ’‚¿Ô–¥…œ÷ª”–“ª∏ˆ‘≠“ÚæÕ «≈¬Õ¸¡À°£°£°£
			Map<String, scheduleRequest> set = new HashMap<>();// ¥À¥¶»Á¥À¥¶¿Ì÷ªŒ™√ø¥Œ±£¡Ù…œ¥Œµƒset
			for (Map.Entry<Integer, List<scheduleWrapper>> entry : listOfSchedule.get(i).entrySet()) {//按时间顺序遍历
				Map<String, scheduleRequest> setCopy = new HashMap<>(set);// ¥À¥¶ «Œ™¡À±£÷§setª·∏ƒ±‰
				for (scheduleWrapper sW : entry.getValue()) {//遍历当前时间点的所有event
					if (sW.startEnd) {
						setCopy.put(sW.originalObject.id, sW.originalObject);
					} else {
						setCopy.remove(sW.originalObject.id);
					} // ππΩ®µ±«∞µƒset
				}
				set = setCopy;
				listofGaps.get(i).put(entry.getKey(), setCopy);//listofgap，存每一次有新event加入或剔除的时间点 
				// ππΩ®µ±«∞À˘”–gapµƒ±Ì
			}
			if (!listofGaps.get(i).containsKey(0)) {
				Map<String, scheduleRequest> emptyMap = new HashMap<>();
				listofGaps.get(i).put(0, emptyMap);// »Áπ˚gapµƒlist¿Ô√Ê√ª”–0 ±º‰º«µ√º”»Î£¨
												// º”»Î0 «“ÚŒ™»Áπ˚0÷Æ∫Û”÷Ωœ¥Ûµƒø’œ–ø…“‘÷±Ω”≤Â»Î
			}
		}
	}

	/**
	 * this function is used to get the next possible begin point of a gap
	 */
	private Integer findNextBegin(TreeMap<Integer, Map<String, scheduleRequest>> map, Integer indexBegin,
			scheduleinsertionObject sI) {
		Integer result = indexBegin;
		while (result != null && (map.get(result).size() == 3 || map.get(result).containsKey(sI.id))) {
			result = map.higherKey(result);
		}
		return result;
	}

	/**
	 * this function is used to get the next possible begin point of a gap
	 */
	private Integer findNextEnd(TreeMap<Integer, Map<String, scheduleRequest>> map, Integer indexBegin,
			scheduleinsertionObject sI) {
		Integer result = map.higherKey(indexBegin);
		while (result != null && map.get(result).size() < 3 && !map.get(result).containsKey(sI.id)) {
			result = map.higherKey(result);
		}
		return result;
	}

	private Integer getMax(List<Integer> Times) {
		Integer result = 0;
		for (Integer time : Times) {
			result = Math.max(time, result);
		}
		return result;
	}

	/**
	 * this function is used to judge the value of a result and it contains some
	 * standard for different cases
	 */
	public scheduleRequest maxValue(List<Integer> insertTimes, List<Integer> endTimes, scheduleinsertionObject sI) {
		Double result = null;
		Integer location = null;
		for (int i = 0; i < insertTimes.size(); ++i) {
			// Double
			// value=(valueList.get(i)*mapOfValue.get(sI.name))/((double)endTimes.get(i)-(double)insertTimes.get(i));//most-fit
			Double mid = insertTimes.get(i) == endTimes.get(i) ? 0
					: Math.log((double) ((getMax(endTimes)) + 1) / (double) ((insertTimes.get(i)) + 1));// ’‚¿Ô√Êµƒ≤‚ ‘÷µ∫Õ≥ı º÷µ“‘∫Û∂ºµ√∏ƒ£¨◊Ó∫√Ã·≥…const
			Double value = valueList.get(i) * mapOfValue.get(sI.id) + (mid); // first
																				// fit
			if (result == null || value > result) {// ±»¥Ûªπ «±»–°÷˜“™ø¥’‚¿Ô
				result = value;
				location = i;
			}
			System.out.println("value:" + value + ",starttime:" + insertTimes.get(i) + ",endtime" + getMax(endTimes));
		} // ¥À¥¶≤¢Œ¥øº¬«location ˝◊÷Œ™0µƒ«Èøˆ°£
		System.out.println("result:" + result + "location" + location);
		return new scheduleRequest(sI.id, location, insertTimes.get(location), insertTimes.get(location) + sI.duration);
	}

	public scheduleRequest optimize1(scheduleinsertionObject sI)// first fit ∞Ê±æ
	{
		// Integer insertTime = null; // Integer insertLocation = null;
		List<Integer> insertTimes = new LinkedList<>();
		List<Integer> endTimes = new ArrayList<>();
		for (int i = 0; i < listofGaps.size(); ++i) {
			Integer insertTime = 0;
			if (!listofGaps.get(i).isEmpty()) {
				endTimes.add(listofGaps.get(i).lastKey());
				Integer indexBegin = findNextBegin(listofGaps.get(i), listofGaps.get(i).firstKey(), sI);// ’“µΩµ⁄“ª∏ˆø…“‘ø™ ºµƒµ„
				while (indexBegin != null) {// µ±∏√¡–±Ì≤ªŒ™ø’£®µ⁄“ª¥Œ£©ªÚ «∏√œ¬¥Œµƒø™ º≤ªŒ™ø’µƒ ±∫Ú
					Integer indexEnd = findNextEnd(listofGaps.get(i), indexBegin, sI);
					if ((indexEnd == null || indexEnd - indexBegin >= sI.duration) && (insertTime == 0)) {// ’‚¿Ô◊¢“‚timeµΩµ◊ «¥”1ªπ «0ø™ º
						insertTime = indexBegin;
						break;// ’“µΩº¥÷’÷π
					} else {
						indexBegin = findNextBegin(listofGaps.get(i), indexEnd, sI);// ∑Ò‘Ú¥”œ¬∏ˆø…“‘µƒŒª÷√ø™ º
					}
				} 
			} else {
				endTimes.add(0);
			}
			insertTimes.add(insertTime);
		} // System.out.println(endTimes.toString());
		return maxValue(insertTimes, endTimes, sI);
	}

	public scheduleRequest optimize2(scheduleinsertionObject sI) {// most fitµƒ◊ˆ∑®
		List<Integer> insertTimes = new LinkedList<>();
		List<Integer> endTimes = new LinkedList<>();
		for (int i = 0; i < listofGaps.size(); ++i) {
			int gap = Integer.MAX_VALUE;
			Integer insertTime = 0;
			Integer endTime = Integer.MAX_VALUE;
			Integer indexBegin = findNextBegin(listofGaps.get(i), listofGaps.get(i).firstKey(), sI);
			while (indexBegin != null) {// µ±∏√¡–±Ì≤ªŒ™ø’£®µ⁄“ª¥Œ£©ªÚ «∏√œ¬¥Œµƒø™ º≤ªŒ™ø’µƒ ±∫Ú
				Integer indexEnd = findNextEnd(listofGaps.get(i), indexBegin, sI);
				if (indexEnd == null) {
					if (endTime == Integer.MAX_VALUE) {//◊¢“‚£¨¥À¥¶”–ø…ƒ‹ª·≥ˆœ÷Ω·Œ≤ «◊Ó¥Û’˚ ˝µƒ«Èøˆ
						insertTime = indexBegin;
					}
				} else if (indexEnd - indexBegin >= sI.duration) {
					if (indexEnd - indexBegin - sI.duration < gap) {
						insertTime = indexBegin;
						endTime = indexEnd;
						gap = indexEnd - indexBegin - sI.duration;
					}
				}
				indexBegin = findNextBegin(listofGaps.get(i), indexEnd, sI);// ∑Ò‘Ú¥”œ¬∏ˆø…“‘µƒŒª÷√ø™ º
			}
			insertTimes.add(insertTime);
			endTimes.add(endTime);
		}
		// System.out.println("insertTimes:"+insertTimes.toString()+"endTimes:"+endTimes.toString());
		return maxValue(insertTimes, endTimes, sI);
	}
}




package test1;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
	static Optimization optimizationUsedForTesting = new Optimization();
	static List<scheduleRequest> test = new ArrayList<>();

	public static void generateSchedule() {
		test.add(new scheduleRequest("1", 0, 0, 5));
		test.add(new scheduleRequest("1", 0, 7, 15));
		test.add(new scheduleRequest("2", 0, 3, 8));
		test.add(new scheduleRequest("3", 0, 0, 6));
		test.add(new scheduleRequest("3", 0, 7, 10));
		test.add(new scheduleRequest("1", 0, 25, 35));
		test.add(new scheduleRequest("2", 0, 20, 30));
		//test.add(new scheduleRequest("3", 0, 50, 65));// location 0µƒlist
		test.add(new scheduleRequest("1", 1, 0, 10));
		test.add(new scheduleRequest("2", 1, 10, 20));
		test.add(new scheduleRequest("3", 1, 20, 30));
		test.add(new scheduleRequest("2", 1, 30, 40));
		test.add(new scheduleRequest("1", 1, 40, 50));// location 1µƒlist
		test.add(new scheduleRequest("1", 2, 0, 25));
		//test.add(new scheduleRequest("2", 2, 0, 45));
		//test.add(new scheduleRequest("3", 2, 0, 40));// location 2µƒlist
	}// this function is used for generating the list for the tests

	public static void testInitial() {
		optimizationUsedForTesting.initializeSchedule(test);
		System.out.println("testInitial result:" + optimizationUsedForTesting.listOfSchedule.toString());
	}

	public static void testGnerateGapList() {
		optimizationUsedForTesting.generateGapList();
		System.out.println(optimizationUsedForTesting.listofGaps.toString());
	}

	static List<Integer> insertTimes = new ArrayList<>();
	static List<Integer> endTimes = new ArrayList<>();
	static scheduleinsertionObject sI = new scheduleinsertionObject("1",4);

//	public static void testMaxValue() {
//		scheduleRequest result = optimizationUsedForTesting.maxValue(insertTimes, endTimes, sI);
//		System.out.println(result);
//	}

	public static void testOptimization() {
		 scheduleRequest result=optimizationUsedForTesting.optimize1(sI);
		//scheduleRequest result = optimizationUsedForTesting.optimize2(sI);
		System.out.println("name:"+sI.id+",startTime:"+result.starttime+",endtime:"+result.endtime+",location"+result.location);
	}

	public static void main(String args[]) {
		generateSchedule();
		testInitial();
		testGnerateGapList();
		//testMaxValue();
		testOptimization();
	}
}