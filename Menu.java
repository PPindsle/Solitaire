import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {
	
	public Menu(Game g) {
		JMenu options = new JMenu("Options");
		add(options);
		
		JMenuItem restart = new JMenuItem("Restart");
		options.add(restart);
		
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				g.restart();
			}
			
		});
	}

}
