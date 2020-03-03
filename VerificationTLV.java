package br.com.hst.tlv;

/***
 * Classe para autenticação de decodificação TLV
 * @author vagner_lima
 *
 */
public class VerificationTLV
{
	/***
	 * Método para verificação de tamanho normal e apresentação de valores
	 * @param i Variável de controle
	 * @param tam O tamanho da tag
	 * @param bits Variável para transformação de bytes e strings
	 * @param output Array de bytes
	 * @param index Posiçoes a se avançar
	 */
	public void tlvShowValues(int i, int tam, ByteTransform bits, byte[] output, int index)
	{
		//Verifica se o tamanho é maior que 7F(127) e então tira 80(128) de seu valor
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
	 * Método(Sobrecarga) para verificação de tamanho grande(82) e apresentação de valores
	 * @param i Variável de controle
	 * @param tam O tamanho da tag
	 * @param bits Variável para transformação de bytes e strings
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
