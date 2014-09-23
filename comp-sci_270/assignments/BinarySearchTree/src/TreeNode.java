/**
 * 
 * @author juanvallejo
 * @version 2013-04-21
 * 
 */
public class TreeNode {
    private Person value;
    private TreeNode leftkid;
    private TreeNode rightkid;

    /**
     * pre: TreeNode does not exist, post: TreeNode created
     * 
     * @param value
     *            to be inserted
     * @param leftkid
     *            link to the left kid node
     * @param rightkid
     *            link to the right kid node
     */
    public TreeNode(Person value, TreeNode leftkid, TreeNode rightkid) {
        this.value = value;
        this.leftkid = leftkid;
        this.rightkid = rightkid;
    }

    /**
     * pre: BstObj exists post: a Person value object is returned or null is
     * returned if none is assigned
     * 
     * @return value returns the value of the current node
     */
    public Person getVal() {
        return value;
    }

    /**
     * pre: bst object exists, post: value is changed to value
     * 
     * @param val
     *            is returned
     */
    public void setVal(Person val) {
        this.value = val;
    }

    /**
     * 
     * @return leftkid returns TreeNode reference to the left kid
     */
    public TreeNode getLkid() {
        return leftkid;
    }

    /**
     * 
     * @param kid
     *            the new left kid to set
     * 
     */
    public void setLkid(TreeNode kid) {
        leftkid = kid;
    }

    /**
     * 
     * @return rightkid returns TreeNode reference to the right kid
     */
    public TreeNode getRkid() {
        return rightkid;
    }

    /**
     * 
     * @param kid
     *            the new right kid to set
     * 
     */
    public void setRkid(TreeNode kid) {
        rightkid = kid;
    }
}