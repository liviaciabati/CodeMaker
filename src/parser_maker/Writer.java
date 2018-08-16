package parser_maker;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import objetos_maker.Coluna;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import util_maker.Constantes;
import util_maker.Utils;

public class Writer
{
    public static HashMap<String, String> valores;
    public static SqlParser tabela;
    public static String codigo;

    public static String Replace(String texto)
    {
        for (String key : valores.keySet())
        {
            texto = texto.replace(key, valores.get(key));
        }
        return texto;
    }

    private static void printNode(Node aNode, String tab)
    {
        //System.out.println(tab +aNode.getNodeType()+", " +aNode.getNodeName() + "," + aNode.getNodeValue());
    }
    
  private static String walkNode(Node theNode, String tab) {
    NodeList children = theNode.getChildNodes();
    //printNode(theNode, tab);
    String result ="";
    for (int i = 0; i < children.getLength(); i++) {
      Node aNode = children.item(i);
      if (aNode.hasChildNodes())
      {
            if (aNode.getNodeType() == Node.ELEMENT_NODE && aNode.getNodeName().equals("for"))
            {
                printNode(aNode, tab);
                TagFor(aNode);
            }
            walkNode(aNode, tab +"    ");
        }
      else{
            printNode(aNode, tab);
        }
      }
    return result;
  }


    public static void setHashMap(SqlParser tabela, Coluna col)
    {
        valores = new HashMap<>();
        String bancoNome = tabela.nomeBanco;
        String tabelaNome = Utils.SplitNomeTabela(tabela.nomeTabela);
        String tabelaSufixo = Utils.SplitNomeSufixo(tabela.nomeTabela);
        valores.put(Constantes.BANCO_NOME, bancoNome);
        valores.put(Constantes.TABELA_NOME, tabelaNome);
        valores.put(Constantes.TABELA_SUFIXO, tabelaSufixo);
        String colunaNome = Utils.SplitNomeColuna(col.Nome);
        String colunaSufixo = Utils.SplitNomeSufixo(col.Nome);
        String colunaLabel = Utils.SplitLabelColuna(col.Nome);
        valores.put(Constantes.COLUNA_SUFIXO, colunaSufixo);
        valores.put(Constantes.COLUNA_NOME, colunaNome);
        valores.put(Constantes.COLUNA_LABEL, colunaLabel);
        valores.put(Constantes.COLUNA_TIPO, String.valueOf(col.Tipo));
        valores.put(Constantes.COLUNA_PK, String.valueOf(col.PK));
        valores.put(Constantes.COLUNA_FK, String.valueOf(col.FK));
        valores.put(Constantes.COLUNA_TAMANHO, col.Size == null ? "" : "(max_length=" + col.Size + ")");
        valores.put(Constantes.COLUNA_REFERENCIA, String.valueOf(col.Referencia));
        valores.put(Constantes.COLUNA_NOT_NULL, String.valueOf(col.NotNull));
        valores.put(Constantes._INT, Constantes.TIPO_INT);
        valores.put(Constantes._VARCHAR, Constantes.TIPO_VARCHAR);
        valores.put(Constantes._TEXT, Constantes.TIPO_TEXT);
        valores.put(Constantes._BOOL, Constantes.TIPO_BOOL);
        valores.put(Constantes._DATE, Constantes.TIPO_DATE);
        valores.put(Constantes._DATETIME, Constantes.TIPO_DATETIME);
        valores.put(Constantes._DATETIME2, Constantes.TIPO_DATETIME2);
    }

    public static String TagIf(Node noh)
    {
        String result = "", vsIf = "", vsElse ="";

        result = Replace(noh.getFirstChild().getTextContent());
        //System.out.println(result);
        if (result.contains("true"))
        {       
            vsIf = result;
            if(noh.hasChildNodes())
            {
                NodeList children = noh.getChildNodes();
                for (int i = 0; i < children.getLength(); i++)
                {
                    if (children.item(i).getNodeType() == Node.ELEMENT_NODE && children.item(i).getNodeName().equals("if"))
                    {
                        vsIf +=TagIf(children.item(i));
                        //System.out.println("result: if " + vsIf);
                        break;
                    }
                }
            }
        }
        else if(result.contains("false") && noh.hasChildNodes())
        { 
            NodeList children = noh.getChildNodes();
            for (int i = 0; i < children.getLength(); i++)
            {
                if (children.item(i).getNodeType() == Node.ELEMENT_NODE && children.item(i).getNodeName().equals("else"))
                {        
                    vsElse = Replace(children.item(i).getFirstChild().getTextContent());
                    if(children.item(i).hasChildNodes())
                    {
                        NodeList childrenElse = children.item(i).getChildNodes();
                        for (int ind = 0; ind < childrenElse.getLength(); ind++)
                        {
                            if (childrenElse.item(ind).getNodeType() == Node.ELEMENT_NODE && childrenElse.item(ind).getNodeName().equals("if"))
                            {
                                //System.out.println("result: antes " + vsElse);
                                vsElse +=TagIf(childrenElse.item(ind));
                                //System.out.println("result: depois " + vsElse);
                                break;
                            }
                        }
                    }
                    vsElse = Replace(vsElse);
                    break;
                }
                else{
                    vsIf = "";
                    vsElse = "";
                }
            }
        }
        else
        {
            result="";vsIf = ""; vsElse ="false";
        }
        return vsIf.equals("") ? vsElse.replace("false", "") : vsIf.replace("true", "");
    }

