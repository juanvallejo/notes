import static org.junit.Assert.*;

import org.junit.Test;


public class MatrixTest {

	/**
	 * Looks for an exception when fetching an element with out of bounds coordinates.
	 * pre: elements 2D array is initialized.
	 * post: elements 2D array has one row / column. IllegalArgumentException is returned.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void doesGetElementHandleOutOfBoundsParameters() {
		
		Matrix matrix = new Matrix(3, 3);
		matrix.setElement(5, 2, 2);
		matrix.getElement(3, 3);
		
	}
	
	/**
	 * Looks for an exception when fetching an element with at least a negative coordinate.
	 * pre: elements 2D array is initialized.
	 * post: elements 2D array has one row / column. IllegalArgumentException is returned.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void doesGetElementHandleNegativeParameters() {
		
		Matrix matrix = new Matrix(3, 3);
		matrix.setElement(5, 2, 2);
		matrix.getElement(-1, 0);
		
	}
	
	/**
	 * Looks for an exception when setting an element with out of bounds coordinates.
	 * pre: elements 2D array is initialized.
	 * post: IllegalArgumentException is returned.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void doesSetElementHandleOutOfBoundsParameters() {
		
		Matrix matrix = new Matrix(3, 3);
		matrix.setElement(5, 9, 8);
		
	}
	
	/**
	 * Looks for an exception when setting an element with at least a negative coordinate.
	 * pre: elements 2D array is initialized.
	 * post: IllegalArgumentException is returned.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void doesSetElementHandleNegativeParameters() {
		
		Matrix matrix = new Matrix(3, 3);
		matrix.setElement(5, -2, 2);
		
	}
	
	/**
	 * Looks for an exception when initializing a matrix with negative parameters.
	 * pre: elements 2D array is not initialized.
	 * post: IllegalArgumentException is returned.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void doesMatrixConstructorAllowNegativeParameters() {
		Matrix matrix = new Matrix(-1, 3);		
	}
	
	/**
	 * Looks for an exception when initializing a matrix with zero as one of its parameters.
	 * pre: elements 2D array is not initialized.
	 * post: IllegalArgumentException is returned.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void doesMatrixConstructorAllowZeroAsAValue() {
		Matrix matrix = new Matrix(0, 0);		
	}
	
	/**
	 * Checks to see that the correct element is returned when its coordinates are specified.
	 * pre: elements 2D array exists.
	 * post: elements 2D array receives a new element.
	 */
	@Test
	public void doesGetElementReturnExpectedElement() {
		
		double expectedElement = 5.0;
		
		Matrix matrix = new Matrix(3, 3);
		matrix.setElement(expectedElement, 2, 2);
		
		assertEquals(expectedElement, matrix.getElement(2, 2), 0);
		
	}
	
	/**
	 * Checks to see that a value of zero is returned when an element's coordinates are not specified correctly.
	 * pre: elements 2D array exists.
	 * post: elements 2D array receives a new element.
	 */
	@Test
	public void doesGetElementReturnZeroWhenElementIsNotSet() {
		
		double expectedElement = 0.0;
		
		Matrix matrix = new Matrix(3, 3);
		matrix.setElement(expectedElement, 2, 2);
		
		assertEquals(expectedElement, matrix.getElement(2, 0), 0);
		
	}
	
	/**
	 * Checks to see that an element is successfully set at its specified location.
	 * pre: elements 2D array exists.
	 * post: elements 2D array receives a new element.
	 */
	@Test
	public void doesSetElementSetValueAtCorrectLocation() {
		
		double expectedElement = 5.0;
		
		Matrix matrix = new Matrix(3, 3);
		matrix.setElement(expectedElement, 2, 2);
		
		assertEquals(expectedElement, matrix.getElement(2, 2), 0);
		
	}
	
	/**
	 * Checks to see that the correctly multiplied values of a matrix are returned.
	 * pre: both arrays exist and have a compatible number of rows and columns.
	 * post: elements 2D array of matrixA are replaced with the product of its elements and those of matrixB.
	 */
	@Test
	public void doesMultiplyWithMatrixMethodCorrectlyMultiplyMatrices() {
		
		Matrix matrixA = new Matrix(3, 3);
		Matrix matrixB = new Matrix(3, 3);
		
		matrixA.setElement(11, 0, 0);
		matrixA.setElement(12, 0, 1);
		matrixA.setElement(13, 0, 2);
		matrixA.setElement(31, 2, 0);
		matrixA.setElement(32, 2, 1);
		matrixA.setElement(11, 2, 2);
		
		matrixB.setElement(11, 0, 0);
		matrixB.setElement(12, 0, 1);
		matrixB.setElement(13, 0, 2);
		matrixB.setElement(31, 2, 0);
		matrixB.setElement(32, 2, 1);
		matrixB.setElement(11, 2, 2);
		
		Matrix expectedMatrix = Matrix.multiply(matrixA, matrixB);

		matrixA.multiplyWithMatrix(matrixB);

		assertEquals(expectedMatrix.toString(), matrixA.toString());
		
	}
	
