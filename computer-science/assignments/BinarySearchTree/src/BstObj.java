/**
 * 
 * @author juanvallejo
 * 
 */

public class BstObj {
    private TreeNode root;

    /**
     * pre: BstObj does not exist post: an instance of BstObj is created with a
     * null root
     */
    public BstObj() {
        root = null;
    }

    /**
     * 
     * @param person
     */
    public void delete(Person person) {
        // TreeNode node = findNode(person,root);
        // if (node != null) {
        // if(node.getLkid() == null && node.getRkid() == null) {
        //
        // } else if(node.getLkid() != null && node.getRkid() == null) {
        // console.log("has left kid");
        // } else if(node.getRkid() != null && node.getLkid() == null) {
        // console.log("has right kid");
        // } else {
        // console.log("has two kids");
        // }
        // }
        root = delete(person, root);

    }

    /**
     * 
     * @param person
     * @param node
     * @return
     */
    private TreeNode delete(Person person, TreeNode node) {
        if (node != null) {
            if (person.compareTo(node.getVal()) < 0) {
                delete(person, node.getLkid());
            }
            else if (person.compareTo(node.getVal()) > 0) {
                delete(person, node.getRkid());
            }
            else {
                if (node.getLkid() != null && node.getRkid() != null) {
                    TreeNode newNode = minNode(node);
                    node = newNode;
                    delete(person, node.getLkid());
                }
                else if (node.getLkid() != null) {
                    node = node.getLkid();
                }
                else if (node.getRkid() != null) {
                    node = node.getRkid();
                }
                else {
                    return null;
                }
            }
            // if(node.getLkid() == null && node.getRkid() == null) {
            // if(node.getVal().allFields().equals(person.allFields())) {
            // console.log("returned null to delete this shit");
            // return null;
            // }
            // } else if(node.getRkid() != null) {
            // if(node.getVal().allFields().equals(person.allFields())) {
            //
            // } else {
            // console.log("look for the right kid");
            // delete(person,node.getRkid());
            // }
            // } else if(node.getLkid() != null) {
            // if(node.getVal().allFields().equals(person.allFields())) {
            //
            // } else {
            // console.log("look for the right kid");
            // delete(person,node.getRkid());
            // }
            // }
        }

        return node;
    }

    private TreeNode minNode(TreeNode node) {
        TreeNode parent = node;
        TreeNode next = node;
        TreeNode curr = node.getRkid();

        while (curr != null) {
            parent = next;
            next = curr;
            curr = curr.getLkid();
        }

        if (next != node.getRkid()) {
            parent.setLkid(next.getRkid());
            next.setRkid(node.getRkid());

        }

        return next;
    }

    /**
     * pre: BstObj exists post: boolean value is returned based on the success
     * or failure of finding a match for the Person value
     * 
     * @param person
     *            is the object to be matched against other values of TreeNodes
     * @return
     */
    public boolean find(Person person) {
        return find(person, root);
    }

    /**
     * 
     * @param person
     *            the value to find in the current treenode
     * @param node
     *            the node to iterate through to look for the Person value
     * @return boolean value based on whether or not the param value passed is
     *         in the passed treenode
     */
    private boolean find(Person person, TreeNode node) {
        if (node == null) {
            return false;
        }
        else {
            if (node.getVal().allFields().equals(person.allFields())) {
                return true;
            }
            else {
                if (find(person, node.getLkid())) {
                    return true;
                }
                else if (find(person, node.getRkid())) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

    private TreeNode findNode(Person person, TreeNode node) {
        if (node == null) {
            return null;
        }
        else {
            if (node.getVal().allFields().equals(person.allFields())) {
                return node;
            }
            else {
                TreeNode rightresult = findNode(person, node.getLkid());
                TreeNode leftresult = findNode(person, node.getRkid());

                if (rightresult != null) {
                    return rightresult;
                }
                else if (leftresult != null) {
                    return leftresult;
                }
                else {
                    return null;
                }
            }
        }
    }

    /**
     * pre: BstObj exists post: Person object is inserted into treenode object
     * 
     * @param value
     *            which is the Person object to be inserted into the structure
     */
    public void insert(Person value) {
        root = insert(value, root);
    }

    /**
     * 
     * @param value
     * @param node
     */
    private TreeNode insert(Person value, TreeNode node) {
        if (node == null) {
            return new TreeNode(value, null, null);
        }
        else if (value.sortKey().compareTo(node.getVal().sortKey()) == 0) {
            throw new IllegalArgumentException();
        }
        else if (value.sortKey().compareTo(node.getVal().sortKey()) > 0) {
            node.setRkid(insert(value, node.getRkid()));
            return node;
        }
        else {
            node.setLkid(insert(value, node.getLkid()));
            return node;
        }
    }

    /**
     * pre: BstObj exists pos: BstObj exists and is checked for nodes
     * 
     * @return true if empty false otherwise
     */
    public boolean isEmpty() {
        boolean r = true;
        if (root != null)
            r = false;
        return r;
    }

    /**
     * pre: BstObj exists post: sort keys for nodes in tree inorder are sorted
     * and returned
     * 
     * @return String of sort keys of the nodes of this BstObj traversed in
     *         inorder or empty string if BstObj empty
     */
    public String nodesInOrder() {
        return "";
    }

    /**
     * pre: BstObj exists and nodesInOrder() has been called post: node
     * addresses for an individual TreeNode are returned as a string in order
     * using this private method recursively
     * 
     * @param node
     * @return String of sort keys of an individual node of the TreeNode object
     *         passed or empty string
     */
    private String nodesInOrder(TreeNode node) {
        return "";
    }
}

class console {
    public static void log(String string) {
        System.out.println(string);
    }

    public static void log(int eger) {
        System.out.println(eger);
    }
}