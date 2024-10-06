import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

// PlainSequences is a class that will read in DNA sequences from a given file
// stores the metadata about the sequence (file name)
// and provides utility methods:
//  check sequence validity
//  format sequence in groups


public class PlainSequences implements Sequences {

    private List<String> descriptions;
    private List<String> sequences;

    PlainSequences() {
        descriptions = new ArrayList<String>();
        sequences = new ArrayList<String>();
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public List<String> getSequences() {
        return sequences;
    }

    public void readSequences(String fileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        String sequence = "";

        while (input.hasNextLine()) {
            sequence = sequence + input.nextLine();
        }

        descriptions.add(fileName);
        sequences.add(sequence);
    }

    // Pre: N/A -- can be invoked prior to readSequences but will return false
    // Post: returns a boolean if the sequence at a given position in sequences contains only valid DNA bases
    public boolean isValidSequence(int index) {
        // check sequences boundary
        if (index < 0 || index > sequences.size() - 1) {
            return false;
        }

        String sequence = sequences.get(index);
        boolean isValidSequence = true;

        for (int x = 0; x < sequence.length(); x++) { // loop the bases in the sequence
            DnaBase dnaBase;

            try {
                // try to get a DNABase value from the char at the sequence position
                dnaBase = DnaBase.valueOf(Character.toString(sequence.charAt(x)));
            } catch (IllegalArgumentException error) {
                // an exception is thrown if a char is not found in enum
                isValidSequence = false;
                break;
            }
        }

        return isValidSequence;
    }

    // Pre: sequences are read and populated within the instances (see readSequences)
    // otherwise throws IndexOutOfBoundsException
    // Post: returns the sequence at the provided index in sequences as
    // a formatted string for a given num chars in group and num groups in a line
    public String formatInGroups(int index, int basesPerGroup, int groupsPerLine) {
        boolean isValidSequence = this.isValidSequence(index);

        if (!isValidSequence) {
            throw new IndexOutOfBoundsException("Provided index " + index + " was not found in sequences of size " + sequences.size());
        }

        // get the sequence by position in the list
        String sequence = sequences.get(index);
        StringBuilder formattedSequence = new StringBuilder();
        // start a cursor to keep track of sequence position during loop
        int cursor = 0;
        // while the cursor is less than the sequence length (accounting for cursor being zero indexed, continue
        while (cursor < (sequence.length() - 1)) {
            // make the rows
            for (int group = 1; group <= groupsPerLine; group++) {
                // make the groups
                for (int base = 1; base <= basesPerGroup; base++) {
                    // if the sequence dries up during the loop, exit loop
                    if (cursor == sequence.length()) {
                        break;
                    }

                    // add the char to the string builder
                    formattedSequence.append(sequence.charAt(cursor));
                    // inc. the cursor
                    cursor++;

                    if (base == basesPerGroup) {
                        formattedSequence.append(" "); // split the groups with a space
                    }
                }
                if (group == groupsPerLine && cursor != sequence.length()) {
                    formattedSequence.append("\n"); // next group on new line if not the end of the sequence
                }
            }
        }

        return formattedSequence.toString();
    }

    // Pre: valid sequence is read into the instance at the provided index
    // Post: a map containing the count each substring occurs in a given sequence
    @Override
    public Map<String, Integer> generateFrequencies(int index) {
        HashMap<String, Integer> baseCount = new HashMap();
        boolean isValidSequence = isValidSequence(index);

        if (!isValidSequence) {
            throw new IndexOutOfBoundsException("Provided index " + index + " was not found in sequences of size " + sequences.size());
        }

        String sequence = sequences.get(index);
        int sequenceLength = sequence.length();

        for (int lCursor = 0; lCursor < sequenceLength; lCursor++) { // start outer loop that acts as left cursor
            StringBuilder substrBuilder = new StringBuilder(); // initialize a string builder for the current substring
            int rCursor = 0; // start a cursor for the end of the substring

            for (int baseIndex = lCursor; baseIndex < sequenceLength; baseIndex++) { // loop through the sequence moving end of substring cursor
                substrBuilder.insert(rCursor, sequence.charAt(baseIndex)); // insert the char at the current sequence index into substring
                String substr = substrBuilder.toString(); // substring as string reference

                // insert the substring into map, if found increment current value otherwise 1
                if (baseCount.containsKey(substr)) {
                    baseCount.put(substr, baseCount.get(substr) + 1);
                } else {
                    baseCount.put(substr, 1);
                }

                // move the end of substring cursor
                rCursor++;
            }
        }

        return baseCount;
    }

    // Pre: a valid sequence is read in at the given index (see readSequences and isValidSequence)
    // Post: a sorted list of the substrings in the sequence at a given index
    public List<String> getSortedListOfSubstrings(int index) {
        ArrayList<String> frequencyKeys = new ArrayList<>(
            generateFrequencies(index)
                .keySet()
                .stream()
                .toList()
        );

        Collections.sort(frequencyKeys);

        return frequencyKeys;
    }

    // Pre: a valid sequence is read in at the given index (see readSequences and isValidSequence)
    // Post: the reversed compliment for DNA sequence bases DnaBase.A, DnaBase.C, DnaBase.G, DnaBase.T
    public String getReverseComplement(int index) {
        // check sequence validity
        if (!isValidSequence(index)) {
            System.out.println("Invalid sequence");
            return null;
        }

        // build the queue
        String sequence = sequences.get(index);
        Queue<DnaBase> sequenceQueue = new LinkedList<>();
        for (int i = 0; i < sequence.length(); i++) {
            sequenceQueue.add(DnaBase.valueOf(Character.toString(sequence.charAt(i))));
        }

        // build the stack based on queue
        Stack<DnaBase> sequenceStack = new Stack();
        while (!sequenceQueue.isEmpty()) {
            DnaBase base = sequenceQueue.remove();
            sequenceStack.push(base);
        }


        // build the sequence complement from the stack
        StringBuilder sequenceReverseComplement = new StringBuilder();
        sequenceStack.forEach((DnaBase base) -> {
            switch (base) {
                case A -> {
                    sequenceReverseComplement.append(DnaBase.T);
                }
                case C -> {
                    sequenceReverseComplement.append(DnaBase.G);
                }
                case G -> {
                    sequenceReverseComplement.append(DnaBase.C);
                }
                case T -> {
                    sequenceReverseComplement.append(DnaBase.A);
                }
                // we weren't given an objective for other valid base, so
                // return the base as is but could throw too if desired.
                default -> sequenceReverseComplement.append(base);
            }
        });

        return sequenceReverseComplement
            .reverse() // reverse the complement
            .toString();
    }
}

/*
    Void Output
 */
