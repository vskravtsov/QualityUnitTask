package com.gmail.vskravtsov992;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Query {
	private int serviceId;
	private int serviceVarId = 0;
	private int questionId;
	private int questionCatId = 0;
	private int questionSubCatId = 0;
	private char responseType;
	private long dateFrom;
	private long dateTo;

	public Query(int serviceId, int serviceVarId, int questionId, int questionCatId, int questionSubCatId,
			char responseType, long dateFrom, long dateTo) {
		super();
		this.serviceId = serviceId;
		this.serviceVarId = serviceVarId;
		this.questionId = questionId;
		this.questionCatId = questionCatId;
		this.questionSubCatId = questionSubCatId;
		this.responseType = responseType;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public Query() {
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

	public long getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(long dateFrom) {
		this.dateFrom = dateFrom;
	}

	public long getDateTo() {
		return dateTo;
	}

	public void setDateTo(long dateTo) {
		this.dateTo = dateTo;
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
			Date date1 = format.parse(splitLine[6]);
			this.setDateFrom(date1.getTime());
			Date date2 = format.parse(splitLine[7]);
			this.setDateFrom(date2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Query [serviceId=" + serviceId + ", serviceVarId=" + serviceVarId + ", questionId=" + questionId
				+ ", questionCatId=" + questionCatId + ", questionSubCatId=" + questionSubCatId + ", responseType="
				+ responseType + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (dateFrom ^ (dateFrom >>> 32));
		result = prime * result + (int) (dateTo ^ (dateTo >>> 32));
		result = prime * result + questionCatId;
		result = prime * result + questionId;
		result = prime * result + questionSubCatId;
		result = prime * result + responseType;
		result = prime * result + serviceId;
		result = prime * result + serviceVarId;
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
		Query other = (Query) obj;
		if (dateFrom != other.dateFrom)
			return false;
		if (dateTo != other.dateTo)
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
		return true;
	}

}
