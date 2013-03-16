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

    public String title;
    public String forename;
    public String surname;
    public int age;
    private String hostname;
    public int pin;
    public Levels level;
    
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
