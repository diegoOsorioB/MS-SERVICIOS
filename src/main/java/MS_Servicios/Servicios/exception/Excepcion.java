package MS_Servicios.Servicios.exception;

public class Excepcion {
    public static class ServicioNotFoundExcepcion extends RuntimeException {
        public ServicioNotFoundExcepcion(int id) {
            super("Servicio no encontrado");
        }
    }
    public static class CategoriaNotFoundExcepcion extends RuntimeException {
        public CategoriaNotFoundExcepcion(int id) {
            super("Categoria no encontrada");
        }
    }

    public static class DataBaseException extends RuntimeException {
        public DataBaseException(String mensaje) {
            super(mensaje);
        }
    }

    public static class AuthException extends RuntimeException {
        public AuthException(String mensaje) {
            super(mensaje);
        }
    }
    public static class ValidationException extends RuntimeException {
        public ValidationException(String mensaje) {
            super(mensaje);
        }
    }
}
