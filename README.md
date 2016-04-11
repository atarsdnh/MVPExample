# MVPExample

### Summary

This is a bacis sample implement Model-View-Presenter pattern with no architectural
frameworks.

### Key concepts

  * A View is a passive interface that defines operating of view components
  * An Activity which implements the view interface and routes user events to the presenter.
  * A Model which is responsible for operating of data
  * A Presenter which is responsible for retrieving data from model and accepting user events from View

### Dependencies

  * Common Android support libraries (<code>com.android.support.\*)</code>
  * Android Testing Support Library (Espresso, AndroidJUnitRunner…)
  * PowerMock
