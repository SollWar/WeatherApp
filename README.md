## Архитектура приложения
### Clean Architecture

app [presentation] ->  
di - AppModule зависимости для WeatherRepository и WeatherApi  
ui ->  Компоненты UI

data.remote ->  
    mapper - Маппинг моделей data в domain  
    models - Модели для Retrofit  
    repository - Репозиторий для Retrofit  
    retrofit - Интерфейс api для Retrofit  

domain ->  
    models - Модели для работы с UI  
    repository - Репозиторий для работы с Date  
usecases - Use кейсы  

### UDF на примере MainScreen  
EventHandler - общий интерфейс для реализации Event  
MainScreenEvent - список возможных Event у MainScreen  
MainScreenViewState - список всех State у MainScreen