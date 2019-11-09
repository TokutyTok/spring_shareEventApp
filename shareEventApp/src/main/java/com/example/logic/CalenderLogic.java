package com.example.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import com.example.domain.Week;
import com.example.mapper.CalenderEventMapper;

public class CalenderLogic {

	@Autowired
	CalenderEventMapper calenderEventMapper;

	//いつのカレンダーを表示するか？
	public String selectedCalender(HttpMethod httpMethod , String selectedYear , String selectedMonth) {

			LocalDate calenderOfTheMonth;

		    int calenderForOtherYears;
			int calenderForOtherMonths;


		if (httpMethod == HttpMethod.GET) {

			calenderOfTheMonth = LocalDate.now();

		} else {

			calenderForOtherYears = Integer.parseInt(selectedYear);
			calenderForOtherMonths = Integer.parseInt(selectedMonth);

			calenderOfTheMonth = LocalDate.of(calenderForOtherYears, calenderForOtherMonths, 1);
		}

		DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy年MM月");
		String selectedCalender = calenderOfTheMonth.format(d);

		return selectedCalender;

	}

	//viewのselect年数
	public ArrayList<Integer> selectYear(){

		ArrayList<Integer> year = new ArrayList<>();

		for (int i = 2019; i <= 2030; i++) {
			year.add(i);
		}

		return year;
	}

	//viewのselect月数
	public ArrayList<Integer> selectMonth() {

		ArrayList<Integer> month = new ArrayList<>();

		for (int i = 1; i <= 12; i++) {
			month.add(i);
		}

		return month;

	}

	public int selectedYear(HttpMethod httpMethod , String selectedYear) {

		LocalDate localDate = LocalDate.now();

		if (httpMethod == HttpMethod.GET) {

			int currentYear = localDate.getYear();

			return currentYear;

		} else {

			int currentYear = Integer.parseInt(selectedYear);

			return currentYear;
		}

	}

	public int selectedMonth(HttpMethod httpMethod , String selectedMonth) {

		LocalDate localDate = LocalDate.now();

		if (httpMethod == HttpMethod.GET) {

			int currentMonth = localDate.getMonthValue();

			return currentMonth;

		} else {

			int currentMonth = Integer.parseInt(selectedMonth);

			return currentMonth;
		}

	}

	//月の日数
	public int totalDay(int currentYear, int currentMonth) {

		int totalDay = LocalDate.of(currentYear, currentMonth, 1).lengthOfMonth();
		return totalDay;

	}

	//月の最初の曜日を調べる 日曜1-土曜日7
	public int weekIndex(int currentYear , int currentMonth) {

		Calendar cal = Calendar.getInstance();
		cal.set(currentYear, currentMonth - 1, 1);
		int weekIndex = cal.get(Calendar.DAY_OF_WEEK);
		return weekIndex;
	}

	public ArrayList<Week> calenderDays(int weekIndex , int totalDay){

		ArrayList<Week> calenderDays = new ArrayList<>();

		Week week = new Week();
		int weekDay = 0;

		//日曜日が最初のカレンダー 10月は火曜からなのでweekDay+2
				if (weekIndex == 2) {
					weekDay += 1;
				}
				if (weekIndex == 3) {
					weekDay += 2;
				}
				if (weekIndex == 4) {
					weekDay += 3;
				}
				if (weekIndex == 5) {
					weekDay += 4;
				}
				if (weekIndex == 6) {
					weekDay += 5;
				}
				if (weekIndex == 7) {
					weekDay += 6;
				}
				//日付を格納
				for (int i = 1; i <= totalDay; i++) {

					week.setMonthDay(weekDay, i);
					weekDay++;

				}

				calenderDays.add(week);

		return calenderDays;

	}

	/*
	public List<List<CalenderEvent>> findEventTitleLists(int totalDay , int weekIndex , int currentYear , int currentMonth){

		CalenderEvents calenderEvents = new CalenderEvents();

		for (int day = 1, a = 0; day <= totalDay; day++) {

			//日付をyyyyMMDDに変更
			LocalDate c = LocalDate.of(currentYear, currentMonth, day);
			DateTimeFormatter da = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String selectedDay = c.format(da);
			//最初の週
			while (a < 1) {
				for (int i = 0; i < weekIndex - 1; i++) {
					calenderEvents.setLists(new ArrayList<CalenderEvent>());
					a++;
				}
				a++;
			}

			calenderEvents.setLists(calenderEventMapper.findTitle(selectedDay));

		}
		//最後の週
		for (int i = 0; i + weekIndex + totalDay <= 42; i++) {
			calenderEvents.setLists(new ArrayList<CalenderEvent>());
		}

		return calenderEvents.getTitleLists();

	}
*/
}
