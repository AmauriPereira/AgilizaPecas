
package br.com.minaspecas.irmaospereira.agilizapecas.apresentacao;

import br.com.minaspecas.irmaospereira.agilizapecas.apresentacao.utilitarios.CriptografiaUtil;
import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Usuario;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoDeletarElemento;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoLogin;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoUsuarioCadastrado;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excessaoUsuarioNaoEncontrado;
import br.com.minaspecas.irmaospereira.agilizapecas.negocios.UsuarioBO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static Integer acaoBotao;
    
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
        
        this.jButtonExcluir.setEnabled(false);
        this.jButtonAlterar.setEnabled(false);
        this.jButtonIncluir.setEnabled(true); 
        this.jButtonPesquisar.setEnabled(true); 
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
    public void persisteUsuario() throws excecaoDeletarElemento, excecaoLogin, excessaoUsuarioNaoEncontrado {  
        
        Usuario usuarioAux = new Usuario();
        
        //cria duas variaveis para receber os dados da tela de  "login do usuario"
        String idUsuario = jTextFieldCodigo.getText();
        String usuario = jTextFieldUsuario.getText().toUpperCase();
        String senha = jPasswordField.getText();
        String confirmaSenha = jPasswordConfirmacao.getText();
        String gerente =  (jCheckBoxGerente.isSelected())? "S" : "N";  
        String ativo = (jCheckBoxAtivo.isSelected()) ? "S" : "N";
                        
        switch (acaoBotao) {
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
                
                int returnInput = JOptionPane.showConfirmDialog(null, 
                "Deseja excluir o registro?", "Selecione a opção...",JOptionPane.YES_NO_OPTION);
                
                if (returnInput == 0){
                    usuarioAux.setIdUsuario(Integer.parseInt(idUsuario));
                    excluirUsuario(usuarioAux);
                    usuarioAux = null;
                    
                }                 
                break;
            case 4:
                       
               
                pesquisarUsuario(usuario,idUsuario);
                this.jButtonExcluir.setEnabled(true);
                this.jButtonAlterar.setEnabled(true);
                this.jButtonIncluir.setEnabled(false);
                
                break;
            default:
        }
        
        
    }
    
    private void pesquisarUsuario(String usuario, String codigo) throws excessaoUsuarioNaoEncontrado, excecaoLogin {        
        
        //cria duas variaveis para receber os dados da tela de  "login do usuario"
        Usuario user;
        UsuarioBO usuarioBO = new UsuarioBO();
        
        try {
            user = usuarioBO.pesquisarUsuario(usuario, codigo);
            
            jTextFieldUsuario.setText(user.getUsuario());  
            jTextFieldCodigo.setText(Integer.toString(user.getIdUsuario())); 
            if ("S".equals(user.getAtivo())) jCheckBoxAtivo.setSelected(true);
            if ("S".equals(user.getGerente())) jCheckBoxGerente.setSelected(true);
                
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel buscar o registro"+ex,
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (excessaoUsuarioNaoEncontrado ex) {
            JOptionPane.showMessageDialog(null, "Usuario não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void incluirUsuario(Usuario usuario) throws excecaoLogin, excessaoUsuarioNaoEncontrado {        
        
        UsuarioBO usuarioBO = new UsuarioBO();
        
        try {
            usuarioBO.incluirUsuario(usuario);
            
            JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!",
                                    "Cadastro de Usuário", JOptionPane.INFORMATION_MESSAGE);
            
            restauraEstadoPadrao();
                
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel buscar o registro"+ex,
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (excecaoUsuarioCadastrado ex) {
            JOptionPane.showMessageDialog(null, "Usuário ja está cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
        }  catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar!"+ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     private void excluirUsuario(Usuario usuario) throws excecaoDeletarElemento {        
        
        UsuarioBO usuarioBO = new UsuarioBO();
        
        try {
            usuarioBO.excluirUsuario(usuario);
            
            JOptionPane.showMessageDialog(null, "Usuario excluído com sucesso!",
                                    "Exclusão de Usuário", JOptionPane.INFORMATION_MESSAGE);
            
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

        jPanelDados = new javax.swing.JPanel();
        jLabelCodigo = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jCheckBoxGerente = new javax.swing.JCheckBox();
        jCheckBoxAtivo = new javax.swing.JCheckBox();
        jLabelUsuario = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabelPassword = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jLabelConfirmacaoPassword = new javax.swing.JLabel();
        jPasswordConfirmacao = new javax.swing.JPasswordField();
        jPanelBotoes = new javax.swing.JPanel();
        jButtonIncluir = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de Usuário");

        jPanelDados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelCodigo.setText("Código");

        jCheckBoxGerente.setText("Gerente");

        jCheckBoxAtivo.setText("Ativo");

        jLabelUsuario.setText("Usuário");

        jLabelPassword.setText("Senha");

        jLabelConfirmacaoPassword.setText("Confirme a Senha");

        javax.swing.GroupLayout jPanelDadosLayout = new javax.swing.GroupLayout(jPanelDados);
        jPanelDados.setLayout(jPanelDadosLayout);
        jPanelDadosLayout.setHorizontalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldUsuario)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBoxGerente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxAtivo))
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDadosLayout.createSequentialGroup()
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDadosLayout.createSequentialGroup()
                                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelUsuario)
                                    .addComponent(jLabelPassword))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelDadosLayout.createSequentialGroup()
                                .addComponent(jLabelConfirmacaoPassword)
                                .addGap(89, 89, 89))
                            .addComponent(jPasswordConfirmacao))))
                .addContainerGap())
        );
        jPanelDadosLayout.setVerticalGroup(
            jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigo)
                    .addComponent(jCheckBoxAtivo)
                    .addComponent(jCheckBoxGerente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosLayout.createSequentialGroup()
                        .addComponent(jLabelConfirmacaoPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelPassword))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelBotoes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/minaspecas/irmaospereira/agilizapecas/icones/incluir.png"))); // NOI18N
        jButtonIncluir.setToolTipText("Incluir");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/minaspecas/irmaospereira/agilizapecas/icones/alterar.png"))); // NOI18N
        jButtonAlterar.setToolTipText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/minaspecas/irmaospereira/agilizapecas/icones/excluir.png"))); // NOI18N
        jButtonExcluir.setToolTipText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/minaspecas/irmaospereira/agilizapecas/icones/pesquisar.png"))); // NOI18N
        jButtonPesquisar.setToolTipText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanelBotoesLayout = new javax.swing.GroupLayout(jPanelBotoes);
        jPanelBotoes.setLayout(jPanelBotoesLayout);
        jPanelBotoesLayout.setHorizontalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jButtonLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBotoesLayout.setVerticalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jButtonIncluir)
                    .addGroup(jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                    .addComponent(jPanelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        try {
            persisteUsuario();
        } catch (excecaoDeletarElemento ex) {
            Logger.getLogger(JInternalFrameCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (excecaoLogin ex) {
            Logger.getLogger(JInternalFrameCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (excessaoUsuarioNaoEncontrado ex) {
            Logger.getLogger(JInternalFrameCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        acaoBotao = 1;
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        acaoBotao = 3;
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
        
        acaoBotao = 2;
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        this.jTextFieldCodigo.setEnabled(true); 
        this.jTextFieldUsuario.setEnabled(true); 
        this.jButtonExcluir.setEnabled(false);
        this.jButtonAlterar.setEnabled(false);
        this.jButtonIncluir.setEnabled(false);
        acaoBotao = 4;
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
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelConfirmacaoPassword;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelDados;
    private javax.swing.JPasswordField jPasswordConfirmacao;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
