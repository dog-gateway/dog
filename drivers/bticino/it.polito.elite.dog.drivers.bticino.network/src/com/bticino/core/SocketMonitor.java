package com.bticino.core;

/***************************************************************************
 * 			              SocketMonitor.java                       *
 * 			              --------------------------                       *
 *   date          : Sep 6, 2004                                          *
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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bticino.GestionePassword;

/**
 * Description: Gestione della socket Monitor, apertura monitor, chiusura
 * monitor
 * 
 */
public class SocketMonitor
{
	
	GestionePassword gestPassword = null;
	
	static final String socketMonitor = "*99*1##";
	static ReadThread readThMon = null; // thread per la ricezione dei caratteri
										// inviati dal webserver
	static NewThread timeoutThreadMon = null; // thread per la gestione dei
												// timeout
	static int statoMonitor = 0; // state socket monitor
	static Socket socketMon = null;
	static String responseLineMon = null; // stringa in ricezione dal Webserver
	BufferedReader inputMon = null;
	PrintWriter outputMon = null;
	Monitoring monThread = null;
	volatile BTicinoReader breader = null;
	
	/**
	 * Costruttore
	 * 
	 */
	public SocketMonitor(BTicinoReader breader)
	{
		gestPassword = new GestionePassword();
		this.breader = breader;
	}
	
	public SocketMonitor()
	{
		gestPassword = new GestionePassword();
		// this.breader=breader;
	}
	
