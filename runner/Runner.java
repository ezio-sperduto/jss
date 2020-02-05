package runner;

import java.io.*;
import java.util.*;



public class Runner{

	private static int 		lastExitCode = 		Integer.MIN_VALUE;
	private static boolean 	echo = 				true;

	public static int exitCode(){
		return lastExitCode;
	}
	public static void printCode(){
		System.out.println("last EXIT_CODE:"+exitCode());
	}

	public static void echoOn(){
		echo = true;
	}
	public static void echoOff(){
		echo = false;
	}

	public static List<String> runSplit(String cmd){
		return run(cmd.split(" "));
	}

	public static List<String> run(String...cmd){
		List<String> res=new LinkedList<>();
        try {

			ProcessBuilder processBuilder = new ProcessBuilder();
	        processBuilder.command(cmd);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
            	res.add(line.trim());
                if(echo)System.out.println(line);
            }

            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = reader.readLine()) != null) {
            	res.add(line.trim());
                if(echo)System.out.println(line);
            }




            lastExitCode = process.waitFor();
            

        } catch (IOException e) {
            e.printStackTrace();
            lastExitCode = -1;
            res.add(e.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
            lastExitCode = -1;
            res.add(e.toString());
        }
        return res;
	}



}