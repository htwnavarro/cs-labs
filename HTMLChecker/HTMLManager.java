import java.util.*;
import java.util.stream.Collectors;


// This class provides a way of fixing invalid HTML data by fixing missing or incorrectly placed html tags.
// Removes closing tags without corresponding opening tags
// Adds in the best logical place missing closing tags
// Provides a toString representation of the fixed HTML data

public class HTMLManager {
    private Queue<HTMLTag> tags;


    HTMLManager(Queue<HTMLTag> tags) {
        if (tags == null) {
            System.out.println("Null HTML data passed into HTMLManager");
            System.exit(1);
        }

        this.tags = tags;
    }

    // Pre: a tags array is assigned to the instance (see constructor)
    // Post: The HTMLTag queue on the instance is returned
    public Queue<HTMLTag> getTags() {
        return this.tags;
    }

    // Pre: a tags array is assigned to the instance (see constructor)
    // Post: a fixed HTMLTag queue is assigned to the instance, call (getTags) to get the fixed html after invocation
    public void fixHTML() {
        Deque<HTMLTag> stack = new ArrayDeque<>(); // a stack of operations to keep track of opening tags
        Queue<HTMLTag> fixedHTML = new LinkedList<>(); // the output

        // run through initially on the tags queue
        while (!tags.isEmpty()) {
            HTMLTag tag = tags.poll();

            if (tag.isSelfClosing()) {
                fixedHTML.add(tag); // simply append self-closing
            } else if (tag.isOpening()) {
                // push the opening tag to the stack and output
                stack.push(tag);
                fixedHTML.add(tag);
            } else {
                // closing tag
                HTMLTag openingTag = tag.getMatching();

                // if the top of the stack equals the matching opening tag for the current closing tag,
                // pop the open tag from the stack and append the closing tag to the output
                if (!stack.isEmpty() && stack.peek().equals(openingTag)) {
                    stack.pop(); // Pop the matching opening tag from the stack
                    fixedHTML.add(tag); // Append the closing tag
                }
            }
        }

        // handle remaining stack (tags needing closing) after the tags are processed
        while (!stack.isEmpty()) {
            HTMLTag openingTag = stack.pop();
            HTMLTag closingTag = openingTag.getMatching();
            fixedHTML.add(closingTag);
        }

        // reassign the tags queue
        tags = fixedHTML;
    }

    // Pre: a tags array is assigned to the instance (see constructor)
    // Post: a single-line string representation of the HTMLTag Queue is returned
    public String toString() {
        return this.tags.stream().map(tag -> tag.toString().trim()).collect(Collectors.joining());
    }
}
