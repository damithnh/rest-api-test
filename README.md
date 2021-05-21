# REST API Endpoints

	http://localhost:8080
		
		/auth
			POST /register - Register - required: email, username, password
			POST /login - Login - required: username, password

		/product
			GET /get - Get all the products 
			GET /getid/{id} - Get a specific product by its id
			GET /getname/{name} - Get a list of prodcut by their names
			POST /add - Add a product - required: name, price, description

		/edit
			PUT /user - Edit the current logged in user info - required: email, username, password
			PUT /product/{id} - Edit a specific product info - required: name, price, description
