import jshellscript.*;

public class JssOp implements Operation{
	@Override
	public String execute(String input){
		return "§"+input.length()+":"+input;
	}
}
