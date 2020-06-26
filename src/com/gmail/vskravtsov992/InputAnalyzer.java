package com.gmail.vskravtsov992;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputAnalyzer {

	public String[] getLines(StringBuilder sb) { 	// Method to get lines from input
		String[] lines = sb.toString().split("\\n");
		return lines;
	}

	public void addWaitingTimeLineFromLinesToList(String line, WaitingTimeLineList list) { 	// Method to get Waiting Time Line from String line and add it to list
		if (checkWaitingTimeLineLine(line) == true) {
			WaitingTimeLine waitingTimeLine = new WaitingTimeLine();
			String[] splitLine = line.replace("\r", "").split(" ");
			String serviceLine = splitLine[1];
			if (serviceLine.contains(".")) {
				String[] serviceLineSplit = serviceLine.split("[.]");
				waitingTimeLine.setServiceId(Integer.valueOf(serviceLineSplit[0]));
				waitingTimeLine.setServiceVarId(Integer.valueOf(serviceLineSplit[1]));
			} else {
				waitingTimeLine.setServiceId(Integer.valueOf(serviceLine));
				waitingTimeLine.setServiceVarId(0);
			}
			String questionLine = splitLine[2];
			int count = questionLine.length() - questionLine.replaceAll("[.]", "").length();
			if (count == 2) {
				String[] questionLineSplit = questionLine.split("[.]");
				waitingTimeLine.setQuestionId(Integer.valueOf(questionLineSplit[0]));
				waitingTimeLine.setQuestionCatId(Integer.valueOf(questionLineSplit[1]));
				waitingTimeLine.setQuestionSubCatId(Integer.valueOf(questionLineSplit[2]));
			}
			if (count == 1) {
				String[] questionLineSplit = questionLine.split("[.]");
				waitingTimeLine.setQuestionId(Integer.valueOf(questionLineSplit[0]));
				waitingTimeLine.setQuestionCatId(Integer.valueOf(questionLineSplit[1]));
				waitingTimeLine.setQuestionSubCatId(0);
			}
			if (count == 0) {
				waitingTimeLine.setQuestionId(Integer.valueOf(questionLine));
				waitingTimeLine.setQuestionCatId(0);
				waitingTimeLine.setQuestionSubCatId(0);
			}
			waitingTimeLine.setResponseType(splitLine[3].charAt(0));

			DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			try {
				Date date = format.parse(splitLine[4]);
				waitingTimeLine.setDate(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			waitingTimeLine.setTime(Integer.valueOf(splitLine[5]));
			if (checkWaitingTimeLine(waitingTimeLine)) {
				list.addWaitingTimeLine(waitingTimeLine);
			}
		}

	}

	private boolean checkWaitingTimeLineLine(String line) { 	// Method to check if a line can be considered as a Waiting Time Line										
		String line2 = line.replace("\r", "");
		String[] splitLine = line2.split(" ");
		boolean check = true;
		if (splitLine.length != 6) {
			return check = false;
		}
		if ((splitLine[0].charAt(0) != 'C') || (splitLine[0].length() != 1)
				|| ((splitLine[3].charAt(0) != ('P')) && (splitLine[3].charAt(0) != ('N')))
				|| (splitLine[3].length() != 1)) {
			return check = false;
		}
		if (splitLine[1].contains(".") && splitLine[1].split("[.]").length != 2) {
			return check = false;
		}
		if (splitLine[2].contains(".") && splitLine[2].split("[.]").length > 3) {
			return check = false;
		}
		try {
			int l1 = Integer.parseInt(splitLine[1].replace(".", ""));
			int l2 = Integer.parseInt(splitLine[2].replace(".", ""));
			int l3 = Integer.parseInt(splitLine[5]);
		} catch (NumberFormatException e) {
			return check = false;
		}

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date date = format.parse(splitLine[4]);
		} catch (ParseException e) {
			return check = false;
		}
		return check;
	}

	private boolean checkWaitingTimeLine(WaitingTimeLine line) { 	// Method to check if a Waiting Time Line has appropriate parameters(fields)					
		boolean check = true;
		if (line.getServiceId() < 1 || line.getServiceId() > 10) {
			return check = false;
		}
		if (line.getServiceVarId() < 0 || line.getServiceVarId() > 3) {
			return check = false;
		}
		if (line.getQuestionId() < 1 || line.getQuestionId() > 10) {
			return check = false;
		}
		if (line.getQuestionCatId() < 0 || line.getQuestionCatId() > 20) {
			return check = false;
		}
		if (line.getQuestionSubCatId() < 0 || line.getQuestionSubCatId() > 5) {
			return check = false;
		}
		if (line.getTime() < 0) {
			return check = false;
		}
		return check;
	}

	public Query getQueryFromLines(String line) { 	// Method to get Query from String line
		if (checkQueryLine(line) == true) {
			Query query = new Query();
			String line2 = line.replace("\r", "");
			String[] splitLine = line2.split(" ");
			String serviceLine = splitLine[1];
			if (serviceLine.contains(".")) {
				String[] serviceLineSplit = serviceLine.split("[.]");
				query.setServiceId(Integer.valueOf(serviceLineSplit[0]));
				query.setServiceVarId(Integer.valueOf(serviceLineSplit[1]));
			} else {
				if (serviceLine.charAt(0) == '*') {
					query.setServiceId(0);
				} else {
					query.setServiceId(Integer.valueOf(serviceLine));
				}
			}
			String questionLine = splitLine[2];
			int count = questionLine.length() - questionLine.replaceAll("[.]", "").length();
			if (count == 2) {
				String[] questionLineSplit = questionLine.split("[.]");
				query.setQuestionId(Integer.valueOf(questionLineSplit[0]));
				query.setQuestionCatId(Integer.valueOf(questionLineSplit[1]));
				query.setQuestionSubCatId(Integer.valueOf(questionLineSplit[2]));
			}
			if (count == 1) {
				String[] questionLineSplit = questionLine.split("[.]");
				query.setQuestionId(Integer.valueOf(questionLineSplit[0]));
				query.setQuestionCatId(Integer.valueOf(questionLineSplit[1]));
				query.setQuestionSubCatId(0);
			}
			if (count == 0) {
				if (questionLine.charAt(0) == '*') {
					query.setQuestionId(0);
				} else {
					query.setQuestionId(Integer.valueOf(questionLine));
				}
				query.setQuestionCatId(0);
				query.setQuestionSubCatId(0);

			}
			query.setResponseType(splitLine[3].charAt(0));

			DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			if (splitLine[4].contains("-")) {
				String[] dateLineSplit = splitLine[4].split("-");
				try {
					Date dateFrom = format.parse(dateLineSplit[0]);
					query.setDateFrom(dateFrom.getTime());
					Date dateTo = format.parse(dateLineSplit[1]);
					query.setDateTo(dateTo.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Date date = format.parse(splitLine[4]);
					query.setDateFrom(date.getTime());
					query.setDateTo(date.getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (checkQuery(query)) {
				return query;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	private boolean checkQueryLine(String line) { 	// Method to check if a line can be considered as a Query
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		String line2 = line.replace("\r", "");
		String[] splitLine = line2.split(" ");
		boolean check = true;
		if (splitLine.length != 5) {
			return check = false;
		}
		if ((splitLine[0].charAt(0) != 'D') || (splitLine[0].length() != 1)
				|| ((splitLine[3].charAt(0) != ('P')) && (splitLine[3].charAt(0) != ('N')))
				|| (splitLine[3].length() != 1)) {
			return check = false;
		}
		if (splitLine[1].contains(".") && (splitLine[1].split("[.]").length != 2)) {
			return check = false;
		}
		if (splitLine[2].contains(".") && splitLine[2].split("[.]").length > 3) {
			return check = false;
		}
		if ((splitLine[1].charAt(0) != '*') || (splitLine[1].length() != 1)) {
			try {
				int l1 = Integer.parseInt(splitLine[1].replace(".", ""));
			} catch (NumberFormatException e) {
				return check = false;
			}
		}
		if ((splitLine[2].charAt(0) != '*') || (splitLine[2].length() != 1)) {
			try {
				Integer.parseInt(splitLine[2].replace(".", ""));
			} catch (NumberFormatException e) {
				return check = false;
			}
		}
		if (splitLine[4].contains("-")) {
			String[] dateLine = splitLine[4].split("-");
			if (dateLine.length > 2) {
				return check = false;
			}
			try {
				Date datefrom = format.parse(dateLine[0]);
				Date dateto = format.parse(dateLine[1]);
				if (datefrom.after(dateto)) {
					return check = false;
				}
			} catch (ParseException e) {
				return check = false;
			}
		} else {
			try {
				Date date = format.parse(splitLine[4]);
			} catch (ParseException e) {
				return check = false;
			}
		}

		return check;
	}

	private boolean checkQuery(Query query) {	// Method to check if a Query has appropriate parameters(fields)
		boolean check = true;
		if (query.getServiceId() < 0 || query.getServiceId() > 10) {
			return check = false;
		}
		if (query.getServiceVarId() < 0 || query.getServiceVarId() > 3) {
			return check = false;
		}
		if (query.getQuestionId() < 0 || query.getQuestionId() > 10) {
			return check = false;
		}
		if (query.getQuestionCatId() < 0 || query.getQuestionCatId() > 20) {
			return check = false;
		}
		if (query.getQuestionSubCatId() < 0 || query.getQuestionSubCatId() > 5) {
			return check = false;
		}
		return check;
	}

	public boolean compareQueryToWaitingTimeLine(Query query, WaitingTimeLine line) { // Method to identify which Waiting Time Lines from the list match a specific Query
		boolean compare = true;
		if ((query.getServiceId() != 0) && (query.getServiceId() != line.getServiceId())) {
			return compare = false;
		}
		if ((query.getServiceId() != 0) && (query.getServiceVarId() != 0)
				&& (query.getServiceVarId() != line.getServiceVarId())) {
			return compare = false;
		}

		if ((query.getQuestionId() != 0) && (query.getQuestionId() != line.getQuestionId())) {
			return compare = false;
		}
		if ((query.getQuestionId() != 0) && (query.getQuestionCatId() != 0)
				&& (query.getQuestionCatId() != line.getQuestionCatId())) {
			return compare = false;
		}
		if ((query.getQuestionId() != 0) && (query.getQuestionSubCatId() != 0)
				&& (query.getQuestionSubCatId() != line.getQuestionSubCatId())) {
			return compare = false;
		}
		if (query.getResponseType() != line.getResponseType()) {
			return compare = false;
		}
		if ((query.getDateFrom() > line.getDate()) || (line.getDate() > query.getDateTo())) {
			return compare = false;
		}
		return compare;
	}

	public int queryOutput(Query query, WaitingTimeLineList list) { 	// Method to get the required output of a Query
		int timeOutput = 0;
		int count = 0;
		for (WaitingTimeLine line : list.getWTLList()) {
			if (compareQueryToWaitingTimeLine(query, line)) {
				count++;
				timeOutput += line.getTime();
			}
		}
		if (count != 0) {
			int time = timeOutput / count;
			return time;
		}
		return 0;
	}

	public StringBuilder output(StringBuilder sb, WaitingTimeLineList list) { 	// Method to display the entire output
		StringBuilder sb1 = new StringBuilder();
		String[] lines = getLines(sb);
		for (String line : lines) {
			if (line.startsWith("C ") && checkWaitingTimeLineLine(line)) {
				addWaitingTimeLineFromLinesToList(line, list);
			}
			if (line.startsWith("D ") && checkQueryLine(line)) {
				if (queryOutput(getQueryFromLines(line), list) != 0) {
					try {
						sb1.append(queryOutput(getQueryFromLines(line), list))
								.append(System.getProperty("line.separator"));
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
				} else {
					sb1.append("-").append(System.getProperty("line.separator"));
				}
			}
		}
		if (sb1.length() == 0) {
			sb1.append("-").append(System.getProperty("line.separator"));
		}
		return sb1;
	}
}
