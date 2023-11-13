package rs.raf.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
// Ova anotacija oznacava da je `JwtUtils` Spring komponenta, sto znaci da ce biti upravljana od strane Spring kontejnera
// i moze se automatksi ubaciti gde god je potrebno
@Component
public class JwtUtil {
    // Kada server generise JWT token,on ukljucuje potpis koji se kreira kriptografski sa tajnim kljucem.
    // Ovaj potpis se dodaje u zaglavlje tokena. Potpis se koristi za verifikaciju da je token stvarno generisao server
    // i da nije bio promenjen od strane neautarizovanih strana.
    private final String SECRET_KEY = "MY JWT SECRET";

    // Ova metoda izvlaci sve informacije iz JWT tokena. Dekodira token, proverava njegov potpis pomocu tajnog kljuca, 
    // i vraca sve claims sadrzane u tokenu.
    public Claims extractAllClaims(String token) {
        System.out.println("Token:" + token);
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Ova meotda generise novi JWT token za dato korisnicko ime. Postavlja claims, korisnicko ime kao podatke o predmetu (subject),
    // vreme izdavanja i vreme isteka tokena, potpisuje ih tajnim kljucem i kompaktira token.
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    // Ova metoda validira JWT token. Proverava da li korisnicko ime u tokenu odgovara korisnickom imenu koje se nalazi u UserDetails objektu i dal je token istekao.
    public boolean validateToken(String token, UserDetails user) {
        return (user.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
