package br.com.treinamento.springMVC;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

// Como o Spring não sabe fazer a conversão de "String" para "Enum", a própria documentação do Spring
//	sugere a implementação abaixo como uma maneira de fazer a conversão de "String" para "Enum". 
@SuppressWarnings({"rawtypes", "unchecked"})
final class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

	@Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToEnumConverter(targetType);
	}
	
	private final class StringToEnumConverter<T extends Enum> implements Converter<String, T> {
		private Class<T> enumType;
		
		public StringToEnumConverter(Class<T> enumType) {
			this.enumType = enumType;
		}

		@Override
		public T convert(String source) {
			return (T) Enum.valueOf(this.enumType, source.trim());
		}
	}

}
