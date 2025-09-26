package vn.BAITAP.TestAPI.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class GenerateToken {

    public static final MacAlgorithm mac = MacAlgorithm.HS256;

    @Value("${qlinh.jwt.base64-secret}")
    private String accessToken;

    @Value("${qlinh.jwt.access-token-validity-in-seconds}")
    private long expiredAccessToken;

    @Value("${qlinh.jwt.access-tokenRefresh-validity-in-seconds}")
    private long expiredAccessRefresh;

    private JwtEncoder jwtEncoder;

    public GenerateToken(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String createAccessToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant expiredToken = now.plus(expiredAccessToken, ChronoUnit.SECONDS);

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(expiredToken)
                .subject(authentication.getName())
                .claim("user", authentication)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(mac).build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }

    public String createRefreshToken(String email) {
        Instant now = Instant.now();
        Instant expiredToken = now.plus(expiredAccessRefresh, ChronoUnit.SECONDS);

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(expiredToken)
                .subject(email)
                .claim("user", email)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(mac).build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }
}
