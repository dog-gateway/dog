package com.bticino.core;

/***************************************************************************
 * 			              CommandsSocket.java                       *
 * 			              --------------------------                       *
 *   date          : Jul 19, 2004                                          *
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
 * Description: Handle commands socket , connection opening, connection closing,
 * command send
 * 
 */
public class CommandsSocket
{
	
	/*
	 * state 0 = not connected. state 1 = socket command request has been sent,
	 * waiting resposne state 2 = results of password managed has been sent ,
	 * waitirng for ack or nack. If ack then state 3 state 3 = connection works.
	 */
	
	GestionePassword gestPassword = null;
	
	static ReadThread readTh = null; // thread that receives the bytes sent by
										// the webserver
	static NewThread timeoutThread = null; // thread that handles the timeouts
	static int state = 0; // state commands socket
	static Socket socket = null;
	static String responseLine = null; // string from Webserver
	static final String commandsSocket = "*99*0##";
	static final String superCommandsSocket = "*99*9##";
	
	BufferedReader input = null;
	PrintWriter output = null;
	OpenWebNet openWebNet = null;
	String lastIp = null;
	int lastPort = 20000;
	
	private BTicinoReader reader;
	
	/**
	 * Constructor
	 * 
	 */
	public CommandsSocket(BTicinoReader reader)
	{
		gestPassword = new GestionePassword();
		this.reader = reader;
	}
	
