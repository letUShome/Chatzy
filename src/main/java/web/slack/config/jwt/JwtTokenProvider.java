package web.slack.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * 토큰 생성, 검증
 * 필터 클래스에서 사전 검증을 거친다* *
 */
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final MemberRepository memberRepository;

    @Value("${spring.jwt.secret-key}")
    private String SECRET_KEY;

    // 토큰 유효시간 30분
    private Long tokenValidTime = 30 * 60 * 1000L;

    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }


    public String createToken(String memberId){
        // JWT payload에 저장되는 정보단위. user 식별값
        Claims claims = Jwts.claims().setSubject(memberId);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public Authentication getAuthentication(String token){
        Member member = memberRepository.findById(getMemberId(token)).get();
        return new UsernamePasswordAuthenticationToken(member, "");
    }

    public String getMemberId(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        }
        catch (Exception e){
            return false;
        }

    }
}
