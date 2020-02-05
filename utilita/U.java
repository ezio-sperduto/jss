package utilita;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class U{

	private static boolean debug = false;
	private static Map<String,String> argomenti;
	private static Map<String,String> props;


	public static void debugOn(){
		debug=true;
	}

	public static void debugOff(){
		debug=false;
	}


	public static void debug(String s){
		if(debug){
			System.out.println("[DEBUG] "+s);
		}
	}

	public static void warn(String s){
		System.out.println("[WARN] "+s);
	}

	public static void err(String s){
		err(true,s);
	}

	public static void err(boolean cond,String s){
		if(cond){
			System.out.println("[ERROR] "+s);
			System.exit(-1);		
		}
	}

	public static void caricaProps(){

		U.props = new HashMap<>();
	}
	
	public static void caricaProps(String fname){

			Properties props = new Properties();

		try{
			InputStream in = new FileInputStream(fname);
			props.load(in);
			
			in.close();
		}catch(IOException ioe){
			err("errore in lettura file parametri: "+fname);
		}


		Map<String,String> mappa=new HashMap<String,String>();

		debug("Caricamento props...");		
		for(Object o:props.keySet()){
			mappa.put((String)o,(String)props.get(o));
			debug(" PROP "+o+":"+props.get(o));			
		}

		U.props = mappa;

	}

	public static String prop(String nome,String defaultValue){
		String res=props.get(nome);
		if(res==null)
			res=defaultValue;
		return res;
	}

	public static String prop(String nome){
		return props.get(nome);
	}

	public static Set<String> nomiProps(){
		return props.keySet();
	}

	public static void setProp(String nome, String val){
		props.put(nome,val);
	}

	public static String leggi(String filename){
			StringBuilder res=new StringBuilder();

			try{

 				File file = new File(filename);

            	Scanner input = new Scanner(file);
            	while (input.hasNextLine()) {
            	    
            	    String line = input.nextLine();
            	    res.append(line);
            	    res.append("\n");
            	
            	}
            	input.close();

        	}catch(FileNotFoundException fe){
        		err("file in leggiFile non trovato: "+filename);
        	}

            return res.toString();
	}


	public static void scrivi(String filename,String contenuto){
		
		// output
		debug("Scrittura su: "+filename+" ...");
		try{
		
			PrintWriter pw = null;
		
			pw = new PrintWriter(new FileOutputStream(new File(filename),false)); 
			pw.println(contenuto);
			pw.close();
		
		}catch(IOException ioe){
			err("Errore in scrittura su "+filename+": "+ioe.getMessage());
		}

	}

	public static String argomento(String nome){
		return argomenti.get(nome);
	}

	public static boolean esisteArgomento(String nome){
		return argomenti.get(nome)!=null;
	}

	public static void caricaArgomenti(String...a){


		argomenti=new HashMap<>();		

		for(String s:a){
			String[] arr=s.split("=");

			String nome=arr[0];

			if(arr.length==1)
				argomenti.put(nome,"true");
			else
				argomenti.put(nome,s.substring(nome.length()+1));
		}

		if(esisteArgomento("debug"))
			U.debugOn();

		debug("Caricamento argomenti...");

		for(String k:argomenti.keySet())
			debug(" ARG "+k+":"+argomento(k));

	}

	public static void riversaArgomentiSuProps(){

		debug("Riversamento argomenti su props...");

		for(String nome:nomiProps())
			if(esisteArgomento(nome))
				setProp(nome,argomento(nome));

	}
	
	public static String tabs(int n) {
		return Stream.generate(()->"\t").limit(n).collect(Collectors.joining());
	}
}