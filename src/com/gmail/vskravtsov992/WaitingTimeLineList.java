package com.gmail.vskravtsov992;

import java.util.ArrayList;
import java.util.List;

public class WaitingTimeLineList {
	private List<WaitingTimeLine> WTLList;

	public WaitingTimeLineList(List<WaitingTimeLine> wTLList) {
		super();
		WTLList = new ArrayList<WaitingTimeLine>();
		this.WTLList = wTLList;
	}

	public WaitingTimeLineList() {
		super();
		WTLList = new ArrayList<WaitingTimeLine>();
	}

	public List<WaitingTimeLine> getWTLList() {
		return WTLList;
	}

	public void setWTLList(List<WaitingTimeLine> wTLList) {
		WTLList = wTLList;
	}
	
	public void addWaitingTimeLine(WaitingTimeLine waitingTimeLine) {
		try {

			if (waitingTimeLine == null) {
				throw new IllegalArgumentException();
			}
			WTLList.add(waitingTimeLine);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
			
		
		
	}

	@Override
	public String toString() {
		return "WaitingTimeLineList [WTLList=" + WTLList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((WTLList == null) ? 0 : WTLList.hashCode());
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
		WaitingTimeLineList other = (WaitingTimeLineList) obj;
		if (WTLList == null) {
			if (other.WTLList != null)
				return false;
		} else if (!WTLList.equals(other.WTLList))
			return false;
		return true;
	}
	
	
	
	

	
	
}
