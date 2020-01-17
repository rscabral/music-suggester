# iFood Challenge

This is a microservice that suggests a music playlist by **city** and its **current temperature**.

## Feature
 - Music playlits come from **Spotify API** (https://developer.spotify.com/) -> **Deezer** comming soon
 - Current city temperature comes from **OpenWeatherMaps API** (https://openweathermap.org/)

## Missing/New Ideas
 - Use Cache (Reddis, Memcache, etc)
 - New Approch for current Circuit Breacker
 - Container
 - H2 to store the Spotify API Token
 - Deezer API :p

## How to build
 - Use Maven: 
   - mvn clean package
   - mvn spring-boot:run 

## How to use it

 - Set the following env variables:
   - SPRING_PROFILES_ACTIVE -> set "prod" to use real apis, and default (or remove variable) to use stubbed values
   - OPEN_WEATHER_API_KEY -> Your Open Weather API key
   - SPOTIFY_API_CLIENT_KEY -> Spotify Dev client id -> Base64 CliendId:ClientSecret

 - Running:
   - Unfortunatelly I didn't implement the available links to access, but I left Rest HAL Browser to help you (HATEOAS comming soon)
   - links examples:
     - Suggesting by city name > **/api/playlist-suggester?cityName=London**
     - Suggesting by city coodinates > **/api/playlist-suggester?lat=51.51&lon=-0.13**
     - Test Purposes
     	- Search city weather by city name: **/api/cityweather?cityName=London**
	- Search city weather by city coordinates: **/api/cityweather?lat=51.51&lon=-0.13**
	- Serch Playlist by genre: **/api/music?genre=rock**

## Code Architecture
Get my draw.io [here](https://drive.google.com/open?id=1qz8vuOCy8hPsy1T82qd0-hld0FCxV1bZ)

## Repositories

 - https://github.com/rscabral
 
## Authors

 * **Rafael Cabral** * - *Just a geek guy* - [Linkedin](https://www.linkedin.com/in/rafael-cabral-9679b498/)
