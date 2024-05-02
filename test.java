import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class test {
	private static JButton predict, add;
	private static Window mainW;
	private static JButton backButton = new JButton("Back to main menu");
	public static void main(String[] args) {
		try {			
			mainW = new Window("Diagno-Health");	
			predict = new JButton("Predict");
			predict.setBounds((mainW.getWidth() - 200) / 2, (mainW.getHeight() - 60)/2 - 35, 200, 60);
			mainW.getContentPane().add(predict);
			add = new JButton("Add");
			add.setBounds((mainW.getWidth() - 200) / 2, (mainW.getHeight() - 60)/2 + 35, 200, 60);
			mainW.getContentPane().add(add);
	        predict.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	try {
	            		predict predict = new predict("Diagno-Health : Predict");
	            		add(predict);
	            		mainW.dispose();
	            	}catch(Exception error) {
	            		error.printStackTrace();
	            	}
	            }
	        });
	        add.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                add add = new add("Diagno-Health : Add");
	                add(add);
	                mainW.dispose();
	            }
	        });
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void add(JFrame w) {
        backButton.setBounds(w.getWidth()-100, w.getHeight()-70, 80, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	w.dispose();
                mainW.setVisible(true);
            }
        });
        w.add(backButton);
	}
}
