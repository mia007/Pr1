package ua.kharkov.knure.kolesnikov.JUnitDemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * �������� �����. ���������� ��������� �������� �������.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ TaskTest.class })
public class AllTests {

}