    public static String TagFor(Node noh)
    {
        String result = "";
        int tamanhoNoh = noh.getTextContent().split("").length;
        for (Coluna col : tabela.colunas)
        {
            setHashMap(tabela, col);
            if(noh.getFirstChild().getNodeType() != Node.ELEMENT_NODE )
                result +=Replace(noh.getFirstChild().getTextContent());
            if(noh.hasChildNodes())
            {
                NodeList list = noh.getChildNodes();
                for(int a=0; a<list.getLength(); a++)
                {
                    if (list.item(a).getNodeType() == Node.ELEMENT_NODE && list.item(a).getNodeName().equals("if"))
                    {
                        result += TagIf(list.item(a));
                    }
                }
            }
        }
        //System.out.println(result);
        noh.setTextContent(result);
        
        return result;
   }

    public static String ToView(SqlParser tabela, String template, String tipoCodigo)
    {
        selecionaTipoCodigo(tipoCodigo);
        String texto = toWrite(tabela, template);
        return texto;
    }
    
    public static void toWriteFile(SqlParser tabela, String salvarEmPath, String template, String templateNome, String tipoCodigo)
    {
        selecionaTipoCodigo(tipoCodigo);
        criarArquivo(tabela.nomeTabela, toWrite(tabela, template), salvarEmPath, tabela.nomeBanco, templateNome);
    }

    public static String toWrite(SqlParser paramtTabela, String template)
    {
        String texto = "";
        tabela = paramtTabela;
        
        setHashMap(tabela, tabela.colunas.get(0));
        if(template.contains(Constantes.IF) ||template.contains(Constantes.ELSE) || 
                template.contains(Constantes.IF_END) ||template.contains(Constantes.ELSE_END) || 
                template.contains(Constantes.FOR) ||  template.contains(Constantes.FOR_END) ){
            template = template.replaceAll("<", "&lt;");
            template = template.replaceAll(">", "&gt;");
            
            template = template.replace(Constantes.IF, "<if>");
            template = template.replace(Constantes.IF_END, "</if>");
            template = template.replace(Constantes.ELSE, "<else>");
            template = template.replace(Constantes.ELSE_END, "</else>");
            template = template.replace(Constantes.FOR, "<for>");
            template = template.replace(Constantes.FOR_END, "</for>");
            
            template = "<template>" + template + "</template>";
        } 
        try
        {
            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(template.getBytes()));

            Element elemen = doc.getDocumentElement();

            walkNode(doc.getDocumentElement(), texto);
            codigo = elemen.getTextContent();
            codigo = Replace(codigo);
            //System.out.println(codigo);
            if(codigo.contains(", )")) {
            	codigo = codigo.replaceAll(", \\)", ")");
            }
            if(codigo.contains(",)")) {
            	codigo = codigo.replaceAll(",\\)" , ")");
            }
            if(codigo.contains(", from")) {
            	codigo = codigo.replaceAll(", from" , " from");
            }
        } catch (ParserConfigurationException | SAXException | IOException ex)
        {
            Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codigo;
    }

    public static void criarArquivo(String tabela, String texto, String salvarEmPath, String banco, String templateNome)
    {
        String prefixo = Utils.SplitNomePrefixo(templateNome);
    	String sufixo = Utils.SplitNomeTemplate(templateNome);
        
        String nomeTabela = Utils.SplitNomeTabela(tabela);
        //String templateTipo = templateNome;
        templateNome = templateNome.split("\\.")[0];
        //File dir = new File(salvarEmPath + "/" + banco  + "/" + templateNome + "/"+ prefixo + "_" + sufixo);
        File dir = new File(salvarEmPath + "/" + banco  + "/" + templateNome + "/");
        boolean mkdirs = dir.mkdirs();

        try
        {
            //File file = new File(salvarEmPath + "/" + banco + "/" + templateNome + "/" + prefixo +"_"+ sufixo  + "/" + prefixo + nomeTabela + templateTipo);
            File file = new File(salvarEmPath + "/" + banco + "/" + templateNome + "/" + prefixo + nomeTabela + sufixo);
            // if file doesnt exists, then create it
            if (!file.exists())
            {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw))
            {
                bw.write(texto);
                bw.close();
            }
            Constantes.Logs += tabela + templateNome + ".cs" + " Done\n";
        } catch (IOException e)
        {
            Constantes.Logs += "\n\n" + e.getMessage();
            e.printStackTrace();
        }
    }

    public static void selecionaTipoCodigo(String tipoCodigo)
    {
        if(tipoCodigo.equals("Java"))
            Constantes.loadJava();
        if(tipoCodigo.equals("C#"))
            Constantes.loadCsharp();
        if(tipoCodigo.equals("Django"))
            Constantes.loadDjango();
    }
}
