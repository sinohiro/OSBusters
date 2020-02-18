import java.awt.*;
import javax.swing.*;

public class Title extends JPanel {
	private Image titleimage;

	public void setTitleImage(Image titleimage){
		this.titleimage = titleimage;
	}

	public Title() {
		setBackground(Color.black);
		setPreferredSize(new Dimension(1280, 720));
		setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync();

		g.drawImage(this.titleimage, 0, 0, this);

		requestFocusInWindow();
	}
}
