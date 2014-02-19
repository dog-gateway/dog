package com.bticino.core;

/***************************************************************************
 * 			                   ReadThread.java                             *
 * 			              --------------------------                       *
 *   date          : Sep 8, 2004                                           *
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

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 * Description: Gestisce tramite un thread la ricezione di una stringa inviata
 * dal WebServer
 * 
 */
public class ReadThread extends Thread
{
	
	Socket socket = null;
	BufferedReader input = null;
	int tipoSocket;
	int num = 0;
	int indice = 0;
	boolean esito = false;
	char risposta[];
	char c = ' ';
	int ci = 0;
	String responseString = null;
	
	/**
	 * Costruttore
	 * 
	 * @param sock
	 *            Socket da analizzare
	 * @param inp
	 *            Character-input stream sul quale leggere i caratteri inviati
	 *            dal WebServer
	 * @param numSocket
	 *            Tipo di socket, 0 se è socket comandi, 1 se è monitor
	 */
	public ReadThread(Socket sock, BufferedReader inp, int numSocket)
	{
		socket = sock;
		input = inp;
		tipoSocket = numSocket;
	}
	
	/**
	 * Avvia il Thread per la ricezione di una stringa inviata dal WebServer
	 */
	@Override
	public void run()
	{
		if (tipoSocket == 0)
			CommandsSocket.responseLine = null;
		else
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
				if (socket != null && !socket.isInputShutdown())
				{
					ci = input.read();
					if (ci == -1)
					{
						num = 0;
						indice = 0;
						c = ' ';
						// ClientFrame.scriviSulLog("----- Socket chiusa dal
						// server -----",1,1,0);
						System.out.println("----- Socket chiusa dal server -----");
						socket = null;
						if (tipoSocket == 0)
						{
							// ClientFrame.gestSocketComandi.stato = 0;
							// ClientFrame.gestSocketComandi.socket = null;
							CommandsSocket.state = 0;
							CommandsSocket.socket = null;
						}
						else
						{
							SocketMonitor.statoMonitor = 0;
							SocketMonitor.socketMon = null;
						}
						break;
					}
					else
					{
						c = (char) ci;
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
						else
							System.out.println("----------ERRORE-------------");
					}
				}
				else
				{
					
				}
			}
			while (true); // in questo ciclo ci rimango fino a quando
			// esito=true o il webserver chiude la socket;
			// altrimenti esco quando scade il timeout e il thread viene
			// interrotto.
		}
		catch (IOException e)
		{
			System.out.println("eccezione <ReadThread>: ");
			e.printStackTrace();
			return;
			// throw(e);
			
		}
		
		if (esito == true)
		{
			responseString = new String(risposta, 0, indice + 1);
			if (tipoSocket == 0)
			{
				CommandsSocket.responseLine = responseString;
			}
			else
			{
				SocketMonitor.responseLineMon = responseString;
			}
		}
		else
		{
			if (tipoSocket == 0)
				CommandsSocket.responseLine = null;
			else
				SocketMonitor.responseLineMon = null;
		}
		
		if (tipoSocket == 0)
		{
			if (CommandsSocket.timeoutThread != null)
				CommandsSocket.timeoutThread.interrupt();
		}
		else
		{
			if (SocketMonitor.timeoutThreadMon != null)
				SocketMonitor.timeoutThreadMon.interrupt();
		}
		
		// ClientFrame.scriviSulLog("Thread ricezione stringa terminato",2,0,0);
		socket = null;
		input = null;
		risposta = null;
	}
	
}
