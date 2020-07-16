package com.yingbao.career.utils;

public class EventMessageBean {
	public int messageType;
	public int intContent;
	public int subIntContent;
	public String strContent;
	public String subStrContent;
	public Object objContent;
	public Object objContent_add;
	public long longContent;
	public long subLongContent;

	public EventMessageBean(int messageType) {
		super();
		this.messageType = messageType;
	}
	

}
