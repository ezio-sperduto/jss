import jshellscript.*;

public class StampaLunghezza implements Operation{
	@Override
	public String execute(String input){
		return "§"+input.length()+":"+input;
	}
}
