package html_validator;

import java.util.ArrayList;

public class PilhaLista<T>{
	ListaEncadeada<T> lista = new ListaEncadeada<T>();
	ArrayList<Tag> lista_tag = new ArrayList<Tag>();
	String[] ignore = new String[] {"<br>", "<meta>", "<!DOCTYPE>", "<base>", "<col>", "<command>", "<embed>", "<hr>", "<img>", "<input>", "<link>", "<param>", "<source>"};
	
	
	public boolean estaVazia() {
		return lista.estaVazia();
	}

	
	public void push(String info) {
		String compare = info.replace("/", "");

		if(info.contains("/")) {
			if(!this.peek().contains("/")) {
				if(!this.peek().equals(compare)) {
					throw new IllegalArgumentException("Necessário fechar a tag: "+ compare);
				}
				lista.inserir(info);
				addList(info);				
				
				return;
			}else {
				lista.inserir(info);
				addList(info);
				return;	
				}
			}
		
		lista.inserir(info);
				
	}
	
	public void addList(String info) {
		Boolean tem = false;
		for(Tag t: lista_tag) {
			if(t.getValue().equals(info.replace("/", "").replace("<", "").replace(">", ""))) {
				t.setQtd(t.getQtd()+1);
				tem=true;
				break;
			}
			
		}
		
		if(!tem) {
			lista_tag.add(new Tag(1, info.replace("/", "").replace("<", "").replace(">", "")));
			  
		}
	}
		
	public boolean validate() {//verifica se as tags sem fechamento estao ok

		NoLista p =  lista.validate();
		if(p==null){
			return true; 
		}else {
			while(p!=null) {
				for (int i = 0; i < ignore.length; i++) {
					if(ignore[i].equals(p.getInfo())) {
						retirar(p.getInfo());
						addList(p.getInfo());
						continue;
					}
				}
				p=p.getProximo();
			}
			
			if(this.lista.getPrimeiro()==null) {
				return true;
			}else {
				return false;
			}
			
		}
	}
	
	public String listaTagToString() {
		String result = " ";
		for(Tag t: lista_tag) {
			result += t.getQtd() +" : "+ t.getValue() +"\n";
		}
		return result;
	}
	
	public void retirar(String info) {
		this.lista.retirar(info);
	}

	
	public String peek() {
		if(estaVazia()) {
			throw new RuntimeException();
		}else {
			return lista.getPrimeiro().getInfo();
		}
		
	}
	
	public void liberar() {
		while(!estaVazia()) {
			pop();
		}
		
	}
	
	public void pop() {
		String valor = peek();
		retirar(valor);
	}
	
	
	public String toString() {
		return lista.toString();
	}
	
}
