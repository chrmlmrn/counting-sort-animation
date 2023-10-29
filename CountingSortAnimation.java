import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CountingSortAnimation extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static final int BOX_WIDTH = 60;
    private static final int BOX_HEIGHT = 35;
    private static final int BOX_PADDING = 10;

    private int boxIndex = 1;
    private Timer timer;
    private int currentIndex = 0;
    private int firstHighlightedBoxIndex;
    private int secondHighlightedBoxIndex = -1;
    private int outputHighlightedBoxIndex = -1;
    private Timer firstHighlightTimer;
    private Timer secondHighlightTimer;

    private int[] arr;
    private int[] unsortedArr;

    public CountingSortAnimation() {
        super();
        setPreferredSize(new Dimension(600, 200));
        setBackground(Color.WHITE);

        // Prompt the user to input values for the array and split them into individual strings.
        String input = JOptionPane.showInputDialog(null, "Enter numbers separated by spaces:");

        // If no input is given or the input is only whitespace, prompt the user to enter a number and exit the program.
        if (input == null || input.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter a number");
            System.exit(0);
        } else {
            // Convert the input strings to integers and store them in an array.
            String[] nums = input.split(" ");
        arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            try {
                arr[i] = Integer.parseInt(nums[i]);
            } catch (NumberFormatException e) {
                // If a non-integer value is entered, prompt the user and exit the program.
                JOptionPane.showMessageDialog(null, "String or Character is not allowed.");
                System.exit(0);
            }
        }
    } 
        initArray(arr);
    }

    public void initArray(int[] arr) {
        this.arr = arr;
        this.unsortedArr = arr.clone(); // create a copy of the input array
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (boxIndex == arr.length) {
            // Animation finished
            timer.stop();
            return;
        }

        // Step 1: Count the occurrences of each value in the array
        int[] count = new int[100];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        // Step 2: Modify the count array to show the position of each value in the
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Create the output array and populate it in order
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        // Step 4: Copy the output array back to the original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }

        repaint();
    }

    // Override the default paintComponent method to customize the drawing behavior
    // This method is responsible for rendering the graphical representation of the component
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the initial coordinates for drawing the component
        int x = BOX_PADDING;
        int y = BOX_PADDING;


        Font newFont = new Font("Arial", Font.BOLD, 16); // create a new Font object with Arial, bold, size 18
        g.setFont(newFont); // set the font to use for drawing text

        // Draw the unsorted array
        x = BOX_PADDING + 140;
        y = BOX_PADDING * 2 + BOX_HEIGHT * 2;
        // Draw labels for user input and index
        g.drawString("User Input: ", x - 100, y - 30 + BOX_HEIGHT + (BOX_PADDING / 2) + 10); 
        g.drawString("Index: ", x - 100, y + BOX_HEIGHT + (BOX_PADDING / 2) + 20); 
        g.drawString("Step 1: Count the occurrences of each value in the array", x - 10, y + BOX_HEIGHT + (BOX_PADDING / 2) + 80); 
        // Iterate through the unsorted array and draw each element
        for (int i = 0; i < unsortedArr.length; i++) {
            // Change the color to light gray for elements that have been processed
            Color color = Color.WHITE;
            if (i <= currentIndex) {
                color = Color.LIGHT_GRAY; 
            }

            g.setColor(color);
            // Set the line width to 2 pixels
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.fillRect(x, y, BOX_WIDTH, BOX_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, BOX_WIDTH, BOX_HEIGHT);
            g.drawString(String.valueOf(unsortedArr[i]), x + (BOX_WIDTH / 2), y + (BOX_HEIGHT / 2));
            int stringY = y + BOX_HEIGHT + (BOX_PADDING / 2);
            g.drawString(String.valueOf(i), x + (BOX_WIDTH / 2), stringY + 20);
            x += BOX_WIDTH + BOX_PADDING;
            x += BOX_PADDING;
        }

        // Draw the count array
        x = BOX_PADDING + 140;
        y = BOX_PADDING * 3 + BOX_HEIGHT * 6;
        // Draw labels for count and index
        g.drawString("Count: ", x - 100, y - 30 + BOX_HEIGHT + (BOX_PADDING / 2) + 10); 
        g.drawString("Index: ", x - 100, y + BOX_HEIGHT + (BOX_PADDING / 2) + 20); 
        g.drawString("Step 2: Modify the count array to update each element by adding the previous element", x - 10, y + BOX_HEIGHT + (BOX_PADDING / 2) + 80); 
        // Find the maximum value in the array
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // Create a count array to hold the frequency of each value
        int[] count = new int[max + 1];
        // Iterate through the input array and update the count array
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        // Iterate through the count array and draw each element
        for (int i = 0; i < count.length; i++) {
            // Change the color to yellow for the current index being processed
            Color color = Color.WHITE;
            if (currentIndex < unsortedArr.length && i == unsortedArr[currentIndex]) {
                color = Color.PINK;
            }
            g.setColor(color);
            g.fillRect(x, y, BOX_WIDTH, BOX_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, BOX_WIDTH, BOX_HEIGHT);
            g.drawString(String.valueOf(count[i]), x + (BOX_WIDTH / 2), y + (BOX_HEIGHT / 2));
            int stringY = y + BOX_HEIGHT + (BOX_PADDING / 2);
            g.drawString(String.valueOf(i), x + (BOX_WIDTH / 2), stringY + 20);
            x += BOX_WIDTH + BOX_PADDING;
            x += BOX_PADDING;
        }

        // Draw modified count array boxes
        x = BOX_PADDING + 140;
        y = BOX_PADDING * 4 + BOX_HEIGHT * 10;
        g.drawString("Modified Count: ", x - 130, y - 30 + BOX_HEIGHT + (BOX_PADDING / 2) + 10); 
        g.drawString("Index: ", x - 100, y + BOX_HEIGHT + (BOX_PADDING / 2) + 20); 
        g.drawString("Step 3: Find the index of each element of the original array in count array and place the elements in output array", x - 10, y + BOX_HEIGHT + (BOX_PADDING / 2) + 80); 
        int[] modifiedCount = new int[count.length];
        modifiedCount[0] = count[0];

        for (int i = 1; i < count.length; i++) {
            modifiedCount[i] = modifiedCount[i - 1] + count[i];
        }

        // Draw modified count array boxes with highlight for i and i-1
        for (int i = 0; i < modifiedCount.length; i++) {
            g.setColor(Color.WHITE);
            // step 3 animation
            if (currentIndex == unsortedArr.length && firstHighlightedBoxIndex >= 0
                    && (i == firstHighlightedBoxIndex || i == firstHighlightedBoxIndex + 1)) {
                g.setColor(Color.YELLOW);
            }

            //step 4 animation
            //secondHighlightedBoxIndex 
            if (secondHighlightedBoxIndex >= 0 && modifiedCount[i] - 1 == secondHighlightedBoxIndex) {
                g.setColor(Color.ORANGE);
            }

            g.fillRect(x, y, BOX_WIDTH, BOX_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, BOX_WIDTH, BOX_HEIGHT);
            g.drawString(String.valueOf(modifiedCount[i]), x + (BOX_WIDTH / 2), y + (BOX_HEIGHT / 2));
            int stringY = y + BOX_HEIGHT + (BOX_PADDING / 2);
            g.drawString(String.valueOf(i), x + (BOX_WIDTH / 2), stringY + 20);
            x += BOX_WIDTH + BOX_PADDING;
            x += BOX_PADDING;

        }

        // Draw the output array
        x = BOX_PADDING + 140;
        y = BOX_PADDING * 5 + BOX_HEIGHT * 14;
        g.drawString("Output: ", x - 100, y - 30 + BOX_HEIGHT + (BOX_PADDING / 2) + 10); 
        g.drawString("Index: ", x - 100, y + BOX_HEIGHT + (BOX_PADDING / 2) + 20); 
        int[] output = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[modifiedCount[arr[i]] - 1] = arr[i];
            modifiedCount[arr[i]]--;
        }
        for (int i = 0; i < output.length; i++) {
            g.setColor(Color.WHITE);
            //step 4 animation
            //outputHighlightedBoxIndex
            if (outputHighlightedBoxIndex >= 0 && i <= outputHighlightedBoxIndex) {
                g.setColor(Color.ORANGE); 
            } 

            g.fillRect(x, y, BOX_WIDTH, BOX_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, BOX_WIDTH, BOX_HEIGHT);
            g.drawString(String.valueOf(output[i]), x + (BOX_WIDTH / 2), y + (BOX_HEIGHT / 2));
            int stringY = y + BOX_HEIGHT + (BOX_PADDING / 2);
            g.drawString(String.valueOf(i), x + (BOX_WIDTH / 2), stringY + 20);
            x += BOX_WIDTH + BOX_PADDING;
            x += BOX_PADDING;
        }

        // timer section
        if (currentIndex < unsortedArr.length) {
            if (timer == null) {
                timer = new Timer(1500, new ActionListener() { // change the color every second
                    @Override
                    //FIRST ANIMATION
                    public void actionPerformed(ActionEvent e) {
                        currentIndex++;
                        if (currentIndex == unsortedArr.length) {
                            timer.stop();
                            timer = null;

                            // Stop the highlight timer and reset the highlighted box index
                            if (firstHighlightTimer != null) {
                                firstHighlightTimer.stop();
                            }
                            firstHighlightedBoxIndex = -1;
                            // Start the animation of the modified count array
                            //SECOND ANIMATION
                            firstHighlightTimer = new Timer(1500, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    // Increment highlighted box index and repaint
                                    firstHighlightedBoxIndex++;
                                    if (firstHighlightedBoxIndex >= modifiedCount.length - 1) {
                                        firstHighlightTimer.stop();
                                        firstHighlightedBoxIndex = -1;
                                        // Start the second animation of the modified count array
                                        //THIRD ANIMATION
                                        secondHighlightTimer = new Timer(1500, new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                secondHighlightedBoxIndex++;
                                                outputHighlightedBoxIndex++;
                                                if (secondHighlightedBoxIndex >= modifiedCount.length - 1 && secondHighlightedBoxIndex >= output.length) {
                                                    secondHighlightTimer.stop();
                                                    secondHighlightedBoxIndex = -1;
                                                    outputHighlightedBoxIndex = - 1;
                                                }
                                                repaint();
                                            }
                                        });
                                        secondHighlightTimer.start();
                                    }
                                    repaint();
                                }
                            });
                            firstHighlightTimer.start();
                        }
                        repaint();
                    }
                });
                timer.start();
            }
        }
    }
}