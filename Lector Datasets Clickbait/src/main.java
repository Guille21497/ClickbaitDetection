import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;


public class main {

	public static final String ROUTE1 ="D:/DATASETS/Dataset for clickbait detection/CSV/clickbait_data.csv";
	public static final String ROUTE2 ="D:/DATASETS/Dataset for clickbait detection/TXT/clickbait_data.txt";
	public static final String ROUTE3 ="D:/DATASETS/Dataset for clickbait detection/TXT/non_clickbait_data.txt";
	public static final String ROUTE4 ="D:/DATASETS/News Clickbait/train.csv";
	public static final String ROUTE5 ="D:/DATASETS/Webis Clickbait 17/clickbait17-validation-170630/instances.jsonl";
	public static final String ROUTE6 ="D:/DATASETS/Webis Clickbait 17/clickbait17-validation-170630/truth.jsonl";
	public static final String ROUTE7 ="D:/DATASETS/The Examiner - Spam Clickbait Catalog/examiner-date-text.csv";

	public static final int numeroDataSets = 3;
	static ArrayList<item> dataset1 = new ArrayList<item>();
	static ArrayList<item> dataset2 = new ArrayList<item>();
	static ArrayList<item> dataset3 = new ArrayList<item>();
	static ArrayList<String> dataset4 = new ArrayList<String>();

	static ArrayList<item> listaClasificada = new ArrayList<item>();
	static ArrayList<String> listaSinClasificar = new ArrayList<String>();
	static ArrayList<item> conjuntoEntrenamiento = new ArrayList<item>();
	static ArrayList<item> conjuntoTest = new ArrayList<item>();

	
	public static void main(String args[]) throws Exception {
		int[][] resultado = new int[numeroDataSets][2];
		int[] resultado1 = new int[3];
		int[] resultado2 = new int[3];
		int[] resultado3 = new int[3];
		int[] resultado4 = new int[3];
		int indice = 0;
		int indice2 = 0;
		int tam1 = Integer.parseInt(args[1]);
		int tam2 = Integer.parseInt(args[4]);
		float per1 = Float.parseFloat(args[2]);
		float per2 = Float.parseFloat(args[5]);
		
		//Mostrar argumentos por consola
		mostrarArgumentos(args);
		
		// -- LECTURA DE DATASETS --
		
		//Dataset for clickbait detection
		if(args[0].contains("1") || args[3].contains("1")) {
			//resultado1 = lectorCSV.leerDataset11(ROUTE1, dataset1, indice);
			resultado1 = lectorTXT.leerDataset12(ROUTE2, ROUTE3, dataset1);
			resultado[0] = resultado1;			
			indice = indice + resultado1[0];			
		}

		//News Clickbait
		if(args[0].contains("2") || args[3].contains("2")) {			
			resultado2 = lectorCSV.leerDataset2(ROUTE4, dataset2);
			resultado[1] = resultado2;
			indice = indice + resultado2[0];			
		}

		//Webis Clickbait 17
		if(args[0].contains("3") || args[3].contains("3")) {			
			resultado3 = lectorJSON.leerDataset3(ROUTE5, ROUTE6, dataset3);
			resultado[2] = resultado3;
			indice = indice + resultado3[0];
		}

		//The Examiner - Spam Clickbait Catalog
		if(args[0].contains("4") || args[3].contains("4")) {		
			resultado4 = lectorCSV.leerDataset4(ROUTE7, dataset4);
			resultado[3] = resultado4;
			indice2 = indice2 + resultado4[0];
		}

		//Resultado lectura datasets
		mostrarResultadoLectura(resultado);
		
		// -- CREAR CONJUNTOS ENTRENAMIENTO Y TEST --
		
		//Conjunto entrenamiento
		int indice3 = 0;
		int indice4 = resultado[0][0]; 
		if(args[0].contains("1")) {
			conjuntoEntrenamiento.addAll(dataset1);
			indice3 = indice3 + resultado[0][0];
			System.out.println("Prueba1: " + conjuntoEntrenamiento.get(31000).getTexto() + " -- " + conjuntoEntrenamiento.get(31000).getClickbait());
		}
		if(args[0].contains("2")) {
			indice4 = resultado[1][0];
			conjuntoEntrenamiento.addAll(dataset2);
			indice3 = indice3 + resultado[1][0];
			System.out.println("Prueba2: " + conjuntoEntrenamiento.get(50000).getTexto() + " -- " + conjuntoEntrenamiento.get(50000).getClickbait());
		}
		if(args[0].contains("3")) {
			indice4 = resultado[2][0];
			conjuntoEntrenamiento.addAll(dataset3);
			indice3 = indice3 + resultado[2][0];
			System.out.println("Prueba3: " + conjuntoEntrenamiento.get(69930).getTexto() + " -- " + conjuntoEntrenamiento.get(69930).getClickbait());
		}
		
		System.out.println();
		System.out.println("CONJUNTO ENTRENAMIENTO: ");
		Collections.shuffle(conjuntoEntrenamiento);
		conjuntoEntrenamiento = ajustaLista(conjuntoEntrenamiento, tam1, per1);
		System.out.println("Prueba entrenamiento: " + conjuntoEntrenamiento.get(1).getTexto() + " -- " + conjuntoEntrenamiento.get(1).getClickbait());
		
		//Conjunto test
		int indice5 = 0;
		int indice6 = resultado[0][0];
		if(args[3].contains("1")) {
			conjuntoTest.addAll(dataset1);
			indice4 = indice4 + resultado[0][0];
			indice5 = indice5 + resultado[0][0];
		}
		if(args[3].contains("2")) {
			conjuntoTest.addAll(dataset2);
			indice4 = indice4 + resultado[1][0];
			indice5 = indice5 + resultado[1][0];
		}
		if(args[3].contains("3")) {
			conjuntoTest.addAll(dataset3);
			indice4 = indice4 + resultado[2][0];
			indice5 = indice5 + resultado[2][0];
		}
		
		//conjunto sin clasificar
		if(args[3].contains("4")) {
			listaSinClasificar.addAll(dataset4);
			indice4 = indice4 + resultado[3][0];
			indice5 = indice5 + resultado[3][0];
		}
		System.out.println();
		System.out.println("CONJUNTO TEST: ");
		Collections.shuffle(conjuntoTest);
		conjuntoTest = ajustaLista(conjuntoTest, tam2, per2);
		System.out.println("Prueba test: " + conjuntoTest.get(1).getTexto() + " -- " + conjuntoTest.get(1).getClickbait());
		
		escritor es = new escritor();
		if(!conjuntoTest.isEmpty()) {
			escritor.escribirCSV(conjuntoTest, "conjuntoTest");
		}
		if(!conjuntoEntrenamiento.isEmpty()) {
			escritor.escribirCSV(conjuntoEntrenamiento, "conjuntoEntrenamiento");
		}
		if(!listaSinClasificar.isEmpty()) {
			escritor.escribirCSV2(listaSinClasificar, "listaSinClasificar");
		}
		if(!listaClasificada.isEmpty()) {
			escritor.escribirCSV(listaClasificada, "listaClasificada");
		}
		
	}
	
