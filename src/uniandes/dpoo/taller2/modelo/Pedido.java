package uniandes.dpoo.taller2.modelo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Pedido {
	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		itemsPedido = new ArrayList<Producto>();
		idPedido ++;
	}
	
	public int getIdPedido(){;
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoItem) {
		itemsPedido.add(nuevoItem);

	}
	private int getPrecioNetoPedido() {
		int precioNeto = 0;
		for (int i = 0;i<itemsPedido.size();i++) {
			Producto objProducto = itemsPedido.get(i);
			int precio = objProducto.getPrecio();
			precioNeto += precio;
		}
		return precioNeto;
	}
	
	private int getPrecioTotalPedido() {
				
		return getPrecioIVAPedido()+getPrecioNetoPedido();
		
	}
	
	private int getPrecioIVAPedido() {
		int iva = 19;
		int total = getPrecioNetoPedido();
		return (iva*total)/100;
	}
	
	private String generarTextoFactura() {
		String factura = "";
		factura += "nombre:"+ nombreCliente;
		factura += "\nDirección: "+direccionCliente;
		factura += "\nid. Pedido: " + numeroPedidos;
		factura += "\n" + "-".repeat(20);
		for (Producto producto : itemsPedido) {
			
			factura +="\n"+ producto.generarTextoFactura();
		}
		factura += "\nTotal Neto: "+ getPrecioNetoPedido();
		factura += "\nIva "+ getPrecioIVAPedido();
		factura += "\nTotal: "+getPrecioTotalPedido();
		
		return factura;
	}
	
	public void guardarFactura() {
		String factura = generarTextoFactura();
		File archivo = new File("./data/"+"facturas/"+numeroPedidos+".txt");
		
		try (PrintWriter writer = new PrintWriter(archivo)){
			writer.write(factura);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
