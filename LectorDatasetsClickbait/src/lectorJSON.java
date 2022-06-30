import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class lectorJSON {

	//Webis Clickbait 17
	public static int[] leerDataset3(String ruta1, String ruta2,  ArrayList<item> lista) throws Exception {
		BufferedReader br = null;
		BufferedReader br2 = null;
		JSONParser jsonParser = new JSONParser();
		int inicial = 0;
		int i = 0;
		int positivos = 0;
		int negativos = 0;
		int[] resultado = new int[3];

		try {
			br = new BufferedReader(new FileReader(ruta1));	       
			String line = br.readLine();
			Map<Long, String> textos = new HashMap<Long, String>();
			while (null != line) {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(line);
				String id1 = (String) jsonObject.get("id");
				long id = Long.parseLong(id1);
				JSONArray textArray = (JSONArray) jsonObject.get("postText");
				String text = (String) textArray.get(0);
				textos.put(id, text);
				//System.out.println("ID : " + id);
				//System.out.println("TEXT : " + text);
				line = br.readLine();
			}

			br2 = new BufferedReader(new FileReader(ruta2));	       
			String line2 = br2.readLine();
			Map<Long, Boolean> valores = new HashMap<Long, Boolean>();
			while (null != line2) {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(line2);
				String id1 = (String) jsonObject.get("id");
				long id = Long.parseLong(id1);
				JSONArray valoraciones = (JSONArray) jsonObject.get("truthJudgments");
				double val1 = (double) valoraciones.get(0);
				double val2 = (double) valoraciones.get(1);
				double val3 = (double) valoraciones.get(2);
				double val4 = (double) valoraciones.get(3);
				double val5 = (double) valoraciones.get(4);
				double media = (val1 + val2 + val3 + val4 + val5)/5;
				boolean clickbait = false;
				if(media > 0.45) {
					clickbait = true;
				}
				valores.put(id, clickbait);

				//System.out.println("ID : " + id);
				//System.out.println("VAL : " + clickbait);

				line2 = br2.readLine();
			}	

			Iterator<Long> it = textos.keySet().iterator();
			while(it.hasNext()){
				long id = (long) it.next();
				String texto = textos.get(id);
				boolean clickbait = valores.get(id);
				lista.add(new item(texto, clickbait));

				if(clickbait) {
					positivos++;
				}else {
					negativos++;
				}
				i++;
				//System.out.println("ID: " + id + " , Texto: " + texto + " , Clickbait: " + clickbait);
			}

		} catch (Exception e) {
			System.out.println("ERROR LEYENDO DATASET 3");
			System.out.println(e);
			throw new Exception(" ERROR LEYENDO DATASET 3 <br>" + e);
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
}
