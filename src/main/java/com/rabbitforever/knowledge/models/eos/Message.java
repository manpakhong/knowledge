package com.rabbitforever.knowledge.models.eos;

public class Message {

    private String from;
    private String to;
    private String text;

    public String getText() {
        return text;
    }

    public String getFrom() {
        return from;
    }

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setText(String text) {
		this.text = text;
	}
    
}
