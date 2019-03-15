package com.example.demo.services.utils;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class UtilsMethods {

	public static final DateTimeFormatter DATEFORMAT_CONVERTOR = DateTimeFormatter
			.ofPattern("dd/MM/yyyy");

	public static final DateTimeFormatter DATEFORMAT_CONVERTOR_SMS = DateTimeFormatter
			.ofPattern("dd/MM/yy");

	public static final DateTimeFormatter DATETIME_FORMAT_CONVERTOR = DateTimeFormatter
			.ofPattern("dd/MM/yyyy HH:mm");

	public static DecimalFormat format = new DecimalFormat(".##");

	public static String[] listOfDay = { "Dimanche", "Lundi", "Mardi",
			"Mercredi", "Jeudi", "Vendredi", "Samedi" };

	private static String CHAR_NUMERIQUE = "0123456789";

	public static double getDouble(double value) {

		double d = value;

		try {

			String s = format.format(value);

			s = s.replace(",", ".");

			s = s.replace("" + (char) 160, "");

			d = Double.valueOf(s);

		} catch (Exception e) {

			d = value;

			e.printStackTrace();

		}

		return d;

	}

	public static String format(String prefixe, Integer value) {

		return prefixe + format(value);

	}

	public static Date convertorTextToDate(String dateText) {

		LocalDate l = null;
		try {

			dateText = dateText == null ? "" : dateText;

			l = LocalDate.parse(dateText, DATEFORMAT_CONVERTOR);

		} catch (Exception DateTimeParseException) {

		}

		return asDate(l);
	}

	public static LocalDate convertorDate(String dateText) {

		LocalDate l = null;
		try {

			dateText = dateText == null ? "" : dateText;

			l = LocalDate.parse(dateText, DATEFORMAT_CONVERTOR);

		} catch (Exception DateTimeParseException) {

		}

		return l;
	}



	public static LocalDateTime convertorDateTime(String dateText) {

		LocalDateTime l = null;
		try {

			dateText = dateText == null ? "" : dateText;

			l = LocalDateTime.parse(dateText, DATETIME_FORMAT_CONVERTOR);

		} catch (Exception DateTimeParseException) {

		}

		return l;
	}

	public static LocalDateTime convertorLocalDateTime(String dateText) {

		LocalDateTime l = null;
		try {

			dateText = dateText == null ? "" : dateText;

			LocalDate d = LocalDate.parse(dateText, DATEFORMAT_CONVERTOR);

			l = d.atStartOfDay();

		} catch (Exception DateTimeParseException) {

		}

		return l;
	}

	public static String getDateToString(Date date) {

		LocalDate d = asLocalDate(date);

		String text = date == null ? "" : DATEFORMAT_CONVERTOR.format(d);

		return text;
	}

	public static String getDateToString(LocalDate date) {

		String text = date == null ? "" : DATEFORMAT_CONVERTOR.format(date);

		return text;
	}

	public static String getDateToString(LocalDateTime date) {

		String text = date == null ? "" : DATETIME_FORMAT_CONVERTOR
				.format(date);

		return text;
	}

	public static String format(String prefixe, Integer value,
			int nombre_char_max, String suffixe) {

		return prefixe + format(value, nombre_char_max) + suffixe;

	}

	public static String format(String prefixe, Integer value,
			int nombre_char_max) {

		return prefixe + format(value, nombre_char_max);

	}

	public static String format(String prefixe, Integer value, String suffixe) {

		return prefixe + format(value) + suffixe;

	}


	public static String format(int nombre, String prefixe, long value) {

		if ((value + "").length() >= nombre) {

			return prefixe + (value + "");

		} else {

			int reste = nombre - (value + "").length();

			if (reste < prefixe.length()) {

				// return prefixe.substring(0, reste) + value;
				return prefixe + (value + "");

			} else {

				return prefixe
						+ generateStringOf("0", (reste - prefixe.length()))
						+ (value + "");

			}

		}
	}

	public static String generateStringOf(String expression, int nombre) {

		String texte = "";

		for (int i = 0; i < nombre; i++) {

			texte += expression;

		}

		return texte;

	}

	public static String format(Integer value) {

		int nombre_char_max = 6;

		return format(value, nombre_char_max);
	}

	public static String format(Integer value, int nombre_char_max) {

		String v = value.toString();

		String fixe = "";

		if (v.length() <= nombre_char_max) {

			int nbre_zero = nombre_char_max - v.length();

			for (int i = 0; i < nbre_zero; i++) {

				fixe += "0";
			}

			fixe += v;

		} else {

			fixe = v;
		}

		return fixe;
	}

	public static String getNoDate() {

		LocalDate today = LocalDate.now();

		String p = (today.getYear() + "").substring(2)
				+ UtilsMethods.format(today.getMonthValue(), 2)
				+ UtilsMethods.format(today.getDayOfMonth(), 2);

		return p;
	}

	public static String formatStringToFind(String find) {

		if (!(find == null || find.length() == 0)) {

			find = find.replace('*', '%');
			if (find.charAt(0) != '%') {
				find = "%" + find;
			}
			if (find.charAt(find.length() - 1) != '%') {
				find = find + "%";
			}
		}

		if (find.length() == 0)
			find = "%%";

		return find;
	}

	public static String formatStringToFindEnd(String find) {

		if (!(find == null || find.length() == 0)) {

			find = find.replace('*', '%');

			if (find.charAt(find.length() - 1) != '%') {
				find = find + "%";
			}
		}

		if (find.length() == 0)
			find = "%%";

		return find;
	}

	public static Integer getLastDayOfMonth(Integer month) {

		Integer lastDay = 30;

		if (month == 1) {

			lastDay = 31;

		} else if (month == 2) {

			lastDay = 28;

		} else if (month == 3) {

			lastDay = 31;

		} else if (month == 4) {

			lastDay = 30;

		} else if (month == 5) {

			lastDay = 31;

		} else if (month == 6) {

			lastDay = 30;

		} else if (month == 7) {

			lastDay = 31;

		} else if (month == 8) {

			lastDay = 31;

		} else if (month == 9) {

			lastDay = 30;

		} else if (month == 10) {

			lastDay = 31;

		} else if (month == 11) {

			lastDay = 30;

		} else if (month == 12) {

			lastDay = 31;
		}

		return lastDay;
	}

	public static LocalDate getFirstLocalDateOfMonth(Integer month, Integer year) {

		return LocalDate.of(year, month, 1);
	}

	public static LocalDate getLastLocalDateOfMonth(Integer month, Integer year) {

		return LocalDate.of(year, month, getLastDayOfMonth(month));
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault())
				.toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime())
				.atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime())
				.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static String getDateComplet(LocalDate date) {

		int day = date.getDayOfWeek().getValue();

		if (day == 7) {

			day = 0;
		}

		String jour = listOfDay[day];

		return jour + " " + getDateToString(date);
	}

	public static String getNumberOfText(String text) {

		String out = "";

		text = text.trim();

		for (int i = 0; i < text.length(); i++) {

			if (isNumerique(text.charAt(i))) {

				out += text.charAt(i);
			}
		}

		return out;
	}

	public static String getDoubleOfText(String text) {

		String out = "";

		text = text.trim();

		for (int i = 0; i < text.length(); i++) {

			if (isNumerique(text.charAt(i)) || '-' == text.charAt(i)) {

				out += text.charAt(i);
			}
		}

		return out;
	}

	private static boolean isNumerique(char c) {

		if (CHAR_NUMERIQUE.indexOf(c) >= 0)
			return true;
		else
			return false;
	}

	public static long getNombreDayOfDate(LocalDate date) {

		return ((date.getYear() * 365) + (date.getMonthValue() * 30) + date
				.getDayOfMonth());
	}

	public static long getNombreMonthOfDate(LocalDate date) {

		return (date.getYear() * 12) + date.getMonthValue();
	}

}
