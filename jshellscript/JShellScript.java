package jshellscript;

import java.util.*;
import utilita.*;

public class JShellScript{

	static void stampaHelp(){
		String help="\n"+
		" -- ISTRUIZIONI UTILIZZO jss (JShellScript) --\n"+
		"\n"+
		"jss\n"+
		"	---> senza parametro, stampa questo help\n"+
		"\n"+
		"\n"+
		"jss stub Pippo\n"+
		"	---> genera stub Pippo.jss\n"+
		"\n"+
		"\n"+
		"jss comp Pippo.jss\n"+
		"	---> compila l'operazione Pippo.jss, producendo Pippo.class\n"+
		"\n"+
		"\n"+
		"cat ESEMPIO.txt | jss Pippo.class | altro | altro > altro.txt\n"+
		"	---> manipola in pipe ogni riga secondo l'operazione\n"+
		"\n";

		System.out.println(help);
	}


	static void generaStub(String nomeStub){
		String sorgenteStub = ""+
		"\n// parametri: String input\n"+
		"\n"+
		"return input;\n\n";

		U.scrivi(nomeStub+".jss",sorgenteStub);
	}

	static void generaCompilaOperazione(String nomeOperazione){

	}


	static void elabora(String nomeOperazione){

		Class<?> cl=null;
		try{
			cl=Class.forName(nomeOperazione);
		}catch(ClassNotFoundException cnfe){
			System.out.println("Err: operazione non trovata:"+nomeOperazione+".class");
			System.exit(-1);
		}		

		Operation op = null;
		try{
			op = (Operation)cl.newInstance();
		}catch(InstantiationException ie){
			System.out.println("Err: impossibile istanziare operazione:"+nomeOperazione+".class");
			System.exit(-1);
		}catch(IllegalAccessException iae){
			System.out.println("Err: impossibile accedere a oggetto operazione:"+nomeOperazione+".class");
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


	public static void main(String...aa){

		if(aa.length==0){
			stampaHelp();
			System.exit(-1);
		}

		String par1 = aa[0];

		if(par1.equals("stub")){
			System.out.println("...generazione STUB");

			if(aa.length==1){
				System.out.println("Err: manca nome stub");
				System.exit(-1);
			}

			String nomeStub = aa[1];


			generaStub(nomeStub);

		}else if(par1.equals("comp")){
			System.out.println("...compilazione operazione");

			if(aa.length==1){
				System.out.println("Err: manca nome operazione .jss");
				System.exit(-1);
			}

			String nomeOperazione = aa[1];

			if(!nomeOperazione.endsWith(".jss")){
				System.out.println("Err: il nome non e' un file .jss");
				System.exit(-1);
			}


			generaCompilaOperazione(nomeOperazione);

		}else{

			String nomeOperazione = par1.substring(0,par1.length()-6);

		}




	}

}