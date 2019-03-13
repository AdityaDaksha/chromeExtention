package com.goblk.app.constant;

public enum Command {
	WEBSTER("web"),
	WIKI("wiki"),
	SPLUNK("splunk"),
	DEFAULT("wiki");
	
	private String command;
	
	private Command(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
	public static Command findCommandByAbbr(String abbr){
	    for(Command v : values()){
	        if( v.getCommand().equals(abbr)){
	            return v;
	        }
	    }
	    return null;
	}
	
}
