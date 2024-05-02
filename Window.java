import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Window extends JFrame{
	private static final long serialVersionUID = 1L;
	private int x, y;
	Window(String title)throws HeadlessException{
		super(title);
		this.setSize(new Dimension(1000,600));
		this.setMinimumSize(new Dimension(1000,600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMaximumSize(new Dimension(1000,600));
		x = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - this.getWidth()) / 2);
		y = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);
		getContentPane().setLayout(null);
		this.setVisible(true);
	}
}
