package com.bticino.core;

/***************************************************************************
 * 			                AvviaCicloComando.java                         *
 * 			              --------------------------                       *
 *   date          : Oct 5, 2004                                           *
 *   Copyright (c)     : (C) 2005 by Bticino S.p.A. Erba (CO) - Italy 	       *
 *   				 Embedded Software Development Laboratory              *
 *   license       : GPL                                                   *
 *   email         : 		             				                   *
 *   web site      : www.bticino.it; www.myhome-bticino.it                 *
 ***************************************************************************/

/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

/**
 * Description: Thread send to command OPEN cycle
 * 
 */
public class StartCommandCycle extends Thread
{
	
	String name = null;
	String comand = null;
	int passwordOpen = 0;
	Integer passInt = null;
	String pass = null;
	int sleepTime;
	static int cycleState = 1;
	CommandsSocket gestSocketComandi;
	
	/**
	 * Costruttore
	 * 
	 * @param threadName
	 *            Nome del Thread
	 * @param comandoOpen
	 *            Comando open da inviare in sequenza
	 * @param sleep
	 *            Tempo di attesa tra l'invio di due comandi consecutivi
	 */
	public StartCommandCycle(String threadName, String comandoOpen, int sleep, CommandsSocket gestSocketComandi)
	{
		name = threadName;
		comand = comandoOpen;
		sleepTime = sleep;
		this.gestSocketComandi = gestSocketComandi;
	}
	
	public void stopCommandCycle()
	{
		StartCommandCycle.cycleState = 0;
	}
	
	/**
	 * Avvio del Thread
	 */
	@Override
	public void run()
	{
		while (cycleState != 0)
		{ // fino a che non viene premuto il
			// pulsante stop resta nel ciclo
			if (comand != null)
			{
				try
				{
					Thread.sleep(sleepTime);
				}
				catch (InterruptedException e)
				{
					
					System.out.println("Thread interrotto: <AvviaCicloComando>");
					e.printStackTrace();
				}
				if (CommandsSocket.state == 0)
				{ // non sono
					// ancora
					// connesso
					if (gestSocketComandi.connect())
					{
						try
						{
							gestSocketComandi.invia(comand);
						}
						catch (Exception e)
						{
							
							e.printStackTrace();
						}
					}
					else
					{
						// Connessione KO
					}
				}
				else if (CommandsSocket.state == 3)
				{ // sono
					// già
					// connesso
					try
					{
						gestSocketComandi.invia(comand);
					}
					catch (Exception e)
					{
						
						e.printStackTrace();
					}
				}
				else
				{
					// System.out.print("QQQQQQQQQQQUUUUUUUUUUUUUUUUUUUUUUUUUUUUIIIIIIIIIIIIIIII");
				}
			}
		}
		// System.out.print("QQQQQQQQQQQUUUUUUUUUUUUUUUUUUUUUUUUUUUUIIIIIIIIIIIIIIII2");
		
		// termino il thread durata
		// if (ClientFrame.threadDurata != null)
		// ClientFrame.threadDurata.interrupt();
	}
	
}
