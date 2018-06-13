package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.neotech.StartPoint;



public class StartPointTest {

	@Test
	public void isPrintTimestampModeTest() {
		//1. normal test
		String args[] = new String[]{"-p"};
		assertEquals(true, StartPoint.isPrintTimestampMode(args));		
		
		//2. normal test 2
		String args2[] = new String[]{"-", "-p", "-pp"};
		assertEquals(true, StartPoint.isPrintTimestampMode(args2));
		
		//3. null array 
		String args3[] = null;
		assertEquals(false, StartPoint.isPrintTimestampMode(args3));
		
		//4. empty array 
		String args4[] = new String[]{};
		assertEquals(false, StartPoint.isPrintTimestampMode(args4));
		
		//5. without '-p'
		String args5[] = new String[]{"-", "-ll", "-o"};
		assertEquals(false, StartPoint.isPrintTimestampMode(args5));
		
		//6. with '-pp'
		String args6[] = new String[]{"-pp"};
		assertEquals(false, StartPoint.isPrintTimestampMode(args6));
	}

}
