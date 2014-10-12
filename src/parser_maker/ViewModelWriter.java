package parser_maker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import objetos_maker.Coluna;
import util_maker.Constantes;
import util_maker.Utils;

public class ViewModelWriter 
{	
	public static String stringViewModel(SqlParser tabela)
	{
		String tabelaViewModel = Utils.SplitNomeTabela(tabela.nomeTabela) + "ViewModel";
		String classeViewName = "";
                                                                            
		classeViewName += "\tpublic class "+ tabelaViewModel +"\n"+
					    "\t{\n";
		
		for (Coluna coluna : tabela.colunas) 
		{
			String colNome = Utils.SplitNomeColuna(coluna.Nome);
			String colLabel = Utils.SplitLabelColuna(coluna.Nome);
			
			classeViewName += 	"\t\t[DisplayName(\"" + colLabel + ": \")]\n";
			if(coluna.NotNull)
				classeViewName += "\t\t[Required(ErrorMessage = \""+ colLabel + " é obrigatório\")]\n";
			
			
			classeViewName += "\t\tpublic ";
			
			switch (coluna.Tipo) 
			{
			case "int":
				classeViewName += "int";
				break;
				
			case "varchar":
				classeViewName += "string ";
				break;
				
			case "text":
				classeViewName += "string ";
				break;
			
			case "bit":
				classeViewName += "bool ";
				break;
				
			case "date":
				classeViewName += "DateTime";
				break;
				
			case "datetime":
				classeViewName += "DateTime";
				break;
				
			case "datetime2":
				classeViewName += "DateTime";
				break;
				
			case "float":
				classeViewName += "float";
				break;
				
			default:
				break;
			}
			
			classeViewName += colNome + " { get; set; }\n\n";
					
		}
		classeViewName += "\t}\n\n";
		
		
		return classeViewName;
	}

	public static void criarArquivo(String tabela, String texto, String salvarEmPath)
	{
                String sufixo = Utils.SplitNomeSufixo(tabela);
		
                File dir = new File(salvarEmPath + "/ViewModel/" + sufixo);
                dir.mkdir();

		String tabelaViewModel = Utils.SplitNomeTabela(tabela);
		
		try {
			File file = new File(salvarEmPath + "/ViewModel/"+ sufixo + tabelaViewModel + "ViewModel.cs");
			 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(texto);
			bw.close();
	
                        Constantes.Logs += tabela + "ViewModel.cs" + " Done\n";
		} 
		catch (IOException e) 
		{
                    Constantes.Logs += "\n\n" +  e.getMessage();
			e.printStackTrace();
		}
	}
	
	public static void WriteViewModelFile(List<SqlParser> tabelas, String salvarEmPath)
	{
		for (SqlParser tabela : tabelas) 
		{		
			//Imports                                                 
			String Imports = "using System;  \n"+
					"using System.Collections.Generic;  \n"+
					"using System.ComponentModel;  \n"+
					"using System.ComponentModel.DataAnnotations;  \n"+
					"using System.Linq;  \n"+
					"using System.Text;    \n\n";
						
			String DeclaraClasse = "namespace OrionDigital"+tabela.nomeBanco+".ViewModel."+Utils.SplitNomeSufixo(tabela.nomeTabela)+"\n" +
           				"{\n";
			
			String ViewModel = stringViewModel(tabela);
			
			String fechaClasse = "\n}";
			
//                        Constantes.Logs += "\n\n" +  Imports +
//                                                    DeclaraClasse +
//                                                    ViewModel +
//                                                    fechaClasse;
			
			criarArquivo(tabela.nomeTabela, Imports +
								DeclaraClasse +
								ViewModel +
								fechaClasse, 
                                        salvarEmPath);
		}
	}
}
