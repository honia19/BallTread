package ball.pack.GUI;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BFrame extends JFrame
{	
	public BFrame()
	{
		setBounds(50, 50, 600, 600);
		setTitle("Balls");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new BPanel() );
		setVisible(true);
	}
}
