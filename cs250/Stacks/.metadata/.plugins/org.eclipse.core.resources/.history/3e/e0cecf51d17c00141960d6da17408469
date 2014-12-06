import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

public class StackExampleTest {
	private class StackTest extends Stack<Integer> {
		public StackTest(int[] values) {
			for (int i = values.length-1; i > -1; i--) {
				push( values[ i ] );
			}
		}
	}
	@Test
	public void testReflection() {
		Class<?> iClass  = StackExample.class;
		Field[]  iFields = iClass.getDeclaredFields();

		for (Field f : iFields) {
			if (!f.isSynthetic()) {
				fail( "Class shouldn't have any fields [found: \""+f.getName()+"\"]" );
			}
		}
	}
	@Test
	public void testEmpty() {
		int[]          input  = new int[]{ };
		Stack<Integer> stack  = new StackTest( input );
		Stack<Integer> result = StackExample.getEvenNumbers( stack );
		
		assertTrue( "result should be empty", result.isEmpty() );
		assertTrue( "stack should be empty",  stack .isEmpty() );
		assertTrue( "stack and result cannot be the same object", stack != result );
	}
	@Test
	public void test1Odd() {
		int[]          input   = new int[]{ 5 };
		Stack<Integer> stack   = new StackTest( input );
		Stack<Integer> result  = StackExample.getEvenNumbers( stack );
		
		assertTrue( "result should be empty", result.isEmpty() );
		
		for (int expected : input) {
			if (stack.isEmpty())
				fail( "\"stack\" empty: '"+ expected +"' expected" );
			else {
				int actual = stack.pop();
				assertEquals( "incorrect result", expected, actual );
			}
		}
		assertTrue( "stack and result cannot be the same object", stack != result );
	}
	@Test
	public void test1Even() {
		int[]          input   = new int[]{ 4 };
		Stack<Integer> stack   = new StackTest( input );
		Stack<Integer> result  = StackExample.getEvenNumbers( stack );
		
		for (int expected : new int[]{ 4 }) {
			if (result.isEmpty())
				fail( "\"result\" empty: '"+ expected +"' expected" );
			else {
				int actual = result.pop();
				assertEquals( "incorrect result", expected, actual );
			}
		}
		for (int expected : input) {
			if (stack.isEmpty())
				fail( "\"stack\" empty: '"+ expected +"' expected" );
			else {
				int actual = stack.pop();
				assertEquals( "incorrect result", expected, actual );
			}
		}
		assertTrue( "stack and result cannot be the same object", stack != result );
	}
	@Test
	public void testNoneEven() {
		int[]          input   = new int[]{ 9, 77, 3, 5, 11 };
		Stack<Integer> stack   = new StackTest( input );
		Stack<Integer> result  = StackExample.getEvenNumbers( stack );
		
		assertTrue( "result should be empty", result.isEmpty() );
		
		for (int expected : input) {
			if (stack.isEmpty())
				fail( "\"stack\" empty: '"+ expected +"' expected" );
			else {
				int actual = stack.pop();
				assertEquals( "incorrect result", expected, actual );
			}
		}
		assertTrue( "stack and result cannot be the same object", stack != result );
	}
	@Test
	public void testSomeEven() {
		int[]          input   = new int[]{ 44, 77, 8, 3, 5, 12 };
		Stack<Integer> stack   = new StackTest( input );
		Stack<Integer> result  = StackExample.getEvenNumbers( stack );
		
		for (int expected : new int[]{ 44, 8, 12 }) {
			if (result.isEmpty())
				fail( "\"result\" empty: '"+ expected +"' expected" );
			else {
				int actual = result.pop();
				assertEquals( "incorrect result", expected, actual );
			}
		}
		for (int expected : input) {
			if (stack.isEmpty())
				fail( "\"stack\" empty: '"+ expected +"' expected" );
			else {
				int actual = stack.pop();
				assertEquals( "incorrect result", expected, actual );
			}
		}
		assertTrue( "stack and result cannot be the same object", stack != result );
	}
	@Test
	public void testAllEven() {
		int[]          input   = new int[]{ 12, 22, 6, 14, 12 };
		Stack<Integer> stack   = new StackTest( input );
		Stack<Integer> result  = StackExample.getEvenNumbers( stack );
		
		for (int expected : input) {
			if (result.isEmpty())
				fail( "\"result\" empty: '"+ expected +"' expected" );
			else {
				int actual = result.pop();
				assertEquals( "incorrect result", expected, actual );
			}
		}
		for (int expected : input) {
			if (stack.isEmpty())
				fail( "\"stack\" empty: '"+ expected +"' expected" );
			else {
				int actual = stack.pop();
				assertEquals( "incorrect result", expected, actual );
			}
		}
		assertTrue( "stack and result cannot be the same object", stack != result );
	}
}
