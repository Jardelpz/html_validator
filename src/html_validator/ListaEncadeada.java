package html_validator;
import java.util.LinkedList;


public class ListaEncadeada<T> {
	private NoLista<String> primeiro;
		
	public ListaEncadeada() {
		super();
		this.primeiro = null;
	}
	
	public NoLista<String> getPrimeiro() {
		return this.primeiro;
	}
	
	
	public void inserir(String info) {
		NoLista<String> novo = new NoLista<String>();
		
		novo.setInfo(info);
		novo.setProximo(this.primeiro);
		this.primeiro = novo;
	}
	
	public boolean estaVazia() {
		return this.primeiro == null;
	}
	
	public boolean findPair(NoLista<String> close_tag) {
		NoLista<String> open_tag = close_tag.getProximo();
		while(open_tag !=null) {
			if(open_tag.getInfo().equals(close_tag.getInfo().replace("/", ""))) {
				retirar(close_tag.getInfo());
				retirar(open_tag.getInfo());
				return true;
			}
			open_tag = open_tag.getProximo();
		}
		
		return false;
		
	}

		
	public NoLista<String> validate() {//valida todas tags que contem par
		NoLista<String> p = this.primeiro;
		while(p!=null) {
			if(p.getInfo().contains("/")) {
				
				if (findPair(p)){
					p = p.getProximo();
					continue;
				}else {
					throw new IllegalArgumentException("Not found pair: "+ p.getInfo());
				}
			
			}
			
			p = p.getProximo();
		}
		
		return this.primeiro;

	}
		
	
	
	public void retirar(String info) {
		NoLista<String> anterior = null;
		NoLista<String> p = this.primeiro;
		while((p != null) && (!p.getInfo().equals(info))){
			anterior=p;
			p =p.getProximo();
		};
		
		if(p != null) {
			if (p == this.primeiro) {
				primeiro = primeiro.getProximo();
			}else {
				anterior.setProximo(p.getProximo());
			}
		}	
		
	}

	@Override
	public String toString() {
		String infos = "";
		NoLista<String> p = this.primeiro;
		int cont =0;
		while(p!=null) {
			infos += p.getInfo();
			cont++;
			p = p.getProximo();
			
		};
		return infos;	
	}
	
	/*public int countItem(String no) {
		int countOpen = 0;
		int countClosed = 0;
		NoLista<String> p = this.primeiro;
		String teste = p.getInfo();
		while(p!=null) {
			if(no.contains("/")) {
				if(no.equals(p.getInfo())) {
					countClosed++;
				}else {
					countOpen++;
				}
			}
			
			p = p.getProximo();
		}
		
		if(countOpen > countClosed) {
			return 1;
		}else {
			return 0;
		}
	}*/
	
	
	
}
