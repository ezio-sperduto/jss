package jshellscript;

import java.util.*;

public class JShellScript{

	public static void main(String...aa){

		if(aa.length==0){
			System.out.println("Err: manca operazione");
			System.exit(-1);
		}

		Class<?> cl=null;
		try{
			cl=Class.forName(aa[0].substring(0,aa[0].length()-6));
		}catch(ClassNotFoundException cnfe){
			System.out.println("Err: operazione non trovata:"+aa[0]);
			System.exit(-1);
		}		

/*		if(!cl.isInstance(Operation.class)){
			System.out.println("Err: il parametro non è un'operazione");
			System.exit(-1);
		}
*/
		Operation op = null;
		try{
			op = (Operation)cl.newInstance();
		}catch(InstantiationException ie){
			System.out.println("Err: impossibile istanziare operazione:"+aa[0]);
			System.exit(-1);
		}catch(IllegalAccessException iae){
			System.out.println("Err: impossibile accedere a oggetto operazione:"+aa[0]);
			System.exit(-1);
		}

		Scanner sc=new Scanner(System.in);
        
		try{

			while(true){
	        	String input = sc.nextLine();

	        	String output = op.execute(input);

	        	System.out.println(output);
    		}

    	}catch(NoSuchElementException nsee){
    		// nulla, finite le righe, si procede

    	}catch(Exception e){
    		e.printStackTrace();
    
    	}finally{
    		sc.close();
    	}
	
	}



}