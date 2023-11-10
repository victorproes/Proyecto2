package com.model;



public class Empleado extends Persona {
	private int categoria;
	public int anyos;
    public String estado; 

   
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empleado(String nombre, String sexo, String dni) throws DatosNoCorrectoExcpetion {
		super(nombre, sexo, dni);
		this.categoria = 1;
		this.anyos = 0;
      ; // Inicializar el estado

	}

	public Empleado(String dni, String nombre, String sexo, int categoria, int anyos) throws DatosNoCorrectoExcpetion {
		super(nombre, sexo, dni);
		if (!(categoria >= 0 && categoria <= 10)) {
			throw new DatosNoCorrectoExcpetion("error la categoria tiene que ser entre 0 y 10");
		}
		if (anyos < 0) {
			throw new DatosNoCorrectoExcpetion("Error los anyos no pueden ser negativos");

		}

		this.categoria = categoria;
		this.anyos = anyos;
        this.estado = estado; // Inicializar el estado

	}

	
	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public void incrAnyo() {
		anyos++;
	}

	public void imprime() {
		Imprime();
		System.out.println("anyos: " + anyos + " categoria: " + categoria);
	}

	public int getAnyos() {
		return anyos;
	}

	public void setAnyos(int anyos) {
		this.anyos = anyos;
	}


	
	

	

	
}
