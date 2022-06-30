import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class escritor {
	
	public static void escribirCSV(List<item> list, String nombre) {
		try {	
			String homeFolder = System.getProperty("user.home"); 
		    Paths.get(homeFolder, "Desktop", "conjuntosClickbait").toFile().mkdir();
			
            PrintWriter writer = new PrintWriter(homeFolder + "//Desktop//conjuntosClickbait//" + nombre + ".csv", "UTF-8");
            
            writer.println("titular;clickbait");
            for(int i=0; i<list.size(); i++) {
            	if(!list.get(i).toCSV().isEmpty() && list.get(i).toCSV() != "") { 
            		writer.println(list.get(i).toCSV());
            	}
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void escribirCSV2(ArrayList<String> lista, String nombre) {
		try {
			String homeFolder = System.getProperty("user.home"); 
		    Paths.get(homeFolder, "Desktop", "conjuntosClickbait").toFile().mkdir();
		    
		    PrintWriter writer = new PrintWriter(homeFolder + "//Desktop//conjuntosClickbait//" + nombre + ".csv", "UTF-16");
            for(int i=0; i<lista.size(); i++) {
            	if(!lista.get(i).isEmpty() && lista.get(i) != "") {
            		writer.println(lista.get(i));
            	}
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void escribirRutas(ArrayList<String> lista) {
		try {
			URL path = ClassLoader.getSystemResource("rutas.txt");
			if(path==null) {
				throw new Exception("Ruta null");
			}
			File f = new File(path.toURI());
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));

            for(int i=0; i<lista.size(); i++) {
            	if(!lista.get(i).isEmpty() && lista.get(i) != "") {
            		bw.write(lista.get(i));
            		bw.newLine();
            	}
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
