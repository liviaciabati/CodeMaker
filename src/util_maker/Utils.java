package util_maker;

import java.util.ArrayList;
import java.util.List;

public class Utils 
{
	public static String SplitNomeTabela(String nome)
	{
		return nome.contains(".") ? nome.substring(nome.indexOf(".") + 1) : nome;
	}
	
	public static String SplitNomeColuna(String nome)
	{
		return nome.contains("_") ? nome.substring(nome.indexOf("_") + 1) : nome;
	}
        	
    public static String SplitNomeSufixo(String nome)
	{
		return nome.contains(".") ? nome.substring(nome.lastIndexOf(".") + 1, nome.length()) : "";
	}
   	
    public static String SplitNomeTemplate(String nome)
	{
		return nome.contains("_") ? nome.substring(nome.lastIndexOf("_") + 1, nome.length()) : "";
	}
    
	
	public static String SplitNomePrefixo(String nome)
	{
		return nome.contains("_") ? nome.substring(0 ,nome.indexOf("_")) : "";
	}

	public static String SplitLabelColuna(String nome)
	{
		nome = SplitNomeColuna(nome);
		
		List<String> listaNome = new ArrayList<String>();
		
		char[] charNome = nome.toCharArray();
		for (int i = 0; i < charNome.length; i++) {
			if(i != 0 && Character.isUpperCase(charNome[i]))
			{
				listaNome.add(" ");
			}
			listaNome.add(String.valueOf(charNome[i]));
		}
		
		String[] stringFinal = new String[listaNome.size()];		
		StringBuilder builder = new StringBuilder();
		for(String s : listaNome) {
		    builder.append(s);
		}
		return builder.toString();
		
	}
}
