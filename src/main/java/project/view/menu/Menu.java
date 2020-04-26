package project.view.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class Menu extends JMenu {

	private JMenuBar menuBar;
	private JMenu menuApp;
	private JMenuItem mClose;
	
	
	public Menu() {
		
		super();
		
		this.menuBar = new JMenuBar();
		this.menuApp = new JMenu("Aplikacja");
		this.mClose = new JMenuItem("Zamknij");
		
		this.menuApp.add(this.mClose);
		this.menuBar.add(this.menuApp);
		this.mClose.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
	}
	
	
	public JMenuBar getMenuBar() {
		
		return this.menuBar;
	}
	
	
	public JMenuItem getMClose() {
		
		return this.mClose;
	}
}
