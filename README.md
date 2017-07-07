 This is a forum crawler that only crawls Index, Thead and Page Flipping URLs using some of methods described in IEEE paper (http://ieeexplore.ieee.org/document/6165295/). However it is not the exact same implementation of the paper. 

# Currently Supported Forum Softwares : 
 1) phpBB
 2) Discuz
 3) MyBB
 4) FluxBB

# Database Setup
1) Change database server credentials from Crawl_URL.java, Posts_Window.java and Question_Window.java
2) Then run below SQL statements in mysql client :

>create database crawler;
>use crawler;
>create table records(id int,question varchar(5000),answer longblob,answer_url varchar(1000));

# Screenshots 




# Wanna contribute ?
Send Pull Requests

