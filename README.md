## Contacts
:tada: This app demonstrates how to consume a list of remote contacts.

## Features
- [x] Allow user to login with id and token
- [x] Allow loginned user to show a list of contacts
- [x] Allow loginned user to show contact details

## Version control
This project use `Git` as a source controle, and mimic a `Git flow` with branches strategy for each feature.

## Architecture 
Contacts app define an architecture that allows the app to scale, increases the app's robustness.

**Seperation of concern** is the core design for contacts archiecture, that applied within three layers

> **Presenation Layer**  Contains only ui logic and operating system interactions. 

> **Domain Layer**  Contains business rules and fetching data sources.

> **Entity Layer**  Contains app data classes.

This app follows [Uncle-Bob Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) ,
[Android-Docs Architecure Guide](https://developer.android.com/jetpack/guide) for project architecure and MVVM as UI architecture

## Tech Stack
- > **Language**  Java,Kotlin
- > **Navigation**  Allow user to navigate between app's destinations 
- > **Material Design** Apply material design guide on app ui
- > **Coil** To load prefered images to user
- > **View Binding** Allow interact with XML layout via generated binding class
- > **Retrofit** As http client to connect via web services
- > **OkHttp** To manage http request headers
- > **ViewModel** To store and manage ui related data
- > **Dagger-Hilt** Provides a standard way to apply dependecy injection
- > **RxJava** As multi-threading approach for composing asynchronous
- > **Koltin Coroutine** As light weigh threading technique  
- > **Koltin Flow** To recieve live updates of data

## App Screens
The app ui get inspired by [OnePageCRM](https://play.google.com/store/apps/details?id=com.onepagecrm&hl=en&gl=US).

### Login
This feature allow to user to login via `ID` and `API TOKEN`, which you can get from [OnePageCRM API](https://app.onepagecrm.com/app/api). The credentials
 data store to `properties` file, as a more secure access layer between sensitive keys and public access.
 
### Contact list
This feature display a list of contacts consumed from [OnePageCRM Contacts](https://developer.onepagecrm.com/api/#/Contacts), with enabling 
expanded option with each item.

### Contact Details
This feature display an extra details data for each contact.

### Demo

| Splash | Login | Login + Error | Contacts 
| ------ | ------ | ------| ----- 
| <img src="https://raw.githubusercontent.com/MohamedGElsharkawy/Contacts/master/screen-shots/splash.jpg" align="center" width="100%"/> |<img src="https://raw.githubusercontent.com/MohamedGElsharkawy/Contacts/master/screen-shots/login.jpg" align="center" width="100%"/>|<img src="https://raw.githubusercontent.com/MohamedGElsharkawy/Contacts/master/screen-shots/login-error.jpg" align="center" width="100%" /> |<img src="https://raw.githubusercontent.com/MohamedGElsharkawy/Contacts/master/screen-shots/contacts.jpg" align="center" width="100%"/>


| Expanded Contacts | Contact Details | Connection Error  
| ------ | ------ | ------
|<img src="https://raw.githubusercontent.com/MohamedGElsharkawy/Contacts/master/screen-shots/contacts-expanded.jpg" align="center" width="100%"/> |<img src="https://raw.githubusercontent.com/MohamedGElsharkawy/Contacts/master/screen-shots/details.jpg" align="center" width="100%"/> | <img src="https://raw.githubusercontent.com/MohamedGElsharkawy/Contacts/master/screen-shots/details-conncetion.jpg" align="center" width="100%"/>

 





