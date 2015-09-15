/**
 * Matrix class. Creates a sparse matrix of specified dimensions to store and allow the performance
 * of mathematical operations on. Only the allocated elements are kept in memory.
 * 
 * This class is part of the SparseMatrix project.
 * 
 * @date February 2, 2015
 * @author juanvallejo
 *
 */

import java.util.HashMap;

public class Matrix {
	
	// holds elements and element locations as key-value pairs
	private HashMap<String, Double> elements;
	private int columnSize;
	private int rowSize;
		
	/**
	 * Matrix class constructor. Takes number of rows and columns and initializes matrix accordingly.
	 * pre: hash map of elements has not been initialized, rows and columns must be at least 1.
	 * post: hash map is instantiated with no key-value pairs.
	 * 
	 * @param rows		integer declaring the number of rows matrix will have
	 * @param columns	integer declaring the number of columns matrix will have
	 */
	public Matrix(int rows, int columns) {
		
		if(rows < 1 || columns < 1) {
			throw new IllegalArgumentException("Rows and columns must be greater than or equal to one.");
		}
		
		elements 	= new HashMap<String, Double>();
		columnSize 	= columns;
		rowSize 	= rows;
		
	}
	
	/**
	 * Getter method for elements in our matrix. Returns element at specified column and row. A value of 0.0
	 * is returned if an element at the specified coordinates is not found.
	 * pre: elements 2D array been initialized and contains at least one element. Row and column must be at least 0.
	 * post: element at specified location is returned. An IllegalArgumentException is thrown if conditions are not met.
	 * 
	 * @param rows		integer specifying the row in which the target element resides. Value must be >= 0.
	 * @param columns	integer specifying the column in which the target element resides. Value must be >= 0.
	 * 
	 * @return element double containing element at specified dimension.
	 */
	public double getElement(int row, int column) {
		
		if(row < 0 || column < 0) {
			throw new IllegalArgumentException("Rows and columns must be greater than or equal to zero.");
		}
		
		if(rowSize <= row || columnSize <= column) {
			throw new IllegalArgumentException("The specified coordinates are outside of the array."); 
		}
		
		double element 			= 0.0;
		Object elementKeyValue 	= elements.get(generateKeyFromElementDimensions(row, column));
		
		if(elementKeyValue != null) {
			element = (Double)elementKeyValue;
		}
		
		return element;
		
	}
	
	/**
	 * Getter method for elements in our matrix. Sets element at specified column and row.
	 * pre: elements 2D array been initialized. Row and column must be at least 0 and within the size of the array.
	 * post: element is assigned to the specified location. If an element exists at that location that element is replaced. An IllegalArgumentException is thrown if conditions are not met.
	 * 
	 * @param element	double 	to add to the matrix
	 * @param rows		integer specifying the row in which the element resides. Value must be >= 0.
	 * @param columns	integer specifying the column in which the target element resides. Value must be >= 0.
	 */
	public void setElement(double element, int row, int column) {
		
		if(row < 0 || column < 0) {
			throw new IllegalArgumentException("Rows and columns must be greater than or equal to zero.");
		}
		
		if(rowSize <= row || columnSize <= column) {
			throw new IllegalArgumentException("The specified coordinates are outside of the array."); 
		}
		
		// generate a key and assign our element to it
		elements.put(generateKeyFromElementDimensions(row, column), element);
		
	}
	
	/**
	 * Generates a String key from the row and column given, that matches that of the one assigned to the desired element
	 * in the elements hash map.
	 * pre: hash map is initialized.
	 * post: String key is returned from adding row and column values.
	 * 
	 * @param rows		integer specifying the row in which the element resides. Value must be >= 0.
	 * @param columns	integer specifying the column in which the target element resides. Value must be >= 0.
	 */
	private String generateKeyFromElementDimensions(int row, int column) {
		return Integer.toString(row) + Integer.toString(column);
	}
	
	/**
	 * Adds the elements of another matrix to the current matrix elements.
	 * An IllegalArgumentException is thrown if the passed matrix is found to have an incompatible number of rows or columns.
	 * pre: hash map initialized. Passed matrix must have compatible dimensions with the current matrix.
	 * post: Current matrix has elements updated to the sum of each of the passed matrix elements and its own.
	 * 
	 * @param matrix Matrix of rows and columns of the same length as those of the current matrix.
	 */
	public void addWithMatrix(Matrix matrix) {
		
		// check dimensional compatibility
		if(matrix.getRowSize() != this.getRowSize() || matrix.getColumnSize() != this.getColumnSize()) {
			throw new IllegalArgumentException("The dimensions of the matrix specified are incompatible.");
		}
		
		for(int row = 0; row < rowSize; row++) {
			for(int column = 0; column < columnSize; column++) {
				setElement(getElement(row, column) + matrix.getElement(row, column), row, column);
			}
		}
		
	}
	
