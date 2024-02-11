
package br.com.minaspecas.irmaospereira.agilizapecas.apresentacao;

import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Marca;
import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Usuario;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoDeletarElemento;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoLogin;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excessaoUsuarioNaoEncontrado;
import br.com.minaspecas.irmaospereira.agilizapecas.negocios.MarcaBO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AMAURI PEREIRA
 */
public class JInternalFrameCadastroMarca extends javax.swing.JInternalFrame {
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
    public JInternalFrameCadastroMarca() {
        initComponents();
        
        this.jTextFieldCodigo.enable(false);
        this.jTextFieldMarca.enable(false);
        
        this.jButtonExcluir.setEnabled(false);
        this.jButtonAlterar.setEnabled(false);
        this.jButtonIncluir.setEnabled(true); 
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
    public void persisteMarca() throws excecaoDeletarElemento, excecaoLogin, excessaoUsuarioNaoEncontrado {  
        
        Usuario usuarioAux = new Usuario();
        
        //cria duas variaveis para receber os dados da tela de  "login do marca"
        String idMarca = jTextFieldCodigo.getText();
        String marca = jTextFieldMarca.getText().toUpperCase();
                        
        switch (acaoBotao) {
            case 1: 
                

                break;
            case 2:
                
        
                break;
            case 3:
                                 
                break;
            case 4:
                                
                break;
            default:
        }
        
        
    }
    
    private void pesquisarMarca(String marca) {      
        //cria duas variaveis para receber os dados da tela de  "login do usuario"
        Marca marcaAux;
        MarcaBO marcaBO = new MarcaBO();
        
        try {
            marcaAux = marcaBO.pesquisarMarca(marca);
            
            jTextFieldMarca.setText(marcaAux.getDescricao());  
            jTextFieldCodigo.setText(Integer.toString(marcaAux.getIdMarca()));
                
                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro não foi possivel buscar o registro"+ex,
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }       
       
    }
    
    private void incluirUsuario(){        
        
        
    }
    
    private void excluirUsuario(){        
        
        
    }
    
    
    private void alterarUsuario() {     
               
    }
    
    private void restauraEstadoPadrao(){
        this.jTextFieldCodigo.setText("");
        this.jTextFieldMarca.setText("");
        
        this.jTextFieldMarca.setEnabled(false);
        
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

        jPanelDadosMarca = new javax.swing.JPanel();
        jLabelCodigo = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jLabelMarca = new javax.swing.JLabel();
        jTextFieldMarca = new javax.swing.JTextField();
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
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Marca");
        setToolTipText("");

        jPanelDadosMarca.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelCodigo.setText("Código");

        jLabelMarca.setText("Descrição");

        javax.swing.GroupLayout jPanelDadosMarcaLayout = new javax.swing.GroupLayout(jPanelDadosMarca);
        jPanelDadosMarca.setLayout(jPanelDadosMarcaLayout);
        jPanelDadosMarcaLayout.setHorizontalGroup(
            jPanelDadosMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosMarcaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDadosMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMarca)
                    .addGroup(jPanelDadosMarcaLayout.createSequentialGroup()
                        .addGroup(jPanelDadosMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelMarca)
                            .addComponent(jLabelCodigo)
                            .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelDadosMarcaLayout.setVerticalGroup(
            jPanelDadosMarcaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosMarcaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jPanelDadosMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelDadosMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        restauraEstadoPadrao();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        this.jTextFieldCodigo.setText("");
        this.jTextFieldMarca.setText("");
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        this.jButtonExcluir.setEnabled(false);
        this.jButtonAlterar.setEnabled(false);
        this.jButtonPesquisar.setEnabled(false);
        this.jButtonIncluir.setEnabled(true);
        
        this.jTextFieldMarca.setEnabled(true);
        
        acaoBotao = 1;
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        acaoBotao = 3;
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        this.jTextFieldMarca.setEnabled(true);
        
        this.jButtonExcluir.setEnabled(false);
        this.jButtonAlterar.setEnabled(true);
        this.jButtonIncluir.setEnabled(false);
        
        acaoBotao = 2;
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        this.jTextFieldMarca.setEnabled(true);        
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
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelMarca;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelDadosMarca;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldMarca;
    // End of variables declaration//GEN-END:variables
}
