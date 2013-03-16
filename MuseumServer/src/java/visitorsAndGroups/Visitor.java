/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visitorsAndGroups;

import QuestionsAndAnswers.Levels;

/**
 *
 * @author neil
 */
public class Visitor {

    private String title;
    private String forename;
    private String surname;
    private int age;
    private String hostname;
    private int pin;
    private Levels level;
    
    public Visitor(String title, String forename, String surname, int age, Levels level){
        
        this.title = title;
        this.forename = forename;
        this.surname = surname;
        this.age = age;
        this.level = level;
    }
    
    public void registerHandset(String hostname, int pin){
        this.hostname = hostname;
    }
    
    public String getHostName(){
        return this.hostname;
    }
    
    public int getPin(){
        return this.pin;
    }
}
