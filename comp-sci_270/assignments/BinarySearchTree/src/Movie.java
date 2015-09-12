public class Movie {
	private String name;
	private String director;
	private String year;

	/**
     * @param pFname
     *            First Name
     * @param pLname
     *            Last Name
     * @param pAge
     *            Age
     * @param pState
     *            State lived in
     */
	public Movie(String name, String director, String year) {
		this.name = name;
		this.director = director;
		this.year = year;
	}

	/**
     * pre: movie object exists, post: movie name is returned
     * @return string director
     */
	public String getName() {
		return name;
	}

	/**
     * pre: movie object exists, post: movie director is returned
     * @return string director
     */
	public String getDirector() {
		return director;
	}

	/**
     * pre: movie object exists, post: movie year is returned
     * @return string year
     */
	public String getYear() {
		return year;
	}

	/**
     * pre: bst object exists, post: movie name is inserted
     * 
     */
	public void setName(String n) {
		name = n;
	}

	/**
     * pre: movie object exists, post: movie year is inserted
     * 
     */
	public void setYear(String y) {
		year = y;
	}

	/**
     * pre: movie object exists, post: movie director is inserted
     * 
     */
	public void setDirector(String d) {
		director = d;
	}
}