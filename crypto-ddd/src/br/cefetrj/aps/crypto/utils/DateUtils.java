package br.cefetrj.aps.crypto.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils 
{
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static LocalDateTime parse(String value)
	{
		return LocalDateTime.parse(value, formatter);
	}
	
	public static String format(LocalDateTime value)
	{
		return formatter.format(value);
	}
}
