package com.example.onlinebanking;

@SuppressWarnings("unused")
public class User {

        String firstname, lastname;
        int userid;
        //Accounts variable :)

        public User(String firstname, String lastname, int userid) {
                this.firstname = firstname;
                this.lastname = lastname;
                this.userid = userid;
        }

        //region Getters and Setters
        public String getFirstname() {
                return firstname;
        }
        public void setFirstname(String firstname) {
                this.firstname = firstname;
        }
        public String getLastname() {
                return lastname;
        }
        public void setLastname(String lastname) {
                this.lastname = lastname;
        }
        public int getUserid() {
                return userid;
        }
        public void setUserid(int userid) {
                this.userid = userid;
        }
        //endregion

        //When user signs out return all bank account data to the database
        public void signOutUser(){
                //store account data in database

                //reset all account data variables for the next user

                //return user to login page
        }
}
