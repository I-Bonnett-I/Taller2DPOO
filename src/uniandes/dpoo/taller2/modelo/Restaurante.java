package uniandes.dpoo.taller2.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Restaurante {
	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<Combo> combos;
	private ArrayList<Pedido> pedidos;
	private ArrayList<Producto> menuBase;
	private Pedido pedidoEnCurso;

		
	public Restaurante(){
		cargarInformacion();
		pedidos = new ArrayList<Pedido>();
	}
	
	public void cargarInformacion() {
		cargarIngredientes();
		cargarMenu();
		cargarCombos();
	}
	private void cargarMenu() {
		
		try{
			 File archivo = new File ("./data/menu.txt");
	         FileReader fr = new FileReader (archivo);
	         BufferedReader br = new BufferedReader(fr);
	         String linea;
	         menuBase = new ArrayList<Producto>();
	         while((linea=br.readLine())!=null) {
	        	String[] partes = linea.split(";");
        	 	String nombre = partes[0];
        	 	int precio = Integer.parseInt(partes[1]);
        	 	ProductoMenu producto = new ProductoMenu(nombre,precio);
        	 	menuBase.add(producto);
        	 	
	         }
			}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void cargarCombos() {
		// TODO Auto-generated method stub
		try{
			File archivo = new File ("./data/combos.txt");
			FileReader fr = new FileReader (archivo);
         	BufferedReader br = new BufferedReader(fr);
         	String linea;
         	combos = new ArrayList<Combo>();
	 		while ((linea=br.readLine())!=null) {
	 			String[] partes = linea.split(";");
	 			String ncombo = partes[0];
	 			double descuento = (Double.parseDouble(partes[1].replace("%", "")))/100;
	 			Combo combo = new Combo(ncombo,descuento);
	 			combos.add(combo);
	 			ArrayList<String> names = new ArrayList<String>();
	 			for (int i = 2; i < partes.length;i++) {
	 				names.add(partes[i]);
	 				
	 			}
	 			int x = 0;
	 			for(Producto producto : menuBase) {
	 				if(names.contains(producto.getNombre())){
	 					combo.agregarItemACombo(producto);
	 				}
	 			}
	 			menuBase.add(combo);
	 		}
	 	}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private void cargarIngredientes() {
		try{
			File archivo = new File ("./data/ingredientes.txt");
	         FileReader fr = new FileReader (archivo);
	         BufferedReader br = new BufferedReader(fr);
	         String linea;
	         ingredientes = new ArrayList<Ingrediente>();
	         while((linea=br.readLine())!=null) {
	        	String[] partes = linea.split(";");
       	 		String nombre = partes[0];
       	 		int precio = Integer.parseInt(partes[1]);
       	 		Ingrediente ingrediente = new Ingrediente(nombre,precio);
       	 		ingredientes.add(ingrediente);
       	 	
	         } 	
	}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void iniciarPedido(String nombre, String address){
		pedidoEnCurso = new Pedido(nombre,address);
	}
	
	public int cerrarYGuardarPedido() {
		pedidos.add(pedidoEnCurso);
		pedidoEnCurso.guardarFactura();
		int id = pedidoEnCurso.getIdPedido();
		return id;
	}
	
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}
	
	public ArrayList<Producto> getMenuBase() {
		return menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	
	public Pedido buscarId(int id) {
		for (Pedido pedido: pedidos) {
			if (pedido.getIdPedido()== id) {
				return pedido;
			}
		}
		return null;
	}
}
