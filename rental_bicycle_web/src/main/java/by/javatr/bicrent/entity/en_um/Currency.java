package by.javatr.bicrent.entity.en_um;

public enum Currency {
    BYN("933"),
    EUR("978"),
    USD("840");


    private String name;

    private Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return ordinal();
    }

    public static Currency getById(Integer id) {
        return Currency.values()[id];
    }

    public static Currency getCurrency(String str) {
        Currency currency = null;
        for (int i = 0; i < Currency.values().length; i++) {
            String currencyFromEnum = Currency.values()[i].getName();
            if (currencyFromEnum.equals(str.toUpperCase())) {
                currency = Currency.getById(i);
            }
        }
        return currency;
    }

    public static String getString(Currency str) {
        String currency = null;
        for (int i = 0; i < Currency.values().length; i++) {
            String currencyFromEnum = Currency.values()[i].getName();
            if (currencyFromEnum.equals(str)) {
                currency = Currency.getById(i).toString();
            }
        }
        return currency;
    }

    public static Integer getCodeOfCurrency(Currency currency) {
        Integer codeOfCurrency = null;
        for (int i = 0; i < Currency.values().length; i++) {
            String currencyFromEnum = Currency.values()[i].getName();
            if (currencyFromEnum.equals(currency.getName())) {
                codeOfCurrency = Integer.valueOf(currencyFromEnum);
            }
        }
        return codeOfCurrency;
    }


    public static String getCodeOfCurrency(String enteredValue) {
        String result = "";
      if (Currency.isValue(enteredValue))
          result = Currency.getCurrency("933").getName();
      else
          result = Currency.valueOf(enteredValue).getName();
        return result;
    }


    public static boolean isValue(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}
