package project.view;

import project.interfaces.ViewListener;
import project.view.menu.Menu;
import project.view.panels.ButtonsPanel;
import project.view.panels.MedicalTestPanel;
import project.view.panels.PatientPanel;
import project.view.panels.PatientsListPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class View extends JFrame implements ActionListener, MouseListener {
	
	private Menu menu;
	
	private JPanel mainPanel, leftPanel, rightPanel;
	
	private PatientPanel patientPanel;
	private MedicalTestPanel medicalTestPanel;
	private PatientsListPanel patientsListPanel;
	private ButtonsPanel buttonsPanel;
	
	private ViewListener viewListener = null;
	
	
	public View() {
		
		this.createGui();
		this.setWindowProperties();
	}
	
	
	public void createGui() {
		
		//menu
		this.menu = new Menu();
		setJMenuBar(this.menu.getMenuBar());
		
		//panels
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.X_AXIS));
		this.getContentPane().add(this.mainPanel);
		
		this.leftPanel = new JPanel();
		this.leftPanel.setLayout(new BoxLayout(this.leftPanel, BoxLayout.Y_AXIS));
		this.mainPanel.add(this.leftPanel);
		
		this.rightPanel = new JPanel();
		this.rightPanel.setLayout(new BoxLayout(this.rightPanel, BoxLayout.Y_AXIS));
		this.rightPanel.setBorder(BorderFactory.createTitledBorder("Lista pacjentów"));
		this.mainPanel.add(this.rightPanel);
		
		
		this.patientPanel = new PatientPanel(410, 235);
		this.leftPanel.add(this.patientPanel);
		
		this.medicalTestPanel = new MedicalTestPanel(410, 200);
		this.leftPanel.add(this.medicalTestPanel);
		
		this.patientsListPanel = new PatientsListPanel(700, 400);
		this.rightPanel.add(this.patientsListPanel);
		
		this.buttonsPanel = new ButtonsPanel(700, 35);
		this.rightPanel.add(this.buttonsPanel);
		
		
		this.menu.getMClose().addActionListener(this);
		this.patientPanel.getBSavePatient().addActionListener(this);
		this.patientPanel.getBCancelPatient().addActionListener(this);
		this.medicalTestPanel.getBSaveMedicalTest().addActionListener(this);
		this.medicalTestPanel.getBCancelMedicalTest().addActionListener(this);
		this.patientsListPanel.getTabPatientsList().addMouseListener(this);
		this.buttonsPanel.getBAdd().addActionListener(this);
		this.buttonsPanel.getBDelete().addActionListener(this);
		
		this.pack();
	}
	
	
	public void setWindowProperties() {
		
		this.setSize(1110, 500);
		this.setResizable(false);
		this.setTitle("Rejestracja wyników badań");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void completePatientPanel() {
		
		Object[] patientInfo = this.patientsListPanel.getSelectedPatientInfo();
		this.patientPanel.completePatientPanel(patientInfo);
	}
	
	
	//messageDialogs
	public void showDialog(String str) {
		
        JOptionPane.showMessageDialog(this, str, "Uwaga", JOptionPane.WARNING_MESSAGE);
	}
	

	//getters
	public Menu getMenu() {
		
		return this.menu;
	}
	
	
	public PatientPanel getPatientPanel() {
		
		return this.patientPanel;
	}
	
	
	public MedicalTestPanel getMedicalTestPanel() {
		
		return this.medicalTestPanel;
	}


	public PatientsListPanel getPatientsListPanel() {
		
		return this.patientsListPanel;
	}
	
	
	public ButtonsPanel getButtonsPanel() {
		
		return this.buttonsPanel;
	}
	
	
	//listeners management
	public void addListener(ViewListener vListener) {
	    
		this.viewListener = vListener;
    }

	
	@Override
	public void actionPerformed(ActionEvent e) {

		this.viewListener.viewChanged(this, e.getSource());
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		this.viewListener.viewChanged(this, e.getSource());
	}

	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
