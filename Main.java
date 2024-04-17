package biblioteca;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import java.util.Scanner;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		System.out.println("INICIO DEL PROGRAMA...");
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg
				.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
		Session session = sessionFactory.openSession();
		System.out.println("CONFIGURACIÓN REALIZADA");

		CrudLibro CrudLibro = new CrudLibro(sessionFactory);
		CrudLector lectoresCrud = new CrudLector(sessionFactory);
		CrudPrestamo crudPrestamo = new CrudPrestamo(session);

		Scanner scanner = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("\nBiblioteca:");
			System.out.println("1- Añadir un libro");
			System.out.println("2- Añadir un lector");
			System.out.println("3- Listado de libros");
			System.out.println("4- Listado de lectores");
			System.out.println("5- Ver libro por ID");
			System.out.println("6- Ver lector por ID");
			System.out.println("7- Libros actualmente prestados a un lector");
			System.out.println("8- Libros disponibles para préstamos");
			System.out.println("9- Historial de préstamos por lector");
			System.out.println("10- Insertar un préstamo");
			System.out.println("11- Insertar una devolución");
			System.out.println("12- Salir");
			System.out.print("Seleccione una opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
			case 1:
				System.out.println("Añadir nuevo libro:");

				System.out.print("Título: ");
				String titulo = scanner.nextLine();

				System.out.print("Autor: ");
				String autor = scanner.nextLine();

				System.out.print("Año de publicación: ");
				int añoPublicacion = scanner.nextInt();
				scanner.nextLine();

				Libro nuevoLibro = new Libro();
				nuevoLibro.setTitulo(titulo);
				nuevoLibro.setAutor(autor);
				nuevoLibro.setAñoPublicacion(añoPublicacion);
				nuevoLibro.setDisponible(true);

				Transaction txLibro = session.beginTransaction();
				CrudLibro.nuevoLibro(nuevoLibro);
				txLibro.commit();

				System.out.println("Libro agregado.");

				break;

			case 2:
				System.out.println("Insertar nuevo lector:");

				System.out.print("Nombre: ");
				String nombre = scanner.nextLine();

				System.out.print("Apellido: ");
				String apellido = scanner.nextLine();

				System.out.print("Email: ");
				String email = scanner.nextLine();

				System.out.print("Número de teléfono: ");
				long telefono = scanner.nextLong();
				scanner.nextLine();

				UsuarioLector nuevoLector = new UsuarioLector();
				nuevoLector.setNombre(nombre);
				nuevoLector.setApellido(apellido);
				nuevoLector.setEmail(email);
				nuevoLector.setTelefono(String.valueOf(telefono));

				Transaction txLector = session.beginTransaction();
				lectoresCrud.agregarLector(nuevoLector);
				txLector.commit();

				System.out.println("Lector agregado correctamente.");
				break;

			case 3:
				System.out.println("Listado de libros disponibles:");
				// Lista de libros disponibles
				List<Libro> librosDisponibles = CrudLibro.obtenerLibrosDisponibles();
				// Comprobar si la lista está vacía
				if (librosDisponibles.isEmpty()) {
					System.out.println("No hay libros disponibles."); // Imprimir si no hay disponibles
				} else {
					// Ver los detalles de cada libro
					for (Libro libro : librosDisponibles) {
						System.out.println("ID: " + libro.getId());
						System.out.println("Título: " + libro.getTitulo());
						System.out.println("Autor: " + libro.getAutor());
						System.out.println("Año de Publicación: " + libro.getAñoPublicacion());
						System.out.println();
					}
				}
				break;

			case 4:
				System.out.println("Listado de usuarios lectores:");
				// Listado de lectores
				List<UsuarioLector> lectores = lectoresCrud.obtenerTodosLosLectores();
				// Comprobar si hay lectores
				if (lectores.isEmpty()) {
					System.out.println("No hay usuarios lectores registrados."); // Imprimir si no hay
				} else {
					// Mostrar cada lector
					for (UsuarioLector lector : lectores) {
						System.out.println("ID: " + lector.getId());
						System.out.println("Nombre: " + lector.getNombre());
						System.out.println("Apellido: " + lector.getApellido());
						System.out.println("Email: " + lector.getEmail());
						System.out.println("Teléfono: " + lector.getTelefono());
						System.out.println();
					}
				}
				break;

			case 5:
				// ver un libro por ID
				System.out.print("Ingrese el ID del libro a consultar: ");
				long idLibro = scanner.nextLong();
				scanner.nextLine();

				Libro libro = CrudLibro.obtenerLibroPorId(idLibro);

				if (libro != null) {
					System.out.println("Detalles del libro:");
					System.out.println("ID: " + libro.getId());
					System.out.println("Título: " + libro.getTitulo());
					System.out.println("Autor: " + libro.getAutor());
					System.out.println("Año de Publicación: " + libro.getAñoPublicacion());
					System.out.println("Disponibilidad: " + (libro.isDisponible() ? "Sí" : "No"));
				} else {
					System.out.println("No se encontró ningún libro con ese ID proporcionado.");
				}
				break;

			case 6:
				// Ver un lector por ID
				System.out.print("Ingrese el ID del lector a consultar: ");
				long idLector = scanner.nextLong();
				scanner.nextLine();

				UsuarioLector lector = lectoresCrud.obtenerLectorPorId(idLector);

				if (lector != null) {
					System.out.println("Detalles del lector:");
					System.out.println("ID: " + lector.getId());
					System.out.println("Nombre: " + lector.getNombre());
					System.out.println("Apellido: " + lector.getApellido());
					System.out.println("Email: " + lector.getEmail());
					System.out.println("Teléfono: " + lector.getTelefono());
				} else {
					System.out.println("No se encontró ningún lector con el ID proporcionado.");
				}
				break;

			case 7:
				// Libros actualmente prestados a un lector
				System.out.println("Libros actualmente prestados a un lector:");
				System.out.print("Ingrese el ID del lector: ");
				long idLectorPrestamos = scanner.nextLong();
				scanner.nextLine();

				// Obtener todos los préstamos asociados al lector que aún no han sido devueltos
				List<Prestamo> prestamos = crudPrestamo.obtenerPrestamosNoDevueltosPorLector(idLectorPrestamos);
				if (prestamos == null || prestamos.isEmpty()) {
					System.out.println("El lector no tiene libros actualmente prestados.");
				} else {
					for (Prestamo prestamo : prestamos) {
						Libro libroPrestado = CrudLibro.obtenerLibroPorId(prestamo.getIdLibro());
						if (libroPrestado != null) {
							System.out.println("Título: " + libroPrestado.getTitulo());
							// Agregar más detalles del préstamo si es necesario
						}
					}
				}
				break;

			case 8:
				// Libros disponibles para préstamos
				System.out.println("Libros disponibles para préstamos:");
				List<Libro> librosDisponibles2 = CrudLibro.obtenerLibrosDisponibles();
				if (librosDisponibles2 == null || librosDisponibles2.isEmpty()) {
					System.out.println("No hay libros disponibles para préstamos en este momento.");
				} else {
					for (Libro libro2 : librosDisponibles2) {
						System.out.println("Título: " + libro2.getTitulo());
						// Agregar más detalles del libro si es necesario
					}
				}
				break;

			case 9:
				// Historial de préstamos por lector
				System.out.println("Historial de préstamos por lector:");
				System.out.print("Ingrese el ID del lector: ");
				long idLectorHistorial = scanner.nextLong();
				scanner.nextLine();

				List<Prestamo> historialPrestamos = crudPrestamo.obtenerHistorialPrestamosPorLector(idLectorHistorial);

				if (historialPrestamos.isEmpty()) {

					System.out.println("El lector no tiene historial de préstamos.");
				} else {
					for (Prestamo prestamo : historialPrestamos) {
						Libro libroPrestado = CrudLibro.obtenerLibroPorId(prestamo.getIdLibro());
						System.out.println("Título: " + libroPrestado.getTitulo());

					}
				}
				break;
					
			case 10:
			    // Insertar un préstamo
			    System.out.println("Asignar un libro a un lector:");
			    System.out.print("Ingrese el ID del lector: ");
			    long idLectorPrestamo = scanner.nextLong();
			    scanner.nextLine();

			    System.out.print("Ingrese el ID del libro: ");
			    long idLibroPrestamo = scanner.nextLong();
			    scanner.nextLine();

			    // Obtener el lector y el libro por sus respectivos IDs
			    UsuarioLector lectorPrestamo = lectoresCrud.obtenerLectorPorId(idLectorPrestamo);
			    Libro libroPrestamo = CrudLibro.obtenerLibroPorId(idLibroPrestamo);

			    if (lectorPrestamo != null && libroPrestamo != null && libroPrestamo.isDisponible()) {
			        // Asignar el préstamo
			        Prestamo prestamo = new Prestamo();
			        prestamo.setFechaPrestamo(LocalDate.now());
			        prestamo.setIdLibro(idLibroPrestamo);
			        prestamo.setIdUsuarioLector(idLectorPrestamo);

			        Transaction txPrestamo = session.beginTransaction();
			        crudPrestamo.agregarPrestamo(prestamo);
			        txPrestamo.commit();

			        // Actualizar la disponibilidad del libro
			        libroPrestamo.setDisponible(false);
			        Transaction txLibroPrestamo = session.beginTransaction();
			        CrudLibro.actualizarLibro(libroPrestamo);
			        txLibroPrestamo.commit();

			        System.out.println("Libro asignado al lector correctamente.");
			    } else {
			        System.out.println("No se puede asignar el libro al lector. Verifique los IDs proporcionados o la disponibilidad del libro.");
			    }
			    break;
			case 11:
				 // Agregar una devolución
			    System.out.println("Registrar la devolución de un libro:");
			    System.out.print("Ingrese el ID del lector que devuelve el libro: ");
			    long idLectorDevolucion = scanner.nextLong();
			    scanner.nextLine();

			    // Obtener los préstamos activos del lector
			    List<Prestamo> prestamosActivos = crudPrestamo.obtenerPrestamosNoDevueltosPorLector(idLectorDevolucion);

			    if (!prestamosActivos.isEmpty()) {
			        System.out.println("Préstamos activos del lector:");
			        for (Prestamo prestamo : prestamosActivos) {
			            Libro libroPrestado = CrudLibro.obtenerLibroPorId(prestamo.getIdLibro());
			            System.out.println("ID del préstamo: " + prestamo.getId());
			            System.out.println("Título del libro: " + libroPrestado.getTitulo());
			        }

			        System.out.print("Ingrese el ID del préstamo que desea devolver: ");
			        long idPrestamoDevolucion = scanner.nextLong();
			        scanner.nextLine();

			        // Obtener el préstamo por su ID
			        Prestamo prestamoDevolucion = crudPrestamo.obtenerPrestamoPorId(idPrestamoDevolucion);

			        if (prestamoDevolucion != null && prestamoDevolucion.getIdUsuarioLector() == idLectorDevolucion) {
			            // Asignar la fecha de devolución
			            prestamoDevolucion.setFechaDevolucion(LocalDate.now());
			            Transaction txDevolucion = session.beginTransaction();
			            crudPrestamo.actualizarPrestamo(prestamoDevolucion);
			            txDevolucion.commit();

			            // Actualizar la disponibilidad del libro
			            Libro libroDevolucion = CrudLibro.obtenerLibroPorId(prestamoDevolucion.getIdLibro());
			            libroDevolucion.setDisponible(true);
			            Transaction txLibroDevolucion = session.beginTransaction();
			            CrudLibro.actualizarLibro(libroDevolucion);
			            txLibroDevolucion.commit();

			            System.out.println("Devolución registrada correctamente.");
			        } else {
			            System.out.println("No se puede encontrar el préstamo proporcionado o no pertenece al lector especificado.");
			        }
			    } else {
			        System.out.println("El lector no tiene préstamos activos.");
			    }
			    break;
				
			case 12:
				System.out.println("Saliendo del programa...");
				break;
			default:
				System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
			}
		} while (opcion != 13);

		scanner.close();
		session.close();
		sessionFactory.close();

	}

}