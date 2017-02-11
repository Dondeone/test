package de.Kruse.commands;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScreenReciever
{
	private Socket client;
	private InputStream in;
	private static final int PORT = 5575;
	private JFrame frame;
	private Thread thread;
	private JLabel label;

	public ScreenReciever()
	{
		frame = new JFrame("ScreenRecieve");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(500, 500);

		label = new JLabel();
		label.setBounds(0, 0, 500, 500);
		frame.add(label);

		frame.setVisible(true);
	}

	public void connect(String ipadress)
	{
		thread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				try
				{
					while (true)
					{
						client = new Socket(ipadress, PORT);
						break;
					}

					in = client.getInputStream();

					ObjectInputStream oin = new ObjectInputStream(in);
					
					while (true)
					{
						oin.readObject();
						
						BufferedImage image = ImageIO.read(oin);
						if(image != null) setIcon(image);
					}

				} catch (IOException | ClassNotFoundException e)
				{
					e.printStackTrace();
				}

			}
		});

		thread.start();
	}

	private void setIcon(Image img)
	{
		label.setIcon(new ImageIcon(img.getScaledInstance(500, 500, Image.SCALE_FAST)));
		label.repaint();
	}

}
