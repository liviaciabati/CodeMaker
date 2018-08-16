package parser_maker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import objetos_maker.Coluna;
import util_maker.Constantes;
import util_maker.Utils;

public class SqlParser {

    public String nomeBanco;
    public String nomeTabela;
    public List<Coluna> colunas;

    public SqlParser() {
        nomeBanco = "";
        nomeTabela = "";
        colunas = new ArrayList<Coluna>();
    }
    
    public static List<SqlParser> ordenaLista(List<SqlParser> lista){
        Collections.sort(lista, new Comparator<SqlParser>() {
                @Override
                public int compare(SqlParser  lista1, SqlParser  lista2)
                {
                    return  lista1.nomeTabela.compareTo(lista2.nomeTabela);
                }
         });
        return lista;
    }
//    public static List<SqlParser> ParserMySql(File sqlFile) throws IOException, Exception {
//        List<SqlParser> sqlList = new ArrayList<SqlParser>();
//        SqlParser sqlObj = new SqlParser();
//        String linha = "";
//        String nomeBanco = "";
//        
//        BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(sqlFile))));
//        //BufferedReader br = new BufferedReader(new FileReader(sqlFile));
//        while ((linha = br.readLine()) != null) {
//            //Nome do banco
//            if (Pattern.matches("CREATE SCHEMA.*", linha)) {
//                //System.out.println(linha);
//                sqlObj = new SqlParser();
//                sqlObj.nomeBanco = linha.split("\\`")[1].split("\\`")[0];
//                nomeBanco = sqlObj.nomeBanco;
//            }
//            
//           // System.out.println("passou Create schema");
//            
//            //Encontrou o nome da tabela
//            if (Pattern.matches("CREATE TABLE.*", linha)) 
//            {
//                sqlObj = new SqlParser();
//                sqlObj.nomeBanco = nomeBanco;
//
//                sqlObj.nomeTabela = linha.split("`[A-Za-z0-9]*`.`")[1].split("`")[0];
//
//                //Le todos os atributos
//                linha = br.readLine();
//                //while(!Pattern.matches(".*CONSTRAINT.*", linha))
//                while (Pattern.matches("^ *`.*`.*", linha)) {
//                    Coluna col = new Coluna();
//
//                    col.Nome = linha.split("`")[1].split("`")[0];
//                    col.NotNull = linha.contains("NOT NULL");
//                    col.Tipo = linha.contains("(") ? linha.split("`.*` ")[1].split("\\(")[0] : linha.split("`.*` ")[1].split(" N.*")[0];
//
//                    switch (col.Tipo)
//                    {
//                        case Constantes.VARCHAR:
//                            col.Size = Integer.parseInt(linha.split("\\(")[1].split("\\)")[0]);
//                            col.Tipo = Constantes.VARCHAR;
//                            break;
//                        case "nvarchar":
//                            col.Tipo = "string";
//                            break;
//                        case Constantes.CHAR:
//                            col.Tipo = "char";
//                            break;
//                        case "TEXT":
//                            col.Tipo = "string";
//                            break;
//                        case Constantes.DATETIME:
//                            col.Tipo = Constantes.DATETIME;
//                            break;
//                        case Constantes.INT:
//                            col.Tipo = Constantes.INT;
//                            break;
//                        case Constantes.TIME:
//                            col.Tipo = Constantes.TIME;
//                            break;  
//                        case "decimal":
//                            col.Tipo = "decimal";
//                            break;
//                        case "FLOAT":
//                            col.Tipo = "double";
//                            break;
//                        case "DOUBLE":
//                            col.Tipo = "double";
//                            break;
//                        case "boolean":
//                            col.Tipo = "bool";
//                            break;
//                        case Constantes.BOOL:
//                            col.Tipo = Constantes.BOOL;
//                            break;
//                        default: return null;
//                    }
//
//
//                    sqlObj.colunas.add(col);
//                    linha = br.readLine();
//                    
//                //System.out.println("passou tipo");
//
//                }
//                sqlList.add(sqlObj);
//            }
//            
//            //System.out.println("passou Create table");
//
//            //Encontro as chaves primarias
//            if (Pattern.matches(".*PRIMARY KEY.*", linha)) {
//                //Encontar a coluna que é a chave primaria
//                
//                String string = linha.split("`")[1].split("`")[0];
//                for (Coluna col : sqlObj.colunas) {
//                    if (col.Nome.equals(string)) {
//                        col.PK = true;
//                        break;
//                    }
//                }
//            }
//                
//            //System.out.println("passou PK");
//            //Encontro as chaves estrangeiras
//            if (Pattern.matches(".*FOREIGN KEY.*", linha)) {
//                String fk = linha.split("\\(`")[1].split("`\\)")[0];
//                linha = br.readLine();
//
//                if (Pattern.matches(".*REFERENCES.*", linha)) {
//                    //Encontra a tabela na lista de tabela"s
//                    String tabela = linha.split(".*`.`")[1].split("` \\(`")[0];
//                    for (SqlParser sqlParser : sqlList) {
//                        if (sqlParser.nomeTabela.equals(sqlObj.nomeTabela)) {
//                            //Encontar a coluna que ï¿½ a chave estrangeira
//                            String coluna = fk;
//                            String referencia = tabela;
//                            for (Coluna col : sqlParser.colunas)
//                            {
//                                if (col.Nome.equals(coluna)) {
//                                    col.FK = true;
//                                    col.Referencia = Utils.SplitNomeTabela(referencia);
//                                    break;
//                                }
//                            }
//                            break;
//                        }
//                    }
//                }
//            }
//            
//            //System.out.println("passou FK");
//        }
//        //System.out.println("terminou");
//        return sqlList;
//    }
//    
    public static List<SqlParser> ParserMS(File sqlFile) throws IOException, Exception {
        List<SqlParser> sqlList = new ArrayList<SqlParser>();
        SqlParser sqlObj = new SqlParser();

        String linha = "";
        String nomeBanco = "";
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(sqlFile)), "UTF-8"));
        //BufferedReader br = new BufferedReader(new FileReader(sqlFile));
        while ((linha = br.readLine()) != null) {
            
            //Nome do banco
            if (Pattern.matches(".*CREATE DATABASE.*", linha)) {
                //System.out.println(linha);
                sqlObj = new SqlParser();
                sqlObj.nomeBanco = linha.split("\\[")[1].split("]")[0];
                nomeBanco = sqlObj.nomeBanco;
            }
            
            //Encontrou o nome da tabela
            if (Pattern.matches(".*CREATE TABLE .*", linha)) {
                //System.out.println(linha);
                sqlObj = new SqlParser();
                sqlObj.nomeBanco = nomeBanco;
                                
                //sqlObj.nomeTabela = linha.split("\\[[A-Za-z]*\\]\\.\\[")[1].split("]")[0];
                sqlObj.nomeTabela = linha.split("CREATE TABLE ")[1].replace("(","").replace("[","").replace("]","");
                
                //Le todos os atributos
                linha = br.readLine();
                //System.out.println(linha);
                //while(!Pattern.matches(".*CONSTRAINT.*", linha))
                while (Pattern.matches(".*\\[.*\\] \\[.*\\].*", linha)) {
                    Coluna col = new Coluna();

                    col.Nome = linha.split("\\[")[1].split("]")[0];
                    col.NotNull = linha.contains("NOT NULL");
                    col.Tipo = linha.split("] \\[")[1].split("]")[0];

                    switch (col.Tipo)
                    {
                        case "varchar":
                        	if(linha.contains("max")) {
                        		col.Size = 1000;
                        	}
                        	else {
                        		col.Size = Integer.parseInt(linha.split("\\(")[1].split("\\)")[0]);
                        	}
                            col.Tipo = Constantes._VARCHAR;
                            col.NotNull = true;
                            break;
                        case "nvarchar":
                            col.Tipo = Constantes._VARCHAR;
                            col.NotNull = true;
                            break;
                        case "char":
                            col.Tipo = Constantes._CHAR;
                            col.NotNull = true;
                            break;
                        case "varbinary":
                            col.Tipo = Constantes._VARCHAR;
                            col.NotNull = true;
                            break;
                        case "text":
                            col.Tipo = Constantes._VARCHAR;
                            col.NotNull = true;
                            break;
                        case "datetime":
                            col.Tipo = Constantes._DATETIME;
                            break;
                        case "int":
                            col.Tipo = Constantes._INT;
                            break;
                        case "bigint":
                            col.Tipo = Constantes._INT;
                            break;
                        case "smallint":
                            col.Tipo = Constantes._INT;
                            break;
                        case "time":
                            col.Tipo = Constantes._TIME;
                            break;  
                        case "decimal":
                            col.Tipo = "decimal";
                            break;
                        case "FLOAT":
                            col.Tipo = "double";
                            break;
                        case "DOUBLE":
                            col.Tipo = "double";
                            break;
                        case "boolean":
                            col.Tipo = "bool";
                            break;
                        case "bool":
                            col.Tipo = Constantes._BOOL;
                            break;
                        case "bit":
                        	col.Tipo = Constantes._BOOL;
                        //default: return col.Tipo = col.Tipo;
                    }
                    
                    sqlObj.colunas.add(col);
                    linha = br.readLine();
                    //System.out.println(linha);

                }
                sqlList.add(sqlObj);
            }

            //Encontro as chaves primarias
            if (Pattern.matches(".*PRIMARY KEY.*", linha)) {
                while (!Pattern.matches(".*ASC", linha)) {
                    linha = br.readLine();
                }

                //Encontar a coluna que é a chave estrangeira
                String coluna = linha.split("\\[")[1].split("]")[0];
                for (Coluna col : sqlObj.colunas) {
                    if (col.Nome.equals(coluna)) {
                        col.PK = true;
                        break;
                    }
                }
            }

            //Encontro as chaves estrangeiras
            if (Pattern.matches(".*ALTER TABLE.*FOREIGN KEY.*", linha)) {
                String tabela = linha.split("\\[[A-Za-z]*\\]\\.\\[")[1].split("]")[0];
                linha = br.readLine();

                if (Pattern.matches(".*REFERENCES.*", linha)) {
                    //Encontra a tabela na lista de tabelas
                    for (SqlParser sqlParser : sqlList) {
                        if (sqlParser.nomeTabela.equals(tabela)) {
                            //Encontar a coluna que ï¿½ a chave estrangeira
                            String coluna = linha.split("\\(\\[")[1].split("]\\)")[0];
                            String referencia = linha.split("\\[[A-Za-z]*\\]\\.\\[")[1].split("]")[0];
                            for (Coluna col : sqlParser.colunas) {
                                if (col.Nome.contains("_") && col.Nome.split("_")[1].equals(coluna.split("_")[1])) {
                                    col.FK = true;
                                    col.Referencia = referencia;
                                    break;
                                }
                                else if (col.Nome.equals(coluna)){
                                	col.FK = true;
                                    col.Referencia = referencia;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
            
        System.out.println(linha);
        }

        br.close();
        return sqlList;
    }


}