	public static ArrayList<item> ajustaLista(ArrayList<item> lista, int tam, float per) {
		ArrayList<item> resultado = new ArrayList<item>();
		ArrayList<item> bote = new ArrayList<item>();
		
		float actual = (float) 0.0;
		float clickbait = (float) 0.0;
		float noClickbait = (float) 0.0;
		for(int i=0; i<lista.size(); i++) {
			if(resultado.size() == tam) {
				break;
			}
			if(actual <= per) {
				if(lista.get(i).getClickbait()) {
					resultado.add(lista.get(i));
					clickbait++;
				}else {
					bote.add(lista.get(i));
				}					
			}else {
				if(!lista.get(i).getClickbait()) {
					resultado.add(lista.get(i));
					noClickbait++;
				}else {
					bote.add(lista.get(i));
				}	
			}
			actual = clickbait / noClickbait;
		}
		
		int cont = 0;
		while(resultado.size() < tam  &&  cont < 50) {
			for(int i=0; i<bote.size(); i++) {
				if(resultado.size() == tam) {
					break;
				}
				if(actual <= per) {
					if(bote.get(i).getClickbait()) {
						resultado.add(bote.get(i));
						bote.remove(i);
						clickbait++;
					}					
				}else {
					if(!bote.get(i).getClickbait()) {
						resultado.add(bote.get(i));
						bote.remove(i);
						noClickbait++;
					}	
				}
				actual = clickbait / noClickbait;
			}
			cont++;
		}
		
		System.out.println("Tamaño: " + resultado.size() + " -- Clickbaits: "  + clickbait + " -- noClickbaits: " + clickbait + " %: " + actual);
		return resultado;
	}
	
	public static void mostrarResultadoLectura(int[][] resultado) {
		DecimalFormat df = new DecimalFormat("#.00");
		double total = 0;
		double totalClickbait = 0;
		double totalNoClickbait = 0;

		for(int i=0; i<numeroDataSets ;i++) {
			String porcentaje1 = df.format( (100*(double)resultado[i][1] / (double)resultado[i][0]) );
			String porcentaje2 = df.format( (100*(double)resultado[i][2] / (double)resultado[i][0]) );

			System.out.println("[DATASET " + (i+1) + ": " + resultado[i][0] + " items] " 
					+ "Clickbaits: " + resultado[i][1] + " (" + porcentaje1 + "%)"
					+ ", No clickbaits: " + resultado[i][2] + " (" + porcentaje2 + "%) " );	

			total = total + (double)resultado[i][0];
			totalClickbait = totalClickbait + (double)resultado[i][1];
			totalNoClickbait = totalNoClickbait + (double)resultado[i][2];	
		}

		String porcentaje1 = df.format( 100*(totalClickbait / total) );
		String porcentaje2 = df.format( 100*(totalNoClickbait / total) );

		System.out.println();
		System.out.println("[TOTAL: " + (int)total + " items] " 
				+ "Clickbaits: " + totalClickbait  + " (" + porcentaje1 + "%)" 
				+ ", No clickbaits: " + totalNoClickbait + " (" + porcentaje2 + "%) " );
		System.out.println();

	}
	
	public static void mostrarArgumentos(String args[]){
		System.out.println("0: "+args[0]);
		System.out.println("1: "+args[1]);
		System.out.println("2: "+args[2]);
		System.out.println("3: "+args[3]);
		System.out.println("4: "+args[4]);
		System.out.println("5: "+args[5]);
		System.out.println();
	}

}
