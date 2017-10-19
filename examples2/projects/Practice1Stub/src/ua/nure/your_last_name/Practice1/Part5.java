package ua.nure.your_last_name.Practice1;

public class Part5 {

	private static final String[] STRS = { "A", "B", "Z", "AA", "AZ", "BA", "ZZ", "AAA"};
	
	public static String int2str(int n) {
		return null;
	}

	public static int str2int(String s) {
		return 0;
	}
	
	public static String rightColumn(String col) {
		return null;
	}

	public static void main(String[] args) {
		for (String str : STRS) {
			int num = str2int(str);
			String str2 = int2str(num);
			System.out.println(str + " ==> " + num + " ==> " + str2);
		}
	}

}
