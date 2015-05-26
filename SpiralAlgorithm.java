public class SpiralAlgorithm {

	public static void findSpiral(int x, int y, int count) {

		int i = 0;
		while (i < count) {
			if (x <= 0 && y <= 0 && x == y) {
				if (y < 0)
					y = -y;
				y++;
			} else if (x < y) {
				if (x < 0)
					x = -x;
				x++;
			} else if (x == y) {
				y = -y;
			} else if (x > y) {
				x = -x;
			}
			System.out.println("(" + x + " ," + y + ")");
			i++;
		}

	}

	public static void main(String[] args) {
		findSpiral(-1, -1, 15);
	}

}