	/**
	 * Checks to see that the correctly multiplied values of two matrices are returned.
	 * pre: both arrays exist and have a compatible number of rows and columns.
	 * post:A matrix containing modified rows / columns is returned with new values.
	 */
	@Test
	public void doesMultiplyMethodCorrectlyMultiplyMatrices() {
		
		Matrix matrixA = new Matrix(3, 3);
		Matrix matrixB = new Matrix(3, 3);
		
		matrixA.setElement(11, 0, 0);
		matrixA.setElement(12, 0, 1);
		matrixA.setElement(13, 0, 2);
		matrixA.setElement(31, 2, 0);
		matrixA.setElement(32, 2, 1);
		matrixA.setElement(11, 2, 2);
		
		matrixB.setElement(11, 0, 0);
		matrixB.setElement(12, 0, 1);
		matrixB.setElement(13, 0, 2);
		matrixB.setElement(31, 2, 0);
		matrixB.setElement(32, 2, 1);
		matrixB.setElement(11, 2, 2);
		
		String expectedMatrix 	= "[524.0, 524.0, 524.0, 0.0, 0.0, 0.0, 524.0, 524.0, 524.0]";
		String actualMatrix		= Matrix.multiply(matrixA, matrixB).toString();

		assertEquals(expectedMatrix, actualMatrix);
		
	}
	
	/**
	 * Checks to see that the correctly added values of two matrices are returned.
	 * pre: both arrays exist and have a compatible number of rows and columns.
	 * post:A matrix containing modified rows / columns is returned with new values.
	 */
	@Test
	public void doesAddMethodCorrectlyAddMatrices() {
		
		Matrix matrixA = new Matrix(3, 3);
		Matrix matrixB = new Matrix(3, 3);
		
		matrixA.setElement(11, 0, 0);
		matrixA.setElement(12, 0, 1);
		matrixA.setElement(13, 0, 2);
		matrixA.setElement(31, 2, 0);
		matrixA.setElement(32, 2, 1);
		matrixA.setElement(11, 2, 2);
		
		matrixB.setElement(11, 0, 0);
		matrixB.setElement(12, 0, 1);
		matrixB.setElement(13, 0, 2);
		matrixB.setElement(31, 2, 0);
		matrixB.setElement(32, 2, 1);
		matrixB.setElement(11, 2, 2);
		
		String expectedMatrix 	= "[22.0, 24.0, 26.0, 0.0, 0.0, 0.0, 62.0, 64.0, 22.0]";		
		String actualMatrix 	= Matrix.add(matrixA, matrixB).toString();
		
		assertEquals(expectedMatrix, actualMatrix);
		
	}
	
	/**
	 * Checks to see that the correctly multiplied values of a matrix are returned.
	 * pre: both arrays exist and have a compatible number of rows and columns.
	 * post: elements 2D array of matrixA are replaced with the product of its elements and those of matrixB.
	 */
	@Test
	public void doesAddWithMatrixMethodCorrectlyAddMatrices() {
		
		Matrix matrixA = new Matrix(3, 3);
		Matrix matrixB = new Matrix(3, 3);
		
		matrixA.setElement(11, 0, 0);
		matrixA.setElement(12, 0, 1);
		matrixA.setElement(13, 0, 2);
		matrixA.setElement(31, 2, 0);
		matrixA.setElement(32, 2, 1);
		matrixA.setElement(11, 2, 2);
		
		matrixB.setElement(11, 0, 0);
		matrixB.setElement(12, 0, 1);
		matrixB.setElement(13, 0, 2);
		matrixB.setElement(31, 2, 0);
		matrixB.setElement(32, 2, 1);
		matrixB.setElement(11, 2, 2);
		
		Matrix expectedMatrix = Matrix.add(matrixA, matrixB);

		matrixA.addWithMatrix(matrixB);

		assertEquals(expectedMatrix.toString(), matrixA.toString());
		
	}
	
	/**
	 * Tests that the toString method returns a correctly formatted list of matrix values.
	 * pre: both arrays are initialized and have values to return.
	 * post: test arrays are added and resulting values are turned to a string and compared to an expected result.
	 */
	@Test
	public void doesToStringReturnCorrectlyFormattedMatrixValueString() {
		
		Matrix matrixA = new Matrix(3, 3);
		Matrix matrixB = new Matrix(3, 3);
		
		matrixA.setElement(11, 0, 0);
		matrixA.setElement(12, 0, 1);
		matrixA.setElement(13, 0, 2);
		matrixA.setElement(31, 2, 0);
		matrixA.setElement(32, 2, 1);
		matrixA.setElement(11, 2, 2);
		
		matrixB.setElement(11, 0, 0);
		matrixB.setElement(12, 0, 1);
		matrixB.setElement(13, 0, 2);
		matrixB.setElement(31, 2, 0);
		matrixB.setElement(32, 2, 1);
		matrixB.setElement(11, 2, 2);
		
		String expectedStringResult = "[22.0, 24.0, 26.0, 0.0, 0.0, 0.0, 62.0, 64.0, 22.0]";
		String actualStringResult 	= Matrix.add(matrixA, matrixB).toString();
		
		assertEquals(expectedStringResult, actualStringResult);
		
	}

}
