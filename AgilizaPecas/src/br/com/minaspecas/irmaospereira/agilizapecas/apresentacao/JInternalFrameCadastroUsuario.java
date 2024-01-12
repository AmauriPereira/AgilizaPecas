
package br.com.minaspecas.irmaospereira.agilizapecas.apresentacao;


import br.com.minaspecas.irmaospereira.agilizapecas.apresentacao.utilitarios.CriptografiaUtil;
import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Usuario;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoLogin;
import br.com.minaspecas.irmaospereira.agilizapecas.negocios.UsuarioBO;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AMAURI PEREIRA
 */
public class JInternalFrameCadastroUsuario extends javax.swing.JInternalFrame {
    /**
     * Variavel para contrar se irar excluir, alterar, incluir ou procurar um novo registro.
     * 1 - incluir
     * 2 - alterar
     * 3 - excluir
     * 4 - pesquisar
     * 
     */
    private static Integer acao;
    
    /**
     * Creates new form JInternalFrameCadastroUsuario
     */
    public JInternalFrameCadastroUsuario() {
        initComponents();
        
        this.jTextFieldCodigo.enable(false);
        this.jTextFieldUsuario.enable(false);
        this.jPasswordField.enable(false);
        this.jPasswordConfirmacao.enable(false);
        this.jCheckBoxAtivo.enable(false);
        this.jCheckBoxGerente.enable(false);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JInternalFrameCadastroUsuario().setVisible(true);
            }
        });
    }
    public void persisteUsuario() {  
        
        Usuario usuarioAux = new Usuario();
        
        //cria duas variaveis para receber os dados da tela de  "login do usuario"
        String idUsuario = jTextFieldCodigo.getText();
        String usuario = jTextFieldUsuario.getText().toUpperCase();
        String senha = jPasswordField.getText();
        String confirmaSenha = jPasswordConfirmacao.getText();
        String gerente =  (jCheckBoxGerente.isSelected())? "S" : "N";  
        String ativo = (jCheckBoxAtivo.isSelected()) ? "S" : "N";
                        
        switch (acao) {
            case 1: 
                
                if (senha.equals(confirmaSenha)){  
                    CriptografiaUtil criptografiaUtil = new CriptografiaUtil();
                    usuarioAux.setSenha(criptografiaUtil.criptografiaSenha(senha));
                    usuarioAux.setUsuario(usuario);
                    usuarioAux.setAtivo(ativo);
                    usuarioAux.setGerente(gerente);
                    
                    incluirUsuario(usuarioAux);
                    usuarioAux = null;

                }else{
                    JOptionPane.showMessageDialog(null, "Senha e confirmação não coincidem. Por favor, verifique.",
                            "Verificação de Senha", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
            case 2:
                
                if (senha.equals(confirmaSenha)){  

                    CriptografiaUtil criptografiaUtil = new CriptografiaUtil();
                    
                    if (!"".equals(jPasswordField.getText())||!"".equals(jPasswordConfirmacao.getText())) {
                         usuarioAux.setSenha(criptografiaUtil.criptografiaSenha(senha));
                    }
               
                    usuarioAux.setIdUsuario(Integer.parseInt(idUsuario));
                    usuarioAux.setUsuario(usuario);
                    usuarioAux.setAtivo(ativo);
                    usuarioAux.setGerente(gerente);
                    
                    alterarUsuario(usuarioAux);
                    usuarioAux = null;

                }else{
                    JOptionPane.showMessageDialog(null, "Senha e confirmação não coincidem. Por favor, verifique.",
                            "Verificação de Senha", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
            case 3:
                break;

            case 4:
                pesquisarUsuario(usuario);
                break;
            default:
        }
        
        
    }
    
    private void pesquisarUsuario(String usuario) {        
        
        //cria duas variaveis para receber os dados da tela de  "login do usuario"
        Usuario user;
        UsuarioBO usuarioBO = new UsuarioBO();
        
        try {
            user = usuarioBO.pesquisarUsuario(usuario);
            
            jTextFieldUsuario.setText(user.getUsuario());  
            jTextFieldCodigo.setText(Integer.toString(user.getIdUsuario())); 
            if ("S".equals(user.getAtivo())) jCheckBoxAtivo.setSelected(true);
            if ("S".equals(user.getGerente())) jCheckBoxGerente.setSelected(true);
                
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel buscar o registro"+ex,
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (excecaoLogin ex) {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void incluirUsuario(Usuario usuario) {        
        
        UsuarioBO usuarioBO = new UsuarioBO();
        
        try {
            usuarioBO.incluirUsuario(usuario);
            
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!",
                                    "Cadastro de Usuário", JOptionPane.INFORMATION_MESSAGE);
            
            restauraEstadoPadrao();
                
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel buscar o registro"+ex,
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (excecaoLogin ex) {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void alterarUsuario(Usuario usuario) {        
        
        UsuarioBO usuarioBO = new UsuarioBO();
        
        try {
            usuarioBO.alterarUsuario(usuario);
            
            JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!",
                                    "Alteração de Usuário", JOptionPane.INFORMATION_MESSAGE);
            
            restauraEstadoPadrao();
                
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel alterar o registro"+ex,
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (excecaoLogin ex) {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void restauraEstadoPadrao(){
        this.jTextFieldCodigo.setText("");
        this.jTextFieldUsuario.setText("");
        this.jPasswordField.setText("");
        this.jPasswordConfirmacao.setText("");
        this.jCheckBoxAtivo.setSelected(false);
        this.jCheckBoxGerente.setSelected(false);
        
        this.jTextFieldUsuario.setEnabled(false);
        this.jPasswordField.setEnabled(false);
        this.jPasswordConfirmacao.setEnabled(false);
        this.jCheckBoxAtivo.setEnabled(false);
        this.jCheckBoxGerente.setEnabled(false);
        
        this.jButtonExcluir.setEnabled(true);
        this.jButtonAlterar.setEnabled(true);
        this.jButtonIncluir.setEnabled(true); 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBoxAtivo = new javax.swing.JCheckBox();
        jCheckBoxGerente = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jPasswordConfirmacao = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jButtonLimpar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();
        jButtonIncluir = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Usuário");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Usuário");

        jLabel2.setText("Senha");

        jLabel3.setText("Confirme a Senha");

        jCheckBoxAtivo.setText("Ativo");

        jCheckBoxGerente.setText("Gerente");

        jLabel4.setText("Código");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldUsuario)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxGerente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxAtivo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(89, 89, 89))
                            .addComponent(jPasswordConfirmacao))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jCheckBoxAtivo)
                    .addComponent(jCheckBoxGerente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonOk.setText("OK");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButtonIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/minaspecas/irmaospereira/agilizapecas/icones/incluir.png"))); // NOI18N
        jButtonIncluir.setToolTipText("Incluir");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/minaspecas/irmaospereira/agilizapecas/icones/pesquisar.png"))); // NOI18N
        jButtonPesquisar.setToolTipText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/minaspecas/irmaospereira/agilizapecas/icones/excluir.png"))); // NOI18N
        jButtonExcluir.setToolTipText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/minaspecas/irmaospereira/agilizapecas/icones/alterar.png"))); // NOI18N
        jButtonAlterar.setToolTipText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonPesquisar)
                .addGap(30, 30, 30)
                .addComponent(jButtonLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jButtonIncluir)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonOk)
                        .addComponent(jButtonLimpar)
                        .addComponent(jButtonCancelar)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        persisteUsuario();
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        restauraEstadoPadrao();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        this.jTextFieldCodigo.setText("");
        this.jTextFieldUsuario.setText("");
        this.jPasswordField.setText("");
        this.jPasswordConfirmacao.setText("");
        this.jCheckBoxAtivo.setSelected(false);
        this.jCheckBoxGerente.setSelected(false);
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        this.jButtonExcluir.setEnabled(false);
        this.jButtonAlterar.setEnabled(false);
        this.jButtonPesquisar.setEnabled(false);
        this.jButtonIncluir.setEnabled(true);
        
        this.jTextFieldUsuario.setEnabled(true);
        this.jPasswordField.setEnabled(true);
        this.jPasswordConfirmacao.setEnabled(true);
        this.jCheckBoxAtivo.setEnabled(true);
        this.jCheckBoxGerente.setEnabled(true);
        
        acao = 1;
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        this.jTextFieldUsuario.setEnabled(true);     
        this.jPasswordField.setEnabled(true);
        this.jPasswordConfirmacao.setEnabled(true);
        this.jCheckBoxAtivo.setEnabled(true);
        this.jCheckBoxGerente.setEnabled(true);
        
        this.jButtonExcluir.setEnabled(false);
        this.jButtonAlterar.setEnabled(true);
        this.jButtonIncluir.setEnabled(false);
        
        acao = 2;
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        this.jTextFieldUsuario.setEnabled(true);        
        this.jButtonExcluir.setEnabled(false);
        this.jButtonAlterar.setEnabled(true);
        this.jButtonIncluir.setEnabled(false);
        acao = 4;
    }//GEN-LAST:event_jButtonPesquisarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JCheckBox jCheckBoxAtivo;
    private javax.swing.JCheckBox jCheckBoxGerente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordConfirmacao;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
