
# Movie API 
## A demonstration of using Spring Boot and MongoDB to create a basic API.
## This application uses Spring Boot and MongoDB

# Instructions
### There are a few steps to running this project
### First, clone this repo `git clone https://github.com/showsjohn/Movie_API`
### Then, run `mvn clean install` to grab all dependencies. You may see a message regarding Lombok wanting to set annotation preprocessing, make sure to hit enable.
### Next, use `pip3 install pymongo[srv]` to install the necessary Python package to work with MongoDB
### Make sure you have `docker` and `docker-compose` installed
### `docker-compose up -d --build' (use sudo if you're on Linux)
### Now, load MongoDB with the data: `python data/loadData.py`
### The project is now ready to run. Navigate to src/main/java/com/moviereview/api/ and run the `ApiApplication.java` file
### The Spring server should start and you should be able to open your browser to http://localhost:8080 to view the page with the movie cards loaded. If you get any errors regarding ports being used, make sure you have no other projects running on ports 8080 or 27018. Otherwise, feel free to change these ports in the `application.properties`, `docker-compose.yml`, and `loadData.py` files

