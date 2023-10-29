import javax.swing.*;
import java.awt.*;

public class Runner extends JFrame{
    public Runner(){
        super("Counting Sort Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        JFrame TitlePage = new TitlePage();
        TitlePage.setVisible(true);
    }

    public static void main(String[] args) {
        new Runner();
    }
}
