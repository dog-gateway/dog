package com.bticino.core;

/***************************************************************************
 * 			                 Monitoring.java                              *
 * 			              --------------------------                       *
 *   date          : Sep 6, 2004                                           *
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

import it.polito.elite.dog.drivers.bticino.interfaces.BTicinoReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 * Description: Gestisce tramite un thread la ricezione di tutti i messaggi che
 * passano sulla socket monitor
 * 
 */
public class Monitoring extends Thread
{
	Socket socketMon = null;
	BufferedReader inputMon = null;
	int num = 0;
	int indice = 0;
	boolean esito = false;
	char risposta[] = null;
	char c = ' ';
	int ci = 0;
	String responseString = null;
	volatile BTicinoReader breader;
	
	// String responseLine = null; //stringa in ricezione dal Webserver
	/**
	 * Costruttore
	 */
	public Monitoring(Socket sock, BufferedReader inp, BTicinoReader breader)
	{
		socketMon = sock;
		this.breader = breader;
		inputMon = inp;
	}
	
	/**
	 * Avvia il Thread per la ricezione dei messaggi sulla monitor
	 */
	@Override
	public void run()
	{
		do
		{
			SocketMonitor.responseLineMon = null;
			num = 0;
			indice = 0;
			esito = false;
			risposta = new char[1024];
			c = ' ';
			ci = 0;
			try
			{
				do
				{
					if (socketMon != null && !socketMon.isInputShutdown() && inputMon.ready())
					{
						ci = inputMon.read();
						
						if (ci == -1)
						{
							num = 0;
							indice = 0;
							c = ' ';
							// ClientFrame
							// .scriviSulLog(
							// "Mon: ----- Socket chiusa dal server -----",
							// 1, 1, 0);
							socketMon = null;
							SocketMonitor.statoMonitor = 0;
							break;
						}
						else
						{
							c = (char) ci;
							// System.out.println("Carattere ricevuto: "+c);
							if (c == '#' && num == 0)
							{
								risposta[indice] = c;
								num = indice;
								indice = indice + 1;
							}
							else if (c == '#' && indice == num + 1)
							{
								risposta[indice] = c;
								esito = true;
								break;
							}
							else if (c != '#')
							{
								risposta[indice] = c;
								num = 0;
								indice = indice + 1;
							}
						}
					}
					else
					{
						// System.out.println("&&&&& socket nulla");
					}
				}
				while (esito != true);
			}
			catch (IOException e)
			{
				System.out.println("Mon eccezione: ");
				e.printStackTrace();
			}
			
			if (esito == true)
			{
				responseString = new String(risposta, 0, indice + 1);
				SocketMonitor.responseLineMon = responseString;
			}
			else
			{
				SocketMonitor.responseLineMon = null;
				SocketMonitor.statoMonitor = 0;
				break;
			}
			
			// ClientFrame.scriviSulLog("Mon:
			// "+SocketMonitor.responseLineMon,0,0,1);
			System.out.println("Mon: " + SocketMonitor.responseLineMon);
			try
			{
				if (breader != null)
					breader.read(new OpenWebNet(SocketMonitor.responseLineMon));
			}
			catch (Exception e)
			{
				
				e.printStackTrace();
			}
			risposta = null;
		}
		while (SocketMonitor.statoMonitor == 3);
		
		// ClientFrame.scriviSulLog("Thread Monitoring terminato",2,0,0);
		System.out.println("Thread Monitoring terminato");
	}
	
}
