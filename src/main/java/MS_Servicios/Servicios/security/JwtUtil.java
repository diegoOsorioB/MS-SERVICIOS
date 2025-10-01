package MS_Servicios.Servicios.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private static final String SECRET = "S3lUd1p3aXhXWnZxVjNrZFRzRW4zR1l4cXBaVjhpV3hCUXlqV1lYcGh0ZA==";

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims validarToken(String token) {
        token = token.trim();
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String getUsernameFromToken(String token) {
        return validarToken(token).getSubject();
    }
}
