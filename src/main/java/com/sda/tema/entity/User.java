package com.sda.tema.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "delete_user_by_username",
                query = "delete from User where userName = :username"),
        @NamedQuery(name = "find_user_and_pasword_from_database",
                query = "select s from User s where s.userName = :username and s.password = :password"),
        @NamedQuery(name = "find_user_from_database",
                query = "select s from User s where s.userName = :username")
})

@Entity
@Table(name = "users")
public class User {

    private static final String USERS_SEQUENCE = "users_id_sequence";
    private static final String USERS_GENERATOR = "users_generator";

    @Id
    @SequenceGenerator(name = "USERS_GENERATOR", sequenceName = USERS_SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USERS_GENERATOR)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
