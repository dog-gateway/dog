package com.bticino.core;

/***************************************************************************
 * 			                     MyDate.java                               *
 * 			              --------------------------                       *
 *   date          : Oct 26, 2004                                          *
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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: Preleva la data e ora corrente secondo il formato
 * "dd/MM/yyyy  HH:mm:ss:SSS" LEGENDA dd: giorno MM: mese yyyy: anno HH: ora mm:
 * minuti ss: secondi SSS: millisecondi
 * 
 */
public class MyDate
{
	Date date = null;
	SimpleDateFormat dateFormat = null; // per vedere il nome del mese
										// sostituire MM con MMMM
	String dataOra = null;
	
	/**
	 * Costruttore
	 * 
	 */
	public MyDate()
	{
		dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss:SSS"); // per
																		// vedere
																		// il
																		// nome
																		// del
																		// mese
																		// sostituire
																		// MM
																		// con
																		// MMMM
	}
	
	/**
	 * Restituisce la data e ora corrente
	 * 
	 * @return Data e ora corrente in formato Stringa
	 */
	public String returnDate()
	{
		date = null;
		date = new Date();
		dataOra = dateFormat.format(date);
		return dataOra;
	}
}
