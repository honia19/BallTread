package ball.pack.GUI;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import ball.pack.API.Ball;

@SuppressWarnings("serial")
public class BPanel extends JPanel implements MouseListener
{	
	public BPanel()
	{
		setLayout(null);
		addMouseListener(this);
	}	
	@Override
	public void mousePressed(MouseEvent e)
	{
		add(new Ball( e.getPoint(),80));
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub		
	}

	

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub		
	}
}
