/**
 * ������������� ������ � ���������������� ��������.<br/>
 * ������������� ��. ������������ � ������ {@link String#intern()}
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class StringInternDemo {

	public static void main(String[] args) {

		// (1) s - ��������� �� ������������ ������
		String s = "abc";
		// pool: "abc"
		// ��� ��������� �������� ��������� �� ������������ ������

		// s.intern ������ ������ �� "abc", �.�. this
		System.out.println(s == s.intern());
		// ===> true

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		// (2) s - ��������� �� �������������� ������ [bc]
		s = "abc".substring(1);
		// pool: "abc"

		// � ���� �� ���������� ����������� �� equals ������� [bc]
		// s.intern ������� ������ [bc] � ��� � ������ this
		System.out.println(s == s.intern());
		// ===> true
		// pool: "abc", [bc]

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		// (3) s - ��������� �� �������������� ������ [c]
		s = "abc".substring(2);
		// pool: "abc", [bc]

		/*****************************************/
		// ������ ��� ������ [c] ���������� �� equals � ���
		// (������ ����� ����� ���, ����� ������� "c" ����� � ���� ���)

		"c".toString(); // <=== TRY TO COMMENT THIS LINE!!!

		// ����� ���������� ���� ������ � ���� ���� ������ "c"
		// pool: "abc", [bc], "c"
		/****************************************/

		// s.intern ������ ������ �� "c", ������� �� ��������� � [c]
		System.out.println(s == s.intern());
		// ===> false

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		/*
		 * ���������: ����� ���������� ���� ��� �������� "abc", [bc], "c"
		 * 
		 * ���� ����� � ���� ����� ����������� ������� "bc", �� �� �����
		 * ��������� �� ������������ ������ [bc].
		 */
	}
}