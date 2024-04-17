# PacDesarrolloAcceso
Pac de desarrollo de la asignatura Acceso a datos (Biblioteca) de Amanda Maximina Rubiera Fernández

Este proyecto se corresponde a un sistema de gestión de biblioteca desarrollado en Java con el entorno de desarrollo de Eclipse utilizando Hibernate como framework de mapeo objeto-relacional para la persistencia de datos en una base de datos MySQL.

El proyecto está formado por las siguientes clases:

1. Main.java
Esta clase contiene el método main, que inicia la aplicación y proporciona un menú de opciones para que el usuario interactue con el sistema de gestión de biblioteca.
Sus funcionalidades son:
  - Inicio del Programa: Al iniciar el programa, se configura la sesión de Hibernate y se imprime un mensaje indicando que el programa ha comenzado.

  - Menú de Opciones: Se presenta un menú de opciones que permiten al usuario de la aplicación realizar diversas operaciones en la biblioteca, como agregar un libro, agregar un lector, eliminar un libro, eliminar un lector, ver listas de libros y lectores...

  - Operaciones según opción seleccionada: Según la opción seleccionada por el usuario en el menú, se ejecuta el código correspondiente, utilizando las clases CrudLibro, CrudLector y CrudPrestamo para interactuar con la base de datos.

  - Finalización del Programa: Cuando el usuario selecciona la opción para salir del programa, se cierra la sesión de Hibernate y se imprime un mensaje de despedida antes de finalizar la ejecución del programa.

  - Manejo de Excepciones: se manejan las excepciones para garantizar la integridad y estabilidad del programa en caso de errores durante la interacción con la base de datos u otras operaciones.
  - Cierre de Recursos: se lleva a cabo el cierre adecuado de los recursos como sesiones de Hibernate y objetos Scanner al finalizar la ejecución del     programa, evitando posibles fugas de recursos.

2. CrudLibro.java
Esta clase gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) relacionadas con la entidad Libro de la base de datos. Proporciona métodos para agregar, actualizar, eliminar y obtener libros, y obtener los libros disponibles.

3. CrudLector.java
Gestiona las operaciones CRUD relacionadas con la entidad UsuarioLector. Proporciona métodos para agregar, actualizar, eliminar y obtener lectores.

4. CrudPrestamo.java
Gestiona las operaciones CRUD relacionadas con la entidad Prestamo. Proporciona métodos para agregar, actualizar, eliminar y obtener préstamos, así como para obtener préstamos no devueltos y el historial de préstamos por lector.

5. Libro.java
Una clase que representa la entidad Libro, con atributos como título, autor, año de publicación y disponibilidad.

6. UsuarioLector.java
Una clase que representa la entidad UsuarioLector, con atributos como nombre, apellido, email y número de teléfono.

7. Prestamo.java
Una clase que representa la entidad Prestamo, con atributos como fecha de préstamo, fecha de devolución, ID del libro y ID del lector.


Configuración de Hibernate
El archivo hibernate.cfg.xml contiene la configuración de Hibernate, incluyendo la URL de la base de datos, el usuario y la contraseña, así como los mapeos de clases a tablas.

Mapeos de Hibernate
Se proporcionan archivos XML de mapeo de Hibernate para las clases Libro, UsuarioLector y Prestamo, que establecen la correspondencia entre las propiedades de las clases y las columnas de las tablas en la base de datos.

Dentro del archivo hibernate.cfg.xml, se especifican los archivos de mapeo de Hibernate (.hbm.xml) que mapean las clases de entidad de Java a las tablas de la base de datos. 

Se incluyen los archivos de mapeo usuarioLector.hbm.xml, libro.hbm.xml y prestamo.hbm.xml, que describen cómo las clases UsuarioLector, Libro y Prestamo se relacionan con las tablas correspondientes en la base de datos.
