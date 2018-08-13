package br.com.caelum.livraria.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Editora {

		@Id
		@GeneratedValue
		private Integer id;
		private String cnpj;
		private String razaoSocial;
		private String nomeFantasia;
		private String emailContato;
		
		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getCnpj() {
			return cnpj;
		}
		
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		
		public String getRazaoSocial() {
			return razaoSocial;
		}
		
		public void setRazaoSocial(String razaoSocial) {
			this.razaoSocial = razaoSocial;
		}
		
		public String getNomeFantasia() {
			return nomeFantasia;
		}
		
		public void setNomeFantasia(String nomeFantasia) {
			this.nomeFantasia = nomeFantasia;
		}
		
		public String getEmailContato() {
			return emailContato;
		}
		
		public void setEmailContato(String emailContato) {
			this.emailContato = emailContato;
		}
		
}
