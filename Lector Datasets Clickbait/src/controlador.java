import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class controlador {

	static ArrayList<String> rutas = new ArrayList<String>();
	static ArrayList<item> dataset1 = new ArrayList<item>();
	static ArrayList<item> dataset2 = new ArrayList<item>();
	static ArrayList<item> dataset3 = new ArrayList<item>();
	static ArrayList<String> dataset4 = new ArrayList<String>();
	public static final int numeroDataSets = 4;
	

	public void crearConjuntos(boolean d1, boolean d2, boolean d3, boolean d4, String porcentaje, float test, int tipoConjunto,  boolean logs, JLabel consoleLabel)  {
		ArrayList<String> listaSinClasificar = new ArrayList<String>();
		ArrayList<item> conjunto = new ArrayList<item>();
		ArrayList<item> conjuntoTest = new ArrayList<item>();
		ArrayList<item> conjuntoEntrenamiento = new ArrayList<item>();

		
		int[][] resultado = new int[numeroDataSets][2];
		int[] resultado1 = new int[3];
		int[] resultado2 = new int[3];
		int[] resultado3 = new int[3];
		int[] resultado4 = new int[3];
		int indice = 0;
		int indice2 = 0;
		consoleLabel.setText("<html>");
		
		// -- LECTURA DE DATASETS --
	
		try {
			if(d1 && dataset1.size() == 0) {
				resultado1 = lectorTXT.leerDataset12(rutas.get(0), rutas.get(1), dataset1);
				resultado[0] = resultado1;
				indice = indice + resultado1[0];
				if(logs) {
					consoleLabel.setText(consoleLabel.getText() + "> Dataset 1: Leido <br>");
				}
			}	
		} catch (Exception e) {
			consoleLabel.setText(consoleLabel.getText() + ">" + e.getMessage() + " <br>");
			return;
		}
		try {
			if(d2 && dataset2.size() == 0) {
				resultado2 = lectorCSV.leerDataset2(rutas.get(2), dataset2);
				resultado[1] = resultado2;
				indice = indice + resultado2[0];
				if(logs) {
					consoleLabel.setText(consoleLabel.getText() + "> Dataset 2: Leido <br>");
				}
			}
		} catch (Exception e) {
			consoleLabel.setText(consoleLabel.getText() + ">" + e.getMessage() + " <br>");
			return;
		}
		try {
			if(d3 && dataset3.size() == 0) {
				resultado3 = lectorJSON.leerDataset3(rutas.get(3), rutas.get(4), dataset3);
				resultado[2] = resultado3;
				indice = indice + resultado3[0];	
				if(logs) {
					consoleLabel.setText(consoleLabel.getText() + "> Dataset 3: Leido <br>");
				}
			}
		} catch (Exception e) {
			consoleLabel.setText(consoleLabel.getText() + ">" + e.getMessage() + " <br>");
			return;
		}
		
		if(logs) {
			int d[] = new int[4];
				d[0] =	(d1 ? 1 : 0);
				d[1] =	(d2 ? 1 : 0);
				d[2] =	(d3 ? 1 : 0);
				d[3] =	(d4 ? 1 : 0);
			mostrarResultadoLectura(resultado, d, consoleLabel);
		}

		// -- CREAR CONJUNTO --

		int indice3 = 0;
		int indice4 = resultado[0][0]; 
		int indice5 = 0;
		if(d1) {
			conjunto.addAll(dataset1);
			indice3 = indice3 + resultado[0][0];
		}
		if(d2) {
			indice4 = resultado[1][0];
			conjunto.addAll(dataset2);
			indice3 = indice3 + resultado[1][0];
		}
		if(d3) {
			indice4 = resultado[2][0];
			conjunto.addAll(dataset3);
			indice3 = indice3 + resultado[0][0];
		}
		
		Collections.shuffle(listaSinClasificar);
		Collections.shuffle(conjunto);
		if(d1 || d2 || d3) {
			//conjunto = ajustaPorcentaje(conjunto, porcentaje, consoleLabel);
			List<List<item>> conjuntos = new ArrayList<>();
			conjuntos = ajustaConjuntos(conjunto, porcentaje, test, consoleLabel);
		
			
			// -- ESCRIBIR FICHERO -- 
			
			String fichero1 = "conjuntoEntrenamiento";
			String fichero2 = "conjuntoTest";
			
			escritor.escribirCSV(conjuntos.get(0), fichero1);
			escritor.escribirCSV(conjuntos.get(1), fichero2);
			
		}
		

	}

	public static List<List<item>> ajustaTest(ArrayList<item> conjunto, float test, JLabel consoleLabel) {
		List<List<item>> resultado = new ArrayList<>();
		ArrayList<item> conjuntoTest = new ArrayList<item>();
		ArrayList<item> conjuntoEntrenamiento = new ArrayList<item>();
		
		int tam = conjunto.size();
		int tamtest = (int) (tam * test);

		Collections.shuffle(conjunto);
		for(int i=0; i<tamtest; i++) {
			conjuntoTest.add(conjunto.get(i));
		}
		for(int i=tamtest; i<tam; i++) {
			conjuntoEntrenamiento.add(conjunto.get(i));
		}
		resultado.add(conjuntoEntrenamiento);
		resultado.add(conjuntoTest);

		consoleLabel.setText(consoleLabel.getText() + "> " + 
		"Tamaño conjunto Entrenamiento: " + conjuntoEntrenamiento.size() + "<br> > Tamaño conjunto Test: " + conjuntoTest.size());
		return resultado;
	}
	
	public static List<List<item>> ajustaConjuntos(ArrayList<item> lista, String per, float test, JLabel consoleLabel) {
		List<List<item>> resultado = new ArrayList<>();
		ArrayList<item> conjuntoTest = new ArrayList<item>();
		ArrayList<item> conjuntoEntrenamiento = new ArrayList<item>();
		ArrayList<item> clickbaits = new ArrayList<item>();
		ArrayList<item> noclickbaits = new ArrayList<item>();

		float clickbait;
		float noClickbait;
		int actualClickbait = 0;
		int actualNoClickbait = 0;
		int x = 0;
		int y = 0;
		
		Collections.shuffle(lista);
		
		for(int i=0; i<lista.size(); i++) {
			if(lista.get(i).getClickbait()) {
				x++;
			}else {
				y++;
			}
		}
		
		float auxX;
		float auxY;
		
		if(per == "0") {
			x = 0;
		}
		if(per == "0.1") {
			auxY = x * 9;
			if(auxY > y) {
				auxX = y/9;
				x = (int) auxX;
			}else {
				y = (int) auxY;
			}
		}
		if(per == "0.2") {
			auxY = x * 4;
			if(auxY > y) {
				auxX = y/4;
				x = (int) auxX;
			}else {
				y = (int) auxY;
			}		
		}
		if(per == "0.3") {
			auxY = (float) (x * 2.33);
			if(auxY > y) {
				auxX = (float) (y/2.33);
				x = (int) auxX;
			}else {
				y = (int) auxY;
			}
		}
		if(per == "0.4") {
			auxY = (float) (x * 1.5);
			if(auxY > y) {
				auxX = (float) (y/1.5);
				x = (int) auxX;
			}else {
				y = (int) auxY;
			}
		}
		if(per == "0.5") {
			auxY = x * 1;
			if(auxY > y) {
				auxX = y/1;
				x = (int) auxX;
			}else {
				y = (int) auxY;
			}
		}
		if(per == "0.6") {
			auxY = (float) (x * 0.65);
			if(auxY > y) {
				auxX = (float) (y/0.65);
				x = (int) auxX;
			}else {
				y = (int) auxY;
			}
		}
		if(per == "0.7") {
			auxY = (float) (x * 0.4285);
			if(auxY > y) {
				auxX = (float) (y/0.4285);
				x = (int) auxX;
			}else {
				y = (int) auxY;
			}
		}
		if(per == "0.8") {
			auxY = (float) (x * 0.25);
			if(auxY > y) {
				auxX = (float) (y/0.25);
				x = (int) auxX;
			}else {
				y = (int) auxY;
			}
		}
		if(per == "0.9") {
			auxY = (float) (x * 0.1);
			if(auxY > y) {
				auxX = (float) (y/0.1);
				x = (int) auxX;
			}else {
				y = (int) auxY;
			}
		}
		if(per == "1") {
			y = 0;
		}
		if(per == "TODO") {
			
		}
		
		for(int i=0; i<lista.size(); i++) {
			if(lista.get(i).getClickbait() && actualClickbait < x) {
				clickbaits.add(lista.get(i));
				actualClickbait++;
			}else {
				if(!lista.get(i).getClickbait() && actualNoClickbait < y) {
					noclickbaits.add(lista.get(i));
					actualNoClickbait++;
				}
			}
		}

		
		int trainC = 0;
		int trainNC = 0;
		int testC = 0;
		int testNC = 0;
		for(int i=0; i<clickbaits.size(); i++) {
			if(i < actualClickbait*test) {
				conjuntoTest.add(clickbaits.get(i));
				testC++;
			}else {
				conjuntoEntrenamiento.add(clickbaits.get(i));
				trainC++;
			}
		}
		
		for(int i=0; i<noclickbaits.size(); i++) {
			if(i < actualNoClickbait*test) {
				conjuntoTest.add(noclickbaits.get(i));
				testNC++;
			}else {
				conjuntoEntrenamiento.add(noclickbaits.get(i));
				trainNC++;
			}
		}
		
		Collections.shuffle(conjuntoTest);
		Collections.shuffle(conjuntoEntrenamiento);
		resultado.add(conjuntoEntrenamiento);
		resultado.add(conjuntoTest);
			
		
		float porClickbait = (float) 0.0;
		float porTest = (float) 0.0;
		float porTestC = (float) 0.0;
		float porTrainC = (float) 0.0;
		
		porClickbait = (float) actualClickbait / (actualClickbait + actualNoClickbait);
		porTest = (float) conjuntoTest.size() / (conjuntoTest.size() + conjuntoEntrenamiento.size());
		porTestC = (float) testC / (testC + testNC);
		porTrainC = (float) trainC / (trainC + trainNC);
		
		int tamañoTotal = (int)actualClickbait + (int)actualNoClickbait;
		System.out.println("Tamaño conjunto total: " + tamañoTotal + " -- Clickbaits: "  + (int)  actualClickbait + " - No Clickbaits: " + (int) actualNoClickbait + " (" + porClickbait + "%)");
		
		consoleLabel.setText(consoleLabel.getText() + "> " + "Tamaño conjunto Entrenamiento: " + conjuntoEntrenamiento.size() +	" <br>"); 
		consoleLabel.setText(consoleLabel.getText() + "> " + "Tamaño conjunto Test: "  + conjuntoTest.size() +	" <br>");
		consoleLabel.setText(consoleLabel.getText() + "> " + "Porcentaje de test: " + porTest*100 +	"% <br>");
		consoleLabel.setText(consoleLabel.getText() + "> " + "Porcentaje clickbait en conjunto test: " + porTestC*100 +	"% <br>");
		consoleLabel.setText(consoleLabel.getText() + "> " + "Porcentaje clickbait en conjunto entrenamiento: " + porTrainC*100 +	"% <br>");

		return resultado;
	}

	public static ArrayList<item> ajustaPorcentaje(ArrayList<item> lista, float per, JLabel consoleLabel) {
		ArrayList<item> resultado = new ArrayList<item>();
		ArrayList<item> bote = new ArrayList<item>();

		float actual = (float) 0.0;
		float clickbait = (float) 0.0;
		float noClickbait = (float) 0.0;
		float total = (float) 0.0;
		total = lista.size(); 
		
		for(int i=0; i<lista.size(); i++) {
	
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
			actual = clickbait / total;
		}

		
		int cont = 0;
		while(cont < 50) {
			for(int i=0; i<bote.size(); i++) {
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
				actual = clickbait / total;
			}
			cont++;
		}
		
		/*consoleLabel.setText(consoleLabel.getText() + "> " + 
		"Tamaño de la lista: " + resultado.size() + " -- Clickbaits: "  + (int)  clickbait + " -- noClickbaits: " + (int) noClickbait + " -- " + actual + "%"
		+	" <br>");
		System.out.println("Tamaño de la lista: " + resultado.size() + " -- Clickbaits: "  + (int)  clickbait + " -- noClickbaits: " + (int) noClickbait + " -- " + actual + "%");
		*/
		Collections.shuffle(resultado);
		return resultado;
	}
	
	public static void mostrarResultadoLectura(int[][] resultado, int[] d, JLabel consoleLabel) {
		DecimalFormat df = new DecimalFormat("#.00");
		double total = 0;
		double totalClickbait = 0;
		double totalNoClickbait = 0;
		
		consoleLabel.setText(consoleLabel.getText() + "<br>");
		for(int i=0; i<4 ;i++) {
			
			if(d[i] == 1) {
				String porcentaje1 = df.format( (100*(double)resultado[i][1] / (double)resultado[i][0]) );
				String porcentaje2 = df.format( (100*(double)resultado[i][2] / (double)resultado[i][0]) );
	
				consoleLabel.setText(consoleLabel.getText() + "> [DATASET " + (i+1) +  ": " + resultado[i][0] + " items] "
						+ "Clickbaits: " + resultado[i][1] + " (" + porcentaje1 + "%)"
						+ ", No clickbaits: " + resultado[i][2] + " (" + porcentaje2 + "%) "
						+ "<br>");
				
				System.out.println("[DATASET " + (i+1) + ": " + resultado[i][0] + " items] " 
						+ "Clickbaits: " + resultado[i][1] + " (" + porcentaje1 + "%)"
						+ ", No clickbaits: " + resultado[i][2] + " (" + porcentaje2 + "%) " );	
				
	
				total = total + (double)resultado[i][0];
				totalClickbait = totalClickbait + (double)resultado[i][1];
				totalNoClickbait = totalNoClickbait + (double)resultado[i][2];	
			}
		}

		String porcentaje1 = df.format( 100*(totalClickbait / total) );
		String porcentaje2 = df.format( 100*(totalNoClickbait / total) );

		consoleLabel.setText(consoleLabel.getText() + "> [TOTAL: " + (int)total + " items] " 
				+ "Clickbaits: " + totalClickbait  + " (" + porcentaje1 + "%)" 
				+ ", No clickbaits: " + totalNoClickbait + " (" + porcentaje2 + "%) "
				+ "<br>");
		consoleLabel.setText(consoleLabel.getText() + "<br>");

		System.out.println();
		System.out.println("[TOTAL: " + (int)total + " items] " 
				+ "Clickbaits: " + totalClickbait  + " (" + porcentaje1 + "%)" 
				+ ", No clickbaits: " + totalNoClickbait + " (" + porcentaje2 + "%) " );
		System.out.println();


	}
	
	public void lecturaRutas(JLabel consoleLabel) {
		try {
			rutas = lectorTXT.leerRutas();
		} catch (Exception e) {
			consoleLabel.setText(consoleLabel.getText() + "> ERROR LEYENDO FICHERO DE RUTAS <br>");
			consoleLabel.setText(consoleLabel.getText() + ">" + e.getMessage() + " <br>");
			return;
		}
	}
	
	public void setRuta1(String ruta) {
		rutas.set(0, ruta);
		escritor.escribirRutas(rutas);
	}
	public void setRuta2(String ruta) {
		rutas.set(1, ruta);
		escritor.escribirRutas(rutas);
	}
	public void setRuta3(String ruta) {
		rutas.set(2, ruta);
		escritor.escribirRutas(rutas);
	}
	public void setRuta4(String ruta) {
		rutas.set(3, ruta);
		escritor.escribirRutas(rutas);
	}
	public void setRuta5(String ruta) {
		rutas.set(4, ruta);
		escritor.escribirRutas(rutas);
	}
	public void setRuta6(String ruta) {
		rutas.set(5, ruta);
		escritor.escribirRutas(rutas);
	}
	
	public String getRuta1() {
		return rutas.get(0);
	}
	public String getRuta2() {
		return rutas.get(1);
	}
	public String getRuta3() {
		return rutas.get(2);
	}
	public String getRuta4() {
		return rutas.get(3);
	}
	public String getRuta5() {
		return rutas.get(4);
	}
	public String getRuta6() {
		return rutas.get(5);
	}

}

