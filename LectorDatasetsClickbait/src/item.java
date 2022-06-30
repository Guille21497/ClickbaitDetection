
public class item {

	private String texto;
	private boolean clickbait;
	
	public item() {
		this.texto = null;
		this.clickbait = false;
	}
	
	public item(String texto, boolean clickbait) {
		this.texto = texto;
		this.clickbait = clickbait;
	}
	
	public String getTexto() {
		return this.texto;
	}

	public boolean getClickbait() {
		return this.clickbait;
	}
	
	public String toCSV() {
		String click = "";
		
		if(this.clickbait) {
			click = "1";
		}else {
			click = "0";
		}
		return removeTrailingQuotes(this.texto) + "; " + click;
	}
	
	public String removeTrailingQuotes(String fields) {

		String result = "";
		result = fields.replaceAll("'", "");
		result = result.replaceAll("â", "");
		result = result.replaceAll("\"", "");
		result = result.replaceAll(";", "");
		result = result.replaceAll("\n", "");
		result = result.replaceAll("™", "");
		result = result.replaceAll("€", "");
		result = result.replaceAll("œ", "");
		result = result.replaceAll("Â", "");
		result = result.replaceAll("�", "");
		result = result.replaceAll("🍿", "");
		result = result.replaceAll("🐶", "");
		result = result.replaceAll("🐱", "");
		//result = result.replaceAll("", "");
		//result = result.replaceAll("ï", "");
		return result;
	}
}
