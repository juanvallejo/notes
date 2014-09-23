import static org.junit.Assert.*;
import org.junit.Test;

/**
 * 
 * @author juanvallejo
 *
 */

public class PolynomialTest {

	/**
	 * @pre Poly object exists
	 * @post order is 0
	 */
	@Test
	public void doesDefaultConstructSetOrderToZero() {
		Polynomial polynomial = new Polynomial();
		
		int expected = 0;
		int actual = polynomial.order();
		
		assertEquals(expected,actual);
	}
	
	/**
	 * @pre Poly object exists
	 * @post coefficient is 1
	 */
	@Test
	public void doesDefaultConstructSetCoefficientToOne() {
		Polynomial polynomial = new Polynomial();
		
		int expected = 1;
		int actual = polynomial.get(0);
		
		assertEquals(expected,actual);
	}
	
	/**
	 * @pre Poly object exists and order is 0
	 * @post order is equal to the passed value
	 */
	@Test
	public void doesConstructorSetOrderToPasssedValue() {
		int order = 1;
		
		Polynomial polynomial = new Polynomial(order);
		
		int expected = order;
		int actual = polynomial.order();
		
		assertEquals(expected,actual);
	}
	
	/**
	 * @pre Poly object exists and order is value passed
	 * @post coefficient is 1
	 */
	@Test
	public void doesConstructorSetCoefficientOfPassedValueTo1() {
		int order = 1;
		
		Polynomial polynomial = new Polynomial(order);
		
		int expected = order;
		int actual = polynomial.get(polynomial.order());
		
		assertEquals(expected,actual);
	}
	
	/**
	 * @pre Poly object exists
	 * @post coefficient is 1 and order is 0
	 */
	@Test
	public void doesConstructorUseDefaultsOnNegativeValue() {
		int order = -1;
		
		Polynomial polynomial = new Polynomial(order);
		
		int expectedOrder = 0;
		int actualOrder = polynomial.order();
		
		assertEquals(expectedOrder,actualOrder);
		
		int expectedCoefficient = 1;
		int actualCoefficient = polynomial.get(expectedOrder);
		
		assertEquals(expectedCoefficient,actualCoefficient);
	}
	
	/**
	 * @pre Poly object exists, coefficient is 1, order is 0
	 * @post coefficient at given term is equal to passed value
	 */
	@Test
	public void doesCoefficientAtGivenTermEqualGivenValue() {
		int value = 5;
		int order = 0;
		
		Polynomial polynomial = new Polynomial();
		polynomial.set(order,value);
		
		int expected = value;
		int actual = polynomial.get(order);
		
		assertEquals(expected,actual);
	}
	
	/**
	 * @pre Poly object exists, coefficient is 1, order is 0
	 * @post coefficient at given term remains the same
	 */
	@Test
	public void doesCoefficientChangeWhenGivenTermIsGreaterThanOrder() {
		int value = 5;
		int term = 2;
		
		Polynomial polynomial = new Polynomial();
		polynomial.set(term,value);
		
		int expected = 1;
		int actual = polynomial.get(polynomial.order());
		
		assertEquals(expected,actual);		
	}
	
	/**
	 * @pre Poly object exists, coefficient is 1, order is 0
	 * @post coefficient at given term remains the same
	 */
	@Test
	public void doesCoefficientChangeWhenGivenTermIsLessThanZero() {
		int value = 5;
		int term = -1;
		
		Polynomial polynomial = new Polynomial();
		polynomial.set(term,value);
		
		int expected = 1;
		int actual = polynomial.get(polynomial.order()); //term
		
		assertEquals(expected,actual);
	}
	
	/**
	 * @pre Both polynomials are valid and exist 
	 * @post coefficient of result is addition of added polynomials
	 */
	@Test
	public void isPassedPolynomialAddedToExistingPolynomial() {
		int highestOrder = 2;
		int[] expectedResults = {2,2,1};
		
		Polynomial polynomial = new Polynomial(highestOrder);
		
		Polynomial expected = new Polynomial(highestOrder-1);
//		expected.set(2,2);
//		expected.set(0,2);
		
		Polynomial actual = polynomial.add(expected);
		
		for(int i=0;i<=highestOrder;i++) {
            assertEquals(expectedResults[i],actual.get(i));
		}
	}
}
