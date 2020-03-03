package br.com.hst.tlv;

/***
 * Classe para autentica��o de decodifica��o TLV
 * @author vagner_lima
 *
 */
public class VerificationTLV
{
	/***
	 * M�todo para verifica��o de tamanho normal e apresenta��o de valores
	 * @param i Vari�vel de controle
	 * @param tam O tamanho da tag
	 * @param bits Vari�vel para transforma��o de bytes e strings
	 * @param output Array de bytes
	 * @param index Posi�oes a se avan�ar
	 */
	public void tlvShowValues(int i, int tam, ByteTransform bits, byte[] output, int index)
	{
		//Verifica se o tamanho � maior que 7F(127) e ent�o tira 80(128) de seu valor
		if(tam > 0x7F)
		{
			tam-=0x80;
			i+=tam;
		}
	
        int iIndex = i+index;
        int size = (tam + i + index);
        
        System.out.printf("\t\t");
        
		for (int j = iIndex; j < size; j++)
		{
			System.out.printf("%2s", bits.oneByteString(output[j]));
		}	
		
	}
	
	/***
	 * M�todo(Sobrecarga) para verifica��o de tamanho grande(82) e apresenta��o de valores
	 * @param i Vari�vel de controle
	 * @param tam O tamanho da tag
	 * @param bits Vari�vel para transforma��o de bytes e strings
	 * @param output Array de bytes
	 */
	public void tlvShowValues(int i, int tam, ByteTransform bits, byte[] output)
	{
	
		int iIndex = i + 1;
		int size = (tam + i + 1);
	     
		System.out.printf("\t\t");
		
		for (int j = iIndex; j < size; j++)
		{
			System.out.printf("%2s", bits.oneByteString(output[j]));
			
		}
				
	}

}
