package SortingTest;

/**
 * This class is a sorting algorithm for an input array of integers. The sorting algorithm used is Tree Sort using
 * a Binary Search Tree data structure.
 */
public class SortingAlgorithm {

    // Instance Variables:
    int[] dataSet;
    int length;
    BinaryNode root; // Root node of Binary Search Tree
    int index; // Index of the sortedData array
    int[] sortedData;

    /**
     * This constructor receives an integer array argument passed as a parameter. The integer array and its
     * associated length integer value are stored as instance variables.
     * @param array Integer array argument passed to this constructor
     */
    SortingAlgorithm(int [] array) {
        dataSet = array;
        length = dataSet.length;
    } // Constructor

    /**
     * This method sorts the data set input.
     * @return Array of sorted data
     */
    public int[] performSort() {

        for (int i = 0; i < length; i++) {
            insertBST(dataSet[i],root);
        }
        sortedData = new int[length];
        index = 0;
        inOrderTraverse(root);
        return sortedData;
    } // performSort

    /**
     * This method takes an integer and a binary node object as arguments. The integer is wrapped into the binary node
     * and is inserted into a Binary Search Tree data structure.
     * */
    private void insertBST (int integer, BinaryNode binaryNode) {

        if (binaryNode == null) { // If Binary Search Tree is empty...
            root = new BinaryNode(null, integer,null); // ... create root node containing integer
        }
        else { // Else, the Binary Search Tree is not empty

            if (integer < binaryNode.value) { // if integer being inserted is less than integer value within node...

                if (binaryNode.left == null ) {
                    binaryNode.left = new BinaryNode(null, integer, null); // Base case
                }
                else {
                    insertBST(integer,binaryNode.left); // Recursive call
                }

            }
            else {  // integer being inserted must be equal to or greater than integer value contained within node.

                if (binaryNode.right == null) {
                    binaryNode.right = new BinaryNode(null, integer, null);    // Base case
                }
                else {
                    insertBST(integer,binaryNode.right); // Recursive call
                }
            }
        }
    }

    /**
     * This method traverses the binary tree in order from smallest to largest. Each element traversed is stored into
     * an array, therefore sorting the elements.
     * @param node BinaryNode object in binary tree data structure
     */
    private void inOrderTraverse(BinaryNode node) {

        if ( node == null) {
            return;
        }
        else {
            inOrderTraverse(node.left);
            sortedData[index] = node.value;
            index++;
            inOrderTraverse(node.right);
        }
    }

}
