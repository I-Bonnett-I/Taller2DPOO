package uniandes.dpoo.taller2.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uniandes.dpoo.taller2.modelo.Pedido;
import uniandes.dpoo.taller2.modelo.Producto;
import uniandes.dpoo.taller2.modelo.Restaurante;

public class Aplicacion {
	
	private Restaurante restaurante;
	private boolean pedidoCreado = false; 
	
	public void ejecutarAplicacion()
	{
		Restaurante restaurante = new Restaurante();
		System.out.println("Menu Resturante\n");
		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejecutarMenu(restaurante);
				else if (opcion_seleccionada == 2 && restaurante != null)
					ejecutarCrearPedido(restaurante);
				else if (opcion_seleccionada == 3 && restaurante != null)
					ejecutarAgregarElemento(restaurante);
				else if (opcion_seleccionada == 4 && restaurante != null)
					ejecutarCerrarPedido(restaurante);
				else if (opcion_seleccionada == 5 && restaurante != null)
					ejecutarConsultarPedido(restaurante);
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else if (restaurante == null)
				{
					System.out.println("Ocurrió un problema cargando la informacion");
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}

	/**
	 * Muestra al usuario el menú con las opciones para que escoja la siguiente
	 * acción que quiere ejecutar.
	 */
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar menu");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Cerrar un pedido");
		System.out.println("5. Consultar la informacion de un pedido");
		System.out.println("6. Salir de la aplicacion");
		
	}
	
	public void ejecutarMenu(Restaurante restaurante) {
		int i = 1;
		ArrayList<Producto> menu = restaurante.getMenuBase();
		for(Producto nProducto : menu) {		
				System.out.println(i+". " + nProducto.getNombre() + " " + nProducto.getPrecio());
				i++;
			}
		}

	private void ejecutarCrearPedido(Restaurante restaurante) {
		
		System.out.println("Creando un nuevo pedido");
		
		String nombre = input("Por favor ingrese su nombre: ");
		String address = input("Por favor ingrese su dirección: ");
		
		restaurante.iniciarPedido(nombre, address);
		
		pedidoCreado = true;		
	}
	
	private void ejecutarAgregarElemento(Restaurante restaurante) {
		if (pedidoCreado == true) {
			int productoId = Integer.parseInt(input("Ingrese el codigo del producto: "));
			Pedido pedActual = restaurante.getPedidoEnCurso();
			ArrayList<Producto> lstMenu = restaurante.getMenuBase();
			Producto prFound = lstMenu.get(productoId-1);
			pedActual.agregarProducto(prFound);
			}
		else {
			System.out.println("Primero debe crear un pedido");
		}
	}

	private void ejecutarConsultarPedido(Restaurante restaurante) {
		int id = Integer.parseInt(input("Ingrese el id del pedido: "));
		Pedido ped = restaurante.buscarId(id);
		if (ped == null)
			System.out.println("Antes debe crear un pedido");
		else {
		File archivo = new File("./data/"+"facturas/"+ped.getIdPedido()+".txt");
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();
			while(linea != null) 
			{
				System.out.println(linea);
				linea = br.readLine();
;			}
		}
		catch(Exception e)
		{e.printStackTrace();
			
		}
		}
	}

	private void ejecutarCerrarPedido(Restaurante restaurante) {
		int id = restaurante.cerrarYGuardarPedido();
		System.out.println("Su pedido fue cerrado y guardado con el id " + id);
	}

	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args)
	{
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
}
