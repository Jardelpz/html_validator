package html_validator;

public class Tag {
	private int qtd;
	private String value;
	
	public Tag(int qtd, String value) {
		super();
		this.qtd = qtd;
		this.value = value;
	}
	
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
