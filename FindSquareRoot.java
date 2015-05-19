public class FindSquareRoot {
	public int mySqrt(int x) {
		long i = 0;
		long j = x / 2 + 1;
		while (i <= j) {
			long mid = (i + j) / 2;
			if (mid * mid == x)
				return (int) mid;
			if (mid * mid < x)
				i = mid + 1;
			else
				j = mid - 1;
		}
		return (int) j;
	}
}

/*
 * Copyright 2004-2015 Pilz Ireland Industrial Automation Ltd. All Rights
 * Reserved. PILZ PROPRIETARY/CONFIDENTIAL.
 * 
 * Created on 19 May 2015
 */