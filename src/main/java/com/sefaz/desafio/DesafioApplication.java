package com.sefaz.desafio;

import javax.persistence.Persistence;

public class DesafioApplication {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("desafio");
	}

}