	/**
	 * Tentativo di apertura socket monitor verso il webserver
	 * 
	 * @param ip
	 *            Ip del webserver al quale connettersi
	 * @param port
	 *            porta sulla quale aprire la connessione
	 * @param passwordOpen
	 *            password open del webserver
	 * @return true se la connessione va a buon fine, false altrimenti
	 */
	public boolean connect(String ip, int port, long passwordOpen)
	{ // tipo rappresenta socket comandi o monitor
		try
		{
			// ClientFrame.scriviSulLog("Mon: Tentativo connessione a "+ ip
			// +"  Port: "+ port,1,0,0);
			socketMon = new Socket(ip, port);
			setTimeout(1);
			inputMon = new BufferedReader(new InputStreamReader(socketMon.getInputStream()));
			// ClientFrame.scriviSulLog("Mon: Buffer reader creato",2,0,0);
			outputMon = new PrintWriter(socketMon.getOutputStream(), true);
			// ClientFrame.scriviSulLog("Mon: Print Writer creato",2,0,0);
		}
		catch (IOException e)
		{
			// ClientFrame.scriviSulLog("Mon: Impossibile connettersi con host "+ip+"\n",1,1,0);
			this.close();
			e.printStackTrace();
		}
		
		if (socketMon != null)
		{
			while (true)
			{
				readThMon = null;
				try
				{
					readThMon = new ReadThread(socketMon, inputMon, 1);
					
					readThMon.start();
					try
					{
						readThMon.join();
					}
					catch (InterruptedException e1)
					{
						// ClientFrame.scriviSulLog("Mon: ----- ERRORE readThread.join() durante la connect:",2,1,0);
						System.out.println("Mon: ----- ERRORE readThread.join() durante la connect:");
						e1.printStackTrace();
					}
				}
				catch (Exception e2)
				{
					
					e2.printStackTrace();
					responseLineMon = null;
				}
				if (responseLineMon != null)
				{
					if (statoMonitor == 0)
					{
						System.out.println("\nMon: ----- STATO 0 ----- ");
						System.out.println("Mon: Rx: " + responseLineMon);
						if (responseLineMon.equals(OpenWebNet.MSG_OPEN_OK))
						{
							System.out.println("Mon: Tx: " + socketMonitor);
							outputMon.write(socketMonitor); // comandi
							outputMon.flush();
							statoMonitor = 1; // setto state autenticazione
							setTimeout(1);
						}
						else
						{
							// se non mi connetto chiudo la socket
							System.out.println("Mon: Chiudo la socket verso il server ");
							this.close();
							break;
						}
					}
					else if (statoMonitor == 1)
					{
						System.out.println("\nMon: ----- STATO 1 -----");
						System.out.println("Mon: Rx: " + responseLineMon);
						
						// --> useless part, third edition, starts <--
						// if(false/*ClientFrame.abilitaPass.isSelected()*/){
						// applico algoritmo di conversione
						// long risultato =
						// gestPassword.applicaAlgoritmo(passwordOpen,
						// responseLineMon);
						// ClientFrame.scriviSulLog("Mon: Tx: "+risultato,1,0,0);
						// outputMon.write("*#"+risultato+"##");
						// outputMon.flush();
						// statoMonitor = 2; //setto state dopo l'autenticazione
						// setTimeout(1);
						// }else{
						// --> useless part, third edition, ends <--
						
						// non devo fare il controllo della password
						// ClientFrame.scriviSulLog("Mon: NON effettuo il controllo sulla password - mi aspetto ACK",2,0,0);
						if (responseLineMon.equals(OpenWebNet.MSG_OPEN_OK))
						{
							System.out.println("Mon: Ricevuto ack, statoMonitor = 3");
							
							statoMonitor = 3;
							System.out.println("Mon: Monitor attivata con successo");
							break;
						}
						else
						{
							System.out.println("Mon: Impossibile connettersi!!");
							// se non mi connetto chiudo la socket
							System.out.println("Mon: Chiudo la socket verso il server ");
							this.close();
							break;
						}
						// }
					}
					else if (statoMonitor == 2)
					{
						System.out.println("\nMon: ----- STATO 2 -----");
						System.out.println("Mon: Rx: " + responseLineMon);
						if (breader != null)
						{
							try
							{
								breader.read(new OpenWebNet(responseLineMon));
							}
							catch (Exception e)
							{
								
								e.printStackTrace();
							}
						}
						if (responseLineMon.equals(OpenWebNet.MSG_OPEN_OK))
						{
							System.out.println("Mon: Monitor attivata con successo");
							statoMonitor = 3;
							break;
						}
						else
						{
							System.out.println("Mon: Impossibile attivare la monitor");
							// se non mi connetto chiudo la socket
							// ClientFrame.scriviSulLog("Mon: Chiudo la socket monitor\n",2,0,0);
							this.close();
							break;
						}
					}
					else
						break; // non dovrebbe servire (quando passo per lo
								// state tre esco dal ciclo con break)
				}
				else
				{
					// null response
					this.breader.networkDisconnected();
					this.close();
					break;// ramo else della funzione riceviStringa()
				}
			}// chiude while(true)
		}
		else
		{
			// System.out.println("$$$$$$$");
		}
		
		if (statoMonitor == 3)
		{
			monThread = null;
			monThread = new Monitoring(socketMon, inputMon, breader);
			monThread.start();
		}
		
		if (statoMonitor == 3)
			return true;
		else
			return false;
		
	}// chiude connect()
	
	/**
	 * Chiude la socket monitor ed imposta statoMonitor = 0
	 * 
	 */
	public void close()
	{
		if (socketMon != null)
		{
			try
			{
				socketMon.close();
				socketMon = null;
				statoMonitor = 0;
				// ClientFrame.scriviSulLog("MON: Socket monitor chiusa correttamente-----\n",1,0,0);
			}
			catch (IOException e)
			{
				
				System.out.println("MON: Errore chiusura Socket: <SocketMonitor>");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Attiva il thread per il timeout sulla risposta inviata dal WebServer.
	 * 
	 * @param tipoSocket
	 *            : 0 se è socket comandi, 1 se è socket monitor
	 */
	public void setTimeout(int tipoSocket)
	{
		timeoutThreadMon = null;
		timeoutThreadMon = new NewThread("timeout", tipoSocket, SocketMonitor.statoMonitor);
		timeoutThreadMon.start();
	}
	
	public void setReader(BTicinoReader reader)
	{
		this.breader = reader;
		
	}
	
}
