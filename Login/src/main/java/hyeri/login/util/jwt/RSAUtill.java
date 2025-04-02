package hyeri.login.util.jwt;

import hyeri.login.util.jwt.dto.RSAkeys;

import java.security.*;
import java.util.Base64;

public class RSAUtill {

    /**
     * RSA 키쌍 (private key, public key) 생성
     */
    public static RSAkeys generateRsaKeyPair() throws NoSuchAlgorithmException {

        // RSA 키쌍을 생성
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();


        // publicKey 획득
        PublicKey publicKey = pair.getPublic();
        System.out.printf("-----BEGIN PUBLIC KEY-----\n%s\n-----END PUBLIC KEY-----\n",
                Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        /*// publicKey 바이너리 파일로 저장
        try (FileOutputStream fos = new FileOutputStream("public.key")) {
            fos.write(publicKey.getEncoded());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        // privateKey 획득
        PrivateKey privateKey = pair.getPrivate();
        System.out.printf("-----BEGIN PRIVATE KEY-----\n%s\n-----END PRIVATE KEY-----\n",
                Base64.getEncoder().encodeToString(privateKey.getEncoded()));

/*        // privateKey 바이너리 파일로 저장
        try (FileOutputStream fos = new FileOutputStream("private.key")) {
            fos.write(privateKey.getEncoded());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return RSAkeys.create(
                Base64.getEncoder().encodeToString(publicKey.getEncoded()),
                Base64.getEncoder().encodeToString(privateKey.getEncoded())
        );


    }
}