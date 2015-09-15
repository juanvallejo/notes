/**
 * Utility methods for lists of Natural numbers
 * 
 * @author juanvallejo
 * @date 2/18/15
 */
public class Utility {

	public Utility() {
		
	}
	
	/**
	 * Returns the maximum Natural in a list as defined by numerical order.
	 * @param numberList
	 * @return Natural number with highest value
	 */
	public static Natural findMaximum(java.util.List<Natural> numberList) {
		
		int indexOfMaximumNatural = 0;
		
		// loop through items on list, find smaller value than minimumValue
		for(int i = 1; i < numberList.size(); i++) {
			if(numberList.get(i).intValue() > numberList.get(indexOfMaximumNatural).intValue()) {
				indexOfMaximumNatural = i;
			}
		}
		
		return numberList.get(indexOfMaximumNatural);
	}
	
	/**
	 * Finds the last Natural in a list as defined by the order established by the comparator argument.
	 * @param numberList
	 * @param numberComparator
	 * @return Natural number
	 */
	public static Natural findMaximum(java.util.List<Natural> numberList, java.util.Comparator<Natural> comparator) {
		
		int indexOfMaximumNatural = 0;
		
		for(int i = 1; i < numberList.size(); i++) {
			if(comparator.compare(numberList.get(i), numberList.get(indexOfMaximumNatural)) > 0) {
				indexOfMaximumNatural = i;
			}
		}
		
		return numberList.get(indexOfMaximumNatural);
	}
	
	/**
	 * Finds the minimum Natural in a list as defined by numerical order.
	 * @param numberList
	 * @return Natural number with highest value
	 */
	public static Natural findMinimum(java.util.List<Natural> numberList) {
		
		int indexOfMinimumNatural = 0;
		
		// loop through items on list, find smaller value than minimumValue
		for(int i = 1; i < numberList.size(); i++) {
			if(numberList.get(i).intValue() < numberList.get(indexOfMinimumNatural).intValue()) {
				indexOfMinimumNatural = i;
			}
		}
		
		return numberList.get(indexOfMinimumNatural);
	}
	
	/**
	 * Returns the first Natural in a list as defined by the order established by the comparator argument.
	 * @param numberList
	 * @param numberComparator
	 * @return Natural number
	 */
	public static Natural findMinimum(java.util.List<Natural> numberList, java.util.Comparator<Natural> comparator) {
		
		int indexOfMinimumNatural = 0;
		
		for(int i = 1; i < numberList.size(); i++) {
			if(comparator.compare(numberList.get(i), numberList.get(indexOfMinimumNatural)) < 0) {
				indexOfMinimumNatural = i;
			}
		}
		
		return numberList.get(indexOfMinimumNatural);
	}
	
}
