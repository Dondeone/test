package de.Kruse;

import de.Kruse.commands.ScreenReciever;

public class Launch_RecieveServer
{
	public static void main(String[] args)
	{
		ScreenReciever s =new ScreenReciever();
		s.connect("192.168.2.104");
	}
}	
