Steel City Codefest 2014 
========================

civic hacking app.


## Developer Instructions

# Initial setup

* Install Git
* Install Maven
* Setup IDE

git clone https://summacodefest.toolscloud.net/git/SummaSteelCityCodeFest2014.git

Import into IDE of your choice using Maven Import.

## Deploy to Heroku

* Create a free Heroku account and get added as a collaborator
* Download the [Heroku Toolbelt](https://devcenter.heroku.com/articles/quickstart#step-2-install-the-heroku-toolbelt)
* Add heroku as a remote in git

```bash
heroku git:remote -a trwib-jobs-calendar
```

* Deploy (this is a git push like any other, you must commit first, etc.) Push to master (github) separately.

```bash
git push heroku master
```

## Resources

* [Heroku app dashboard](https://dashboard.heroku.com/apps/trwib-jobs-calendar/)
* [Live app logs](https://papertrailapp.com/systems/trwib-jobs-calendar/events)
* [DB information](https://www.cleardb.com/database/details?id=191E94E1CE0DD7A187E2FF60869A89FE)
