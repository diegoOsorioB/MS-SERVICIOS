package MS_Servicios.Servicios.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExcepcion {

    @ExceptionHandler(Excepcion.ServicioNotFoundExcepcion.class)
    public ResponseEntity<?> servicioNotFound(Excepcion.ServicioNotFoundExcepcion servicioNotFoundExcepcion) {
        return  buildResponse(HttpStatus.NOT_FOUND,servicioNotFoundExcepcion.getMessage());
    }

    @ExceptionHandler(Excepcion.CategoriaNotFoundExcepcion.class)
    public ResponseEntity<?> categoriaNotFound(Excepcion.CategoriaNotFoundExcepcion categoriaNotFoundExcepcion) {
        return  buildResponse(HttpStatus.NOT_FOUND,categoriaNotFoundExcepcion.getMessage());
    }

    @ExceptionHandler({Excepcion.DataBaseException.class, DataAccessException.class})
    public ResponseEntity<?> manejarErrorBD(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error en la base de datos: " + ex.getMessage());
    }
    @ExceptionHandler(Excepcion.AuthException.class)
    public ResponseEntity<Map<String, Object>> handleAuth(Excepcion.AuthException ex) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String mensaje) {
        Map<String, Object> body = new HashMap<>();
        body.put("Origen", "MS-Servicios");
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", mensaje);
        return new ResponseEntity<>(body, status);
    }
}
