/***************************************************************************
 *                            InterfaceOpenWebNet.java                     *
 *                    --------------------------                           *
 *   date          : Jun 15, 2005                                          *
 *   Copyright (c)     : (C) 2005 by Bticino S.p.A. Erba (CO) - Italy          *
 *                   Embedded Software Development Laboratory              *
 *   license       : GPL                                                   *
 *   email         :                                                       *
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
package com.bticino.core;

/**
 * Description:
 * 
 * 
 */
public interface InterfaceOpenWebNet
{
	
	final static int MAX_LENGTH_OPEN = 1024;
	final static int MAX_LENGTH = 30;
	final static int MAX_NUM_VALORI = 20;
	final static int MAX_INDIRIZZO = 4;
	final static int ERROR_FRAME = 1;
	final static int NULL_FRAME = 2;
	final static int NORMAL_FRAME = 3;
	final static int MEASURE_FRAME = 4;
	final static int STATE_FRAME = 5;
	final static int OK_FRAME = 6;
	final static int KO_FRAME = 7;
	final static int WRITE_FRAME = 8;
	final static int PWD_FRAME = 9;
	final static String MSG_OPEN_OK = "*#*1##";
	final static String MSG_OPEN_KO = "*#*0##";
	
	public void createNullMsgOpen();
	
	// open normale
	public void createMsgOpen(String who, String what, String where, String when);
	
	public void createMsgOpen(String who, String what, String where, String lev, String interfac, String when);
	
	// richiesta state
	public void CreateStateMsgOpen(String who, String where);
	
	public void CreateStateMsgOpen(String who, String where, String lev, String interfac);
	
	// richiesta grandezza
	public void CreateDimensionMsgOpen(String who, String where, String dimension);
	
	public void CreateDimensionMsgOpen(String who, String where, String lev, String interfac, String dimension);
	
	// scrittura grandezza
	public void CreateWrDimensionMsgOpen(String who, String where, String dimension, String value[], int numValue);
	
	public void CreateWrDimensionMsgOpen(String who, String where, String lev, String interfac, String dimension,
			String value[], int numValue);
	
	// open generale
	public void createMsgOpen(String message);
	
}
