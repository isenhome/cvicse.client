package cvicse.client.isen.framework.validation;


/**
 * <p>
 * Type of validation.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 2, 2011
 */
public enum ValidationType {
	NULL {
		
		@Override
		public String getRule() {
			return "[^\\s]+";
		}
	},
	EMAIL {

		@Override
		public String getRule() {
			return "[\\w\\d]+[\\w\\.\\-\\+]*@[\\w\\d\\-]+(\\.[\\w]+)+";
		}
	},	
	USERNAME {

		@Override
		public String getRule() {
			return "[\\d\\w_]{4,20}";
		}
	},
	PASSWORD {

		@Override
		public String getRule() {
			return "[^\\s]{2,32}";
		}
	},
	PHONE_NUMBER {

		@Override
		public String getRule() {
			return "\\d{11}";
		}
	},
	BIRTHDAY {

		@Override
		public String getRule() {
			return "[^\\s]+";
		}
	};

	/**
	 * Define validation rule with regular expression
	 * @return
	 */
	abstract public String getRule();

}