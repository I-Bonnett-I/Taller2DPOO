package uniandes.dpoo.taller2.modelo;

public class ProductoMenu implements Producto {

	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombre, int precioBase) {
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	public int getPrecio() {

		return precioBase;
	}

	
	public String getNombre() {

		return nombre;
	}

	
	public String generarTextoFactura() {
		String fact ="";
		fact = nombre + ": "+Integer.toString(precioBase);
		return fact;
	}

}
