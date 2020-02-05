package jshellscript;

import java.util.*;

public interface Operation{

	public static Map<String,Object> env=new HashMap<>();

	public abstract String execute(String input);

}