	/**
	 * Multiplies the elements of another matrix to the current matrix elements.
	 * An IllegalArgumentException is thrown if the passed matrix is found to have an incompatible number of rows or columns.
	 * pre: hash map initialized. Passed matrix must have the same number of rows as the current matrix's number of columns.
	 * post: A new matrix is created to hold the products of the two matrices, and the elements and dimensions of the current matrix are replaced with those of the new matrix.
	 * 
	 * @param matrix Matrix with rows equal to the columns of the current matrix.
	 */
	public void multiplyWithMatrix(Matrix matrix) {
		
		// check dimensional compatibility
		if(this.getColumnSize() != matrix.getRowSize()) {
			throw new IllegalArgumentException("The dimensions of the matrix specified are incompatible.");
		}
		
		HashMap matrixProduct = new HashMap();
		
		for(int row = 0; row < rowSize; row++) {
			
			for(int foreignColumn = 0; foreignColumn < matrix.getColumnSize(); foreignColumn++) {
				
				double currentProduct = 0;
				
				for(int column = 0; column < columnSize; column++) {
					currentProduct += (getElement(row, column) * matrix.getElement(column, row));
				}
				
				matrixProduct.put(generateKeyFromElementDimensions(row, foreignColumn), currentProduct);
			}
			
		}
		
		// replace values with new ones and adjust column size to that of new matrix
		elements 	= matrixProduct;
		columnSize 	= matrix.getColumnSize();
		
	}
	
	/**
	 * Multiplies the elements of two matrices.
	 * An IllegalArgumentException is thrown if the passed matrix is found to have an incompatible number of rows or columns.
	 * pre: Number of rows of matrixB must be equal to the number of columns of matrixA.
	 * post: A new matrix is created to hold the products of the two matrices and is returned.
	 * 
	 * @param matrixA Matrix with columns equal to the rows of matrixB.
	 * @param matrixB Matrix with rows equal to columns of matrixA.
	 * 
	 * @return matrixProduct Matrix with row number equal to number of rows of matrixA and column number equal to number of columns of matrixB.
	 */
	public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
		
		// check dimensional compatibility
		if(matrixA.getColumnSize() != matrixB.getRowSize()) {
			throw new IllegalArgumentException("The dimensions of the matrix specified are incompatible.");
		}
		
		Matrix matrixProduct 	= new Matrix(matrixA.getRowSize(), matrixB.getColumnSize());
		double currentProduct	= 0;
		
		for(int matrixARow = 0; matrixARow < matrixA.getRowSize(); matrixARow++) {
			
			for(int matrixBColumn = 0; matrixBColumn < matrixB.getColumnSize(); matrixBColumn++) {
				
				currentProduct = 0;
				
				for(int matrixAColumn = 0; matrixAColumn < matrixA.getColumnSize(); matrixAColumn++) {
					currentProduct += (matrixA.getElement(matrixARow, matrixAColumn) * matrixB.getElement(matrixAColumn, matrixARow));
				}
				
				matrixProduct.setElement(currentProduct, matrixARow, matrixBColumn);
				
			}
			
		}
		
		return matrixProduct;
	}
	
	/**
	 * Adds the elements of two matrices.
	 * An IllegalArgumentException is thrown if the passed matrix is found to have an incompatible number of rows or columns.
	 * pre: Number of rows and columns of matrixB must be equal to the number of rows and columns of matrixA.
	 * post: A new matrix is created to hold the sum of the two matrices and is returned.
	 * 
	 * @param matrixA Matrix with row and column numbers equal to the rows of matrixB.
	 * @param matrixB Matrix with row and columns numbers equal to columns of matrixA.
	 * 
	 * @return matrixProduct Matrix with row number equal to number of rows of matrixA and column number equal to number of columns of matrixB.
	 */
	public static Matrix add(Matrix matrixA, Matrix matrixB) {
		
		// check dimensional compatibility
		if(matrixA.getRowSize() != matrixB.getRowSize() || matrixA.getColumnSize() != matrixB.getColumnSize()) {
			throw new IllegalArgumentException("The dimensions of the matrix specified are incompatible.");
		}
		
		Matrix matrixSum = new Matrix(matrixA.getRowSize(), matrixA.getColumnSize());
		
		for(int row = 0; row < matrixA.getRowSize(); row++) {
			for(int column = 0; column < matrixA.getColumnSize(); column++) {
				matrixSum.setElement(matrixA.getElement(row, column) + matrixB.getElement(row, column), row, column);
			}
		}
		
		return matrixSum;
	}
	
	/**
	 * Getter method returning the amount of rows in our matrix. Matrix initially starts with 0 rows.
	 * pre: HashMap instantiated.
	 * post: the stored amount of rows is returned.
	 * 
	 * @return elements.length integer containing the amount of rows in our matrix.
	 */
	public int getRowSize() {
		return rowSize;
	}
	
	/**
	 * Getter method returning the amount of columns in our matrix. Matrix initially starts with 0 columns.
	 * pre: HashMap initialized.
	 * post: the stored amount of columns is returned.
	 * 
	 * @return elements.length integer containing the amount of columns in our matrix.
	 */
	public int getColumnSize() {
		return columnSize;
	}
	
	/**
	 * Returns all of the assigned matrix elements in a string, with correct "array" list notation.
	 * pre: Matrix has been instantiated.
	 * post: String with all elements is returned.
	 * 
	 * @return matrixAsString String containing all assigned elements in matrix.
	 */
	@Override
	public String toString() {
		
		String matrixAsString = "[";
		
		for(int row = 0; row < rowSize; row++) {
			for(int column = 0; column < columnSize; column++) {
				matrixAsString += getElement(row, column) + ", ";
			}
		}
		
		// trim last comma
		matrixAsString = matrixAsString.substring(0, matrixAsString.length() - 2);
		matrixAsString += "]";
		
		return matrixAsString;
	}

}

