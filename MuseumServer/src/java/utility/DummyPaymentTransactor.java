package utility;

/**
 * Takes card details and transacts the payment
 * 
 * @author Alex Cooper <alexander2.cooper@live.uwe.ac.uk>
 */
public class DummyPaymentTransactor {
    private String cardType;
    private String cardNumber;
    private String expiryDate;
    private String securityNumber;
    
    public DummyPaymentTransactor() {        
    }
    
    public static boolean transactPayment(String cardType, String cardNumber, 
            String expiryDate, String securityNumber) {
        return true;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }      
}