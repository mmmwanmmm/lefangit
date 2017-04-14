package com.lefanfs.base.utils;

import com.lefanfs.base.enums.ConstellationEnum;

import java.util.Calendar;
import java.util.Date;

public class ConstellationUtil {
	public static final ConstellationEnum[] ConstellationArr = { ConstellationEnum.Aquarius, ConstellationEnum.Pisces, ConstellationEnum.Aries, ConstellationEnum.Taurus, ConstellationEnum.Gemini,
			ConstellationEnum.Cancer, ConstellationEnum.Leo, ConstellationEnum.Virgo, ConstellationEnum.Libra, ConstellationEnum.Scorpio, ConstellationEnum.Sagittarius, ConstellationEnum.Capricorn };

	public static final int[] ConstellationEdgeDay = { 21, 20, 21, 21, 22, 22, 23, 24, 24, 24, 23, 22 };

	// public static ConstellationEnum calculateConstellation(String birthday) {
	// if (birthday == null || birthday.trim().length() == 0) {
	// throw new IllegalArgumentException("the birthday can not be null");
	// }
	// String[] birthdayElements = birthday.split("-");
	// if (birthdayElements.length != 3) {
	// throw new IllegalArgumentException("the birthday form is not invalid");
	// }
	// int month = Integer.parseInt(birthdayElements[1]);
	// int day = Integer.parseInt(birthdayElements[2]);
	// if (month == 0 || day == 0 || month > 12) {
	// return null;
	// }
	// month = day < ConstellationEdgeDay[month - 1] ? month - 1 : month;
	// return month > 0 ? ConstellationArr[month - 1] : ConstellationArr[11];
	// }

	public static ConstellationEnum getConstellation(Date birthday) {
		Calendar c = Calendar.getInstance();
		c.setTime(birthday);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		month = day < ConstellationEdgeDay[month - 1] ? month - 1 : month;
		return month > 0 ? ConstellationArr[month - 1] : ConstellationArr[11];
	}
	//
	// public static void main(String[] args) {
	// System.out.println(calculateConstellation("1986-07-05"));
	// }
}
