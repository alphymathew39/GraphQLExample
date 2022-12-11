# spring-boot-graphQL
GraphQL is a relatively new concept from Facebook that is billed as an alternative to REST for Web APIs


Readme
GraphQL

//Post req is used for get in graphql because of query in body

#####################################################

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
