
package br.com.minaspecas.irmaospereira.agilizapecas.apresentacao.utilitarios;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author AMAURI PEREIRA
 */
public class CriptografiaUtil {
    
    public String criptografiaSenha(String senha) {
        
        
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            senha = hexString.toString();

        } catch (NoSuchAlgorithmException ex) {
            //Logger.getLogger(CadastroDiretorForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
           // Logger.getLogger(CadastroDiretorForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senha;
    }
    
}
