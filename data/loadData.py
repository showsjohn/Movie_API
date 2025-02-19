import pymongo
import json
from pymongo import MongoClient, InsertOne

client = MongoClient("mongodb://localhost:27018/MovieReviewer")
db = client.MovieReviewer
collection = db.movie

with open(r"./data/movie.json", 'r') as f:
    movies = json.load(f)


if movies:
    requesting = [InsertOne(movie) for movie in movies]
    result = collection.bulk_write(requesting)
    print(f"Successfully inserted {result.inserted_count} movies.")
else:
    print("No data found in JSON file.")

client.close()
