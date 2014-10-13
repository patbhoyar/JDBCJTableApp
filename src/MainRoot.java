import javax.swing.*;

/**
 * Created by admin on October/13/14.
 */
public class MainRoot {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JTableClass();
            }
        });

    }

}
