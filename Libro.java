package biblioteca;

public class Libro {
    private Long id;
    private String titulo;
    private String autor;
    private int añoPublicacion;
    private boolean disponible;

    public Libro() {
        this.disponible = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(long i) {
        this.id = i;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
    	 if (añoPublicacion >= 0) {
             this.añoPublicacion = añoPublicacion;
         } else {
             throw new IllegalArgumentException("El año de publicación no puede ser negativo");
         }
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
