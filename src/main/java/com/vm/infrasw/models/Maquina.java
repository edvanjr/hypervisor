package com.vm.infrasw.models;

public class Maquina {
	private String nome;
	private int ram;
	private int nucleos;
	private int armazenamento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public int getNucleos() {
		return nucleos;
	}
	public void setNucleos(int nucleos) {
		this.nucleos = nucleos;
	}
	public int getArmazenamento() {
		return armazenamento;
	}
	public void setArmazenamento(int armazenamento) {
		this.armazenamento = armazenamento;
	}
	
}
