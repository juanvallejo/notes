public class Person implements Comparable<Person> {
    private String fname;
    private String lname;
    private int age;
    private String state;

    public Person(String fname, String lname, int age, String state) {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.state = state;
    }

    public String allFields() {
        return lname + "," + fname + "," + age + "," + state;
    }

    @Override
    public int compareTo(Person person) {
        return sortKey().compareTo(person.sortKey());
    }

    public String sortKey() {
        return lname + fname;
    }
}