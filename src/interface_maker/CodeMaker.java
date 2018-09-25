package interface_maker;

/*
Cria a interface grafica
*/

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import parser_maker.SqlParser;
import parser_maker.Writer;
import util_maker.Constantes;

public class CodeMaker extends javax.swing.JFrame {

    List<SqlParser> sqlList = new ArrayList<SqlParser>();
    String templateNome="";
    String tipoCodigo; 
    File[] templates;
    
    /* Contrutor
    torna visivel o painel da interface */
    public CodeMaker() {
        initComponents();
        setVisible(true);
        tipoCodigo = ddlTipoCodigo.getSelectedItem().toString();
        //testes
        txtPathArqSql.setText("C:\\Users\\Livia Ciabati\\Dropbox\\codemaker templates\\mBOLD_v2_1 (4).sql"); 
        txtPathSalvarArq.setText("C:\\Users\\Livia Ciabati\\Documents\\");
        Constantes.Logs += "Lendo arquivo...\n\n";
        try {
        	sqlList = SqlParser.ParserMySql(new File(txtPathArqSql.getText()));     
        }
        catch (Exception ex) {
            txtLogs.setText(txtLogs.getText() + "\n\n" + ex.getMessage());
            Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {        	
	        if(sqlList == null || sqlList.size() == 0) {
	        	sqlList = SqlParser.ParserMS(new File(txtPathArqSql.getText()));
	        }
        }
        catch (Exception ex) {
            txtLogs.setText(txtLogs.getText() + "\n\n" + ex.getMessage());
            Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SqlParser.ordenaLista(sqlList);
        String [] arrTabelas = new String[sqlList.size()];
        
        for (int i = 0; i < sqlList.size(); i++)
        {
            arrTabelas[i] = sqlList.get(i).nomeTabela;
        }

        lstNomeTabelas.setListData(arrTabelas);
        lstNomeTabelas.setSelectedIndex(0);
        txtLogs.setText(Constantes.Logs + "Done!" );
//        File f = new File("/Users/liviaciabati/Desktop/templates.rar Folder/template_Dto.cs");
//            
//            String linha = "", texto = "";
//
//            BufferedReader br;
//            try {
//                br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(f))));
//                //BufferedReader br = new BufferedReader(new FileReader(sqlFile));
//                while ((linha = br.readLine()) != null) {
//                    texto += linha+"\n";
//                } 
//                txtTemplate.setText(texto);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
//            }     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPathSalvarArq = new javax.swing.JTextField();
        btnGetArqSql = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPathArqSql = new javax.swing.JTextField();
        btnCriarArq = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnPathSalvarArq = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLogs = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTemplate = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstNomeTabelas = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        btnListarTabelas = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtVisualRapida = new javax.swing.JTextPane();
        btnVisualRapida = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ddlTipoCodigo = new javax.swing.JComboBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstTemplates = new javax.swing.JList();
        btnAddTemplates = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Code Maker", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        jLabel1.setText("Salvar arquivos em:");

        txtPathSalvarArq.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtPathSalvarArqActionPerformed(evt);
            }
        });

        btnGetArqSql.setText("...");
        btnGetArqSql.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGetArqSqlActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        jLabel2.setText("Template:");

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel3.setText("CodeMaker");

