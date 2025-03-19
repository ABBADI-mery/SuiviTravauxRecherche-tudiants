/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author pc
 */
public class User {

    private String login;
    private String password;

    private String securityQuestion;
    private String securityAnswer;

    public User(String login, String password, String securityQuestion, String securityAnswer) {
        this.login = login;
        this.password = password;

        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", password=" + password + ", securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer + '}';
    }

}
