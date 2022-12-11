# spring-boot-graphQL

POST: localhost:8080/addMovie

Body:
[{ "id" : 1,
"name" : "titanic",
"title" : "An ocean of memories",
"director" : "Leonardo",
"actors": [
"kate","Leonardo","Billy"
]
},
{ "id" : 2,
"name" : "avatar",
"title" : "war",
"director" : "james",
"actors": [
"zoe","sam","Sigourney"
]
}]

#####################################################

GET: localhost:8080/findAllMovie

#####################################################

POST: localhost:8080/getAll
Body:
    {
       getAllMovie
        { name,
          director,
          actors
        }
    }

#####################################################

POST: localhost:8080/getMovieByDirector
Body:

	{
		findMovie(director :"James"){
			name
		}	
	}

#####################################################	
