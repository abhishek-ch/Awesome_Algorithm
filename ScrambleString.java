public class ScrambleString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.err.println("==> " + new ScrambleString().isScramble("great", "rgtae"));
	}

	public boolean isScramble(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;
		if (s1.equals(s2))
			return true;
		
		int[] proxy = new int[26];
		int L = s1.length();
		for (int i = 0; i < L; i++) {
			proxy[s1.charAt(i) - 'a']++;
			proxy[s2.charAt(i) - 'a']--;
		}

		for (int i = 0; i < 26; i++) {
			if (proxy[i] != 0)
				return false;
		}

		
		for (int i = 1; i < L; i++) {
			String s11 = s1.substring(0, i);
			String s12 = s1.substring(i, L);
			String s21 = s2.substring(0, i);
			String s22 = s2.substring(i, L);

			if (isScramble(s11, s21) && isScramble(s12, s22))
				return true;
			s21 = s2.substring(0, L - i);
			s22 = s2.substring(L - i, L);
			if (isScramble(s11, s22) && isScramble(s12, s21))
				return true;
		}

		return false;
	}

}
