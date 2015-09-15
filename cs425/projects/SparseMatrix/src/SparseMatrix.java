import java.util.ArrayList;
import java.util.HashMap;

public class SparseMatrix {
	
	ArrayList coordinates;
	
	public SparseMatrix() {
		
		HashMap coordinate = new HashMap();
		coordinate.put("x", 5.0);
		coordinate.put("y", new Integer(50));
		coordinate.put("z", new Integer(100));
		
		coordinates = new ArrayList();
		coordinates.add(coordinate);
				
	}
	
	public static void main(String[] args) {
		
		SparseMatrix m = new SparseMatrix();
		HashMap map = (HashMap)m.coordinates.get(0);
		
		System.out.println(map.get("9"));
		 
	}
	
}
