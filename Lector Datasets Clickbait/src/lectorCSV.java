import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class lectorCSV {

	public static final String SEPARATOR1 =",";
	public static final String SEPARATOR2 =",";
	public static final String QUOTE ="\"";

	public static String[] removeTrailingQuotes(String[] fields) {

		String result[] = new String[fields.length];

		for (int i=0;i<result.length;i++){
			result[i] = fields[i].replaceAll(QUOTE, "").replaceAll(QUOTE+"$", "");
		}
		return result;
	}

	//Dataset for clickbait detection
	public static int[] leerDataset11(String ruta, ArrayList<item> lista) throws Exception {
		BufferedReader br = null;
		int positivos = 0;
		int  negativos = 0;
		int inicial = 0;
		int i = 0;
		int[] resultado = new int[3];

		try {
			br = new BufferedReader(new FileReader(ruta));	       
			String line = br.readLine();

			while (null != line) {	         
				String [] fields = line.split(SEPARATOR1);
				//System.out.println(Arrays.toString(fields));

				fields = removeTrailingQuotes(fields);	          

				boolean clickbait =  false;	            
				if(fields[1].equals(Integer.toString(1))) {
					clickbait = true;
				}
				lista.add(new item(fields[0], clickbait));			
				//System.out.print(lista[i].getTexto());
				//System.out.print(", ");
				//System.out.println(lista[i].getClickbait());
				i++;
				if(clickbait) {
					positivos++;
				}else {
					negativos++;
				}
				line = br.readLine();
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

	//News Clickbait
	public static int[] leerDataset2(String ruta, ArrayList<item> lista) throws Exception {
		BufferedReader br = null;
		int inicial = 0;
		int i = 0;
		int positivos = 0;
		int  negativos = 0;
		int[] resultado = new int[3];

		try {
			br = new BufferedReader(new FileReader(ruta));	       
			String line = br.readLine();

			while (null != line) {
				//System.out.println();
				//System.out.println("Empezamos: " + line); 
				String [] fields = line.split(SEPARATOR2);		            
				fields = removeTrailingQuotes(fields);	

				if(fields.length < 2) {
					//System.out.println("BORRADA");
					line = br.readLine();
					continue;					
				}else {
					if(fields[0].contains("label,title,text") || fields[1].isEmpty()  || !(fields[0].contains("news") || fields[0].contains("clickbait")) ) {
						//System.out.println("BORRADA");
						line = br.readLine();
						continue;
					}		           

					//System.out.println("F0:" + fields[0] + " F1:" + fields[1]);
					boolean clickbait =  false;
					
					if(fields[0].contains("clickbait")) {
						clickbait = true;
					}

					lista.add(new item(fields[1], clickbait));
					//System.out.print("Añadido: ");
					//System.out.print(lista[i].getTexto() + ", ");
					//System.out.println(lista[i].getClickbait());
					if(clickbait) {
						positivos++;
					}else {
						negativos++;
					}
					i++;
					line = br.readLine();
				}				
			}	         	        

		} catch (Exception e) {
			System.out.println("ERROR LEYENDO DATASET 2");
			System.out.println(e);
			throw new Exception(" ERROR LEYENDO DATASET 2 <br>" + e);
		} finally {
			if (null!=br) {
				br.close();
			}
		}
		resultado[0] = i - inicial;
		resultado[1] = positivos;
		resultado[2] = negativos;
		//System.out.println("PruebaAA: " + lista[0].getTexto());
		return resultado;
	}
	
		//The Examiner - Spam Clickbait Catalog
		public static int[] leerDataset4(String ruta, ArrayList<String> lista) throws Exception {
			BufferedReader br = null;
			int inicial = 0;
			int i = 0;
			int positivos = 0;
			int  negativos = 0;
			int[] resultado = new int[3];

			try {
				br = new BufferedReader(new FileReader(ruta));	       
				String line = br.readLine();
				if(line.contains("headline")) {
					line = br.readLine();
				}

				while (null != line) {	         
					String [] fields = line.split(SEPARATOR1);

					fields = removeTrailingQuotes(fields);	 

					boolean clickbait =  false;	            
					lista.add(fields[1]);		
					i++;
					if(clickbait) {
						positivos++;
					}else {
						negativos++;
					}
					line = br.readLine();
				}	         	        

			} catch (Exception e) {
				System.out.println("ERROR LEYENDO DATASET 4");
				System.out.println(e);
				throw new Exception(" ERROR LEYENDO DATASET 4 <br>" + e);
			} finally {
				if (null!=br) {
					br.close();
				}
			}
			resultado[0] = i - inicial;
			resultado[1] = positivos;
			resultado[2] = 0;
			return resultado;
		}
	
}
