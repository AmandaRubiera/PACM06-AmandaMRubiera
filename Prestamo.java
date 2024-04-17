package biblioteca;

import java.time.LocalDate;

public class Prestamo {
    private Long id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private Long idLibro;
    private Long idUsuarioLector;

    public Prestamo() {
       
    }

    public Prestamo(Long id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Long idLibro, Long idUsuarioLector) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.idLibro = idLibro;
        this.idUsuarioLector = idUsuarioLector;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public Long getIdUsuarioLector() {
        return idUsuarioLector;
    }

    public void setIdUsuarioLector(Long idUsuarioLector) {
        this.idUsuarioLector = idUsuarioLector;
    }
}

