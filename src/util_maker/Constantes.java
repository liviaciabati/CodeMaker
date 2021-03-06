/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util_maker;

/**
 *
 * @author orionws
 */
public class Constantes 
{
    static public String Logs = "";
    
    static final public String IF = "[IF]";
    static final public String ELSE = "[ELSE]";
    static final public String FOR = "[FOR]";
    static final public String IF_END = "[IF_END]";
    static final public String ELSE_END = "[ELSE_END]";
    static final public String FOR_END = "[FOR_END]";
    
    static final public String BANCO_NOME = "[BANCO_NOME]";
    
    static final public String TABELA_NOME = "[TABELA_NOME]";
    static final public String TABELA_SUFIXO = "[TABELA_SUFIXO]";
    
    static final public String COLUNA_SUFIXO = "[COLUNA_SUFIXO]";
    static final public String COLUNA_PREFIXO = "[COLUNA_PREFIXO]";
    static final public String COLUNA_NOME = "[COLUNA_NOME]";
    static final public String COLUNA_TIPO = "[COLUNA_TIPO]";
    static final public String COLUNA_PK = "[COLUNA_PK]";
    static final public String COLUNA_FK = "[COLUNA_FK]";
    static final public String COLUNA_REFERENCIA = "[COLUNA_REFERENCIA]";
    static final public String COLUNA_NOT_NULL = "[COLUNA_NOT_NULL]";
    static final public String COLUNA_LABEL = "[COLUNA_LABEL]";
    static final public String COLUNA_TAMANHO = "[COLUNA_TAMANHO]";

    static public String TIPO_INT = "int";
    static public String TIPO_VARCHAR = "String";
    static public String TIPO_CHAR = "String";
    static public String TIPO_TEXT = "String";
    static public String TIPO_BOOL = "Boolean";
    static public String TIPO_DATE = "DateTime";
    static public String TIPO_DATETIME = "DateTime";
    static public String TIPO_DATETIME2 = "DateTime";
    static public String TIPO_TIME = "Time";
    
    static final public String _INT = "[INT]";
    static final public String _VARCHAR = "[VARCHAR]";
    static final public String _CHAR = "[CHAR]";
    static final public String _BOOL = "[TINYINT]";
    static final public String _TEXT = "[TEXT]";
    static final public String _DATE = "[DATE]";
    static final public String _DATETIME = "[DATETIME]";
    static final public String _DATETIME2 = "[DATETIME2]";
    static final public String _TIME = "[TIME]";

    static final public String _Mysql_INT = "int";
    static final public String _Mysql_VARCHAR = "varchar";
    static final public String _Mysql_CHAR = "char";
    static final public String _Mysql_BOOL = "tinyint";
    static final public String _Mysql_TEXT = "text";
    static final public String _Mysql_DATE = "date";
    static final public String _Mysql_DATETIME = "datetime";
    static final public String _Mysql_DATETIME2 = "datetime2";
    static final public String _Mysql_TIME = "time";
    
    public static void loadJava()
    {
        TIPO_INT = "int";
        TIPO_VARCHAR = "String";
        TIPO_CHAR = "String";
        TIPO_TEXT = "String";
        TIPO_BOOL = "Boolean";
        TIPO_DATE = "DateTime";
        TIPO_DATETIME = "DateTime";
        TIPO_DATETIME2 = "DateTime";
        TIPO_TIME = "Time";
    }
    
    public static void loadCsharp()
    {
        TIPO_INT = "int";
        TIPO_VARCHAR = "string";
        TIPO_CHAR = "string";
        TIPO_TEXT = "string";
        TIPO_BOOL = "bool";
        TIPO_DATE = "DateTime";
        TIPO_DATETIME = "DateTime";
        TIPO_DATETIME2 = "DateTime";
        TIPO_TIME = "Time";
    }
    
    public static void loadDjango()
    {
        TIPO_INT = "IntegerField";
        TIPO_VARCHAR = "CharField";
        TIPO_CHAR = "CharField";
        TIPO_TEXT = "TextField";
        TIPO_BOOL = "BooleanField";
        TIPO_DATE = "DateField";
        TIPO_DATETIME = "DateField";
        TIPO_DATETIME2 = "DateField";
        //TIPO_TIME = "TimeField";
    }
}

