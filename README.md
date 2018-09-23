# Demo application

## Starting the website on your local machine
Just start `DemoApplication` with the *dev* profile and keep it running.
This will startup the website using a local database on port **8080**.

Browsing to http://localhost:8080 should give you the homepage.

> NOTE: you can force the dev profile by specifying system property *-Dspring.profiles.active=dev* 

## Front-end development
When starting on your local machine, you should get instant reloading of all static files.
Simply make a change and refresh the page in your browser.

All front-end related static files are located in `src/main/resources/views`.

> WARNING: _All files in `src/main/resources` will be packaged and deployed with the website.
> Do not put any files anywhere in that folder if they are not supposed to be deployed!

### LiveReload integration
The application supports LiveReload.
If you have the plugin in your browser, just activating it on the site should be enough.

### Templating system
The application is built using Thymeleaf 3 for templating.
User documentation: http://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html

## Troubleshooting
### Resetting the local database
Starting up locally will create a local database in a folder `local-data/db`.
If you find any problems starting up the application, try removing this folder and then restarting.

