package DeltaDebugging;

/**
 * This class uses Delta Debugging to find the 1-minimal case of an input string causing a bug if the bug exists.
 * An input string is processed by a function in this class, generating a different string. The input string and
 *processed string are compared to verify the string is processed correctly.
 */
public class ProcessStringDebugger {

    /**
     * This constructor receives an input string to process. Once a processed string is returned,
     * the constructor performs delta debugging using the input string and processed string as arguments.
     */
    ProcessStringDebugger (String inStr) {
        String outStr = processString(inStr);
        deltaDebug(inStr,outStr);
    } // Constructor

    /**
     * This method recursively prints the first instance of an error in the input
     * @param input String to be processed
     * @param output String returned after processing
     */
    private boolean deltaDebug (String input, String output) {

        // If the length of the input String is greater than zero, the String contains characters. The output string
        // must therefore also contain characters. Else, the input and output are empty Strings; nothing to perform
        // delta debugging with.
        if (input.length() > 0) {

            // If input String and output string are not the same...
            if (!compareStrings(input, output)) {
                // ... a bug must exist in String output, therefore partition input
                System.out.println("BUG DETECTED IN: "+input);

                // Partition input String into a substring containing all elements to the left of the middle
                // of input string. Process the left partition and perform delta debugging using the left partition
                // along with the string returned by processing the left partition.
                int mid = (int) Math.floor(input.length() / 2);
                String in1 = input.substring(0,mid);
                String out1 = processString(in1);
                boolean bool = deltaDebug(in1,out1);

                if(bool) {  // If performing delta debugging on the left substring returns true...
                    // ... the right substring must contain the bug. Partition the input string into a substring
                    // containing all elements right of the middle. Perform delta debugging using the right substring
                    // along with the processed substring.
                    mid = (int) Math.ceil(input.length() / 2);
                    in1 = input.substring(mid);
                    out1 = processString(in1);
                    return deltaDebug(in1,out1);
                }
                else {  // Else, the bug is present in this partition of the input string. Perform recurrence of delta
                        // debugging using this partition.
                    return false;
                }
            } else {
                System.out.println("NO BUGS IN: "+input);
                return true;
            }
        }
        return false;
    }

    /**
     * This method compares two strings.
     * @param input A String
     * @param output String being compared to input
     * @return True if strings match, otherwise false
     */
    private boolean compareStrings(String input, String output) {

        // Convert input string and output string to character arrays to compare each character individually.
        char[] inputCharacters = input.toCharArray();
        char[] outputCharacters = output.toCharArray();

        // If input string has a different length of than output string, return false;
        if (inputCharacters.length != outputCharacters.length) {
            return false;
        }

        for (int i = 0; i < inputCharacters.length; i++ ) { // For each character of the input String...

            // ... if the character is a digit, check if it matches with the digit at the same index in output.
            if (Character.isDigit(inputCharacters[i])) {
                if (inputCharacters[i] != outputCharacters[i]) {
                    return false;
                }
            }

            // ... else if the character is uppercase, check if the character at the same index in output is the
            // corresponding lowercase
            else if (Character.isUpperCase(inputCharacters[i])) {
                if ( inputCharacters[i] != Character.toUpperCase(outputCharacters[i]) ) {
                    return false;
                }
            }

            // ... else, the character is lowercase; check if the character at the same index in output is the
            // corresponding uppercase.
            else {
                if ( inputCharacters[i] != Character.toLowerCase(outputCharacters[i]) ) {
                    return false;
                }
            }
        }
        return true; // Return true if the strings match
    }

    /**
     * This method is the java translated version of the Python function.
     * @param str String to be processed
     * @return Processed String
     */
    private String processString(String str) {
        char[] stringChars = str.toCharArray();
        String output = "";

        for (int i = 0; i < stringChars.length; i++) {
            if( Character.isLowerCase(stringChars[i])) {
                output = output+Character.toUpperCase(stringChars[i]);
            }
            else if (Character.isDigit(stringChars[i])) {
                output = output + stringChars[i]+stringChars[i];
            }
            else {
                output = output + Character.toLowerCase(stringChars[i]);
            }
        }
        return output;
    }

    public static void main (String [] args) {
        ProcessStringDebugger psd = new ProcessStringDebugger("abcdefG8");
        System.out.println();
        ProcessStringDebugger psd2 = new ProcessStringDebugger("CDDEExy");
        System.out.println();
        ProcessStringDebugger psd3 = new ProcessStringDebugger("1234567b");
        System.out.println();
        ProcessStringDebugger psd4 = new ProcessStringDebugger("8665");
    }

}
