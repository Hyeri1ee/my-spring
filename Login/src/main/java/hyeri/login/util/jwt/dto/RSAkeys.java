package hyeri.login.util.jwt.dto;

public record RSAkeys(
        String publickey,
        String privatekey
) {
    public static RSAkeys create(String publickey, String privatekey) {
        return new RSAkeys(publickey, privatekey);
    }
}
