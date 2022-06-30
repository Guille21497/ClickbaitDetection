import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class lectorTXT {

	//Dataset for clickbait detection
	public static int[] leerDataset12(String ruta1, String ruta2, ArrayList<item> lista) throws Exception {
		BufferedReader br = null;
		BufferedReader br2 = null;
		int positivos = 0;
		int  negativos = 0;
		int inicial = 0;
		int i = 0;
		int[] resultado = new int[3];

		try {

			//titulares clicbait
			br = new BufferedReader(new FileReader(ruta1));	       
			String line = br.readLine();

			while (null != line) {	         	
				boolean clickbait =  true;
				String texto = line;
				//System.out.println("LINEA:" + texto);
				if(!texto.contains("a") && !texto.contains("e") && !texto.contains("i") && !texto.contains("o") && !texto.contains("u")) {
					//System.out.println("BORRADA");
					line = br.readLine();
					continue;	
				}

				lista.add(new item(texto, clickbait));			
				//System.out.print(lista[i].getTexto());
				//System.out.print(", ");
				//System.out.println(lista[i].getClickbait());
				i++;
				positivos++;
				line = br.readLine();
			}	    

			//titulares no clicbait
			br2 = new BufferedReader(new FileReader(ruta2));	       
			String line2 = br2.readLine();

			while (null != line2) {	         	
				boolean clickbait =  false;
				String texto = line2;
				//System.out.println("LINEA:" + texto);
				if(!texto.contains("a") && !texto.contains("e") && !texto.contains("i") && !texto.contains("o") && !texto.contains("u")) {
					//System.out.println("BORRADA");
					line2 = br2.readLine();
					continue;	
				}

				lista.add(new item(texto, clickbait));			
				//System.out.print(lista[i].getTexto());
				//System.out.print(", ");
				//System.out.println(lista[i].getClickbait());
				i++;
				negativos++;
				line2 = br2.readLine();
			}	      

		} catch (Exception e) {
			System.out.println("ERROR LEYENDO DATASET 1");
			System.out.println(e);
			throw new Exception(" ERROR LEYENDO DATASET 1 <br>" + e);
		} finally {
			if (null!=br) {
				br.close();
			}
		}
		resultado[0] = i - inicial;
		resultado[1] = positivos;
		resultado[2] = negativos;
		return resultado;
	}

	//Rutas datasets 
	public static ArrayList<String> leerRutas() throws Exception {
		BufferedReader br = null;
		ArrayList<String> rutas = new ArrayList<String>();

		try {
			URL path = ClassLoader.getSystemResource("rutas.txt");
			if(path==null) {
				throw new Exception("Ruta incorrecta");
			}
			File f = new File(path.toURI());
			br = new BufferedReader(new FileReader(f));	       
			String line = br.readLine();

			while (null != line) {	         	
				String texto = line;
				rutas.add(texto);
				line = br.readLine();
			}	    	      
		} finally {
			if (null!=br) {
				br.close();
			}
		}

		return rutas;
	}
}
