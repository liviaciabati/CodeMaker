package parser_maker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import objetos_maker.Coluna;
import util_maker.Constantes;
import util_maker.Utils;

public class DtoWriter {
    
    public static String stringDto(SqlParser tabela) {
        String tabelaDto = Utils.SplitNomeTabela(tabela.nomeTabela) + "Dto";
        String tabelaSufixo = Utils.SplitNomeSufixo(tabela.nomeTabela);
        String nomeBanco = tabela.nomeBanco;
        
        String classeDto = "";
        //Constantes.Logs += "\n\n" + tabelaDto + " " + tabela.nomeTabela;

        classeDto += "\t[Serializable]\n"
                + "\t[DataContract(Name = \"" + tabelaDto + "\", Namespace = \"OrionDigital"+nomeBanco+".BusinessObjects."+tabelaSufixo+"\")] \n"
                + "\tpublic class " + tabelaDto + "\n"
                + "\t{\n";

        for (Coluna coluna : tabela.colunas) {
            String colNome = Utils.SplitNomeColuna(coluna.Nome);
            String fk = "";

            classeDto += "\t\t[DataMember]\n";

            if (coluna.FK) {
                String colRefNome = Utils.SplitNomeColuna(coluna.Referencia);

                classeDto += "\t\t[ForeignKey(\"" + colRefNome + "\")]\n";
                fk = "\t\t[DataMember]\n"
                        + "\t\tpublic virtual " + colRefNome + "Dto " + colRefNome + " { get; set; }\n\n";
            }

            classeDto += "\t\tpublic ";

            switch (coluna.Tipo) {
                case "int":
                    classeDto += "int" + (coluna.NotNull ? " " : "? ");
                    break;

                case "varchar":
                    classeDto += "string ";
                    break;

                case "text":
                    classeDto += "string ";
                    break;

                case "bit":
                    classeDto += "bool ";
                    break;

                case "date":
                    classeDto += "DateTime" + (coluna.NotNull ? " " : "? ");
                    break;

                case "datetime":
                    classeDto += "DateTime" + (coluna.NotNull ? " " : "? ");
                    break;

                case "datetime2":
                    classeDto += "DateTime" + (coluna.NotNull ? " " : "? ");
                    break;

                case "float":
                    classeDto += "float" + (coluna.NotNull ? " " : "? ");
                    break;

                default:
                    break;
            }

            classeDto += colNome + " { get; set; }\n\n";

            classeDto += fk;

        }
        classeDto += "\t}\n\n";

        return classeDto;
    }

    public static String stringConfiguration(SqlParser tabela) {
        String tabelaDto = Utils.SplitNomeTabela(tabela.nomeTabela) + "Dto";
        String classeDtoConfiguration = "";

        classeDtoConfiguration += "\tpublic class " + tabelaDto + "Configuration : EntityTypeConfiguration<" + tabelaDto + ">\n"
                + "\t{\n"
                + "\t\tpublic " + tabelaDto + "Configuration() : base()\n"
                + "\t\t{\n";
        classeDtoConfiguration += "\t\t\tToTable(\"" + tabela.nomeTabela + "\");\n\n";

        for (Coluna coluna : tabela.colunas) {
            String colNome = Utils.SplitNomeColuna(coluna.Nome);
            if (coluna.PK) {
                classeDtoConfiguration += "\t\t\tHasKey(p => p." + colNome + ");\n"
                        + "\t\t\tProperty(p => p." + colNome + ").HasDatabaseGeneratedOption(System.ComponentModel.DataAnnotations.Schema.DatabaseGeneratedOption.Identity);\n"
                        + "\t\t\tProperty(p => p." + colNome + ").HasColumnName(\"" + coluna.Nome + "\").IsRequired();\n\n";
            } else {
                classeDtoConfiguration += "\t\t\tProperty(p => p." + colNome + ").HasColumnName(\""
                        + coluna.Nome + "\")" + (coluna.NotNull ? ".IsRequired()" : "") + ";\n";
            }
        }

        classeDtoConfiguration += "\t\t}\n\t}";

        return classeDtoConfiguration;
    }

    public static void criarArquivo(String tabela, String texto, String salvarEmPath) {
        
        String sufixo = Utils.SplitNomeSufixo(tabela);
        
        File dir = new File(salvarEmPath + "/Dto/" + sufixo);
        dir.mkdir();
        
        String tabelaDto = Utils.SplitNomeTabela(tabela);

        try {
            File file = new File(salvarEmPath + "/Dto/" + sufixo + "/" + tabelaDto + "Dto.cs");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();

            Constantes.Logs += tabela + "Dto.cs" + " Done\n";
        } catch (IOException e) {
            Constantes.Logs += "\n\n" + e.getMessage();
            e.printStackTrace();
        }
    }

    public static void WriteDtoFile(List<SqlParser> tabelas, String salvarEmPath) {
        for (SqlParser tabela : tabelas) {		//Imports                                                 
            String Imports = "using System;                                           \n"
                    + "using System.Collections.Generic;                    \n"
                    + "using System.ComponentModel;                         \n"
                    + "using System.ComponentModel.DataAnnotations;         \n"
                    + "using System.ComponentModel.DataAnnotations.Schema;  \n"
                    + "using System.Data.Entity.ModelConfiguration;         \n"
                    + "using System.Linq;                                   \n"
                    + "using System.Runtime.Serialization;                  \n"
                    + "using System.Web;                                    \n\n";

            String DeclaraClasse = "namespace *OrionDigital"+tabela.nomeBanco+".BusinessObjects."+Utils.SplitNomeSufixo(tabela.nomeTabela)+"\n" 
                    + "{\n";
            
            String Dto = stringDto(tabela);
            String Configuration = stringConfiguration(tabela);

            String fechaClasse = "\n}";

            /*Constantes.Logs += "\n\n" + Imports
                    + DeclaraClasse
                    + Dto
                    + Configuration
                    + fechaClasse;*/

            criarArquivo(tabela.nomeTabela, Imports
                    + DeclaraClasse
                    + Dto
                    + Configuration
                    + fechaClasse,
                    salvarEmPath);

        }
    }
}
