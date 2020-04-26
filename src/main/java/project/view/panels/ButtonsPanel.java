package project.view.panels;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	
	private JButton bAdd, bDelete;
	
	
	public ButtonsPanel(int x, int y) {
		
		super();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(x,y));
		
		
		this.bAdd = new JButton("Dodaj");
		this.bAdd.setBounds(20, 5, 80, 20);
		this.add(this.bAdd);
		
		this.bDelete = new JButton("Usuń");
		this.bDelete.setBounds(105, 5, 80, 20);
		this.add(this.bDelete);
		
		this.bDelete.setEnabled(false);
	}


	public JButton getBAdd() {
		
		return this.bAdd;
	}
	
	
	public JButton getBDelete() {
		
		return this.bDelete;
	}
	
	
	public void disableButtonsPanel() {
		
		this.bAdd.setEnabled(false);
		this.bDelete.setEnabled(false);
	}
	
	
	public void disableButtonsPanel(String button) {
		
		if (button == "add") {
			this.bAdd.setEnabled(false);
		} else if (button == "delete") {
			this.bDelete.setEnabled(false);
		}
	}
	
	
	public void enableButtonsPanel() {
		
		this.bAdd.setEnabled(true);
		this.bDelete.setEnabled(true);
	}
}
