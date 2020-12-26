package com.zuhlke.idam.application.command;

public class SetupMFACommand {
	
		private String userId;
	
		public SetupMFACommand(String userId){
				this.userId = userId;
		}
		
		public String getUserId(){
			return userId;
		}
	
}