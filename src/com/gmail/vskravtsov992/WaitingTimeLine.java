package com.gmail.vskravtsov992;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitingTimeLine {
	private int serviceId;
	private int serviceVarId = 0;
	private int questionId;
	private int questionCatId = 0;
	private int questionSubCatId = 0;
	private char responseType;
	private long date;
	private int time;

	public WaitingTimeLine(int serviceId, int serviceVarId, int questionId, int questionCatId, int questionSubCatId,
			char responseType, long date, int time) {
		super();
		this.serviceId = serviceId;
		this.serviceVarId = serviceVarId;
		this.questionId = questionId;
		this.questionCatId = questionCatId;
		this.questionSubCatId = questionSubCatId;
		this.responseType = responseType;
		this.date = date;
		this.time = time;
	}

	public WaitingTimeLine() {
		super();
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getServiceVarId() {
		return serviceVarId;
	}

	public void setServiceVarId(int serviceVarId) {
		this.serviceVarId = serviceVarId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionCatId() {
		return questionCatId;
	}

	public void setQuestionCatId(int questionCatId) {
		this.questionCatId = questionCatId;
	}

	public int getQuestionSubCatId() {
		return questionSubCatId;
	}

	public void setQuestionSubCatId(int questionSubCatId) {
		this.questionSubCatId = questionSubCatId;
	}

	public char getResponseType() {
		return responseType;
	}

	public void setResponseType(char responseType) {
		this.responseType = responseType;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void setFieldsFromStrings(String[] splitLine) {
		this.setServiceId(Integer.valueOf(splitLine[1]));
		this.setServiceVarId(Integer.valueOf(splitLine[2]));
		this.setQuestionId(Integer.valueOf(splitLine[3]));
		this.setQuestionCatId(Integer.valueOf(splitLine[4]));
		this.setQuestionSubCatId(Integer.valueOf(splitLine[5]));
		this.setResponseType(splitLine[6].charAt(0));
		DateFormat format = new SimpleDateFormat("DD.MM.RRRR");
		try {
			Date date2 = format.parse(splitLine[6]);
			this.setDate(date2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.setTime(Integer.valueOf(splitLine[7]));
	}

	@Override
	public String toString() {
		return "WaitingTimeLine [serviceId=" + serviceId + ", serviceVarId=" + serviceVarId + ", questionId="
				+ questionId + ", questionCatId=" + questionCatId + ", questionSubCatId=" + questionSubCatId
				+ ", responseType=" + responseType + ", date=" + date + ", time=" + time + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (date ^ (date >>> 32));
		result = prime * result + questionCatId;
		result = prime * result + questionId;
		result = prime * result + questionSubCatId;
		result = prime * result + responseType;
		result = prime * result + serviceId;
		result = prime * result + serviceVarId;
		result = prime * result + time;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WaitingTimeLine other = (WaitingTimeLine) obj;
		if (date != other.date)
			return false;
		if (questionCatId != other.questionCatId)
			return false;
		if (questionId != other.questionId)
			return false;
		if (questionSubCatId != other.questionSubCatId)
			return false;
		if (responseType != other.responseType)
			return false;
		if (serviceId != other.serviceId)
			return false;
		if (serviceVarId != other.serviceVarId)
			return false;
		if (time != other.time)
			return false;
		return true;
	}

}
