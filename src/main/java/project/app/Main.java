package project.app;

import javax.swing.SwingUtilities;

import project.controller.Controller;
import project.model.Model;
import project.view.View;


public class Main {

	public static void main(String[] args) {
		
		Runnable program = new Runnable() {
			@Override
			public void run() {
				
				View view = new View();
				Model model = new Model(); 
				Controller controller = new Controller(view, model);
				
				view.addListener(controller);
			}	
		};
		SwingUtilities.invokeLater(program);
	}
}
