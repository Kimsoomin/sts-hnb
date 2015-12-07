package com.hnb.global;

public class CommandFactory {
	public Command createCommand(String action, String page){
		return new Command(action, page);
	}
}
