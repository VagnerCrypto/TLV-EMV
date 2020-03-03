package br.com.hst.tlv;

/***
 * Transformação de string em hexadecimal para sua representação decimal
 * @author vagner_lima
 *
 */
public class DecimalConvert
{
	/***
	 * Valores hexa
	 */
	private static String hexRep = "0123456789ABCDEF";
	
	/***
	 * Converte uma string em hexa para decimal
	 * @param hex A string em hexa
	 * @return O valor decimal
	 */
	public static int hexToDecimal(String hex)
	{
		int counter = hex.length() - 1;
		int decimal = 0;

		for (char c : hex.toCharArray())
		{
			int i = hexRep.indexOf(c);
			decimal = (int) (decimal + (Math.pow(16, counter)) * i);
			counter--;
		}

		return decimal;
	}
}
