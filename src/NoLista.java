package html_validator;

public class NoLista<T> { 
	private String info;
	private NoLista<String> proximo;

	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public NoLista<String> getProximo() {
		return proximo;
	}
	public void setProximo(NoLista<String> proximo) {
		this.proximo = proximo;
	}	
	
}
