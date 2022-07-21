package exam1.studentsystemplus;

public class User {
    String username;
    String password;
    String idCard;
    String phoneNumber;

    public User() {
    }

    public User(String username, String password, String idCard, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return idCard
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置
     * @param idCard
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
