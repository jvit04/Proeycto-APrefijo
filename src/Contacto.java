/**
 * Representa un contacto dentro de la agenda.
 * Contiene la información personal y de contacto, así como la frecuencia
 * de búsqueda utilizada para el ordenamiento en el Heap.
 */
public class Contacto {

    private String nombre;
    private String apellido;
    private String apodo;
    private String telefonoMovil;
    private String telefonoConvencional;
    private String correoElectronico;
    private int frecuencia;

    /**
     * Constructor principal de la clase Contacto.
     * Inicializa un nuevo contacto con la información proporcionada y
     * establece la frecuencia de búsqueda en cero.
     *
     * @param nombre               El nombre del contacto.
     * @param apellido             El apellido del contacto.
     * @param apodo                El apodo o alias del contacto.
     * @param telefonoMovil        El número de teléfono móvil.
     * @param telefonoConvencional El número de teléfono fijo o convencional.
     * @param correoElectronico    La dirección de correo electrónico.
     */
    public Contacto(String nombre, String apellido, String apodo, String telefonoMovil,
                    String telefonoConvencional, String correoElectronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.telefonoMovil = telefonoMovil;
        this.telefonoConvencional = telefonoConvencional;
        this.correoElectronico = correoElectronico;
        this.frecuencia = 0;
    }

  // GETTERS Y SETTERS
    /**
     * Obtiene el nombre del contacto.
     * @return El nombre como cadena de texto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el apellido del contacto.
     * @return El apellido como cadena de texto.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Obtiene el apodo del contacto.
     * @return El apodo como cadena de texto.
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * Obtiene el teléfono móvil del contacto.
     * @return El teléfono móvil como cadena de texto.
     */
    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    /**
     * Obtiene el teléfono convencional del contacto.
     * @return El teléfono convencional como cadena de texto.
     */
    public String getTelefonoConvencional() {
        return telefonoConvencional;
    }

    /**
     * Obtiene el correo electrónico del contacto.
     * @return El correo electrónico como cadena de texto.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Obtiene la cantidad de veces que este contacto ha sido buscado.
     * @return La frecuencia de búsqueda como entero.
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public void setTelefonoConvencional(String telefonoConvencional) {
        this.telefonoConvencional = telefonoConvencional;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    // Equals
    /**
     * Compara este contacto con otro objeto para determinar si son iguales.
     * Se considera que dos contactos son el mismo si comparten el nombre y el apellido.
     *
     * @param o El objeto a comparar.
     * @return true si los contactos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacto)) return false;
        Contacto c = (Contacto) o;
        return nombre.equals(c.nombre) && apellido.equals(c.apellido);
    }


    //To String
    /**
     * Devuelve una representación en formato de texto del contacto.
     *
     * @return Cadena de texto con los detalles principales del contacto.
     */
    @Override
    public String toString() {
        return nombre + " " + apellido + " ('" + apodo + "') - Móvil: " + telefonoMovil +
                " | Convencional: " + telefonoConvencional + " | Email: " + correoElectronico;
    }
}