package ball.pack.API;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

@SuppressWarnings("serial")
public class Ball extends JPanel implements Runnable
{
	Point p;
	Random rnd = new Random();
	int dx = rnd.nextInt(10);// движение по х
	int dy = rnd.nextInt(10);// движение по у
	//int dz = rnd.nextInt(70);
	float r = rnd.nextFloat();
	float g = rnd.nextFloat();
	float b = rnd.nextFloat();
	Color color = new Color(r, g, b);
	int size = 30;

	public Ball(Point p, int size) 
	{
		setLocation(p);
		setSize(size, size);
		setBackground(new Color(0, 0, 0, 0));
		setVisible(true);
		new Thread(this).start();
		this.size = size;
		addMouseListener(new MyMouseAdapter(this));
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D gg = (Graphics2D) g;
		gg.setColor(color);
		gg.fillOval(0, 0, size, size);
	}

	public void move() 
	{
		JPanel parent = (JPanel) getParent();
		if(parent!= null)
		{
		if (getX() <= parent.getLocation().getX() || getX() >= parent.getWidth() - 40)
		{
			dx = -dx;
		}
		if (getY() <= parent.getLocation().getY() || getY() >= parent.getHeight() - 40)
		{
			dy = -dy;
		}
//		if(getWidth()>=10 || getWidth()<=80)
//		{
//			dz = -dz;
//		}
		
		Component[] cc = parent.getComponents();
		
		for (Component component : cc) 
		{
			Ball b = (Ball) component;
			if (b != this) 
			{
				if (Proverka(b, this)) 
				{
					if (size > b.size) 
					{
						eat(this, b);
					} 
					else if (size < b.size)
					{
						eat(b, this);
					} 
					else 
					{
						dx = -dx;
						dy = -dy;
						b.dx = -b.dx;
						b.dy = -b.dy;
					}
				}
			}
		}
	}
		//setSize(getWidth()+dz, getHeight()+dz);
		p = getLocation();
		p.translate(dx, dy);
		setLocation(p);
	
	}

	private void eat(Ball ball, Ball b2)
	{
		Panel pp = (Panel) getParent();
		if(pp!=null)
		{
			 pp.remove(b2);
			 pp.remove(ball);
			 pp.add(new Ball(ball.getLocation(), ball.size + b2.size));
		}
	  
	}

	class MyMouseAdapter extends MouseInputAdapter
	{
		Ball b = null;

		public MyMouseAdapter(Ball b) 
		{
			this.b = b;
		}

		@Override
		public void mousePressed(MouseEvent e) 
		{
			super.mousePressed(e);
			if (e.getButton() == e.BUTTON1)
			{
				JPanel panel = (JPanel) b.getParent();
				panel.remove(b);
			}
			else if (e.getButton() == e.BUTTON3)
			{
				JPanel pp = (JPanel) b.getParent();
				pp.remove(b);
				for (int i = 1; i <= 8; i++)
				{
					pp.add(new Ball(p.getLocation(), size/8));
				}
			}
		}
	}

	public boolean Proverka(Ball a, Ball b) 
	{
		return ((((a.getX() >= b.getX() && a.getX() <= b.getX() + b.size)
				|| 
				(a.getX() + a.size >= b.getX() && a.getX() + a.size <= b.getX() + b.size))
				&& 
				((a.getY() >= b.getY() && a.getY() <= b.getY() + b.size)
						|| 
						(a.getY() + a.size >= b.getY() && a.getY() + a.size <= b.getY() + b.size)))
				|| 
				(((b.getX() >= a.getX() && b.getX() <= a.getX() + a.size)
						|| 
						(b.getX() + b.size >= a.getX() && b.getX() + b.size <= a.getX() + a.size))
						&& 
						((b.getY() >= a.getY() && b.getY() <= a.getY() + a.size)
								|| 
								(b.getY() + b.size >= a.getY() && b.getY() + b.size <= a.getY() + a.size))))
				|| 
				((((a.getX() >= b.getX() && a.getX() <= b.getX() + b.size)
						|| 
						(a.getX() + a.size >= b.getX() && a.getX() + a.size <= b.getX() + b.size))
						&& 
						((b.getY() >= a.getY() && b.getY() <= a.getY() + a.size)
								|| 
								(b.getY() + b.size >= a.getY() && b.getY() + b.size <= a.getY() + a.size)))
						|| 
						(((b.getX() >= a.getX() && b.getX() <= a.getX() + a.size)
								|| 
								(b.getX() + b.size >= a.getX() && b.getX() + b.size <= a.getX() + a.size))
								&& 
								((a.getY() >= b.getY() && a.getY() <= b.getY() + b.size)
										|| 
										(a.getY() + a.size >= b.getY() && a.getY() + a.size <= b.getY() + b.size))));
	}

	@Override
	public void run()
	{
		while (true)
		{
			move();
			try 
			{
				Thread.sleep(50);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