        txtPathArqSql.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtPathArqSqlActionPerformed(evt);
            }
        });

        btnCriarArq.setText("Criar Arquivos");
        btnCriarArq.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCriarArqActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSairActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLimparActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        jLabel4.setText("Logs");

        btnPathSalvarArq.setText("...");
        btnPathSalvarArq.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnPathSalvarArqActionPerformed(evt);
            }
        });

        txtLogs.setEditable(false);
        jScrollPane1.setViewportView(txtLogs);

        txtTemplate.setColumns(20);
        txtTemplate.setRows(5);
        txtTemplate.setToolTipText("");
        jScrollPane2.setViewportView(txtTemplate);

        jLabel5.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        jLabel5.setText("Arquivo SQL:");

        jScrollPane3.setViewportView(lstNomeTabelas);

        jLabel6.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setAutoscrolls(true);

        btnListarTabelas.setText("Listar Tabelas");
        btnListarTabelas.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnListarTabelasActionPerformed(evt);
            }
        });

        txtVisualRapida.setEditable(false);
        jScrollPane4.setViewportView(txtVisualRapida);

        btnVisualRapida.setLabel("Gerar Visualização Rápida");
        btnVisualRapida.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnVisualRapidaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Sans", 0, 11)); // NOI18N
        jLabel7.setText("Visualizar:");

        ddlTipoCodigo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Java", "C#", "Django" }));
        ddlTipoCodigo.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ddlTipoCodigoActionPerformed(evt);
            }
        });

        lstTemplates.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lstTemplatesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(lstTemplates);

        btnAddTemplates.setText("Adicionar Templates");
        btnAddTemplates.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAddTemplatesActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel8.setText("Lista de Templates");
        jLabel8.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPathArqSql, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGetArqSql))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(txtPathSalvarArq, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPathSalvarArq))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnLimpar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnListarTabelas))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(btnSair))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAddTemplates)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(ddlTipoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVisualRapida))
                            .addComponent(btnCriarArq, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel8))
                            .addComponent(btnAddTemplates, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnVisualRapida)
                                .addComponent(ddlTipoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPathArqSql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGetArqSql))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPathSalvarArq, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPathSalvarArq))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimpar)
                            .addComponent(btnListarTabelas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair)
                    .addComponent(btnCriarArq)))
        );

        jLabel1.getAccessibleContext().setAccessibleName("Salvar os arquivos em:");
        btnCriarArq.getAccessibleContext().setAccessibleName("Criar");
        jLabel6.getAccessibleContext().setAccessibleName("Lista de tabelas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("CodeMaker");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVisualRapidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualRapidaActionPerformed
        int[] tabelasSelecionadas = lstNomeTabelas.getSelectedIndices();

        for (int i : tabelasSelecionadas)
        {
            SqlParser tabela = sqlList.get(i);
            Constantes.Logs += tabela.nomeTabela+"\n";
            txtVisualRapida.setText(Writer.ToView(tabela, txtTemplate.getText(), tipoCodigo));
        }
    }//GEN-LAST:event_btnVisualRapidaActionPerformed

    private void btnListarTabelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTabelasActionPerformed

        Constantes.Logs += "Lendo arquivo...\n\n";
        try {
            sqlList = SqlParser.ParserMySql(new File(txtPathArqSql.getText()));
        }
        catch (Exception ex) {
            txtLogs.setText(txtLogs.getText() + "\n\n" + ex.getMessage());
            Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if(sqlList == null || sqlList.size() == 0) {
            	sqlList = SqlParser.ParserMS(new File(txtPathArqSql.getText()));
            }
        }
        catch (Exception ex) {
            txtLogs.setText(txtLogs.getText() + "\n\n" + ex.getMessage());
            Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String [] arrTabelas = new String[sqlList.size()];
        for (int i = 0; i < sqlList.size(); i++)
        {
            arrTabelas[i] = sqlList.get(i).nomeTabela;
        }

        lstNomeTabelas.setListData(arrTabelas);
    
        txtLogs.setText(Constantes.Logs + "Done!" );
    }//GEN-LAST:event_btnListarTabelasActionPerformed

    private void btnPathSalvarArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPathSalvarArqActionPerformed
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int answer = jf.showOpenDialog(null);

        //verifica se a resposta foi uma resposta de aprovacao
        if (answer == JFileChooser.APPROVE_OPTION) {
            //Armazena o caminho do arquivo selecionada
            File f = new File(jf.getSelectedFile().getAbsolutePath());
            txtPathSalvarArq.setText(jf.getSelectedFile().getAbsolutePath());

        } else {
            //Tratamento para o cancelamento do usuario
            //JOptionPane jo = new JOptionPane();
            JOptionPane.showConfirmDialog(null, "Comando cancelado pelo usuario", "Cancel", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btnPathSalvarArqActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
        txtPathSalvarArq.setText("");
        txtPathArqSql.setText("");
        txtPathArqSql.requestFocus();
        txtLogs.setText("");
        lstNomeTabelas.setListData(new Object[0]);
        lstTemplates.setListData(new Object[0]);
        Constantes.Logs = "";
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        int answer = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnCriarArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarArqActionPerformed
        int[] tabelasSelecionadas = lstNomeTabelas.getSelectedIndices();
        int[] templatesSelecionados = lstTemplates.getSelectedIndices();
        for (int j : templatesSelecionados)
        {                                            
            String linha = "", texto = "";
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(templates[j]))));
                //BufferedReader br = new BufferedReader(new FileReader(sqlFile));
                while ((linha = br.readLine()) != null) {
                    texto += linha+"\n";
                }             
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
            }   
            for (int i : tabelasSelecionadas)
            {
                SqlParser tabela = sqlList.get(i);
                Constantes.Logs += tabela.nomeTabela+"\n";
                Writer.toWriteFile(tabela, txtPathSalvarArq.getText(), texto, 
                        //templates[j].getName().contains("_")  ?  templates[j].getName().split("_")[1] : 
                        templates[j].getName(),
                        tipoCodigo);
            }
        }
        txtLogs.setText(Constantes.Logs);
    }//GEN-LAST:event_btnCriarArqActionPerformed

    private void txtPathArqSqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPathArqSqlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPathArqSqlActionPerformed

	//Abre a imagem selecionada, aplica a transformaÃ§ao e salva a assinatura, sendo esta assinatura a de referencia
    private void btnGetArqSqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetArqSqlActionPerformed
        JFileChooser jf = new JFileChooser();
        int answer = jf.showOpenDialog(null);

        //verifica se a resposta foi uma resposta de aprovacao
        if (answer == JFileChooser.APPROVE_OPTION) {
        	sqlList = null;
            //Armazena o caminho do arquivo selecionada
            File f = new File(jf.getSelectedFile().getAbsolutePath());
            txtPathArqSql.setText(jf.getSelectedFile().getAbsolutePath());

            //Utiliza o caminho para o arquivo selecionado acima como sendo o caminho de arquivos de comparacao
            String caminho = jf.getSelectedFile().getAbsolutePath();
            int sep = caminho.contains("\\") ? caminho.lastIndexOf("\\") : caminho.lastIndexOf("/");
            caminho = caminho.substring(0, sep);

            txtPathSalvarArq.setText(caminho);
            
            Constantes.Logs += "Lendo arquivo...\n\n";
            try {
            	sqlList = SqlParser.ParserMySql(new File(txtPathArqSql.getText()));
            }
            catch (Exception ex) {
                txtLogs.setText(txtLogs.getText() + "\n\n" + ex.getMessage());
                Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if(sqlList == null || sqlList.size() == 0) {
                	sqlList = SqlParser.ParserMS(new File(txtPathArqSql.getText()));
                }
            }
            catch (Exception ex) {
                txtLogs.setText(txtLogs.getText() + "\n\n" + ex.getMessage());
                Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String [] arrTabelas = new String[sqlList.size()];
            for (int i = 0; i < sqlList.size(); i++)
            {
                arrTabelas[i] = sqlList.get(i).nomeTabela;
            }

            lstNomeTabelas.setListData(arrTabelas);
            if(arrTabelas.length> 0)
                lstNomeTabelas.setSelectedIndex(0);
            
            txtLogs.setText(Constantes.Logs + "Done!" );

        } else {
            //Tratamento para o cancelamento do usuario
            JOptionPane.showConfirmDialog(null, "Comando cancelado pelo usuario", "Cancel", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_btnGetArqSqlActionPerformed

    private void txtPathSalvarArqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPathSalvarArqActionPerformed
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int answer = jf.showOpenDialog(null);

        //verifica se a resposta foi uma resposta de aprovacao
        if (answer == JFileChooser.APPROVE_OPTION) {
            //Armazena o caminho do arquivo selecionada
            File f = new File(jf.getSelectedFile().getAbsolutePath());
            txtPathSalvarArq.setText(jf.getSelectedFile().getAbsolutePath());

        } else {
            //Tratamento para o cancelamento do usuario
            JOptionPane.showConfirmDialog(null, "Comando cancelado pelo usuario", "Cancel", JOptionPane.CLOSED_OPTION);
        }
    }//GEN-LAST:event_txtPathSalvarArqActionPerformed

    private void ddlTipoCodigoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ddlTipoCodigoActionPerformed
    {//GEN-HEADEREND:event_ddlTipoCodigoActionPerformed
         tipoCodigo = ddlTipoCodigo.getSelectedItem().toString();
    }//GEN-LAST:event_ddlTipoCodigoActionPerformed

    private void btnAddTemplatesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAddTemplatesActionPerformed
    {//GEN-HEADEREND:event_btnAddTemplatesActionPerformed
        JFileChooser jf = new JFileChooser();
        jf.setMultiSelectionEnabled(true);
        int answer = jf.showOpenDialog(null);

        //verifica se a resposta foi uma resposta de aprovacao
        if (answer == JFileChooser.APPROVE_OPTION) {
            templates = jf.getSelectedFiles();
            String[] nomeTemplate = new String[templates.length];
            
            for (int i = 0; i < templates.length; i++)
            {
                nomeTemplate[i] = templates[i].getName();
            }
            lstTemplates.setListData(nomeTemplate);
            
            if(nomeTemplate.length > 0)
                lstTemplates.setSelectedIndex(0);
        } 
        else {
            //Tratamento para o cancelamento do usuario
            //JOptionPane jo = new JOptionPane();
            JOptionPane.showConfirmDialog(null, "Comando cancelado pelo usuario", "Cancel", JOptionPane.CLOSED_OPTION);
        }   
    }//GEN-LAST:event_btnAddTemplatesActionPerformed

    private void lstTemplatesMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lstTemplatesMouseClicked
    {//GEN-HEADEREND:event_lstTemplatesMouseClicked
            String linha = "", texto = "";
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream( templates[lstTemplates.getSelectedIndex()]))));
                //BufferedReader br = new BufferedReader(new FileReader(sqlFile));
                while ((linha = br.readLine()) != null) {
                    texto += linha+"\n";
                }             
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
            }   
                txtTemplate.setText(texto);
    }//GEN-LAST:event_lstTemplatesMouseClicked
	
    public static void main(String[] Args)
    {
        CodeMaker inter = new CodeMaker();
    }
    
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTemplates;
    private javax.swing.JButton btnCriarArq;
    private javax.swing.JButton btnGetArqSql;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnListarTabelas;
    private javax.swing.JButton btnPathSalvarArq;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnVisualRapida;
    private javax.swing.JComboBox ddlTipoCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList lstNomeTabelas;
    private javax.swing.JList lstTemplates;
    private javax.swing.JTextPane txtLogs;
    private javax.swing.JTextField txtPathArqSql;
    private javax.swing.JTextField txtPathSalvarArq;
    private javax.swing.JTextArea txtTemplate;
    private javax.swing.JTextPane txtVisualRapida;
    // End of variables declaration//GEN-END:variables
    


}
