package uniandes.dpoo.taller2.modelo;

import java.util.ArrayList;

public class Combo implements Producto {
	private double descuento;
	private String nombreCombo;
	private ArrayList<Producto> itemsCombo;
	
	public Combo(String nombreCombo,double descuento){
		this.nombreCombo = nombreCombo;
		this.descuento = descuento;
		itemsCombo = new ArrayList<Producto>();
	}
	
	public int getPrecio() {
		int precio = 0;
		for(Producto producto: itemsCombo) {
			int valorProducto = producto.getPrecio();
			precio += valorProducto;
		}
		double vDescuento = precio*descuento;
		return (int)(precio - vDescuento);
	}
	
	public String getNombre() {
	
		return nombreCombo;
	}
	
	public String generarTextoFactura() {
	
		String factura = "";
		factura += nombreCombo+="\n";
		for (Producto producto : itemsCombo) {
			factura += "\n"+producto.generarTextoFactura();
		}
		return factura;
	}
	
	public void agregarItemACombo(Producto itemCombo){
		itemsCombo.add(itemCombo);
	}
}
