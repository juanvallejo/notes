import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Juan Vallejo
 * @version 2014-04-21
 * 
 */
public class BstObjTest {

    /**
     * doesInsertMethodInsertNodes check the insertion method against empty method
     */
    @Test
    public void doesInsertMethodInsertNodes() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Smith", 25, "VA"));
        bst.insert(new Person("Alex", "Johanson", 43, "TX"));

        assertFalse(bst.isEmpty());
    }

    /**
     * doesInsertMethodHandleDuplicates does the insert method allow duplicates to be inserted
     */
    @Test(expected = IllegalArgumentException.class)
    public void doesInsertMethodHandleDuplicates() {
        try {
            BstObj bst = new BstObj();
            bst.insert(new Person("John", "Smith", 25, "VA"));
            bst.insert(new Person("John", "Smith", 25, "VA"));

            fail();
        }
        catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * doesFindMethodCorrectlySearchForValue checks value for true when finding inserted result
     */
    @Test
    public void doesFindMethodCorrectlySearchForValue() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Smith", 25, "VA"));
        bst.insert(new Person("Alex", "Johanson", 43, "TX"));
        bst.insert(new Person("Mark", "James", 42, "NV"));

        assertTrue(bst.find(new Person("John", "Smith", 25, "VA")));
    }

    /**
     * doesDeleteMethodCheckForNodeExistence
     */
    @Test
    public void doesDeleteMethodCheckForNodeExistence() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Smith", 25, "VA"));
        bst.delete(new Person("John", "James", 25, "VA"));

        assertFalse(bst.isEmpty());
    }

    /**
     * doesDeleteMethodDeleteRoot
     */
    @Test
    public void doesDeleteMethodDeleteRoot() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Smith", 25, "VA"));
        bst.insert(new Person("Alex", "Johnson", 45, "VA"));
        bst.insert(new Person("Paul", "Waters", 20, "VA"));
        bst.delete(new Person("John", "Smith", 25, "VA"));

        Person expected = new Person("Paul", "Waters", 20, "VA");

        assertEquals(bst.getRoot().getVal().allFields(), expected.allFields());
    }

    /**
     * doesDeleteMethodDeleteTreeWithLeftKid
     */
    @Test
    public void doesDeleteMethodDeleteTreeWithLeftKid() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Smith", 25, "VA"));
        bst.insert(new Person("Alex", "Johnson", 45, "VA"));
        bst.delete(new Person("John", "Smith", 25, "VA"));

        Person expected = new Person("Alex", "Johnson", 45, "VA");

        assertEquals(bst.getRoot().getVal().allFields(), expected.allFields());
    }

    /**
     * doesDeleteMethodDeleteRoot
     */
    @Test
    public void doesDeleteMethodDeleteRightKid() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Smith", 25, "VA"));
        bst.insert(new Person("Alex", "Johnson", 45, "VA"));
        bst.insert(new Person("Paul", "Waters", 20, "VA"));
        bst.delete(new Person("Paul", "Waters", 20, "VA"));

        boolean expected = bst.find(new Person("Paul", "Waters", 20, "VA"));

        assertFalse(expected);
    }

    /**
     * isTreeEmptyWhenRootIsNull
     */
    @Test
    public void isTreeEmptyWhenRootIsNull() {
        BstObj bst = new BstObj();

        boolean expected = bst.isEmpty();

        assertTrue(expected);
    }

    /**
     * isTreeEmptyWhenRootIsNull
     */
    @Test
    public void doesNodesInOrderSortTree() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Smith", 25, "VA"));
        bst.insert(new Person("Alex", "Johnson", 45, "VA"));
        bst.insert(new Person("Karl", "Zall", 45, "VA"));

        System.out.println(bst.nodesInOrder());
        String expected = "JohnsonAlex,SmithJohn,ZallKarl,";
        assertEquals(bst.nodesInOrder(), expected);
    }

    /**
     * doesInsertMethodKeepBSTFormat
     */
    @Test
    public void doesInsertMethodKeepBSTFormat() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Mayer", 25, "VA"));
        bst.insert(new Person("Alex", "Johnson", 45, "VA"));
        bst.insert(new Person("Jack", "Smith", 25, "VA"));
        bst.insert(new Person("Jamie", "White", 45, "VA"));
        bst.insert(new Person("Leroy", "Noun", 45, "VA"));

        Person expected = new Person("Leroy", "Noun", 45, "VA");

        assertEquals(bst.getRoot().getRkid().getLkid().getVal().allFields(),
                expected.allFields());
    }

    /**
     * doesInorderReturnEmptyStringWhenTreeIsEmpty
     */
    @Test
    public void doesInorderReturnEmptyStringWhenTreeIsEmpty() {
        BstObj bst = new BstObj();

        String expected = "";

        assertEquals(bst.nodesInOrder(), expected);
    }

    /**
     * doesGetMinReturnMin
     */
    @Test
    public void doesGetMinReturnMinTreeValue() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Mayer", 25, "VA"));
        bst.insert(new Person("Alex", "Johnson", 45, "VA"));
        bst.insert(new Person("Jack", "Smith", 25, "VA"));
        bst.insert(new Person("Jamie", "White", 45, "VA"));
        bst.insert(new Person("Leroy", "Noun", 45, "VA"));
        bst.delete(new Person("John", "Mayer", 25, "VA"));

        assertFalse(bst.isEmpty());

    }

    /**
     * doesFindMethodFindSubNode
     */
    @Test
    public void doesFindMethodFindSubNode() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Mayer", 25, "VA"));
        bst.insert(new Person("Alex", "Johnson", 45, "VA"));
        bst.insert(new Person("Jack", "Smith", 25, "VA"));
        bst.insert(new Person("Jamie", "White", 45, "VA"));
        bst.insert(new Person("Leroy", "Noun", 45, "VA"));
        bst.delete(new Person("John", "Mayer", 25, "VA"));

        boolean actual = bst.find(new Person("Leroy", "Noun", 45, "VA"));

        assertTrue(actual);

    }

    /**
     * doesFindMethodFindLeftSubNode
     */
    @Test
    public void doesFindMethodFindLeftSubNode() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Mayer", 25, "VA"));
        bst.insert(new Person("Alex", "Johnson", 45, "VA"));
        bst.insert(new Person("Jack", "Smith", 25, "VA"));
        bst.insert(new Person("Jamie", "White", 45, "VA"));
        bst.insert(new Person("Leroy", "Noun", 45, "VA"));
        bst.delete(new Person("John", "Mayer", 25, "VA"));

        boolean actual = bst.find(new Person("Alex", "Johnson", 45, "VA"));

        assertTrue(actual);

    }

    /**
     * doesFindMethodFindRightSubNode
     */
    @Test
    public void doesFindMethodFindRightSubNode() {
        BstObj bst = new BstObj();
        bst.insert(new Person("John", "Mayer", 25, "VA"));
        bst.insert(new Person("Alex", "Johnson", 45, "VA"));
        bst.insert(new Person("Jack", "Smith", 25, "VA"));
        bst.insert(new Person("Jamie", "White", 45, "VA"));
        bst.insert(new Person("Leroy", "Noun", 45, "VA"));
        bst.delete(new Person("John", "Mayer", 25, "VA"));

        boolean actual = bst.find(new Person("Jack", "Smith", 25, "VA"));

        assertTrue(actual);

    }
}
