import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JTextField;

public class predict extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
    private int textFieldCount = 1;
    private int x, y;
    private JTextArea predictionArea;
	private final String plFile = "prolog/prolog.pl";
    public predict(String title) throws Exception{
        super(title);
        this.setSize(new Dimension(1000, 600));
        this.setMinimumSize(new Dimension(1000, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMaximumSize(new Dimension(1000, 600));
        x = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - this.getWidth()) / 2);
        y = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setLayout(new FlowLayout());  

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  

        JButton addButton = new JButton("Add Text Field");
        addButton.setBounds(10, 10, 150, 50);
        JTextField firstTextField = new JTextField();
        firstTextField.setPreferredSize(new Dimension(340, 40));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldCount < 4 ) {
                	JTextField newTextField = new JTextField();
                	newTextField.setPreferredSize(new Dimension(340, 40));  
                	panel.add(newTextField);
                	textFieldCount++;
                	panel.revalidate();                	
                }
            }
        });
        predictionArea = new JTextArea();
        predictionArea.setEditable(false);  
        predictionArea.setLineWrap(true);   
        predictionArea.setWrapStyleWord(true);

        predictionArea.setPreferredSize(new Dimension(800, 400));

        Font areaFont = new Font("Arial", Font.PLAIN, 16); 
        predictionArea.setFont(areaFont);
        prolog p  = new prolog(plFile);
        JButton predictButton = new JButton("Predict");
        predictButton.setBounds(10, 220, 150, 30);
        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                ArrayList<String> resultList = new ArrayList<>();
                predictionArea.setText("");
                for (Component component : panel.getComponents()) {
                    if (component instanceof JTextField) {
                        JTextField textField = (JTextField) component;
                        String text = textField.getText().trim().replace(" ", "_").replace("(","").replace(")","").toLowerCase();
                        if (!text.isEmpty()) {
                            resultList.add(text);
                        }
                    }
                }
                if (resultList.toString().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No input in text fields!");
                } else {
                    String[] resultArray = resultList.toArray(new String[0]);
                    try {
                    	predictionArea.setText(p.getResults(resultArray).replace("_"," "));                    	
                    }catch(Exception error) {
                    	error.printStackTrace();
                    }
                }
            }
        });
        panel.add(addButton);
        panel.add(firstTextField);
        this.add(panel);
        this.add(predictButton);
        this.add(predictionArea);
        this.add(panel);
        this.add(predictionArea);
        this.setVisible(true);
    }
}
