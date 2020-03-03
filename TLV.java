package br.com.hst.tlv;

/***
 * Transoformação TLV decoder
 * @author vagner_lima
 *
 */
public class TLV
{
	/***
	 * Aplica a decodificação TLV
	 * @param input Codificação tlv
	 */
	public void tlvDecoder(String input)
	{
		ByteTransform bits = new ByteTransform();
		VerificationTLV ver = new VerificationTLV();
		
		String str = "";
		String str2 = "";
		int tam = 0;
		int tlvSize = 0;
		int tlvSizeGreat = 0;

		byte[] output = bits.stringToByte(input);
		
		try
		{
			for (int i = 0; i < output.length; i++)
			{				  
				str2 = bits.oneByteString(output[3]);
				str2+= bits.oneByteString(output[4]);
				tlvSizeGreat = DecimalConvert.hexToDecimal(str2);
				
				//Verifica se tem um byte
				if ((output[i] & 0x1F) != 0x1F)
				{			
					//Verifica se é template
					if ((output[i] & 0x20) == 0x20)
					{					
						System.out.println(bits.oneByteString(output[i]) + " TEMPLATE");
						i++;	
						
						//Converte o byte para string
						str = bits.oneByteString(output[i]);
						
						//Converte para decimal
						tlvSize = DecimalConvert.hexToDecimal(str);
						
						
						//Verifica se é maior que 80
						if(tlvSize > 0x7F)
						{
							//Subtrai 80 de value
							tlvSize-=0x80;
							
							
							//Adiciona o valor para a varivael de controle
							i+=tlvSize;
						}
										
						System.out.println();
						
					}
					else
					{													   				    
						System.out.printf("\t %s TAG", bits.oneByteString(output[i]));
						
						System.out.println();
											
						str = bits.oneByteString(output[i+1]);
						tlvSize = DecimalConvert.hexToDecimal(str);
						
						ver.tlvShowValues(i, tlvSize, bits, output, 2);
						i+=tlvSize;
											
						System.out.println();
					    i+= tam+1;	
					}
	
				}
				else
				{					
					String s = "";
					
					s = bits.oneByteString(output[i]);
					s+= bits.oneByteString(output[i+1]);
					System.out.printf("\t");
					System.out.println(s + " TAG");
			
					i+=2;
								
					str = bits.oneByteString(output[i]);
					tlvSize = DecimalConvert.hexToDecimal(str);								    	      				
					
					//Veirifica se o índice ultrapassou o índice do primeiro tamanho e se o valor é maior que FF(256)
					if(i < 4 && tlvSizeGreat > 0xFF)
					{
						tlvSize-=0x80;
						i+=tlvSize;
						
						ver.tlvShowValues(i, tlvSizeGreat, bits, output);
						
						//Avança a variavel de controle com o tamanho
						i+=tlvSizeGreat;
					}
					else
					{
						ver.tlvShowValues(i, tlvSize, bits, output, 1);
						i+=tlvSize;								
					}
					
					System.out.println();
					
									        			    			        																																	
				}				
							
			}

		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
	       System.out.println();
	       System.out.println();
	       System.out.println("O índice ultrapassou os limites do array.");
	       System.out.println("Code: " + ex.getMessage());
		}
	}

}
