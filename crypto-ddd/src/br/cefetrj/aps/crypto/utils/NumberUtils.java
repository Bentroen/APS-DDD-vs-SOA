package br.cefetrj.aps.crypto.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class NumberUtils 
{
	private static final String PERC_FORMAT = "%2.0f";
	
	public static String formatUsd(double value)
	{
		return "U$ "+format(value,2);
	}
	
	public static String formatBrl(double value) 
	{
		return "R$ "+format(value,2);
	}
	
	public static String format(double value, String asset)
	{
		return format(value)+" "+asset;
	}
	
	public static String format(double value)
	{
		int decimalPlaces = determineDecimalPlaces(value);
		return format(value,decimalPlaces);
	}	
	
	public static String format(double value, int decimalPlaces)
	{
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		
		numberFormat.setMinimumFractionDigits(decimalPlaces);
		numberFormat.setMaximumFractionDigits(decimalPlaces);
		
		return numberFormat.format(value);
	}
	
	public static String formatSharePrc(double share, double total) 
	{
		double prc = 0;
		
		if (share>0 && total>0)
			prc = share/total;
		
		return String.format(PERC_FORMAT,prc*100)+"%";		
	}
	
	public static String formatGainPrc(double current, double previous)
	{
		double prc = 0;
		
		if (previous>0)
			prc = (current-previous)/previous;
		
		return String.format(PERC_FORMAT,prc*100)+"%";		
	}
	
	private static int determineDecimalPlaces(double doubleValue)
	{
		if(doubleValue ==0)
			return 0;
		
		BigDecimal value = new BigDecimal(doubleValue);
		
		String string = value.toPlainString();
		
		if (string.contains("."))
		{
			String[] split = string.split("\\.");
			String decimals = split[1].trim();
			
			int totalDecimals = 0;
			boolean allZeroes = true;
			
			for (int i=0;i<decimals.length();i++)
			{
				totalDecimals++;
				
				if (decimals.charAt(i)!='0')
				{
					allZeroes = false;
					break;
				}
			}
			
			if (allZeroes)
				return 0;
			else
				return totalDecimals+1;
		}
		else
		{
			return 0;
		}		
	}
}
