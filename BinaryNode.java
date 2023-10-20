package SortingTest;

/**
 * This class is an object for a binary node within a binary tree or binary search tree.
 */
public class BinaryNode {

    BinaryNode left;
    int value;
    BinaryNode right;

    /**
     * This constructor stores three parameters as instance variables
     * @param smallerNode A Binary node containing an integer value less than current node
     * @param i An integer
     * @param largerNode A Binary node containing an integer value greater than current node
     */
    BinaryNode (BinaryNode smallerNode, int i, BinaryNode largerNode) {
        left = smallerNode;
        value = i;
        right = largerNode;
    }

}
