package lib;

public class Geometry<T extends Number> {
	
	// dot product
	T dot(Point<T> p1, Point<T> p2) {
		Number res = p1.x.doubleValue() * p2.x.doubleValue() + p1.y.doubleValue() * p2.y.doubleValue();
		return (T)res;
	}
	
	// cross product
	
	
	public static void main(String[] args) {
		Geometry<Integer> geo = new Geometry<Integer>();
		Point p1 = new Point(1, 2);
		Point p2 = new Point(2, 3);
		
		System.out.println(geo.dot(p1,p2));
	}
}