	/**
	 * Tentativo di apertura socket comandi verso il webserver Diversi possibili
	 * stati: state 0 = non connesso state 1 = inviata richiesta socket comandi,
	 * in attesa di risposta state 2 = inviato risultato sulle operazioni della
	 * password, attesa per ack o nack. Se la risposta è ack si passa allo state
	 * 3 state 3 = connesso correttamente
	 * 
	 * @param ip
	 *            Ip del webserver al quale connettersi
	 * @param port
	 *            Porta sulla quale aprire la connessione
	 * @param passwordOpen
	 *            Password open del webserver
	 * @return true Se la connessione va a buon fine, false altrimenti
	 */
	public boolean connect(String ip, int port, long passwordOpen)
	{
		try
		{
			// ClientFrame.scriviSulLog("Tentativo connessione a "+ ip +" Port:
			// "+ port,1,0,0);
			socket = new Socket(ip, port);
			this.lastIp = ip;
			this.lastPort = port;
			setTimeout(0);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// ClientFrame.scriviSulLog("Buffer reader creato",2,0,0);
			output = new PrintWriter(socket.getOutputStream(), true);
			// ClientFrame.scriviSulLog("Print Writer creato",2,0,0);
		}
		catch (IOException e)
		{
			// ClientFrame.scriviSulLog("Impossibile connettersi!",0,1,1);
			this.close();
			// e.printStackTrace();
		}
		
		if (socket != null)
		{
			while (true)
			{
				readTh = null;
				readTh = new ReadThread(socket, input, 0);
				readTh.start();
				try
				{
					readTh.join();
				}
				catch (InterruptedException e1)
				{
					
					// ClientFrame.scriviSulLog("----- ERRORE readThread.join()
					// durante la connect:",2,1,0);
					e1.printStackTrace();
				}
				
				if (responseLine != null)
				{
					if (state == 0)
					{ // ho mandato la richiesta di
						// connessione
						// ClientFrame.scriviSulLog("\n----- STATO 0 -----
						// ",2,0,0);
						// ClientFrame.scriviSulLog("Rx: " +
						// responseLine,1,0,0);
						if (responseLine.equals(OpenWebNet.MSG_OPEN_OK))
						{
							output.write(superCommandsSocket); // super
							
							// --> useless part starts <--
							// if (/* ClientFrame.superSocket.isSelected()
							// */true) {
							// ClientFrame.scriviSulLog("Tx:
							// "+superCommandsSocket,1,0,0);
							// output.write(superCommandsSocket); // super
							// comandi
							// } else {
							// ClientFrame.scriviSulLog("Tx:
							// "+commandsSocket,1,0,0);
							// output.write(commandsSocket); // comandi
							// }
							// --> useless part ends <--
							
							output.flush();
							state = 1;
							setTimeout(0);
						}
						else
						{
							// se non mi connetto chiudo la socket
							// ClientFrame.scriviSulLog("Chiudo la socket verso
							// il server " + ip,1,0,0);
							this.close();
							break;
						}
					}
					else if (state == 1)
					{ // ho mandato il tipo di
						// servizio richiesto
						// ClientFrame.scriviSulLog("\n----- STATO 1
						// -----",2,0,0);
						// ClientFrame.scriviSulLog("Rx: " +
						// responseLine,1,0,0);
						
						// --> useless part, second edition, starts <--
						// if (false/* ClientFrame.abilitaPass.isSelected() */)
						// {
						// applico algoritmo di conversione
						// ClientFrame.scriviSulLog("Controllo sulla
						// password",1,0,0);
						// long risultato =
						// gestPassword.applicaAlgoritmo(passwordOpen,
						// responseLine);
						// ClientFrame.scriviSulLog("Tx: "+risultato,1,0,0);
						// output.write("*#" + risultato + "##");
						// output.flush();
						// state = 2; // setto state dopo l'autenticazione
						// setTimeout(0);
						// } else {
						// --> useless part, second edition, ends <--
						
						// non devo fare il controllo della password
						// ClientFrame.scriviSulLog("NON effettuo il
						// controllo sulla password - mi aspetto
						// ACK",1,0,0);
						if (responseLine.equals(OpenWebNet.MSG_OPEN_OK))
						{
							// ClientFrame.scriviSulLog("Ricevuto ack, state
							// = 3",1,0,0);
							state = 3;
							break;
						}
						else
						{
							// ClientFrame.scriviSulLog("Impossibile
							// connettersi!!",0,1,1);
							// se non mi connetto chiudo la socket
							// ClientFrame.scriviSulLog("Chiudo la socket
							// verso il server " + ip,2,0,0);
							this.close();
							break;
						}
						// }
					}
					else if (state == 2)
					{
						// ClientFrame.scriviSulLog("\n----- STATO 2
						// -----",2,0,0);
						// ClientFrame.scriviSulLog("Rx: " +
						// responseLine,1,0,0);
						if (responseLine.equals(OpenWebNet.MSG_OPEN_OK))
						{
							// ClientFrame.scriviSulLog("Connessione OK",1,0,0);
							state = 3;
							break;
						}
						else
						{
							// ClientFrame.scriviSulLog("Impossibile
							// connettersi!!",0,1,1);
							// se non mi connetto chiudo la socket
							// ClientFrame.scriviSulLog("Chiudo la socket verso
							// il server " + ip,2,0,0);
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
					// ClientFrame.scriviSulLog("--- Risposta dal webserver
					// NULL",2,0,0);
					this.close();
					break;// ramo else di if(responseLine != null)
				}
			}// chiude while(true)
		}
		else
		{
			
		}
		
		if (state == 3)
			return true;
		else
			return false;
	}// chiude connect()
	
	/**
	 * Chiude la socket comandi ed imposta state = 0
	 * 
	 */
	public void close()
	{
		if (socket != null)
		{
			try
			{
				socket.close();
				this.input.close();
				socket = null;
				state = 0;
				// ClientFrame.scriviSulLog(
				// "-----Socket chiusa correttamente-----", 1, 0, 0);
			}
			catch (IOException e)
			{
				
				System.out.println("Errore Socket: <CommandsSocket>");
				e.printStackTrace();
			}
			finally
			{
				System.out.println("Errore Socket: QUI");
				this.reader.networkDisconnected();
				
			}
		}
	}
	
	/**
	 * Metodo per l'invio di un comando open
	 * 
	 * @param comandoOpen
	 *            comando da inviare
	 */
	public void invia(String commandOpen) throws Exception
	{
		// creo l'oggetto openWebNet con il comandoOpen
		OpenWebNet messageOpen = new OpenWebNet(commandOpen);
		this.invia(messageOpen);
	}// chiude metodo invia(...)
	
	/**
	 * Attiva il thread per il timeout sulla risposta inviata dal WebServer.
	 * 
	 * @param tipoSocket
	 *            : 0 se è socket comandi, 1 se è socket monitor
	 */
	public void setTimeout(int tipoSocket)
	{
		timeoutThread = null;
		timeoutThread = new NewThread("timeout", tipoSocket, CommandsSocket.state);
		timeoutThread.start();
	}
	
	public int getStato()
	{
		
		return CommandsSocket.state;
	}
	
	public boolean connect()
	{
		return this.connect(lastIp, lastPort, 0);
		
	}
	
	public void invia(OpenWebNet openWebNet) throws Exception
	{
		try
		{
			if (openWebNet.isErrorFrame())
			{
				// ClientFrame.scriviSulLog("ERRATA frame open "+comandoOpen+",
				// la invio comunque!!!",1,0,0);
				System.out.println("ERRATA frame open " + openWebNet + ", la invio comunque!!!");
			}
			else
			{
				// ClientFrame.scriviSulLog("CREATO oggetto OpenWebNet
				// "+openWebNet.getFrameOpen(),1,0,0);
				System.out.println("CREATO oggetto OpenWebNet " + openWebNet.getFrameOpen());
			}
		}
		catch (Exception e)
		{
			// ClientFrame.scriviSulLog("ERRORE nella creazione dell'oggetto
			// OpenWebNet "+comandoOpen,1,0,0);
			System.out.println("Eccezione in CommandsSocket durante la creazione del'oggetto OpenWebNet");
			e.printStackTrace();
		}
		
		// ClientFrame.scriviSulLog("Tx: "+comandoOpen,0,0,1);
		output.write(openWebNet.getFrameOpen());
		output.flush();
		
		do
		{
			setTimeout(0);
			readTh = null;
			readTh = new ReadThread(socket, input, 0);
			readTh.start();
			try
			{
				readTh.join();
			}
			catch (InterruptedException e1)
			{
				
				// ClientFrame.scriviSulLog("----- ERRORE readThread.join()
				// durante l'invio comando:",2,1,0);
				e1.printStackTrace();
			}
			
			if (responseLine != null)
			{
				if (responseLine.equals(OpenWebNet.MSG_OPEN_OK))
				{
					// ClientFrame.scriviSulLog("Rx: " + responseLine,0,0,1);
					// ClientFrame.scriviSulLog("Comando inviato
					// correttamente",0,0,0);
					// if(!ClientFrame.mantieniSocket.isSelected())
					// this.close();
					break;
				}
				else if (responseLine.equals(OpenWebNet.MSG_OPEN_KO))
				{
					// ClientFrame.scriviSulLog("Rx: " + responseLine,0,0,1);
					// ClientFrame.scriviSulLog("Comando NON inviato
					// correttamente",0,1,0);
					// if(!ClientFrame.mantieniSocket.isSelected())
					// this.close();
					break;
				}
				else
				{
					// RICHIESTA STATO
					// ClientFrame.scriviSulLog("Rx: " + responseLine,0,0,1);
					if (responseLine == OpenWebNet.MSG_OPEN_OK)
					{
						// ClientFrame.scriviSulLog("Comando inviato
						// correttamente",0,0,0);
						// if(!ClientFrame.mantieniSocket.isSelected())
						// this.close();
						break;
					}
					else if (responseLine == OpenWebNet.MSG_OPEN_KO)
					{
						// ClientFrame.scriviSulLog("Comando NON inviato
						// coinviarrettamente",0,1,0);
						// if(!ClientFrame.mantieniSocket.isSelected())
						// this.close();
						break;
					}
				}
			}
			else
			{
				
				this.close();
				throw (new Exception("Impossible to send the command"));
				// ClientFrame.scriviSulLog("Impossibile inviare il
				// comando",0,1,1);
				// if(!ClientFrame.mantieniSocket.isSelected()) this.close();
				// break;
				// this.close();
			}
		}
		while (true);
		
	}
	
}// chiuse la classe
