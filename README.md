# CoTest

Here, I will present the project with the implemented architecture and all the technologies used.

## Description

This application allows you to load a list of users then a given api in the form of pagination.
It also makes it possible to carry out the possibility of research which is done from a local database.
There is in view which allows to display the detail of a user

## Getting Started

### Dependencies

* Retrofit: to ensure communication with the server
* Room : to create local data base
* koin : dependency Injection
* Flow : to transfer the data between different layer
* Navigation-compose component: to ensure the navigation between components
* Couroutine : reactive programming 
* Jetpack Compose State & StateFlow: to update the component
* paging-compose: to perform paging
* landscapist-coil:library that manage the presented images
* junit4: to impelmentes the unit test
* esspresso: to impelmente the ui test
* mockito: to mock the tested object
* truth : library provided by google to facilitate the test 

### Architecture

This project was implemented by the MVVM architecture by respecting the principles of modular programming and clean architecture.
This project containe 4 module :
* app: prensente the UI Layer
* domain : preneste the DOMAIN Layer
* data: prensente the DATA Layer
* base : this module includes the different Koin data and domain modules and provided it to the app module ( to insure the independence between modules)

This miro link prensente the app architecture : https://miro.com/app/board/uXjVMxongNc=/?share_link_id=530901246513


## Authors

Contributors names and contact info

FullName. Mohamed Mbarki 
mail. mbarki.mouhammed@gmail.com

