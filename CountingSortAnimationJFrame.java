import javax.swing.*;
import java.awt.*;


public class CountingSortAnimationJFrame extends JFrame {
    public CountingSortAnimationJFrame(){
        super("Counting Sort Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        JPanel CountingSortAnimation = new CountingSortAnimation();
        add(CountingSortAnimation);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CountingSortAnimationJFrame();
    }
}
