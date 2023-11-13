package rs.raf.demo.filters;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.raf.demo.services.UserService;
import rs.raf.demo.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Ovo je konkretna implementacija Filtera ili ti interseptora
 * Koji dekoduje token, proverava njegovu validnost i ukoliko je token validan vraca nazad.
 * U sustini JWTFilter je odgovoran za proveru i obradu JWT tokena usvakom zahtevu, pruzajuci na taj nacin
 * autentifikaciju korisnika u sistemu.
 */

@CrossOrigin(origins = "http://localhost:4200")
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public JwtFilter(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


    /**
     * Ja ovde u filteru moram da izvucem te njegove role iz baze i da proverim da li je
     * useru dozvoljeno da pristupi toj nekoj ruti kojoj on zeli da dodje
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = httpServletRequest.getHeader("Authorization");
        String jwt = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            if(!authHeader.equals("Bearer null")){
                jwt = authHeader.substring(7);
                username = jwtUtil.extractUsername(jwt);
            }
        }
        System.out.println("Username je: " + username);

        if (username != null) {

            UserDetails userDetails = this.userService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                // on ovde cupa te role i dodaje ih u UsernamePasswordauthenticationToken
                // Koji ce da ih setuje na context holder
                // tako da iz contextHoldera mogu da iscupam te role posle u bilo kom trenutku
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
