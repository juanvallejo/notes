import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
	private List<T> array = new ArrayList<T>();

	public void push(T value) {
		array.add( value );
	}
	public T pop() {
		int last = array.size() - 1;
		return array.remove( last );
	}
	public boolean isEmpty() {
		return array.isEmpty();
	}
	@Override
	public String toString() {
		return array.toString();
	}
}
