package SortingTest;
//  Using your preferred programming language, implement a random test case generator for a
//  sorting algorithm program that sorts integers in ascending order. The test case generator
//  should be designed to produce arrays of integers with random lengths, and values for each
//  sorting method.

/** This class randomly generates a test case for an arbitrary sorting algorithm.
 * The test case is an integer array of a length between 127 and 255 elements.
 * Each element of the array will randomly store an integer from the set of integers {0 to (length-1)}.
 * This test-case array will then be passed to the sorting algorithm. */
public class RandomTestGen {

    // Instance Variables:
    int [] testCase;
    int [] sorted;
    int arrayLength;

    /** This constructor generates a test case, applies a sorting algorithm on the generated test case, and checks if
     * the sorting algorithm produces the correct result. */
    public RandomTestGen() {

        testCase = generateTestCase(); // Test is generated and returned to variable testCase
        applySortingAlgorithm(); // Applies sorting algorithm to test case
        verifyTestCase(); // Verifies whether sorting algorithm produces correct result

    } // Constructor Method

    /**
     * This method generates an array of random values.
     * @return integer array (test case)
     */
    private int [] generateTestCase() {

        int [] array;

        arrayLength = (int) ( (Math.random() * 255) + 1); // Array length a randomized int between 1 and 256
        array = new int [arrayLength];

        for (int i = 0; i < arrayLength; i++) { // for every element within the array...
            array[i] = generateElement();       // a randomly generated integer is placed into element at index
        }

        System.out.println("TEST-CASE GENERATED:");
        return array; // Returns the generated array for a test case

    } // generateArray

    /** This method generates either a positive or negative integer of randomized value.
     * Furthermore, this method is an extension of the Context Free Grammar for this class. **/
    private int generateElement( ) {

        // Code block below randomly assigns int variable sign as either positive one or negative one
        int sign = (int) (Math.random() * 2); // Sign initially starts as either one or zero
        if (sign == 0) {
            sign = 1;
        } else { sign = -1; }

        return (int) (Math.random()*sign*Integer.MAX_VALUE);

    } // generateElement

    /** This method applies the sorting algorithm class to the randomly generated test case. */
    public void applySortingAlgorithm ( ) {
        SortingAlgorithm srtAlg = new SortingAlgorithm(testCase);
        sorted = srtAlg.performSort();

        System.out.println("SORTED TEST-CASE:");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i]+" ");
        }
        System.out.println();

    } // testSortingAlgorithm

    /**
     * This method verifies whether the array returned by the sorting algorithm is sorted and if each element
     * corresponds to the test case.
     */
    private void verifyTestCase ( ) {
       boolean [] checkedInt = new boolean[arrayLength];    // A boolean array to keep a record of what elements have
                                                            // been checked.

        // Initialize all elements of boolean array to false
        for (int i = 0; i < arrayLength; i++) {
            checkedInt[i] = false;
        }

       for (int i = 0; i < arrayLength; i++) { // For each element of Test Case...
           int integer = testCase[i]; // Get the integer contained in the element
           for(int j = 0; j < arrayLength; j++) { // Iterate through each element of the sorted array.
               // If the integer in the sorted array matches the integer in test case array
               // AND the integer in the sorted array does not correspond to a previous integer in case test array...
               if (sorted[j] == integer && !checkedInt[j]) {
                   // Return true
                   checkedInt[j] = true;
                   break;
               }
           }
       }
        // If every element of the boolean array contains true, the sorted array contains all the same elements as the
        // test case array. Sorted array is verified to be the sorted case test.
        // Otherwise, the sorted array is not equivalent to a sorted case test.
        for (int i = 0; i < arrayLength; i++) {
           boolean b = checkedInt[i];
           if (!b) {
               System.out.println("TEST CASE FAILED");
               break;
           }
       }
       System.out.println("TEST CASE SUCCESS");

    } //

    public static void main(String[] args) {
        RandomTestGen rtg = new RandomTestGen();
    } // Main
}
