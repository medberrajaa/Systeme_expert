import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class add extends Window{
	private static final long serialVersionUID = 1L;
	private JPanel panel;
    private int textFieldCount = 1;
    private int x, y;
    private JTextField diagnosisField;
	add(String title){
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
        addButton.setPreferredSize(new Dimension(150, 50));  
        JTextField firstTextField = new JTextField();
        firstTextField.setPreferredSize(new Dimension(340, 40));
        diagnosisField = new JTextField("Diagnosis");
        diagnosisField.setPreferredSize(new Dimension(340, 30));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldCount < 4) {
                    JTextField newTextField = new JTextField();
                    newTextField.setPreferredSize(new Dimension(340, 40));
                    panel.add(newTextField);
                    textFieldCount++;
                    panel.revalidate();
                }
            }
        });

        JButton send = new JButton("Send");
        send.setPreferredSize(new Dimension(150, 30));  
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> resultList = new ArrayList<>();
                String diagnosis = "";
                for (Component component : panel.getComponents()) {
                    if (component instanceof JTextField) {
                        JTextField textField = (JTextField) component;
                        if(textField == diagnosisField) {
                        	diagnosis = textField.getText().trim();
                        }else {
                        	String text = textField.getText().trim();
                        	if (!text.isEmpty()) {
                        		resultList.add(text);
                        	}                        	
                        }
                    }
                }
                MongoConnect mongoConnect = new MongoConnect();
				mongoConnect.setData(diagnosis, resultList);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());  
        buttonPanel.add(addButton);
        buttonPanel.add(send);

        panel.add(buttonPanel, BorderLayout.CENTER);  
        panel.add(diagnosisField, BorderLayout.EAST);   
        panel.add(firstTextField);

        this.add(panel);
        this.setVisible(true);
	}
}
