package lib;

public class Point<T extends Number> {

	public Point(T x, T y) {
		this.x = x;
		this.y = y;
	}

	T x, y;
}
