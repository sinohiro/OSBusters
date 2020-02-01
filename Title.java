import java.awt.*;
import javax.swing.*;

public class Title extends JPanel {
	private MyModel mm;

	public Title() {
		this.mm = new MyModel();
		setBackground(Color.black);
		setPreferredSize(new Dimension(1280, 720));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync();

		g.drawImage(mm.getTitleImage(), 0, 0, this);

		requestFocusInWindow();
	}
}
