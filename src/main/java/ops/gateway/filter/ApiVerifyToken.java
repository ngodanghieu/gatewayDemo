package ops.gateway.filter;

import lombok.SneakyThrows;
import ops.gateway.util.Constant;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.beans.factory.annotation.Value;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;


public class ApiVerifyToken {

    private String public_key;
    private String token;

    public ApiVerifyToken(String token, String public_key) {
        this.token = token;
        this.public_key = public_key;
    }

    @SneakyThrows
    public Map<String, Object> verifyToken() {
        if (token == null || !token.startsWith(Constant.FilterConstant.PREFIX)) return null;


        public_key = public_key.replace("-----BEGIN PUBLIC KEY-----\n", "");
        public_key = public_key.replace("-----END PUBLIC KEY-----", "");
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] publicKeyBytes = base64Decoder.decodeBuffer(public_key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(Constant.FilterConstant.KEY_FACTORY);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setVerificationKey(publicKey)
                .build();

        try {
            JwtClaims jwtDecoded = jwtConsumer.processToClaims(token.replace(Constant.FilterConstant.PREFIX, ""));
            Map<String, Object> jwtClaims = jwtDecoded.getClaimsMap();
            Map<String, Object> object_permissions = (Map<String, Object>) jwtClaims.get(Constant.FilterConstant.ROLE);
            if (checkVinPayCashbackManagement(object_permissions))
                return jwtClaims;
            else
                return null;
        } catch (Exception e) {

            return null;
        }

    }

    private Boolean checkVinPayCashbackManagement(Map<String, Object> object_permissions) {
        return object_permissions.get(Constant.FilterConstant.VIN_PAY_CASH_BACK_MANAGEMENT) != null;
    }
}
